package com.what.semi.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.member.model.service.MemberService;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/checkId.do")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String tBtn = request.getParameter("tBtn");
		String gender = request.getParameter("gender");
		int result = new MemberService().checkId(id);
		
		if(tBtn.equals("BO")){
			tBtn="업주";
		}else{
			tBtn="구직자";
		}
		
		if(result>0) {
			System.out.println("아이디 있음");
			response.sendRedirect("views/member/join.jsp?flag=false&id="+id+"&tBtn="+URLEncoder.encode(tBtn, "UTF-8")+"&gender="+gender);
		}
		else {
			System.out.println("아이디 없음");
			response.sendRedirect("views/member/join.jsp?flag=true&id="+id+"&tBtn="+URLEncoder.encode(tBtn, "UTF-8")+"&gender="+gender);
		}
	}

}
