package com.what.semi.resume.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/seeMyResume.do")
public class SeeMyResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SeeMyResumeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		HttpSession session = request.getSession(false);
//		String id = (String)session.getAttribute("id");
		// 나의 이력서
		String userId = request.getParameter("userId");
		int resume_id = Integer.parseInt(request.getParameter("resume_id"));
		
		MyResumeVo member = new MyResumeService().selectMyResume(userId, resume_id);
		
		System.out.println("test3 : " + member.getAchievement());
		System.out.println("seeMYresume 호출");
			String url = "";
		if(null!=member){
			url="views/resume/Myresume.jsp";
			request.setAttribute("member", member);
		}else{
			url="/sp";
			System.out.println("에러");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
