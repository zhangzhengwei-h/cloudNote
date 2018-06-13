package com.tarena.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

public class MyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public MyServlet(){
		
	}
	
	public void init(){
		System.out.println("MyServlet init" );
		
		this.destroy();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		//spring上下文
	    WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    
	    ServletContext c = springContext.getServletContext();
	    c.setAttribute("1", "111");
	    //springMVC上下文
	    WebApplicationContext springMVCContext = RequestContextUtils.getWebApplicationContext(request);
	    c = springMVCContext.getServletContext();
	    c.setAttribute("2", "222");
	    
	  //spring上下文
	    WebApplicationContext springContext1 = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    ServletContext c1 = springContext1.getServletContext();
	    String str1 = (String)c1.getAttribute("1");
	    System.out.println(str1);
	    
	    //springMVC上下文
	    WebApplicationContext springMVCContext1 = RequestContextUtils.getWebApplicationContext(request);
	    c1 = springMVCContext1.getServletContext();
	    String str2 = (String)c1.getAttribute("2");
	    System.out.println(str2);
	    
	    
		System.out.println("doGet");
	}
	
	public void destroy(){
	    
		System.out.println("destroy");
	}
}
