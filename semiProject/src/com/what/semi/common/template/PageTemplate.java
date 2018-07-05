package com.what.semi.common.template;

import javax.servlet.http.HttpServletRequest;

import com.what.semi.blackList.model.service.BlackListService;
import com.what.semi.blackList.model.vo.ConditionVo;
import com.what.semi.qna.model.service.QnaService;
import com.what.semi.qna.model.vo.QnaVo;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.resume.model.vo.MyResumeVo;




public class PageTemplate {
	
	public static PageInfo LocalPaging(HttpServletRequest request, RecruitmentService rs, LocalPageInfo lpi) {
		//페이징 처리 변수
		int currentPage = 1;	//현재 페이지의 번호(default=1)
		int limitPage = 10;		//한 페이지에 출력할 페이지 개수
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지
		int endPage;		//마지막 페이지 변수
		
		int limit = 12;			//한 페이지에 출력할 글의 개수(default=12)
		int listCount;
		
		
		//게시글의 총 갯수
		
		listCount = rs.selectLocalListTotalCount(lpi);			//검색어와 검색조건을 통해 받아온 DB상의 데이터 수를 리턴받음
		System.out.println("listCount/"+listCount);
		if(null != request.getParameter("currentPage")) {		//만약 페이지를 이동했을경우는 해당 페이지를 기준으로 잡아줌
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//130 -> 13
		maxPage = (int)((double)listCount / limit + 0.95);
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		//11~20 -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);		
		return pi;
		
	}
	
	public static PageInfo indexPaging(HttpServletRequest request, RecruitmentService rs) {
		//페이징 처리 변수
		int currentPage = 1;	//현재 페이지의 번호(default=1)
		int limitPage = 10;		//한 페이지에 출력할 페이지 개수
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지
		int endPage;		//마지막 페이지 변수
		
		int limit = 12;			//한 페이지에 출력할 글의 개수(default=12)
		int listCount;
		
		
		//게시글의 총 갯수
		listCount = rs.selectIndexListTotalCount();
		
		if(null != request.getParameter("currentPage")) {					//페이지가 이동되었을경우
			currentPage = Integer.parseInt(request.getParameter("currentPage"));	//넘어온 페이지 값을 현재페이지로 설정
		}
		
		
		//130 -> 13
		maxPage = (int)((double)listCount / limit + 0.95);
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		//11~20 -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);		
		return pi;
		
	}
	
	public static PageInfo byDatePaging(HttpServletRequest request,RecruitmentService rs,String dateStr) {
		//페이징 처리 변수
		int currentPage = 1;	//현재 페이지의 번호(default=1)
		int limitPage = 10;		//한 페이지에 출력할 페이지 개수
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지
		int endPage;		//마지막 페이지 변수
		
		int limit = 12;			//한 페이지에 출력할 글의 개수(default=12)
		int listCount;
		
		//게시글의 총 갯수
		listCount = rs.byDateListTotalCount(dateStr);
		
		if(null != request.getParameter("currentPage")) {					//페이지가 이동되었을경우
			currentPage = Integer.parseInt(request.getParameter("currentPage"));	//넘어온 페이지 값을 현재페이지로 설정
		}
		
		
		//130 -> 13
		maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		//11~20 -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);		
		return pi;
		
	}
	
	
	public static PageInfo myRecPaging(HttpServletRequest request,RecruitmentService rs,String id) {
		//페이징 처리 변수
		int currentPage = 1;	//현재 페이지의 번호(default=1)
		int limitPage = 10;		//한 페이지에 출력할 페이지 개수
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지
		int endPage;		//마지막 페이지 변수
		
		int limit = 12;			//한 페이지에 출력할 글의 개수(default=12)
		int listCount;
		
		//게시글의 총 갯수
		listCount = rs.myRecListTotalCount(id);
		
		if(null != request.getParameter("currentPage")) {					//페이지가 이동되었을경우
			currentPage = Integer.parseInt(request.getParameter("currentPage"));	//넘어온 페이지 값을 현재페이지로 설정
		}
		
		
		//130 -> 13
		maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		//11~20 -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);		
		return pi;
		
	}
	
	public static PageInfo machingSearchPaging(HttpServletRequest request, RecruitmentService rs,
			MyResumeVo myResumeVo) {
		//페이징 처리 변수
		int currentPage = 1;	//현재 페이지의 번호(default=1)
		int limitPage = 10;		//한 페이지에 출력할 페이지 개수
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지
		int endPage;		//마지막 페이지 변수
		
		int limit = 12;			//한 페이지에 출력할 글의 개수(default=12)
		int listCount;
		
		
		//게시글의 총 갯수
		listCount = rs.selectMachingListTotalCount(myResumeVo);			//검색어와 검색조건을 통해 받아온 DB상의 데이터 수를 리턴받음
		System.out.println("listCount/"+listCount);
		
		if(null != request.getParameter("currentPage")) {		//만약 페이지를 이동했을경우는 해당 페이지를 기준으로 잡아줌
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//130 -> 13
		maxPage = (int)((double)listCount / limit + 0.95);
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		//11~20 -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);		
		return pi;
	}

	public static PageInfo blackListPaging(HttpServletRequest request, BlackListService bls, ConditionVo condition) {
		//페이징 처리 변수
		int currentPage = 1;	//현재 페이지의 번호(default=1)
		int limitPage = 10;		//한 페이지에 출력할 페이지 개수
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지
		int endPage;		//마지막 페이지 변수
		
		int limit = 10;			//한 페이지에 출력할 글의 개수(default=10)
		int listCount;
		
		
		//게시글의 총 갯수
		listCount = bls.selectBlackListTotalCount(condition);			//검색조건을 통해 받아온 DB상의 데이터 수를 리턴받음
		System.out.println("listCount/"+listCount);
		
		if(null != request.getParameter("currentPage") && !"".equals(request.getParameter("currentPage"))) {		//만약 페이지를 이동했을경우는 해당 페이지를 기준으로 잡아줌
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//130 -> 13
		maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		//11~20 -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);		
		return pi;
	}
	
	public static PageInfo QnaPage(HttpServletRequest request, String id, QnaService qs) {
	      //페이징 처리 변수
	      int currentPage = 1;   //현재 페이지의 번호(default=1)
	      int limitPage = 10;      //한 페이지에 출력할 페이지 개수
	      int maxPage;         //가장 마지막 페이지
	      int startPage;         //시작 페이지
	      int endPage;         //마지막 페이지 변수
	      int limit = 10;         //한 페이지에 출력할 글의 개수(default=12)
	      int listCount;
	            
	      //게시글의 총 갯수
	      listCount = qs.QnaCount(id);
	            
	      if(null != request.getParameter("currentPage")) {                     //페이지가 이동되었을경우
	         currentPage = Integer.parseInt(request.getParameter("currentPage"));   //넘어온 페이지 값을 현재페이지로 설정
	      }
	            
	            
	      //130 -> 13
	      maxPage = (int)((double)listCount / limit + 0.9);
	      //현재 페이지 번호
	      //12 - 10
	      startPage = (int)(currentPage / limitPage * limitPage) + 1;
	      //11~20 -> 134 -> 14
	      endPage = startPage + limitPage - 1;
	      if(maxPage < endPage) {
	         endPage = maxPage;
	      }
	            
	      PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);      
	      return pi;
	   }

	public static PageInfo membersQnaPage(HttpServletRequest request, QnaService qs, QnaVo qv) {
		//페이징 처리 변수
	      int currentPage = 1;   //현재 페이지의 번호(default=1)
	      int limitPage = 10;      //한 페이지에 출력할 페이지 개수
	      int maxPage;         //가장 마지막 페이지
	      int startPage;         //시작 페이지
	      int endPage;         //마지막 페이지 변수
	      int limit = 10;         //한 페이지에 출력할 글의 개수(default=12)
	      int listCount;
	            
	      //게시글의 총 갯수
	      listCount = qs.membersQnaCount(qv);
	            
	      if(null != request.getParameter("currentPage")) {                     //페이지가 이동되었을경우
	         currentPage = Integer.parseInt(request.getParameter("currentPage"));   //넘어온 페이지 값을 현재페이지로 설정
	      }
	            
	            
	      //130 -> 13
	      maxPage = (int)((double)listCount / limit + 0.9);
	      //현재 페이지 번호
	      //12 - 10
	      startPage = (int)(currentPage / limitPage * limitPage) + 1;
	      //11~20 -> 134 -> 14
	      endPage = startPage + limitPage - 1;
	      if(maxPage < endPage) {
	         endPage = maxPage;
	      }
	            
	      PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);      
	      return pi;
	}
}
