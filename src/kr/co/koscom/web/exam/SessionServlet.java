package kr.co.koscom.web.exam;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/SessionServlet",initParams = {
		@WebInitParam(name = "server", value = "https://javatutorial.net"), 
		@WebInitParam(name = "api-key", value = "h6Thd5guE4Kl12g3")
} )
public class SessionServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cookieName = request.getParameter("cookieName");
		String cookieValue = request.getParameter("cookieValue");
		HttpSession session = request.getSession();
		//session.setAttribute(cookieName, cookieValue);
		System.out.println(this.getServletConfig());
		ServletConfig config = this.getServletConfig();
		System.out.println(config.getInitParameter("api-key"));
		ServletContext context = /*config.getServletContext();*/this.getServletContext();
		System.out.println("contextParam:::"+context.getInitParameter("key"));
	}

}
