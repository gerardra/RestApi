package au.com.restapi;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.support.*;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	 @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return new Class[] { App.class };
	    }

	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }

	    @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return null;
	    }

	    @Override
	    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
	        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
	        servlet.setThrowExceptionIfNoHandlerFound(true);
	        return servlet;
	    }

}
