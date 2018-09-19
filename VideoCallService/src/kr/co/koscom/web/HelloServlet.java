package kr.co.koscom.web;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns = "/HelloServlet",initParams= {@WebInitParam(name="name",value="kang")})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
																ServletContext context = this.getServletContext();
		System.out.println("contextParam::::::"+context.getInitParameter("contextParam"));
		
		ServletConfig config = this.getServletConfig();
		System.out.println("initParam name ::::::  "+config.getInitParameter("name"));
		System.out.println("doGet 호출");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 호출");
	}
}
