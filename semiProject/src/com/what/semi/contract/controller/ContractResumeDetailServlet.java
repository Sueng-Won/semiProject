package com.what.semi.contract.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

/**
 * Servlet implementation class ContractResumeDetailServlet
 */
@WebServlet("/contractResumeDetail.do")
public class ContractResumeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContractResumeDetailServlet() {
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
		String jsId = request.getParameter("jsId");

		MyResumeVo resume = new MyResumeService().selectMyResume(jsId, resumeId);

		RequestDispatcher view = null;
		String url = "";
		if (resume != null) {
			url = "/views/resume/contractResumeDetail.jsp";
			request.setAttribute("resume", resume);

		} else {
			System.out.println("계약이력서상세페이지오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
