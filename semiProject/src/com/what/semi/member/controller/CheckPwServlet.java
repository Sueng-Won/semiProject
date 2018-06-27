package com.what.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.filter.Sha512;
import com.what.semi.member.model.service.MemberService;
import com.what.semi.member.model.vo.MemberVo;

/**
 * Servlet implementation class CheckPwServlet
 */
@WebServlet("/checkPw.do")
public class CheckPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		String pw = Sha512.getSha512(request.getParameter("pw"));
		int result = new MemberService().checkPw(id,pw);
		
		
		
		if(result>0) {
			System.out.println("인증확인");
			MemberVo member = new MemberService().getMemberInfo(id);
			RequestDispatcher view = request.getRequestDispatcher("/views/member/updateMember.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		} else {
			System.out.println("인증실패");
			response.sendRedirect("/sp/views/member/pwCheck.jsp?flag=true");
		}
	}

}
