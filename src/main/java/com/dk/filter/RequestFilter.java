package com.dk.filter;  
  
import java.io.IOException;  
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletResponse;  
  
public class RequestFilter implements Filter {  
  
    public void init(FilterConfig filterConfig) throws ServletException {  
        // TODO Auto-generated method stub  
  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse pResponse, FilterChain chain)  
            throws IOException, ServletException {  
        // TODO Auto-generated method stub  
        HttpServletResponse response = (HttpServletResponse) pResponse;  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");  
          
        chain.doFilter(request, response);  
    }  
  
    public void destroy() {  
        // TODO Auto-generated method stub  
  
    }  
  
}  