package com.what.semi.recruitment.controller.index;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;




@WebServlet("/indexList.do")
public class IndexListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RecruitmentService rs = new RecruitmentService();
		PageInfo pi = PageTemplate.indexPaging(request, rs);
		
		ArrayList<RecruitmentVo> list = rs.loadRecruitmentList(pi.getCurrentPage(), pi.getLimit());
		String url = "";
		if(null != list) {
				url = "views/common/mainPage.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
		}else {
			url = "index.jsp";
			request.setAttribute("msg", "오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
