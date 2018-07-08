package com.what.semi.resume.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

/**
 * Servlet implementation class SearchSameJobseekerServlet
 */
@WebServlet("/searchSameJobseeker.do")
public class SearchSameJobseekerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchSameJobseekerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int resumeId = Integer.parseInt(request.getParameter("resumeId"));

		MyResumeVo resume = new MyResumeService().selectResume(resumeId);
		
		ArrayList<MyResumeVo> list = new MyResumeService().selectMyInfo(resume.getId());
		System.out.println(list.size());

		String url = "";
		if (null != list) {
			url = "/views/resume/SameResumeList.jsp";
			request.setAttribute("list", list);
		} else {
			System.out.println("오류");
			url = "index.jsp";
			request.setAttribute("msg", "오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
