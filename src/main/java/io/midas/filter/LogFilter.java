package io.midas.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(filterName = "logFilter",urlPatterns = "/*",dispatcherTypes = {DispatcherType.REQUEST})
public class LogFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig)
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        logger.info("I am at logger");
        filterChain.doFilter(request,response);
    }

    public void destroy()
    {

    }


}
