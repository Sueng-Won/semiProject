package com.what.semi.managePost.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.managePost.model.service.ManagePostService;


@WebServlet("/deleteRec.do")
public class DeleteRecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteRecServlet() {
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
			result = mps.deleteRecruitment(r_no);
		}
		String msg = "";
		if(result != -1) {
			msg = "삭제되었습니다.";
		}else {
			msg = "해당 게시물은 삭제할 수 없습니다.";
		}
		response.getWriter().print(msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
