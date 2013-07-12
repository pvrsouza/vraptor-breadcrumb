package br.com.breadcrumb.controllers;

import br.com.breadcrumb.annotations.BreadCrumb;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class BreadcrumbTestController {

	@Get
	@Path("/")
	@BreadCrumb(message="home.key", level=0)
	public void index(){
		
	}
	
	@Get
	@Path("/levelone")
	@BreadCrumb(message="my.leveone.key", level=1)
	public void levelOne(){
		
	}
	
	@Get
	@Path("/leveltwo")
	@BreadCrumb(message="my.levetwo.key", level=2)
	public void levelTwo(){
		
	}
}
