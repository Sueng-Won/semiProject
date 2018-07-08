package com.what.semi.contract.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.GmailSend;
import com.what.semi.contract.QRUtil;
import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class AcceptRecruitmentServlet
 */
@WebServlet("/acceptRecruitment.do")
public class AcceptRecruitmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptRecruitmentServlet() {
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
		
		ContractVo cont = cs.selectContract(contId);
		int result = cs.updateContractState(contId,1);
		int dateresult = cs.updateContractDate(contId);
		
		RecruitmentVo rec = new RecruitmentService().selectRecruitment(cont.getRecruitment_id());
		String root = request.getServletContext().getRealPath("/");
		QRUtil.makeQR("http://localhost:8081/sp/timeStampPage.do?contId="+contId, root, String.valueOf(contId)+".png");
		new GmailSend().sendQRcode(rec.getRecruitment_email(), cont, String.valueOf(contId)+".png");
		
		
		if (result!=0) {
			response.sendRedirect("/sp/myWorkedList.do?contId="+contId+"&currentPage="+currentPage);
		} else {
			System.out.println("계약 수락 오류");
		}
	}

}
