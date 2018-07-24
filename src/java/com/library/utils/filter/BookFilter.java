/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.utils.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kelechi
 */
@WebFilter(filterName = "BookFilter", urlPatterns = {"*.xhtml"})
public class BookFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.contains("/bookpage.xhtml")) {
            if (session != null && session.getAttribute("userId") != null) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/login.xhtml");
            }
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
