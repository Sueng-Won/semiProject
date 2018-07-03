package com.what.semi.resume.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.resume.model.service.MyResumeService;

@WebServlet("/modifyIs_post.do")
public class ModifyIs_postServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyIs_postServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//is_post ㅌ컬럼을  0->1   1->0 공개1 비공개0
		String id = request.getParameter("id");
		int is_post = Integer.parseInt(request.getParameter("is_post"));
		
		int result = new MyResumeService().updateIs_post(id, is_post);
		
		String url= "";
		if(0<result){
			url = "/sp/getResume.do";
			response.sendRedirect(url);
		}else{
			System.out.println("에러");
		}

	}

}
