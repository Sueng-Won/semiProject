package com.what.semi.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.qna.model.service.QnaService;
import com.what.semi.qna.model.vo.QnaVo;

@WebServlet("/adminQnaList.do")
public class AdminQnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminQnaListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<QnaVo> list = null;		//조회한 데이터를 저장할 list 선언
		QnaService qs = new QnaService();	//서비스를 호출할 객체 선언
		PageInfo pi = null;					//페이지정보를 저장할 객체 선언
		QnaVo qv = new QnaVo();				//각각의 qna를 저장할 객체 선언
		
		String category = request.getParameter("category");//view에서 받아온 값을 저장할 변수 선언
		int is_checked = Integer.parseInt(request.getParameter("is_checked"));					//view에서 받아온 값을 저장할 변수 선언
		String m_id = request.getParameter("m_id");;					//view에서 받아온 값을 저장할 변수 선언
		
		String answer = request.getParameter("answer");
		int q_no = Integer.parseInt(request.getParameter("q_no"));
		//==============검색관련 전송데이터 유무를 판단하여 재 초기화하는 로직==================
		if(null == category) {
			category = "";
		}
		if(0 > is_checked || is_checked > 1) {
			is_checked = 0;
		}
		if(null == request.getAttribute("")) {
			m_id = null;
		}
		//===========================================================
		
		qv.setCategory(category);
		qv.setIs_checked(is_checked);
		qv.setM_id(m_id);
		
		//=============답변을 올렸을때 수행될 로직============================
		if(null != answer && null != request.getParameter("q_no")) {
			qs.updateAnswer(answer, q_no);
		}
		
		//==========================================================
		
		
		pi = PageTemplate.membersQnaPage(request, qs, qv);
		list = qs.selectMembersQna(pi, qv);
		
		for(QnaVo result : list) {
			System.out.println(result.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
