package com.what.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.member.model.service.MemberService;
import com.what.semi.member.model.vo.MemberVo;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String zipcode = request.getParameter("zipcode");
		String member_type = request.getParameter("rTypeValue");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberVo member = new MemberVo(id,name,email,phone,zipcode,member_type,latitude,longitude,address,addressDetail);
		int result = new MemberService().updateMember(member);
		
		if(result>0) {
			response.sendRedirect("/sp/views/member/updateConfirm.jsp");
		}
		else {
			System.out.println("update실패");
			response.sendRedirect("index.jsp");
		}
	
	}

}
