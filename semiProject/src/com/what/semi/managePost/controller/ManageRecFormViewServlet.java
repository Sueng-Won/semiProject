package com.what.semi.managePost.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.managePost.model.service.ManagePostService;
import com.what.semi.managePost.model.vo.ManageRecruitmentVo;

@WebServlet("/manageRecFormView.do")
public class ManageRecFormViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManageRecFormViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_no;
		ManageRecruitmentVo mrv = null;
		if(null != request.getParameter("r_no")) {
			r_no = Integer.parseInt(request.getParameter("r_no"));
			mrv = new ManagePostService().selectedRecruitment(r_no); 
		}
		
		if(null != mrv) {
			request.setAttribute("rec", mrv);
		}else {
			request.setAttribute("msg", "해당 게시물은 삭제할 수 없습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher("views/admin/manageRecruitmentForm.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
