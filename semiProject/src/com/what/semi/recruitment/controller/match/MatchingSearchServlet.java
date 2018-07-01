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
		resume = mrs.selectMyInfo(id);		//회원 아이디를 기반으로 이력서를 조회
		
		if(0 < resume.size()) {				//작성한 이력서가 있을 경우 실행
			System.out.println("이력서가 있을경우 실행");
			resumeNames = new ArrayList<String>();
			
			for(MyResumeVo names : resume) {	//이력서를 기반으로 해당 이력서들의 title을 List형식으로 저장(view로 전달할 객체)
				resumeNames.add(names.getIntroduce_title());
				System.out.println(names.getIntroduce_title());
			}
			
			if(null != request.getParameter("resumeId")) {	//페이지 상에서 선택한 이력서가 있을 경우 실행될 로직
				
				for(MyResumeVo mr : resume) {			//이력서목록에서 view에서 받아온 이력서 명과 동일한 이력서을 바탕으로 게시물을 저장
					if(Integer.parseInt(request.getParameter("resumeId")) == mr.getResume_id()) {
						pi = PageTemplate.machingSearchPaging(request, rs, mr);						
						list = rs.loadMatchingSearchList(pi.getCurrentPage(), pi.getLimit(), mr);
					}
				}
				
				
			}else {				//최초 접속시 default로 view에 전달할 로직 
				System.out.println("이력서가 있으면서 최초 접속일 경우 실행");
				System.out.println(resume.get(0).toString());
				pi = PageTemplate.machingSearchPaging(request, rs, resume.get(0));						
				list = rs.loadMatchingSearchList(pi.getCurrentPage(), pi.getLimit(), resume.get(0));
			}
			
			
		}else {				//작성한 이력서가 없을경우 실행(인덱스와 검색조건 동일)
			System.out.println("이력서가 없을경우 실행");
			pi = PageTemplate.indexPaging(request, rs);
			list = rs.loadRecruitmentList(pi.getCurrentPage(), pi.getLimit());
		}
		
		
			
		
		String url = "";
		
		if(null != list) {
			url = "views/matchingSearch/matchingSearchPage.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			if(0 < resume.size()) {
				request.setAttribute("resumeNames", resumeNames);
			}
		}else {
			url = "index.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
