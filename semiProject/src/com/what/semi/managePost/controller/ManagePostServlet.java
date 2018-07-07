package com.what.semi.managePost.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.managePost.model.service.ManagePostService;
import com.what.semi.managePost.model.vo.ManageRecruitmentVo;
import com.what.semi.managePost.model.vo.ManageResumeVo;
import com.what.semi.managePost.model.vo.PostCondition;

@WebServlet("/managePost.do")
public class ManagePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagePostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ManageRecruitmentVo> recruitment = null;
		ArrayList<ManageResumeVo> resume = null;
		ManagePostService mps = new ManagePostService();
		PageInfo pi = null; 
		
		String member_type = "BO";
		String userName = "";
		String content = "";
		int isPost = -1;
		
		if(null != request.getParameter("member_type") && !"NO".equals(request.getParameter("member_type"))) {
			member_type = request.getParameter("member_type");
		}
		if(null != request.getParameter("keyword")) {
			userName = request.getParameter("keyword");
		}
		if(null != request.getParameter("content")) {
			content = request.getParameter("content");
		}
		if(null != request.getParameter("is_Post")&&!"".equals(request.getParameter("is_Post"))) {
			isPost = Integer.parseInt(request.getParameter("is_Post"));
		}
		
		PostCondition condition = new PostCondition(member_type, userName, content, isPost);
		
		pi = PageTemplate.postPaging(request, mps, condition);
		if("JS".equals(condition.getMember_type())) {
			resume = mps.loadResumeList(condition, pi);
		}else if("BO".equals(condition.getMember_type())) {
			recruitment = mps.loadRecruitmentList(condition, pi);
		}
		
		String url = "";
		if(null != recruitment) {
			url = "views/admin/managePost.jsp";
			request.setAttribute("recruitment", recruitment);
			request.setAttribute("condition", condition);
			request.setAttribute("pi", pi);
		}else if(null != resume) {
			url = "views/admin/managePost.jsp";
			request.setAttribute("resume", resume);
			request.setAttribute("condition", condition);
			request.setAttribute("pi", pi);
		}else {
			response.sendRedirect("/sp/index.jsp");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
