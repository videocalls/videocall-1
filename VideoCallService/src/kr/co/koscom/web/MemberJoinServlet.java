package kr.co.koscom.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		member.setEmail(request.getParameter("email"));
		member.setPassword(request.getParameter("password"));
		member.setAddress(request.getParameter("address"));
		member.setPhonenumber(request.getParameter("phonenumber"));
		
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
			out.print("<h1>�쉶�썝媛��엯 �꽦怨�^^</h1>");
		}else {
			out.println("<h1>�쉶�썝媛��엯 �떎�뙣 -_-;;</h1>");
		}
		out.print("</body></html>");
		out.close();*/
	}

}
