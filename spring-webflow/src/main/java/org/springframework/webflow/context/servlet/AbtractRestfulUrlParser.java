package org.springframework.webflow.context.servlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public abstract class AbtractRestfulUrlParser<T> implements RestfulUrlParser<T> {

	private String resourceIdentifierAttributeName;
    private static Pattern pattern = Pattern.compile("/([^\\/]+)$");

    public String getFlowId(String requestedFlowId) {
        return match(requestedFlowId).replaceFirst("");
    }

    protected boolean isNew(String requestedFlowId) {
        return "new".equals(getResourceIdentifier(requestedFlowId));
    }
    
    private Matcher match(String requestedFlowId) {
    	Matcher m = pattern.matcher(requestedFlowId);
        if (!m.find()) {
            throw new Http404Exception();
        }
        return m;
    }

    /**
     * @return the resource identifier associated with the request or null if the request
     * is to create a new resource
     */
    @SuppressWarnings("unchecked")
	public T getResourceIdentifier(HttpServletRequest request) {
        return (T) request.getAttribute(resourceIdentifierAttributeName);
    }
    
    protected String getResourceIdentifier(String requestedFlowId) {
        return match(requestedFlowId).group(1);
    }
    
    public void storeResourceIndendifier(String requestedFlowId, HttpServletRequest request) {
        if (!supports(requestedFlowId)) {
            throw new IllegalStateException("This parser does not support the flow " + requestedFlowId);
        }
        if (!isNew(requestedFlowId)) {
            request.setAttribute(resourceIdentifierAttributeName, convert(getResourceIdentifier(requestedFlowId)));
        }
    }
    
    protected abstract T convert(String resourceIdentifier);

    public abstract boolean supports(String requestedFlowId);

	public void setResourceIdentifierAttributeName(
			String resourceIdentifierAttributeName) {
		this.resourceIdentifierAttributeName = resourceIdentifierAttributeName;
	}

}
