package com.what.semi.contract.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.contract.model.service.ContractService;

/**
 * Servlet implementation class RejectResumeServlet
 */
@WebServlet("/rejectResume.do")
public class RejectResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int contId = Integer.parseInt(request.getParameter("contId"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		ContractService cs = new ContractService();
		
		int result = cs.updateContractState(contId,2);
		
		
		if (result!=0) {
			response.sendRedirect("/sp/myHiredList.do?contId="+contId+"&currentPage="+currentPage);
		} else {
			System.out.println("계약 거절 오류");
		}
	}

}
