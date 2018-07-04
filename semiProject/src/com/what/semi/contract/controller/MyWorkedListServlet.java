package com.what.semi.contract.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

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

		ArrayList<MyResumeVo> resumeList = new MyResumeService().selectMyInfo(id);
		System.out.println("이력서리스트"+resumeList.size());

		ArrayList<ContractVo> myConList = new ArrayList<ContractVo>();
		ArrayList<ContractVo> temp = null;
		for (int i = 0; i < resumeList.size(); i++) {
			temp = new ContractService().selectMyWorkedList(resumeList.get(i).getResume_id());
			System.out.println(resumeList.get(i).getResume_id() + " / " + temp.size());
			myConList.addAll(myConList.size(), temp);
			System.out.println(myConList.size());
		}
	}

}
