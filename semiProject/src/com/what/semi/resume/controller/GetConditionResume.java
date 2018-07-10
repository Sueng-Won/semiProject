package com.what.semi.resume.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.what.semi.common.template.PageInfo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/getResumeCondition.do")
public class GetConditionResume extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetConditionResume() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchJob = request.getParameter("searchJob");
		String searchLocal = request.getParameter("searchLocal");
		String searchworkTime = request.getParameter("searchworkTime");
		String searchDate = request.getParameter("searchDate");
		
		
		
		
		if(request.getParameter("searchDate")!=null){
			System.out.println("searchDate가 널이야");
		}else{
			searchDate ="0";
			System.out.println("searchDate가 널이아니다");
		}
		
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
			int jSlistCount = myResumePage.selectJSTotalCount(searchJob, searchLocal, searchworkTime, searchDate);
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
			
		
//		for(String job : searchJob){
//			System.out.println("job : " + job);
//		}
//		for(String local : searchLocal){
//			System.out.println("local : " + local);
//		}
//		for(int i=0; i<searchLocal.length; i++){
//			System.out.println(searchLocal[i]);
//		}
		// 스트링배열 널값 확인해야..
		ArrayList<MyResumeVo> jsList = new MyResumeService().selectResumeCondition(currentPage, limit, searchJob, searchLocal, searchworkTime, searchDate);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("jsList", jsList);
		map.put("pi", pi);
		
		if(null!=jsList){
			response.setContentType("application/json; charset=UTF-8"); 
			new Gson().toJson(map, response.getWriter());
		}else{
			System.out.println("에러");
		}
		
	}

}
