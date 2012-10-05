package org.springframework.webflow.context.servlet;

public class Http404Exception extends RuntimeException {

	private static final long serialVersionUID = -7793523976270790622L;

	public Http404Exception() {
        super();
    }

    public Http404Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http404Exception(String message) {
        super(message);
    }

    public Http404Exception(Throwable cause) {
        super(cause);
    }

}
