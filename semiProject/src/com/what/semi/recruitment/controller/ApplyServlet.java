package com.what.semi.recruitment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class ApplyServlet
 */
@WebServlet("/apply.do")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int resumeId = Integer.parseInt(request.getParameter("resume_id"));
		String recId = request.getParameter("recId");
		String boId = request.getParameter("bo_id");
		String jsId = request.getParameter("userId");
		
		int result = new ContractService().insertContract(recId,boId,jsId,resumeId);
		RecruitmentVo rec = new RecruitmentService().selectRecruitment(recId);
		
		RequestDispatcher view = null;
		String url = "";
		if (result != 0) {
			url = "/views/recruitment/contractRecDetail.jsp";
			request.setAttribute("rec", rec);

		} else {
			System.out.println("계약구인상세페이지오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
