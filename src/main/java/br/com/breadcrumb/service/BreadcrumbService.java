package br.com.breadcrumb.service;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.resource.ResourceMethod;

public interface BreadcrumbService {
	
	void addItem(ResourceMethod method, HttpServletRequest request);
}
