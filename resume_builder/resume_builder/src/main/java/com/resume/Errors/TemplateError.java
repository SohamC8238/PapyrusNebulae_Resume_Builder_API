package com.resume.Errors;

public class TemplateError extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TemplateError(String message) {
		super("Template Not Found");
	}

	public String getMessage() {
		return "Template Not Found";
	}
}
