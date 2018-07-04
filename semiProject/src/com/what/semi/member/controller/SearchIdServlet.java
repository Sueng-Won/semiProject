package com.what.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.member.model.service.MemberService;

/**
 * Servlet implementation class SearchIdServlet
 */
@WebServlet("/searchId.do")
public class SearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String resultId = new MemberService().searchId(email);
		String url = "/views/member/searchId.jsp";
		
		if(resultId == null) {
			System.out.println("아이디 찾기 실패");
			RequestDispatcher view = request.getRequestDispatcher(url);
			request.setAttribute("activatedFlag", "true");
			view.forward(request, response);
		} else {
			System.out.println("아이디 찾기 성공");
			System.out.println("servlet id : "+resultId);
			RequestDispatcher view = request.getRequestDispatcher(url);
			request.setAttribute("resultId", resultId);
			request.setAttribute("activatedFlag", "true");
			request.setAttribute("searchFlag", "true");
			view.forward(request, response);
		}
	}

}
