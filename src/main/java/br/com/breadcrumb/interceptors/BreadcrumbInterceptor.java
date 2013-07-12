package br.com.breadcrumb.interceptors;

import javax.servlet.http.HttpServletRequest;

import br.com.breadcrumb.annotations.BreadCrumb;
import br.com.breadcrumb.service.BreadcrumbService;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class BreadcrumbInterceptor implements Interceptor {

	private final BreadcrumbService service;
	private final HttpServletRequest request;

	public BreadcrumbInterceptor(BreadcrumbService service,
			HttpServletRequest request) {
		this.service = service;
		this.request = request;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		service.addItem(method, request);
		stack.next(method, resourceInstance);
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.getMethod().isAnnotationPresent(BreadCrumb.class);
	}

}
