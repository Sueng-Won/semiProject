package com.what.semi.resume.controller;

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
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;
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

		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("id");

		// 나의 이력서
		String userId = request.getParameter("userId");
		int resume_id = Integer.parseInt(request.getParameter("resume_id"));

		MyResumeVo member = new MyResumeService().selectMyResume(userId, resume_id);

		ArrayList<RecruitmentVo> recList = new ArrayList<RecruitmentVo>();
		ArrayList<ContractVo> mySuggestedConList = new ArrayList<ContractVo>();
		ArrayList<RecruitmentVo> result = new ArrayList<RecruitmentVo>();
		boolean flag = true;
		if (id != null) {
			recList = new RecruitmentService().loadSameBusiness(id);
			mySuggestedConList = new ContractService().selectmySuggestedConList(resume_id, id);
			for (int i = 0; i < recList.size(); i++) {
				for (int j = 0; j < mySuggestedConList.size(); j++) {
					if (recList.get(i).getRecruitment_id().equals(mySuggestedConList.get(j).getRecruitment_id())) {
						flag = false;
						break;
					}
				}
				// 확인 되셧습니다.
				if (flag) {
					result.add(recList.get(i));
				} else {
					flag = true;
				}
			}
		}
	

		String url = "";
		if (null != member) {
			url = "views/resume/Myresume.jsp";
			request.setAttribute("member", member);
			request.setAttribute("recList", result);
			request.setAttribute("contRe", 0);
		} else {
			url = "/sp";
			System.out.println("에러");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
