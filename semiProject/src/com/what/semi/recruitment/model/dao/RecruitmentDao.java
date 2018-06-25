package com.what.semi.recruitment.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.recruitment.model.vo.RecruitmentVo;


public class RecruitmentDao {

	public ArrayList<RecruitmentVo> loadRecruitmentList(Connection con, int currentPage, int limit) {
		ArrayList<RecruitmentVo> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = ""; 
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			stmt = con.createStatement();
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + 
					"BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, " + 
					"ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + 
					"START_WORK_TIME, END_WORK_TIME, PAY, " + 
					"GENDER, MILITARY_SERVICE, INTRODUCE, " + 
					"KAKAO_ID, IS_POST, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + 
					"FROM (SELECT ROWNUM RNUM, P.* " + 
					"FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + 
					"BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, " + 
					"R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + 
					"START_WORK_TIME, END_WORK_TIME, PAY, " + 
					"R.GENDER, MILITARY_SERVICE, INTRODUCE, " + 
					"R.KAKAO_ID, IS_POST, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + 
					"FROM RECRUITMENT R " + 
					"JOIN MEMBER M ON (M.KAKAO_ID = R.KAKAO_ID) " + 
					"WHERE IS_POST != 0 " + 
					"ORDER BY WORK_DAY) P) " + 
					"WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo temp = null;
			while(rs.next()) {
				temp = new RecruitmentVo();
				temp.setRecruitment_name(rs.getString("recruitment_name"));
				temp.setRecruitment_title(rs.getString("recruitment_title"));
				temp.setAddress(rs.getString("address"));
				temp.setBusiness_type(rs.getString("business_type"));
				temp.setR_latitude(rs.getDouble("r_latitude"));
				temp.setR_longitude(rs.getDouble("r_longitude"));
				temp.setPay(rs.getInt("pay"));
				temp.setWork_day(rs.getDate("work_day"));
				list.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	public int selectIndexListTotalCount(Connection con) {
		int result = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			stmt = con.createStatement();
			query = "SELECT COUNT(*) AS LISTCOUNT " + 
					"FROM RECRUITMENT " + 
					"WHERE IS_POST != 0 " +
					"ORDER BY WORK_DAY";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				result = rs.getInt("listCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return result;
	}

	public int selectLocalListTotalCount(Connection con, String keyword) {
		int result = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			stmt = con.createStatement();
			query = "SELECT COUNT(*) AS LISTCOUNT " + 
					"FROM RECRUITMENT " + 
					"WHERE IS_POST != 0 " +
					"ORDER BY WORK_DAY";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				result = rs.getInt("listCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return result;
	}

	public ArrayList<RecruitmentVo> selectByDateList(Connection con, String dateStr) {
		ArrayList<RecruitmentVo> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = ""; 
		if(dateStr.equals("null")){
			dateStr="SELECT TO_CHAR(SYSDATE,'MM/DD/RRRR') FROM SYS.DUAL";
		}
		try {
			stmt=con.createStatement();
			query = "SELECT * FROM RECRUITMENT WHERE TO_CHAR(WORK_DAY,'MM/DD/RRRR') IN ("+dateStr+")";
			//query = "SELECT * FROM RECRUITMENT";
			//System.out.println(query);
			
			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo temp = null;
			while(rs.next()) {
				temp = new RecruitmentVo();
				temp.setRecruitment_id(rs.getString("RECRUITMENT_ID"));
				temp.setRecruitment_name(rs.getString("recruitment_name"));
				temp.setRecruitment_title(rs.getString("recruitment_title"));
				temp.setAddress(rs.getString("address"));
				temp.setBusiness_type(rs.getString("business_type"));
				temp.setR_latitude(rs.getDouble("r_latitude"));
				temp.setR_longitude(rs.getDouble("r_longitude"));
				temp.setPay(rs.getInt("pay"));
				temp.setWork_day(rs.getDate("work_day"));
				
				//System.out.println(temp);
				list.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	


}
