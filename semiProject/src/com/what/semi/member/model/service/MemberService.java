package com.what.semi.member.model.service;

import java.sql.Connection;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.dao.MemberDao;

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
	
}
