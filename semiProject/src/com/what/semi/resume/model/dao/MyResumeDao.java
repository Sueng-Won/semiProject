package com.what.semi.resume.model.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.resume.model.vo.MyResumeVo;

public class MyResumeDao {

	public ArrayList<MyResumeVo> selectMyInfo(Connection con, String id) {
		PreparedStatement pstmt = null;
		String query = null;
		ResultSet rs = null;
		ArrayList<MyResumeVo> userType = null;
		
		try {
			query = "SELECT PRI_RESUME, RESUME_ID, INTRODUCE_TITLE, IS_POST, MEMBER_TYPE FROM RESUME R JOIN MEMBER M ON (R.M_ID = M.M_ID) WHERE M.M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			userType = new ArrayList<MyResumeVo>();
			MyResumeVo temp = null;
			
			while(rs.next()){
				temp = new MyResumeVo();
				temp.setId(id);
				temp.setResume_id(rs.getInt("resume_id"));
				temp.setIntroduce_title(rs.getString("introduce_title"));
				temp.setIs_post(rs.getInt("is_post"));
				temp.setMember_type(rs.getString("member_type"));
				temp.setPri_resume(rs.getString("pri_resume").charAt(0));
				userType.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return userType;
	}

	public int updatePri_resumeY(int resume_id, String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query =null;
		try {
			query = "UPDATE RESUME SET PRI_RESUME=? WHERE RESUME_ID=? AND M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, resume_id);
			pstmt.setString(3, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updatePri_resumeN(int resume_id, String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query =null;
		try {
			query = "UPDATE RESUME SET PRI_RESUME=? WHERE RESUME_ID!=? AND M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "N");
			pstmt.setInt(2, resume_id);
			pstmt.setString(3, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateIs_post0(String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query="";
		
		try {
			query = "UPDATE RESUME SET IS_POST=0 WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateIs_post1(String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query="";
		
		try {
			query = "UPDATE RESUME SET IS_POST=1 WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public MemberVo selectMemberInfo(String userId, Connection con) {
		PreparedStatement pstmt = null;
		MemberVo member = null;
		ResultSet rs = null;
		String query =null;
		
		
		try {
			query = "SELECT NAME, PHONE, EMAIL, ADDRESS, GENDER, IS_BLACK_LIST  FROM MEMBER WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				member = new MemberVo();
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setGender(rs.getString("gender").charAt(0));
				member.setIs_black_list(rs.getInt("is_black_list"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
