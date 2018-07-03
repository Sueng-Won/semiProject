package com.what.semi.resume.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.member.model.service.MemberService;
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/getMemberInfo.do")
public class GetMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetMemberInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//유저 아이디를 이용해서 member테이블에 저장된 값을 가져오는 서블릿
				String userId = request.getParameter("userId");
				
				MyResumeVo member = new MyResumeService().selectMemberInfo(userId);
				
				String url="";
				if(null!=member){
					url="/views/resume/resumeForm.jsp";
					request.setAttribute("member", member);
				}else{
					url="/sp";
				}
				RequestDispatcher view = request.getRequestDispatcher(url);
				view.forward(request, response);
	}
}
