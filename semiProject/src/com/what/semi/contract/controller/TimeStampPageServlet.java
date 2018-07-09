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
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class TimeStampPageServlet
 */
@WebServlet("/timeStampPage.do")
public class TimeStampPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeStampPageServlet() {
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
		RecruitmentVo rec = new RecruitmentService().selectRecruitment(cont.getRecruitment_id());

		RequestDispatcher view = null;
		String url = "";
		if (cont != null) {
			url = "/views/common/timeStamp.jsp";
			request.setAttribute("cont", cont);
			request.setAttribute("rec", rec);
			request.setAttribute("result", 0);

		/*	response.setContentType("text/html;charset=utf-8"); // 어떤 타입으로
			// 출력할것인지 명시하였다.

			PrintWriter out = response.getWriter(); // getWriter() 출력스트림.
			String str = "";
			str = "<script language='javascript'>";
			str += "var cont="+cont+";";
			str += "var rec="+rec+";";
			str += "var result="+0+";";
			str += "window.open(\"/views/common/timeStamp.jsp\",\"_blank\", \"width=600, height=700\");"; // 얼럿창 띄우기
			str += "</script>";
			out.print(str);*/
		} else {
			System.out.println("타임스탬프페이지오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
