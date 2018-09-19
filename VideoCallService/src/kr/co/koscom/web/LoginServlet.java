package kr.co.koscom.web;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		MemberDAO dao = new MemberDAOImpl();
		MemberDTO member = dao.getMember(email);
		HttpSession session = request.getSession();
		System.out.println(email+password+member.getPassword());
		
		if(member != null & member.getEmail() != null) {
			if(member.getPassword().equals(password)) {
				Cookie cookie = new Cookie("loginOK", email);
				cookie.setPath("/");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
				
				session.setAttribute("loginOK", member);
				
				response.sendRedirect("memberList");
			}else {
				session.setAttribute("msg", "비밀번호를 확인해주세요.^^");
				response.sendRedirect("loginForm.html");			
			}
		}else {
			session.setAttribute("msg", email+"가입되지 않은 이메일입니다.");
			response.sendRedirect("memberInput.html");
		}
	}

}
