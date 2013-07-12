package br.com.breadcrumb.beans;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.breadcrumb.exceptions.BreadcrumbException;

public class BreadcrumbSessionTest {
	
	private BreadcrumbSession breadcrumbSession;

	@Test
	public void shouldAddALevelZeroItemBreadcrumb() {
		
		breadcrumbSession = new BreadcrumbSession();
		
		ItemBreadcrumb it = new ItemBreadcrumb("some.name", "/", 0);
		
		breadcrumbSession.addItem(it);
		
		assertThat(breadcrumbSession.getItens().size(), is(1));
		assertThat(breadcrumbSession.getItens().get(0), is(notNullValue()));
		assertThat(((ItemBreadcrumb)breadcrumbSession.getItens().get(0)).getLevel(), is(it.getLevel()));
		assertThat(((ItemBreadcrumb)breadcrumbSession.getItens().get(0)).getNome(), is(it.getNome()));
		assertThat(((ItemBreadcrumb)breadcrumbSession.getItens().get(0)).getUri(), is(it.getUri()));
	}

	
	@Test
	public void shouldNotAddTwoLevelZeroItemBreadcrumb() {
		breadcrumbSession = prepareTwoItemsLevelZero();
		assertThat(breadcrumbSession.getItens().size(), is(1));
	}

	@Test
	public void shouldAddTwoItemBreadcrumbWithDifferentsLevels(){
		breadcrumbSession = buildABreadCrumbSessionWithNItens(2);
		assertThat(breadcrumbSession.getItens().size(), is(2));
	}
	
	@Test
	public void removeAllNextItemsWhenNavigateToPreviousLevel(){
		breadcrumbSession = buildABreadCrumbSessionWithNItens(10);
		assertThat(breadcrumbSession.getItens().size(), is(10));
		
		ItemBreadcrumb itemLevel2 = new ItemBreadcrumb("some.name", "/someurl", 3);
		
		breadcrumbSession.addItem(itemLevel2);
		assertThat(breadcrumbSession.getItens().size(), is(4));
		
	}
	
	@Test(expected=BreadcrumbException.class)
	public void shouldNotIncludeALevelWithoutTheirPrevious(){
		breadcrumbSession = new BreadcrumbSession();
		breadcrumbSession.addItem(new ItemBreadcrumb("some.name", "/someurl", 3));
	}
	
	@Test
	public void everReplaceTheLastLevelIfOneAlreadyExists(){
		breadcrumbSession = buildABreadCrumbSessionWithNItens(5);
		
		assertThat(breadcrumbSession.getItens().size(), is(5));
		assertThat(breadcrumbSession.getItens().get(4).getUri(), is("/someurl"+4));
		
		ItemBreadcrumb refreshItemLevel4 = new ItemBreadcrumb("some.name", "/some_url_with_the_same_final_level", 4);
		
		breadcrumbSession.addItem(refreshItemLevel4);
		
		assertThat(breadcrumbSession.getItens().get(4).getUri(), is("/some_url_with_the_same_final_level"));
		assertThat(breadcrumbSession.getItens().size(), is(5));
		
	}
	
	private BreadcrumbSession prepareTwoItemsLevelZero() {
		BreadcrumbSession breadcrumbSession = new BreadcrumbSession();
		ItemBreadcrumb itLevelZero1 = new ItemBreadcrumb("some.name", "/", 0);
		ItemBreadcrumb itLevelZero2 = new ItemBreadcrumb("some.name", "/someurl", 0);
		breadcrumbSession.addItem(itLevelZero1);
		breadcrumbSession.addItem(itLevelZero2);
		return breadcrumbSession;
	}
	
	
	private BreadcrumbSession buildABreadCrumbSessionWithNItens(int maxLevel){
		BreadcrumbSession breadcrumbSession = new BreadcrumbSession();
		
		for (int i = 0; i < maxLevel; i++) {
			ItemBreadcrumb item = new ItemBreadcrumb("some.key", "/someurl"+i, i);
			breadcrumbSession.addItem(item);
		}
		
		return breadcrumbSession;
	}
	
	

}
