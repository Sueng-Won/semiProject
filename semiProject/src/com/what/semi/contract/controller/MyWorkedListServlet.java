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

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class MyWorkedListServlet
 */
@WebServlet("/myWorkedList.do")
public class MyWorkedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyWorkedListServlet() {
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
		String id = (String) session.getAttribute("id");

		ArrayList<ContractVo> myConList = new ContractService().selectMyWorkedList(id);
		ArrayList<RecruitmentVo> conRecList = new ArrayList<RecruitmentVo>();

		RecruitmentVo temp = null;
		int flag = -1;
		for (int i = 0; i < myConList.size(); i++) {
			temp = new RecruitmentService().selectRecruitment(myConList.get(i).getRecruitment_id());
			for (int j = 0; j < conRecList.size(); j++) {
				if (conRecList.get(j).getRecruitment_id().equals(temp.getRecruitment_id())) {
					flag = 1;
				}
			}
			if (flag == -1) {
				System.out.println(temp);
				conRecList.add(temp);
			}else{
				flag=-1;
			}
		}

		RequestDispatcher view = null;
		String url = "";
		if (myConList != null) {
			url = "/views/member/workedList.jsp";
			request.setAttribute("myConList", myConList);
			request.setAttribute("conRecList", conRecList);
			// request.setAttribute("pi", pi);

		} else {
			System.out.println("근로내역오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);

	}

}
