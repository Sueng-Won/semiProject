package com.what.semi.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.member.model.service.MemberService;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("hd");
		String name = request.getParameter("name");
		int result = 0;
		
//		System.out.println(id);
//		System.out.println(name);
		
		result = new MemberService().checkId(id);
		
		if(0 > result) {
			result = new MemberService().enterUser(id);
		}
		HttpSession session = request.getSession();
		
		
		if(0<=result) {
			System.out.println(name);
			System.out.println("결과가 있을경우 호출");
			session.setAttribute("name", name);
			response.sendRedirect("index.jsp");;
			
		}else {
			response.sendRedirect("/sp/indexList.do?name="+URLEncoder.encode(name, "UTF-8"));
			System.out.println("결과가 없을경우 호출");
		}

		
		
	}

}
