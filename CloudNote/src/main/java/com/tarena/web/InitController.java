package com.tarena.web;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitController implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext ct = arg0.getServletContext();
		String str = (String) ct.getAttribute("111");
		System.out.println(str);
		System.out.println("destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("initialized");
		ServletContext ct = arg0.getServletContext();
		ct.setAttribute("111", "111");
		this.contextDestroyed(arg0);
	}
	@PostConstruct
	public void show(){
		System.out.println(111111111);
	}

}
