package com.what.semi.recruitment.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.LocalPageInfo;
import com.what.semi.recruitment.model.vo.RecruitmentVo;
import com.what.semi.resume.model.vo.MyResumeVo;

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
					"M_ID, IS_POST, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + 
					"FROM (SELECT ROWNUM RNUM, P.* " + 
					"FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + 
					"BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, " + 
					"R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + 
					"START_WORK_TIME, END_WORK_TIME, PAY, " + 
					"R.GENDER, MILITARY_SERVICE, INTRODUCE, " + 
					"R.M_ID, IS_POST, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + 
					"FROM RECRUITMENT R " + 
					"JOIN MEMBER M ON (M.M_ID = R.M_ID) " + 
					"WHERE IS_POST != 0 " + 
					"ORDER BY WORK_DAY) P) " + 
					"WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
			//System.out.println(query);
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

	public int selectLocalListTotalCount(Connection con, LocalPageInfo lpi) {
		int result = -1;
		double minLatitude = lpi.getMinLatitude();
		double maxLatitude = lpi.getMaxLatitude();
		double minLongitude = lpi.getMinLongitude();
		double maxLongitude = lpi.getMaxLongitude();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT COUNT(*) AS LISTCOUNT " + 
					"FROM RECRUITMENT " + 
					"WHERE (R_LATITUDE > ? AND R_LATITUDE < ?) " + 
					"AND (R_LONGITUDE > ? AND R_LONGITUDE < ?) "+
					"ORDER BY WORK_DAY";
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, minLatitude);
			pstmt.setDouble(2, maxLatitude);
			pstmt.setDouble(3, minLongitude);
			pstmt.setDouble(4, maxLongitude);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("listCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("로컬리스트카운트 메소드 실행");
		return result;
	}


	public ArrayList<RecruitmentVo> loadLocalRecruitmentList(Connection con, int currentPage, int limit,
			LocalPageInfo lpi) {
		ArrayList<RecruitmentVo> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = ""; 
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + 
					"BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL,   " + 
					"ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE,  " + 
					"START_WORK_TIME, END_WORK_TIME, PAY,  " + 
					"GENDER, MILITARY_SERVICE, INTRODUCE,  " + 
					"M_ID, IS_POST, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE  " + 
					"FROM (SELECT ROWNUM RNUM, P.*  " + 
					"FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC,  " + 
					"BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL,  " + 
					"R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE,  " + 
					"START_WORK_TIME, END_WORK_TIME, PAY,  " + 
					"R.GENDER, MILITARY_SERVICE, INTRODUCE,  " + 
					"R.M_ID, IS_POST, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE  " + 
					"FROM RECRUITMENT R  " + 
					"JOIN MEMBER M ON (M.M_ID = R.M_ID)  " + 
					"WHERE (IS_POST != 0  " + 
					"AND (R_LATITUDE > ? AND R_LATITUDE < ?)  " + 
					"AND (R_LONGITUDE > ? AND R_LONGITUDE < ?)) " + 
					"ORDER BY WORK_DAY) P)  " + 
					"WHERE RNUM BETWEEN ? AND ?";
			stmt = con.prepareStatement(query);
			stmt.setDouble(1, lpi.getMinLatitude());
			stmt.setDouble(2, lpi.getMaxLatitude());
			stmt.setDouble(3, lpi.getMinLongitude());
			stmt.setDouble(4, lpi.getMaxLongitude());
			System.out.println(lpi.toString());
			stmt.setInt(5, startRow);
			stmt.setInt(6, endRow);
			System.out.println(query);
			rs = stmt.executeQuery();
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
				System.out.println(temp.toString());
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


	public ArrayList<RecruitmentVo> selectByDateList(Connection con, String dateStr, int currentPage, int limit) {
		ArrayList<RecruitmentVo> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		if (dateStr.equals("null")) {
			dateStr = "SELECT TO_CHAR(SYSDATE,'MM/DD/RRRR') FROM SYS.DUAL";
		}
		try {
			stmt = con.createStatement();
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + "BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, "
					+ "ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + "START_WORK_TIME, END_WORK_TIME, PAY, "
					+ "GENDER, MILITARY_SERVICE, INTRODUCE, "
					+ "M_ID, IS_POST, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + "FROM (SELECT ROWNUM RNUM, P.* "
					+ "FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, "
					+ "BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, " + "R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, "
					+ "START_WORK_TIME, END_WORK_TIME, PAY, " + "R.GENDER, MILITARY_SERVICE, INTRODUCE, "
					+ "R.M_ID, IS_POST, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + "FROM RECRUITMENT R "
					+ "JOIN MEMBER M ON (M.M_ID = R.M_ID) "
					+ "WHERE IS_POST != 0 AND TO_CHAR(WORK_DAY,'MM/DD/RRRR') IN (" + dateStr + ")"
					+ "ORDER BY WORK_DAY) P) " + "WHERE RNUM BETWEEN " + startRow + " AND " + endRow;
			// query = "SELECT * FROM RECRUITMENT";
			System.out.println(query);

			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo temp = null;
			while (rs.next()) {
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

				// System.out.println(temp);
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

	public int selectByDateListTotalCount(Connection con, String dateStr) {
		int result = -1;

		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		if (dateStr.equals("null")) {
			dateStr = "SELECT TO_CHAR(SYSDATE,'MM/DD/RRRR') FROM SYS.DUAL";
		}

		try {
			stmt = con.createStatement();
			query = "SELECT COUNT(*) AS LISTCOUNT " + "FROM RECRUITMENT "
					+ "WHERE IS_POST != 0 AND TO_CHAR(WORK_DAY,'MM/DD/RRRR') IN (" + dateStr + ")"
					+ "ORDER BY WORK_DAY";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
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
	
	public int insertRecruitment(Connection con, RecruitmentVo rec) {
		int result = -1;

		PreparedStatement pstmt = null;

		String query = "INSERT INTO RECRUITMENT VALUES (SEQ_RECRUITMENT.NEXTVAL" + ",?, ?,?, ?, ?,"
				+ "?,TO_DATE(?,'RRRR-MM-DD'), ?, ?, TO_DATE(?,'HH24-MI')," + "TO_DATE(?,'HH24-MI'),?,"
				+ "?,?,?, ?, 1,?,?,?)";
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1,rec.getRecruitment_image_src());
			pstmt.setString(2,rec.getRecruitment_name());
			pstmt.setString(3,rec.getRecruitment_title());
			pstmt.setString(4,rec.getBusiness_type());
			pstmt.setString(5,rec.getAddress());
			pstmt.setString(6,rec.getAddress_detail());
			pstmt.setDate(7, rec.getWork_day());
			pstmt.setDouble(8, rec.getR_latitude());
			pstmt.setDouble(9, rec.getR_longitude());
			pstmt.setDate(10,rec.getStart_work_time());
			pstmt.setDate(11,rec.getEnd_work_time());
			pstmt.setInt(12, rec.getPay());
			pstmt.setString(13,String.valueOf(rec.getGender()));
			pstmt.setInt(14, rec.getMilitary_service());
			pstmt.setString(15,rec.getIntroduce());
			pstmt.setString(16,rec.getM_id());
			pstmt.setString(17,rec.getRecruitment_phone());
			pstmt.setString(18,rec.getRecruitment_email());
			pstmt.setString(19,rec.getZipcode());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectMachingListTotalCount(Connection con, MyResumeVo myResumeVo) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT COUNT(*) AS LISTCOUNT " + 
					"FROM RECRUITMENT " + 
					"WHERE IS_POST != 0 " + 
					"AND BUSINESS_TYPE = ? " + 
					"AND WORK_DAY = ? " + 
					"AND GENDER = ? " + 
					"AND MILITARY_SERVICE = ? " + 
					"ORDER BY WORK_DAY";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myResumeVo.getBusiness_type());
			pstmt.setDate(2, myResumeVo.getWorkable_days());
			pstmt.setString(3, String.valueOf(myResumeVo.getGender()));
			pstmt.setInt(4, myResumeVo.getMiltary_service());
			
			rs = pstmt.executeQuery(query);
			while(rs.next()) {
				result = rs.getInt("listCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<RecruitmentVo> loadMatchingSearchList(Connection con, int currentPage, int limit,
			MyResumeVo myResumeVo) {
		ArrayList<RecruitmentVo> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = ""; 
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + 
					"BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, " + 
					"ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + 
					"START_WORK_TIME, END_WORK_TIME, PAY, " + 
					"GENDER, MILITARY_SERVICE, INTRODUCE, " + 
					"M_ID, IS_POST, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + 
					"FROM (SELECT ROWNUM RNUM, P.* " + 
					"FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + 
					"BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, " + 
					"R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + 
					"START_WORK_TIME, END_WORK_TIME, PAY, " + 
					"R.GENDER, MILITARY_SERVICE, INTRODUCE, " + 
					"R.M_ID, IS_POST, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + 
					"FROM RECRUITMENT R " + 
					"JOIN MEMBER M ON (M.M_ID = R.M_ID) " + 
					"WHERE IS_POST != 0 " +
					"AND BUSINESS_TYPE = ? " + 
					"AND WORK_DAY = ? " + 
					"AND GENDER = ? " + 
					"AND MILITARY_SERVICE = ? " + 
					"ORDER BY WORK_DAY) P) " + 
					"WHERE RNUM BETWEEN ? AND ?";
			//System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myResumeVo.getBusiness_type());
			pstmt.setDate(2, (Date) myResumeVo.getWorkable_days());
			pstmt.setString(3, String.valueOf(myResumeVo.getGender()));
			pstmt.setInt(4, myResumeVo.getMiltary_service());
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			rs = pstmt.executeQuery();
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
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	

}
