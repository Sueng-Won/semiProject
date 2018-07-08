package com.what.semi.recruitment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class UpdateRecFormServlet
 */
@WebServlet("/updateRecForm.do")
public class UpdateRecFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRecFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recId = request.getParameter("recId");
		
		RecruitmentVo rec = new RecruitmentService().selectRecruitment(recId);
		
		String url = "";
		if(null != rec){
			url = "views/recruitment/recModifyForm.jsp";
			request.setAttribute("rec", rec);
		}else{
			/*url = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 상세조회에 실패하였습니다.");*/
			System.out.println("구인글수정페이지오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
