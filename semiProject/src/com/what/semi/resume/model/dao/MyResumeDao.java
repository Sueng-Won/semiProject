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
			query = "SELECT PRI_RESUME, RESUME_ID, INTRODUCE_TITLE, IS_POST, MEMBER_TYPE FROM RESUME R JOIN MEMBER M ON (R.M_ID = M.M_ID) WHERE M.M_ID=? AND MEMBER_TYPE='JS' ";
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

	public MyResumeVo selectMemberInfo(String userId, Connection con) {
		PreparedStatement pstmt = null;
		MyResumeVo member = null;
		ResultSet rs = null;
		String query =null;
		
		
		try {
			query = "SELECT NAME, PHONE, EMAIL, ADDRESS FROM MEMBER WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				member = new MyResumeVo();
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}

	public int insertResume(MyResumeVo resume, Connection con) {
		int result= -1;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "INSERT INTO RESUME VALUES(RESUME_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?, DEFAULT, ?)";
			pstmt = con.prepareStatement(query);
			
			
			pstmt.setString(1, resume.getProfile_image_src());
			pstmt.setString(2, resume.getAchievement());
			pstmt.setInt(3, resume.getDisability());
			pstmt.setInt(4, resume.getMiltary_service());
			pstmt.setInt(5, resume.getCareer());
			pstmt.setString(6, resume.getBusiness_type());
			pstmt.setDate(7, resume.getWorkable_days());
			pstmt.setString(8, resume.getId());
			pstmt.setString(9, resume.getIntroduce());
			pstmt.setString(10, resume.getIntroduce_title());
			pstmt.setString(11, resume.getWorkTime());
			
			
			result = pstmt.executeUpdate();
			
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public MyResumeVo selectMyResume(Connection con, String userId, int resume_id) {
		MyResumeVo member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			query ="SELECT NAME, PHONE, EMAIL, ADDRESS, PROFILE_IMAGE_SRC FROM RESUME R  JOIN MEMBER M ON(R.M_ID=M.M_ID) WHERE R.M_ID=? AND R.RESUME_ID=?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, resume_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				member = new MyResumeVo();
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setProfile_image_src(rs.getString("profile_image_src"));
				member.setResume_id(resume_id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return member;
	}

	public int updateResume(MyResumeVo resume, Connection con) {
		int result= -1;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE RESUME SET PROFILE_IMAGE_SRC = ?, ACHIEVEMENT = ?, DISABILITY = ?, MILTARY_SERVICE = ?, CAREER = ?, BUSINESS_TYPE = ?, WORKABLE_DAYS = ?, INTRODUCE = ?, INTRODUCE_TITLE = ?, WORK_TIME = ? WHERE RESUME_ID =? ";
			pstmt = con.prepareStatement(query);
			
			
			
			pstmt.setString(1, resume.getProfile_image_src());
			pstmt.setString(2, resume.getAchievement());
			pstmt.setInt(3, resume.getDisability());
			pstmt.setInt(4, resume.getMiltary_service());
			pstmt.setInt(5, resume.getCareer());
			pstmt.setString(6, resume.getBusiness_type());
			pstmt.setDate(7, resume.getWorkable_days());
			pstmt.setString(8, resume.getIntroduce());
			pstmt.setString(9, resume.getIntroduce_title());
			pstmt.setString(10, resume.getWorkTime());
			pstmt.setInt(11, resume.getResume_id());
			
			
			result = pstmt.executeUpdate();
			
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteResume(String userId, String resume_id, Connection con) {
		int result= -1;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "DELETE FROM RESUME WHERE RESUME_ID=? AND M_ID=?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, resume_id);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
