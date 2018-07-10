package com.what.semi.contract.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.GmailSend;
import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

/**
 * Servlet implementation class SuggestServlet
 */
@WebServlet("/suggest.do")
public class SuggestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuggestServlet() {
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
		String recId = request.getParameter("recId");
		String boId = request.getParameter("bo_id");
		String jsId = request.getParameter("js_id");
		HttpSession session = request.getSession();
		String demander = (String) session.getAttribute("member_type");


		int result = new ContractService().insertContract(recId, boId, jsId, resumeId, demander);
		ContractVo cont = new ContractService().selectThisCon(recId, boId, jsId, resumeId);
		RecruitmentVo rec = new RecruitmentService().selectRecruitment(recId);
		MyResumeVo resume = new MyResumeService().selectMyResume(jsId, resumeId);

		ArrayList<RecruitmentVo> recList = new RecruitmentService().loadSameBusiness(boId);
		
		for (int i = 0; i < recList.size(); i++) {
			if (recList.get(i).getRecruitment_id().equals(recId)) {
				recList.remove(recList.get(i));
			}
		}
		
		

		String title = "<" + resume.getIntroduce_title() + ">의 구인 제안이 들어왔습니다.";
		String recUrl = "contractRecDetail.do?recId=" + recId + "&state=0&contId="+cont.getC_no();
		new GmailSend().sendResume(resume.getEmail(), title, recUrl,0);

		RequestDispatcher view = null;
		String url = "";
		if (result != 0) {
			url = "/views/resume/Myresume.jsp";
			request.setAttribute("member", resume);
			request.setAttribute("recList", recList);
			request.setAttribute("contRe", 0);
		} else {
			System.out.println("계약구인상세페이지오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
