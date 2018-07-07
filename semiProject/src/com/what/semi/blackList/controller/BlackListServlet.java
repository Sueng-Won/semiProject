package com.what.semi.blackList.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.blackList.model.service.BlackListService;
import com.what.semi.blackList.model.vo.BlackListVo;
import com.what.semi.blackList.model.vo.ConditionVo;
import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;

@WebServlet("/blackList.do")
public class BlackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlackListServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageInfo pi = null;
		BlackListService bls = new BlackListService();
		ConditionVo condition = null;
		ArrayList<BlackListVo> list = null;
		String b_id = "";
		String isReport = request.getParameter("reportValue");
		String memberType = request.getParameter("mTypeValue");
		String keyword = request.getParameter("keyword");
		
		//==view에서 건내받은 값이 없을경우 초기화를 위한 로직==
		if(null == memberType) {
			memberType = "";
		}
		if(null == keyword) {
			keyword = "";
		}
		if(null == isReport) {
			isReport = "";
		}
		//======================================
		
		
		//===================블랙리스트 등록==================
		if(null != request.getParameter("blackListId")&&!"".equals(request.getParameter("blackListId"))) {
			b_id = request.getParameter("blackListId");
			System.out.println("블랙리스트 대상 전송 ->"+b_id);
			bls.updateBlackList(b_id);
		}
		//===============================================
		
		//===================블랙리스트 제거==================
		if(null != request.getParameter("deleteblackId")&&!"".equals(request.getParameter("deleteblackId"))) {
			b_id = request.getParameter("deleteblackId");
			bls.deleteBlackList(b_id);
		}
		//===============================================
		
		
		//=========================검색조건기반 회원list 조회==========================
		condition = new ConditionVo(isReport, memberType, keyword);
		pi = PageTemplate.blackListPaging(request,bls,condition);
		list = bls.loadBlackList(pi.getCurrentPage(), pi.getLimit(), condition);
		//======================================================================
		
		System.out.println(pi.toString());
		String url = "";
		if("admin".equals(request.getSession().getAttribute("id"))) {
			url = "views/admin/manageMember.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("condition", condition);
		}else {
			url = "index.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
