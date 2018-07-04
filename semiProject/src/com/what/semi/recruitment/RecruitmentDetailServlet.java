package com.what.semi.recruitment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.member.model.service.MemberService;
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class RecruitmentDetailServlet
 */
@WebServlet("/recruitmentDetail.do")
public class RecruitmentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitmentDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("id");
		
		String recId = (String) request.getParameter("recId");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage);
		
		RecruitmentVo rec = new RecruitmentService().selectRecruitment(recId);
		MemberVo writer = new MemberService().getMemberInfo(rec.getM_id());
		System.out.println(writer.toString());
	
		String url = "";
		if(null != rec){
			url = "views/recruitment/recruitmentDetail.jsp";
			System.out.println(rec.toString());
			request.setAttribute("rec", rec);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("writer", writer);
		}else{
			/*url = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 상세조회에 실패하였습니다.");*/
			System.out.println("구인게시글상세페이지오류");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	
	}

}
