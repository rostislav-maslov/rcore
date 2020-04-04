package com.ub.core.xss;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class CrossScriptingFilter implements Filter {
    //private static Logger logger = Logger.getLogger(CrossScriptingFilter.class);
    private FilterConfig filterConfig;
    private String pathToBeIgnored;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.pathToBeIgnored = filterConfig.getInitParameter("pathToBeIgnored");
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();

        if (pathToBeIgnored != null && path.startsWith(pathToBeIgnored)) {
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            //logger.info("Inlter CrossScriptingFilter  ...............");
            chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
            //logger.info("Outlter CrossScriptingFilter ...............");
        }
    }

}
