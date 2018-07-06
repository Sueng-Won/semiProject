package com.what.semi.recruitment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.recruitment.model.service.RecruitmentService;

/**
 * Servlet implementation class DeleteRecruitmentServlet
 */
@WebServlet("/deleteRecruitment.do")
public class DeleteRecruitmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRecruitmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recId = request.getParameter("recId");
		int result = new RecruitmentService().deleteRec(recId);
		
		if(result<0) {
			System.out.println("DELFLAG 업데이트 실패");
		}else {
			System.out.println("DELFLAG 업데이트 성공");
			response.sendRedirect("/sp/myRecruitmentList.do");
		}
	}

}
