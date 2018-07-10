package com.what.semi.contract.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class ContractRecDetailServlet
 */
@WebServlet("/contractRecDetail.do")
public class ContractRecDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContractRecDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recId = request.getParameter("recId");
		int state = Integer.parseInt(request.getParameter("state"));
		int contId =Integer.parseInt(request.getParameter("contId"));

		RecruitmentVo rec = new RecruitmentService().selectRecruitment(recId);

		RequestDispatcher view = null;
		String url = "";
		if (rec != null) {
			url = "/views/recruitment/contractRecDetail.jsp";
			request.setAttribute("rec", rec);
			request.setAttribute("state", state);
			request.setAttribute("contId", contId);
		} else {
			System.out.println("계약구인상세페이지오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
