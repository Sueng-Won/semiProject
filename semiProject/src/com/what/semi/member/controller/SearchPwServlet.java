package com.what.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.GmailSend;
import com.what.semi.member.model.service.MemberService;

/**
 * Servlet implementation class SearchPwServlet
 */
@WebServlet("/searchPw.do")
public class SearchPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		String result = new MemberService().searchPw(id,email);
		
		if(result!=null) {
			GmailSend.GmailSetTxt(email, "비밀번호를 잃어버린 불쌍한 녀석", "새 비밀번호는 "+result+" 입니다.");
			response.sendRedirect("/sp/views/member/searchPw.jsp?activeFlag=true&searchFlag=true");
		}else {
			response.sendRedirect("/sp/views/member/searchPw.jsp?activeFlag=true&searchFlag=false");
		}
	}

}
