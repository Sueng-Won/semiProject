package com.what.semi.recruitment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.recruitment.model.service.RecruitmentService;

/**
 * Servlet implementation class SearchIdByRecIdServlet
 */
@WebServlet("/searchIdByRecId.do")
public class SearchIdByRecIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdByRecIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recId = request.getParameter("recId");
		
		String memId = new RecruitmentService().searchIdByRecId(recId);
		if(memId==null) {
			System.out.println("아이디 확인 오류");
		}else {
			System.out.println("아이디 확인 성공 : "+memId);
			response.sendRedirect("/sp/views/member/declarationMember.jsp?declarationId="+memId);
		}
	}

}
