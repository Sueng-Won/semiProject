package com.what.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.filter.Sha512;
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
		String id = request.getParameter("id");
		String pw = Sha512.getSha512(request.getParameter("pw"));
		int result = 0;
		
		result = new MemberService().checkLogin(id, pw);

		HttpSession session = request.getSession();
		
		
		if(result>0) {
			session.setAttribute("id", id);
			if("admin".equals(id)) {
				response.sendRedirect("views/admin/adminMain.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
		}else {
			response.sendRedirect("index.jsp");
		}
	}

}
