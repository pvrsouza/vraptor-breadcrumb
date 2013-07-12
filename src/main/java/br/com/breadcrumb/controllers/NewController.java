package br.com.breadcrumb.controllers;

import br.com.breadcrumb.annotations.BreadCrumb;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class NewController {

	@Get
	@Path("/new")
	public void index() {

	}

	@Get
	@Path("/new/levelone")
	@BreadCrumb(message="newcontroller.leveone.key", level=1)
	public void levelOne() {

	}

	@Get
	@Path("/new/leveltwo")
	@BreadCrumb(message="newcontroller.levetwo.key", level=2)
	public void levelTwo() {

	}
}
