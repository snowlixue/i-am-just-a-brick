package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class CharFilter extends HttpServlet implements Filter {
	
	private FilterConfig filterConfig;
	
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 try
         {
            request.setCharacterEncoding("GBK");
            filterChain.doFilter(request, response);
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
         }
	}



}
