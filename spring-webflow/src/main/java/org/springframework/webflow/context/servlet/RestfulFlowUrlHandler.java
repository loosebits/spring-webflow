package org.springframework.webflow.context.servlet;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component
public class RestfulFlowUrlHandler extends DefaultFlowUrlHandler {
    
    private List<RestfulUrlParser> restfulUrlParsers = Collections.emptyList();
    
    @Override
    public String getFlowId(HttpServletRequest request) {
        RestfulUrlParser parser = null;
        String flowId = super.getFlowId(request);
        if (flowId == null)
            return null;
        for (RestfulUrlParser aParser : restfulUrlParsers) {
            if (aParser.supports(flowId)) {
                parser = aParser;
                break;
            }
        }
        if (parser != null) {
            parser.storeResourceIndendifier(flowId,request);
            return parser.getFlowId(flowId);
        }
        return flowId;
    }

	public void setRestfulUrlParsers(List<RestfulUrlParser> restfulUrlParsers) {
		this.restfulUrlParsers = restfulUrlParsers;
	}
}

