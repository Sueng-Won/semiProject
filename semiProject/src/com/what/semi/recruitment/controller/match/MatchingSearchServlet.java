package com.what.semi.recruitment.controller.match;

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

@WebServlet("/matchingSearch.do")
public class MatchingSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchingSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<RecruitmentVo> list = null;
		PageInfo pi = null;
		RecruitmentService rs = new RecruitmentService();
		HttpSession session = request.getSession();
		String id = null;
		String resume = null;
		ArrayList<String> resumeNames = null;
		if(null != session.getAttribute("id")) {			//로그인한 상태
			
			if(null != request.getParameter("resume")) {	//이력서를 선택한 경우
				
			}else {		//이력서를 선택하지 않은 경우
				id = (String) session.getAttribute("id");
				resumeNames = rs.getResumeNames();
				resume = rs.getDefaultResume(id);
				list = rs.loadMatchingSearchList(id);
				pi = PageTemplate.machingSearchPaging(request,session, rs);
				
			}
			
		} else {
			pi = PageTemplate.indexPaging(request, rs);
			
			list = rs.loadRecruitmentList(pi.getCurrentPage(), pi.getLimit());
		}
		
		String url = "";
		
		if(null != list) {
				url = "views/matchingSearch/matchingSearchPage.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
		}else {
			url = "index.jsp";
			request.setAttribute("msg", "오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
