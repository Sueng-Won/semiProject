package com.what.semi.member.model.service;

import java.sql.Connection;

import com.sun.rowset.JdbcRowSetResourceBundle;
import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.dao.MemberDao;
import com.what.semi.member.model.vo.MemberVo;

public class MemberService {

	public int enterUser(String id) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println("service = ["+id+"]");
		int result = new MemberDao().enterUser(conn, id);
		JDBCTemplate.close(conn);
		return result;
	}

	public int checkId(String id) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().checkId(conn, id);
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int signIn(MemberVo member) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().signIn(conn,member);
		JDBCTemplate.close(conn);
		return result;
	}

	public int checkLogin(String id, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().logIn(conn,id,pw);
		JDBCTemplate.close(conn);
		return result;
	}
}
