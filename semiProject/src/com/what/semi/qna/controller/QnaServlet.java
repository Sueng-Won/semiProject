package com.what.semi.qna.controller;

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
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.qna.model.service.QnaService;
import com.what.semi.qna.model.vo.QnaVo;

@WebServlet("/qna.do")
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaServlet() {
        super();
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PageInfo pi = null;
		QnaService qs = new QnaService();
		String id = (String)session.getAttribute("id");
		String email = (String)session.getAttribute("email");
		String phone = (String)session.getAttribute("phone");
		String content = request.getParameter("Contents");
		String category = request.getParameter("slt");
		
	
		ArrayList<QnaVo> qna = null;
		int result = 0;
		
		if(null != request.getParameter("Contents") && null != request.getParameter("slt")) {
			result = new QnaService().insertQna(content,category,id);
		}else {
			pi = PageTemplate.QnaPage(request,id,qs);
			System.out.println(pi.toString());
			qna = new QnaService().selectQna(pi,id);
		}
		
		String url= "";
		if(result > 0) {
			url = "views/qna/qnaNew.jsp";
		}else {
			request.setAttribute("qna", qna);
			request.setAttribute("pi", pi);
			url = "views/qna/myQna.jsp";
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}
}
