package com.beelego.global.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hama
 * @since : created in  2018/6/26
 */
@Slf4j
public class RestfulFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("restful filter init");
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        //log.info("restful filter doFilter in");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());
        //log.info("method is [{}]", method);
        if(200 == response.getStatus()
                && RequestMethod.POST.equals(method)){
            response.setStatus(201);
        }
        chain.doFilter(request, response);
        //log.info("restful filter doFilter out");
    }

    @Override
    public void destroy() {
        log.info("restful filter destroy");
    }
}
