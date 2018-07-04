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

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class RecruitmentListServlet
 */
@WebServlet("/myRecruitmentList.do")
public class MyRecruitmentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecruitmentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		RecruitmentService rs = new RecruitmentService();
		PageInfo pi = PageTemplate.myRecPaging(request, rs, id);
		
		ArrayList<RecruitmentVo> list = rs.loadMyRecList(pi.getCurrentPage(), pi.getLimit(),id);
		
		
		String url = "";
		if(null != list) {
				url = "/views/member/manageRecruitment.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
		}else {
			System.out.println("오류");
			url = "index.jsp";
			request.setAttribute("msg", "오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
