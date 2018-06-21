package com.what.semi.recruitment.controller.local;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;


@WebServlet("/localList.do")
public class LocalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocalListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		System.out.println("local"+session.getAttribute("nickname"));
		
		RecruitmentService rs = new RecruitmentService();
		String keyword = null;
		ArrayList<RecruitmentVo> list = null;
		
		PageInfo pi = PageTemplate.LocalPaging(request, response, rs);
		
		if(null != request.getParameter("keyword")) {
			keyword = request.getParameter("keyword");
			list = new RecruitmentService().loadRecruitmentList(pi.getCurrentPage(), pi.getLimit(), keyword);
		}else {
			list = new RecruitmentService().loadRecruitmentList(pi.getCurrentPage(), pi.getLimit());
		}
		
		
		String url = "";
		
		if(null != list) {
			url = "views/local/localSearch.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("keyword", keyword);
		}else {
			url = "index.jsp";
			request.setAttribute("msg", "오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
