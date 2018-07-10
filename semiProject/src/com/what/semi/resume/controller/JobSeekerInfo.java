package com.what.semi.resume.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/jobSeekerInfo.do")
public class JobSeekerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobSeekerInfo() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			MyResumeService myResumePage = new MyResumeService();
			//페이징 처리 변수
				int currentPage;	//현재 페이지의 번호
				int limitPage;		//한페이지에 출력할 페이지 갯수
				//1~10
				int maxPage;		//가장 마지막 페이지
				int startPage;		//시작 페이지 변수
				int endPage;		//마지막 페이지 변수
				int limit;				//한페이지에 출력할 글에 갯수
				
				limit = 4;
				limitPage = 5;
				
				if(request.getParameter("currentPage") != null){
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}else{
					currentPage = 1;
				}
				
				//게시글의 총 갯수
				int jSlistCount = myResumePage.selectJSTotalCount();
				//134 -> 14
				maxPage = (int)((double)jSlistCount / limit + 0.9);
				
				//현재 페이지 번호
				//12 - 10
				startPage = (int)(currentPage / limitPage * limitPage) + 1;
				
				//11~20  -> 134 -> 14
				endPage = startPage + limitPage - 1;
				if(maxPage < endPage){
					endPage = maxPage;
				}
				
				PageInfo pi = new PageInfo(currentPage, limitPage, maxPage,
																		startPage, endPage, jSlistCount);
				
				ArrayList<MyResumeVo> jsList = myResumePage.selectJSList(currentPage, limit);
				
				String url = "";
				if(null!=jsList){
					url="views/jobSeekerInfo/jobSeekerInfo.jsp";
					request.setAttribute("jsList", jsList);
					request.setAttribute("pi", pi);
				}else{
					url="views/common/errorPage.jsp";
					request.setAttribute("msg", "구직자정보조회에러");
				}
				RequestDispatcher view = request.getRequestDispatcher(url);
				view.forward(request, response);
	}

}
