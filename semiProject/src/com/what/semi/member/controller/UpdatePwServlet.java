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
 * Servlet implementation class UpdatePwServlet
 */
@WebServlet("/updatePw.do")
public class UpdatePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pw = request.getParameter("pw1");
		System.out.println(pw);
		HttpSession session = request.getSession();
		boolean result = new MemberService().updatePw((String)session.getAttribute("id"),pw);
		
		if(result) {
			response.sendRedirect("/sp/views/member/updatePw.jsp?activeFlag=true&searchFlag=true");
		} else {
			response.sendRedirect("/sp/views/member/updatePw.jsp?activeFlag=true&searchFlag=false");
		}
	}

}
