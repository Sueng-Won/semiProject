package com.what.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.member.model.service.MemberService;

/**
 * Servlet implementation class DropMemberServlet
 */
@WebServlet("/dropMember.do")
public class DropMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		//System.out.println(id);
		int result = 0;
		
		result = new MemberService().dropMember(id);
		
		if(result>0) {
			System.out.println("유저 삭제 완료");
			response.sendRedirect("/sp/views/member/dropMemberConfirmed.jsp");
		} else {
			System.out.println("유저 삭제 실패");
			response.sendRedirect("index.jsp");
		}
	}

}
