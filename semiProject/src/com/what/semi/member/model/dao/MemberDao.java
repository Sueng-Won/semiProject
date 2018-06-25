package com.what.semi.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.vo.MemberVo;

public class MemberDao {

	public int enterUser(Connection conn, String id) {
		int result = -1;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		System.out.println("Dao = ["+id+"]");
		try {
			query = "INSERT INTO MEMBER VALUES( ? , NULL, NULL,"
					+ " NULL, NULL, NULL, NULL, NULL, DEFAULT, NULL, NULL, DEFAULT, DEFAULT)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			System.out.println("INSERT 성공");
		} catch (SQLException e) {
			System.out.println("INSERT 실패");
			
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int checkId(Connection conn, String id) {
		int result = 0;
		MemberVo mv = null;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT ID FROM MEMBER WHERE ID = '?'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mv = new MemberVo();
				mv.setId(rs.getString("kakao_id"));
				System.out.println("아이디 조회됨");
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
