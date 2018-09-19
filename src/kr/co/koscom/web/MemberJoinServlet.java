package kr.co.koscom.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.koscom.dao.MemberDAO;
import kr.co.koscom.dao.MemberDAOImpl;
import kr.co.koscom.dto.MemberDTO;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet("/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDTO member = new MemberDTO();
		member.setName(request.getParameter("name"));
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setEmail(request.getParameter("email"));
		
		MemberDAO dao = new MemberDAOImpl();
		int resultCount = dao.addMember(member);
		if(resultCount == 1) {
			response.sendRedirect("memberList");
		}else {
			response.sendRedirect("memberInput.html");
		}
		/*response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>memberJoin</title></head><body>");
		if(resultCount == 1) {
			out.print("<h1>회원가입 성공^^</h1>");
		}else {
			out.println("<h1>회원가입 실패 -_-;;</h1>");
		}
		out.print("</body></html>");
		out.close();*/
	}

}
