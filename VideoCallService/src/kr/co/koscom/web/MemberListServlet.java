package kr.co.koscom.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean loginFlag = false;
		String email = null;
		//쿠키를 이용한 상태유지
		/*Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginOK")) {
					loginFlag = true;
					id = cookie.getValue();
					break;
				}
			}
		}*/
		//세션을 이용한 상태유지
		HttpSession session = request.getSession();
		MemberDTO member=(MemberDTO)   session.getAttribute("loginOK");
		if(member != null) {
			loginFlag = true;
			email = member.getEmail();
		}
		if (loginFlag) {
			MemberDAO dao = new MemberDAOImpl();
			List<MemberDTO> memberList = dao.getMemberList();

			request.setAttribute("memberList", memberList);

			RequestDispatcher rd = request.getRequestDispatcher("memberlist.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("loginForm.html");
		}
	}

}
