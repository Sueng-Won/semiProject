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
		
		
		String isReport = request.getParameter("reportValue");
		String memberType = request.getParameter("mtypeValue");
		String keyword = request.getParameter("keyword");
		
		//view에서 건내받은 값이 없을경우 초기화를 위한 로직
		if(null == memberType || "NO".equals(memberType)) {
			memberType = "";
		}
		if(null == keyword) {
			keyword = "";
		}
		
		condition = new ConditionVo(isReport, memberType, keyword);
		pi = PageTemplate.blackListPaging(request,bls,condition);
		list = bls.loadBlackList(pi.getCurrentPage(), pi.getLimit(), condition);
		
		String url = "";
		if(null != list) {
			url = "view/admin/manageMember.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			url = "index.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
