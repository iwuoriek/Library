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
@WebFilter(filterName = "UserAuthorization", urlPatterns = {"*.xhtml"})
public class UserAuthorization implements Filter {

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
        try {
            if (requestURI.contains("/admindetails.xhtml") || requestURI.contains("/adminhome.xhtml") || requestURI.contains("/newbook.xhtml")) {
                if (session.getAttribute("role").equals("ADMIN")) {
                    chain.doFilter(httpRequest, httpResponse);
                } else {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/userhome.xhtml");
                }
            } else if (requestURI.contains("/userhome.xhtml") || requestURI.contains("/userDetails.xhtml")) {
                if (session.getAttribute("role").equals("NON-ADMIN")) {
                    chain.doFilter(httpRequest, httpResponse);
                } else {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/adminhome.xhtml");
                }
            } else {
                chain.doFilter(httpRequest, httpResponse);
            }
        } catch (NullPointerException e) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/login.xhtml");
        }
    }

    @Override
    public void destroy() {
    }

}
