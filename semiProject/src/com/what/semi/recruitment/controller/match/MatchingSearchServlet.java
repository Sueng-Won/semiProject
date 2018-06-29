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
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/matchingSearch.do")
public class MatchingSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchingSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<RecruitmentVo> list = null;				//사용자의 이력서에 기반해 조회한 게시물들을 저장할 List 선언
		PageInfo pi = null;									//페이징 처리를 위한 객체 선언
		
		MyResumeService mrs = new MyResumeService();		//이력서에 기재한 조건을 조회해올 service 선언
		RecruitmentService rs = new RecruitmentService();	//게시물 조회에 이용할 service 선언
		
		HttpSession session = request.getSession();			//세션 선언
		String id = null;									//세션으로부터 조회한 id를 저장할 변수 선언
		
		ArrayList<String> resumeNames = null;				//회원의 이력서title명을 저장할 List 선언
		ArrayList<MyResumeVo> resume = null;				//해당 회원의 이력서를 조회해올 List 선언
		
		id = (String) session.getAttribute("id");
		resume = mrs.selectMyInfo(id);
		
		if(null != resume) {
			
			resumeNames = new ArrayList<String>();
			
			for(MyResumeVo names : resume) {
				resumeNames.add(names.getIntroduce_title());
			}
			
			if(null != request.getParameter("resumeName")) {
				
				
				
			}else {
				
				pi = PageTemplate.machingSearchPaging(request, rs, resume.get(0));
				list = rs.loadMatchingSearchList(pi.getCurrentPage(), pi.getLimit(), resume.get(0));
				
			}
			
			
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
