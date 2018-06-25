package com.what.semi.recruitment.controller.date;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.GmailSend;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class SearchByDateListServlet
 */
@WebServlet("/searchByDateList.do")
public class SearchByDateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByDateListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr = request.getParameter("dateStr");
		//System.out.println(dateStr);
		ArrayList<RecruitmentVo> list = new RecruitmentService().selectByDateList(dateStr);
		
		GmailSend gs = new GmailSend();
		//gs.GmailSet("yul2514@naver.com",dateStr,dateStr);
		
		RequestDispatcher view = null;
		String url="";
		if(list!=null){
			for(int i=0;i<list.size();i++){
				//System.out.println(list.get(i).toString());
			}
			url="/views/byDate/searchByDate.jsp";
			request.setAttribute("list", list);
			
		}else{
			System.out.println("null");
		}
		view=request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
