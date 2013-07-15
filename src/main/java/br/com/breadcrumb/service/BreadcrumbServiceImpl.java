package br.com.breadcrumb.service;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.breadcrumb.annotations.BreadCrumb;
import br.com.breadcrumb.beans.BreadcrumbSession;
import br.com.breadcrumb.beans.ItemBreadcrumb;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Component
public class BreadcrumbServiceImpl implements BreadcrumbService {

	private final BreadcrumbSession breadSession;
	
	private static final Logger LOG = Logger.getLogger(BreadcrumbServiceImpl.class);

	public BreadcrumbServiceImpl(BreadcrumbSession breadSession) {
		super();
		this.breadSession = breadSession;
	}

	@Override
	public void addItem(ResourceMethod method, HttpServletRequest request) {
		ItemBreadcrumb item = buildAnItemBreadcrumb(method, request);
		breadSession.addItem(item);
	}
	
	
	private ItemBreadcrumb buildAnItemBreadcrumb(ResourceMethod method,
			HttpServletRequest request) {
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		
		ItemBreadcrumb item = null;
		Method m = method.getMethod();
		item = new ItemBreadcrumb(
				bundle.getString(m.getAnnotation(BreadCrumb.class).message()),
				getUriWithoutContext(request),
				m.getAnnotation(BreadCrumb.class).level());

		return item;
	}

	private String getUriWithoutContext(HttpServletRequest request) {
		return request.getRequestURI().replaceFirst(request.getContextPath(),"");
	}

}
