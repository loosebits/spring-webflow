package org.springframework.webflow.context.servlet;

import javax.servlet.http.HttpServletRequest;

public interface RestfulUrlParser<T> {
    public String getFlowId(String flowId);
    public T getResourceIdentifier(HttpServletRequest request);
    public boolean supports(String flowId);
	public void storeResourceIndendifier(String flowId, HttpServletRequest request);
}