package com.what.semi.contract.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;

/**
 * Servlet implementation class TimeStampServlet
 */
@WebServlet("/timeStamp.do")
public class TimeStampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeStampServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int contId = Integer.parseInt(request.getParameter("contId"));
		ContractVo cont = new ContractService().selectContract(contId);

		int result = 0;
		if (cont.getStart_work_time() == null) {
			result = new ContractService().writeStartTime(cont.getC_no());
		} else {
			result = new ContractService().writeEndTime(cont.getC_no());
			new ContractService().updateContractState(contId, 2);
		}

		int count = new ContractService().contractCurrentPage(cont.getJs_id(), contId);
		int currentPage = 0;
		if (count % 12 == 0) {
			currentPage = count / 12;
		} else {
			currentPage = (count / 12) + 1;
		}

		RequestDispatcher view = null;
		String url = "";
		if (cont != null) {
			/*
			 * url = "/views/common/timeStamp.jsp"; request.setAttribute("cont",
			 * cont); request.setAttribute("rec", null);
			 * request.setAttribute("result", result);
			 */
			response.setContentType("text/html;charset=utf-8"); // 어떤 타입으로
																// 출력할것인지 명시하였다.

			PrintWriter out = response.getWriter(); // getWriter() 출력스트림.
			String msg = "근무시간이 기록되었습니다. 확인하시겠습니까?";
			String str = "";
			str = "<script language='javascript'>";
			str += "var bool = confirm('" + msg + "');"; // 얼럿창 띄우기
			str += "if(bool){";
			str += "location.href=\"myWorkedList.do?contId=" + contId + "&currentPage=" + currentPage + "\";";
			str += "}else{";
			str += "self.close();}";
			str += "</script>";
			out.print(str);

		} else {
			System.out.println("타임스탬프오류");
		}
		/*
		 * view = request.getRequestDispatcher(url); view.forward(request,
		 * response);
		 */
	}

}
