package org.springframework.webflow.context.servlet;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Goes through the caused-by stack and tries a handler for the deepest exception
 * in the stack.
 * @author I300145
 *
 */
public class UnwrappingExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LinkedList<Exception> exceptions = new LinkedList<Exception>();
        Throwable t = ex;
        exceptions.add(ex);
        while ((t = t.getCause()) != null) {
            if (t instanceof Exception) {
                exceptions.addFirst((Exception) t);
            }
        }
        for (Exception e : exceptions) {
            ModelAndView result = super.doResolveException(request, response, handler, e);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

}
