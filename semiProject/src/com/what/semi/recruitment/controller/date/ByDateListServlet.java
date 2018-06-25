package com.what.semi.recruitment.controller.date;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class ByDateListServlet
 */
@WebServlet("/byDateList.do")
public class ByDateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ByDateListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr = "null";
		//System.out.println(dateStr);
		
		RecruitmentService rs = new RecruitmentService();

		PageInfo pi = PageTemplate.byDatePaging(request, rs, dateStr);

		ArrayList<RecruitmentVo> list = rs.selectByDateList(dateStr, pi.getCurrentPage(), pi.getLimit());
		
		//System.out.println(list.size());
		
		RequestDispatcher view = null;
		String url="";
		if(list!=null){
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).toString());
			}
			url="/views/byDate/searchByDate.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
		}else{
			System.out.println("null");
		}
		view=request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
