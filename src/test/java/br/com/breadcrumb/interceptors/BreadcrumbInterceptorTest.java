package br.com.breadcrumb.interceptors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.breadcrumb.annotations.BreadCrumb;
import br.com.breadcrumb.service.BreadcrumbService;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.resource.DefaultResourceClass;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.resource.ResourceMethod;

public class BreadcrumbInterceptorTest {

	@Resource
	public class IndexController {

		@Get
		@BreadCrumb(message = "some.key", level = 0)
		public void index() {

		}

		@Get
		public void noBreadcrumb() {

		}

	};

	@Mock private BreadcrumbService service;
	@Mock private InterceptorStack stack;
	@Mock private ResourceMethod method;
	@Mock private HttpServletRequest request;
	
	private BreadcrumbInterceptor interceptor;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		initializeBreadcrumbInterceptor();
	}

	@Test
	public void shouldExecuteAddItemMethod() {
		whenInterceptOccurs();
		verify(service).addItem(any(ResourceMethod.class), any(HttpServletRequest.class));
	}
	

	@Test
	public void shouldNotInterceptNoBreadcrumbMethodInIndexController()
			throws Exception {
		ResourceMethod resourceMethod = obtainMethodByName(IndexController.class, "noBreadcrumb");
		assertFalse(interceptor.accepts(resourceMethod));
	}
	
	@Test
	public void shouldInterceptIndexMethodInIndexController()
			throws Exception {
		ResourceMethod resourceMethod = obtainMethodByName(IndexController.class, "index");
		assertTrue(interceptor.accepts(resourceMethod));
	}

	
	private ResourceMethod obtainMethodByName(Class<?> clazz, String methodName) {
		Method[] methods = clazz.getDeclaredMethods();
		Method methodFound = null;

		for (Method method : methods) {
			if (methodName.equalsIgnoreCase(method.getName())) {
				methodFound = method;
				break;
			}
		}

		return new DefaultResourceMethod(new DefaultResourceClass(clazz),
				methodFound);
	}

	private void whenInterceptOccurs() {
		interceptor.intercept(stack, method, null);
	}

	private void initializeBreadcrumbInterceptor() {
		interceptor = new BreadcrumbInterceptor(service, request);
	}

}
