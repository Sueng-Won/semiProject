package com.what.semi.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.JDBCTemplate;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("hd");
		String name = request.getParameter("name");
		
		Connection conn = null;	//DB연결된 상태(세션)을 담은 객체
		PreparedStatement pstm = null;	//SQL문을 나타내는 객체
	
		String query = "INSERT INTO MEMBER VALUES("+id+", NULL, NULL"
				+ ",NULL,NULL,NULL,NULL,NULL,DEFAULT,NULL,NULL,DEFAULT,DEFAULT)";
		
		conn = JDBCTemplate.getConnection();
		try {
			pstm = conn.prepareStatement(query);
			pstm.executeUpdate();
			System.out.println("INSERT 성공");
//			RequestDispatcher view = request.getRequestDispatcher("/sp/indexList.do");
//			request.setAttribute("name", name);
//			view.forward(request, response);
		} catch (SQLException e) {
			System.out.println("INSERT 실패");
			response.sendRedirect("/sp/indexList.do?name="+URLEncoder.encode(name, "UTF-8"));
			//e.printStackTrace();
		} finally {
			try {
				if(pstm!=null) {pstm.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}
