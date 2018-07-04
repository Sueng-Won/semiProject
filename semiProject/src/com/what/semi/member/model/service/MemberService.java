package com.what.semi.member.model.service;

import java.sql.Connection;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.dao.MemberDao;
import com.what.semi.member.model.vo.MemberVo;

public class MemberService {

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

	public int dropMember(String id) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().drop(conn,id);
		JDBCTemplate.close(conn);
		return result;
	}

	public int checkPw(String id ,String pw) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().checkPw(conn,id,pw);
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberVo getMemberInfo(String id) {
		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo member = new MemberDao().getMember(conn,id);
		JDBCTemplate.close(conn);
		return member;
	}

	public int updateMember(MemberVo m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn,m);
		JDBCTemplate.close(conn);
		return result;
	}

	public String searchId(String email) {
Connection conn = JDBCTemplate.getConnection();
		
		String resultId = new MemberDao().searchId(conn,email);
		JDBCTemplate.close(conn);
		return resultId;
	}
}
