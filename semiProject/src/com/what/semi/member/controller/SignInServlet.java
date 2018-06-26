package com.what.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.member.model.service.MemberService;
import com.what.semi.member.model.vo.MemberVo;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/signIn.do")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService ms = new MemberService();
		//id, pw, name, birth, phone, email,address, address_detail, zipcode, member_type, latitude, longitude,gender
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String address_detail = request.getParameter("addressDetail");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String member_type = request.getParameter("rTypeValue");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		char gender = request.getParameter("gender").charAt(0);
		
		MemberVo member = new MemberVo(id, pw, name, birth, phone, email, address, address_detail, zipcode, member_type, latitude, longitude, gender, 0);
		
		//System.out.println(member.toString());
		
		
		int result = 0;
		result = ms.signIn(member);
		if(result>0) {
			System.out.println("회원가입 완료");
			response.sendRedirect("index.jsp");
		} else {
			System.out.println("회원가입 실패");
			response.sendRedirect("index.jsp");
		}
	}

}
