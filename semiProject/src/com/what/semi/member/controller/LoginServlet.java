package com.what.semi.member.controller;

import java.io.IOException;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
=======
import java.net.URLEncoder;
>>>>>>> refs/heads/master

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import com.what.semi.common.template.JDBCTemplate;

/**
 * Servlet implementation class LoginServlet
 */
=======
import com.what.semi.member.model.service.MemberService;

>>>>>>> refs/heads/master
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
	      
	      String id = request.getParameter("hd");
	      String name = request.getParameter("name");
	      
	      HttpSession session = request.getSession(false);
	      session.setAttribute("nickname", name);
	      System.out.println("login"+session.getAttribute("nickname"));
	      
	      Connection conn = null;   //DB연결된 상태(세션)을 담은 객체
	      PreparedStatement pstm = null;   //SQL문을 나타내는 객체
	   
	      String query = "INSERT INTO MEMBER VALUES("+id+", NULL, NULL"
	            + ",NULL,NULL,NULL,NULL,NULL,DEFAULT,NULL,NULL,DEFAULT,DEFAULT)";
	      
	      conn = JDBCTemplate.getConnection();
	      try {
	         pstm = conn.prepareStatement(query);
	         pstm.executeUpdate();
	         System.out.println("INSERT 성공");
	         response.sendRedirect("/sp/indexList.do");
	         //response.sendRedirect("/sp/indexList.do?name="+URLEncoder.encode(name, "UTF-8"));
	      } catch (SQLException e) {
	         System.out.println("INSERT 실패");
	         response.sendRedirect("/sp/indexList.do");
	         //response.sendRedirect("/sp/indexList.do?name="+URLEncoder.encode(name, "UTF-8"));
	         //e.printStackTrace();
	      } finally {
	         try {
	            if(pstm!=null) {pstm.close();}
	            if(conn!=null) {conn.close();}
	         }catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	         }
	      }
=======
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("hd");
		String name = request.getParameter("name");
		int result = 0;
		
		result = new MemberService().checkId(id);
		
		if(0 > result) {
			result = new MemberService().enterUser(id);
		}
		HttpSession session = request.getSession();
		
		
		if(0<=result) {
			System.out.println(name);
			System.out.println("결과가 있을경우 호출");
			session.setAttribute("name", name);
			response.sendRedirect("index.jsp");;
			
		}else {
			response.sendRedirect("/sp/indexList.do?name="+URLEncoder.encode(name, "UTF-8"));
			System.out.println("결과가 없을경우 호출");
		}

		
		
>>>>>>> refs/heads/master
	}

}
