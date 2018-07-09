package com.what.semi.managePost.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.managePost.model.service.ManagePostService;


@WebServlet("/resetRec.do")
public class ResetRecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResetRecServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isAdmin = (String) request.getSession().getAttribute("id");
		ManagePostService mps = new ManagePostService();
		int r_no;
		int result = -1;
		if("admin".equals(isAdmin)&&null != request.getParameter("r_no")) {
			r_no = Integer.parseInt(request.getParameter("r_no"));
			System.out.println(r_no);
			result = mps.resetRecruitment(r_no);
		}
		String msg = "";
		if(result != -1) {
			msg = "복구되었습니다.";
		}else {
			msg = "해당 게시물은 복구할 수 없습니다.";
		}
		response.getWriter().print(msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
