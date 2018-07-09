package com.what.semi.recruitment.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.member.model.service.MemberService;
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

/**
 * Servlet implementation class RecruitmentDetailServlet
 */
@WebServlet("/recruitmentDetail.do")
public class RecruitmentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecruitmentDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String js_id = (String) session.getAttribute("id");

		String recId = (String) request.getParameter("recId");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		RecruitmentVo rec = new RecruitmentService().selectRecruitment(recId);
		MemberVo writer = new MemberService().getMemberInfo(rec.getM_id());

		ArrayList<MyResumeVo> myResumes = new MyResumeService().selectMyInfo(js_id);
		ArrayList<ContractVo> myAppliedConList = new ContractService().selectmyAppliedConList(recId, js_id);

		for (int i = 0; i < myResumes.size(); i++) {
			for (int j = 0; j < myAppliedConList.size(); j++) {
				if (myResumes.get(i).getResume_id() == myAppliedConList.get(j).getResume_id()) {
					myResumes.remove(myResumes.get(i));
				}
			}
		}
		
		/*for (int i = 0; i < myResumes.size(); i++) {
			for (int j = 0; j < removeResume.size(); j++) {
				if (myResumes.get(i).getResume_id() == removeResume.get(j)) {
					System.out.println("지울거"+removeResume.get(j));
					myResumes.remove(removeResume.get(j));
				}
			}
		}*/
		

		String url = "";
		if (null != rec) {
			url = "views/recruitment/recruitmentDetail.jsp";
			request.setAttribute("rec", rec);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("writer", writer);
			request.setAttribute("myResumes", myResumes);
			request.setAttribute("contRe", 0);
		} else {
			/*
			 * url = "views/common/errorPage.jsp"; request.setAttribute("msg",
			 * "게시글 상세조회에 실패하였습니다.");
			 */
			System.out.println("구인게시글상세페이지오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);

	}

}
