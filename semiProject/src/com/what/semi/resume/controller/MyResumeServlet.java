package com.what.semi.resume.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/getResume.do")
public class MyResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyResumeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 나의 이력서 불러오기 
			HttpSession session = request.getSession(false);
		
			String id = (String)session.getAttribute("id");
		
		ArrayList<MyResumeVo> userType = new MyResumeService().selectMyInfo(id);
		
		
		
		String url="";
		if(null!=userType){
			url = "views/member/manageResume.jsp";
			request.setAttribute("userType", userType);
		}else{
			url = "views/member/logout.jsp";
			response.sendRedirect(url);
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
		
	}

}
