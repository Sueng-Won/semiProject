package com.what.semi.resume.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.resume.model.service.MyResumeService;

@WebServlet("/deleteResume.do")
public class DeleteResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteResumeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		String resume_id = request.getParameter("resume_id");
		
		int result = new MyResumeService().deleteResume(userId, resume_id);
		
		
			String url = "";
		if(0<result){
			url="/sp/getResume.do";
			response.sendRedirect(url);
		}else{
			System.out.println("에러");
		}

	}

}
