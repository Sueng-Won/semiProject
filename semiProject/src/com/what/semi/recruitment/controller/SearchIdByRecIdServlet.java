package com.what.semi.recruitment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.member.model.service.MemberService;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.resume.model.service.MyResumeService;

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
		HttpSession session = request.getSession();
		
		String recId = request.getParameter("recId");
		String declarationId = (String)session.getAttribute("id");
		String memberType = (String)session.getAttribute("member_type");
		String memId = new ContractService().searchMemId(recId,declarationId,memberType);
			if(memberType.equals("JS")) {
				response.sendRedirect("/sp/views/member/declarationJS.jsp?memId="+memId);
			}else {
				response.sendRedirect("/sp/views/member/declarationBO.jsp?memId="+memId);
			}
		
	}

}
