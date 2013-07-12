package br.com.breadcrumb.beans;

import java.util.ArrayList;
import java.util.List;

import br.com.breadcrumb.exceptions.BreadcrumbException;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class BreadcrumbSession {

	private static final int ITEM_LEVEL_ZERO = 0;

	private List<ItemBreadcrumb> itens;

	public BreadcrumbSession() {
		this.itens = new ArrayList<ItemBreadcrumb>();
	}

	public List<ItemBreadcrumb> getItens() {
		return itens;
	}

	public void addItem(ItemBreadcrumb item) {
		ItemBreadcrumb i = validateItemBreadcrumb(item);

		if (i != null) {
			if (this.itens.contains(i)) {
				int position = this.itens.indexOf(i);

				for (int x = itens.size() - 1; x >= position; x--) {
					itens.remove(x);
				}
			}
			this.itens.add(i);
		}
	}

	private ItemBreadcrumb validateItemBreadcrumb(ItemBreadcrumb item) {
		if (!validateIsUniqueZeroLevel(item) || !validatePredecessors(item)) {
			return null;
		}
		return item;
	}

	private boolean validateIsUniqueZeroLevel(ItemBreadcrumb item) {
		if (itens.contains(item) && item.getLevel() == ITEM_LEVEL_ZERO)
			return false;
		return true;
	}

	private boolean validatePredecessors(ItemBreadcrumb item) {
		if (item.getLevel() > ITEM_LEVEL_ZERO
				&& item.getLevel() > getItens().size()) {
			throw new BreadcrumbException(
					"Can not display one level without its predecessor.");
		}
		return true;
	}

}
