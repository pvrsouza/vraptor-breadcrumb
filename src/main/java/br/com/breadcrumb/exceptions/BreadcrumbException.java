package br.com.breadcrumb.exceptions;

public class BreadcrumbException extends RuntimeException {

	private static final long serialVersionUID = -8934430759469227245L;

	public BreadcrumbException(String message) {
		super(message);
	}

}
