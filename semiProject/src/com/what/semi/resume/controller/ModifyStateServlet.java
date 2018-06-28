package com.what.semi.resume.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.resume.model.service.MyResumeService;

@WebServlet("/modifyState.do")
public class ModifyStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyStateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int resume_id = Integer.parseInt(request.getParameter("resume_id"));
		String id = request.getParameter("userId");
		
		//선택된 이력서를 대표로
		int result = new MyResumeService().updatePri_resume(resume_id, id);
			
		String url = "";
			
		if(0<result){
			url = "/sp/getResume.do";
			response.sendRedirect(url);
		}else{
			System.out.println("에러");
		}

	}

}
