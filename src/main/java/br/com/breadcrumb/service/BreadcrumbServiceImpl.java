package br.com.breadcrumb.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import br.com.breadcrumb.annotations.BreadCrumb;
import br.com.breadcrumb.beans.BreadcrumbSession;
import br.com.breadcrumb.beans.ItemBreadcrumb;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Component
public class BreadcrumbServiceImpl implements BreadcrumbService {

	private final BreadcrumbSession breadSession;

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
		Annotation[] annotations = m.getDeclaredAnnotations();
		
		for (Annotation annotation : annotations) {
			if (annotation instanceof BreadCrumb) {
				item = new ItemBreadcrumb(
						bundle.getString(((BreadCrumb) annotation).message()),
						getUriWithoutContext(request),
						((BreadCrumb) annotation).level());
			}
		}

		return item;
	}

	private String getUriWithoutContext(HttpServletRequest request) {
		return request.getRequestURI().replaceFirst(request.getContextPath(),"");
	}

}
