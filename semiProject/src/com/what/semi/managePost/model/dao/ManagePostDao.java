package com.what.semi.managePost.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.PageInfo;
import com.what.semi.managePost.model.vo.ManageRecruitmentVo;
import com.what.semi.managePost.model.vo.ManageResumeVo;
import com.what.semi.managePost.model.vo.PostCondition;

public class ManagePostDao {
	public ArrayList<ManageRecruitmentVo> loadRecruitmentList(Connection con, PostCondition pc, PageInfo pi) {
		ArrayList<ManageRecruitmentVo> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		int startRow = (pi.getCurrentPage()- 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		try {
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, ZIPCODE, "
					+ "WORK_DAY, R_LATITUDE, R_LONGITUDE,START_TIME, "
					+ "END_TIME, PAY, GENDER, MILITARY_SERVICE, INTRODUCE, M_ID, IS_POST,DELFLAG,"
					+ "RECRUITMENT_NAME, RECRUITMENT_PHONE,RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,"
					+ "NAME,PHONE FROM (SELECT ROWNUM RNUM, P.* FROM "
					+ "(SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, R.ZIPCODE,"
					+ " WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME,"
					+ " PAY,R.GENDER, MILITARY_SERVICE, INTRODUCE,M. M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE,"
					+ "RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,M.NAME,M.PHONE "
					+ "FROM RECRUITMENT R JOIN MEMBER M ON (M.M_ID = R.M_ID) "
					+ "WHERE( M.NAME LIKE '%' || ? || '%' "
					+ "OR R.M_ID LIKE '%' || ? || '%' "
					+ "OR RECRUITMENT_NAME LIKE '%' || ? || '%') "
					+ "AND (INTRODUCE LIKE '%' || ? || '%' "
					+ "OR RECRUITMENT_TITLE LIKE '%' || ? || '%') ";
			
			if(-1 != pc.getIs_post()) {
				query += "AND IS_POST != ? ";
			}
			if(-1 != pc.getDelflag()) {
				query += "AND DELFLAG != ? ";
			}
			
			query += "ORDER BY WORK_DAY) P) "
					+ "WHERE RNUM BETWEEN ? AND ? ";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, pc.getUserName());
			pstmt.setString(2, pc.getUserName());
			pstmt.setString(3, pc.getUserName());
			pstmt.setString(4, pc.getContent());
			pstmt.setString(5, pc.getContent());
			
			if(-1 != pc.getIs_post()) {
				pstmt.setInt(6, pc.getIs_post());
				if(-1 != pc.getDelflag()) {
					pstmt.setInt(7, pc.getDelflag());
					pstmt.setInt(8, startRow);
					pstmt.setInt(9, endRow);
				}else {
					pstmt.setInt(7, startRow);
					pstmt.setInt(8, endRow);
				}
			}else if(-1 != pc.getDelflag()){
				pstmt.setInt(6, pc.getDelflag());
				pstmt.setInt(7, startRow);
				pstmt.setInt(8, endRow);
			}else {
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<ManageRecruitmentVo>();
			ManageRecruitmentVo mrec = null;
			while (rs.next()) {
				mrec = new ManageRecruitmentVo();
				mrec.setRecruitment_id(rs.getString("recruitment_id"));
				mrec.setRecruitment_image_src(rs.getString("recruitment_image_src"));
				mrec.setRecruitment_name(rs.getString("recruitment_name"));
				mrec.setRecruitment_title(rs.getString("recruitment_title"));
				mrec.setAddress(rs.getString("address"));
				mrec.setAddress_detail(rs.getString("address_detail"));
				mrec.setBusiness_type(rs.getString("business_type"));
				mrec.setR_latitude(rs.getDouble("r_latitude"));
				mrec.setR_longitude(rs.getDouble("r_longitude"));
				mrec.setPay(rs.getInt("pay"));
				mrec.setWork_day(rs.getDate("work_day"));
				mrec.setStart_work_time(rs.getString("start_time"));
				mrec.setEnd_work_time(rs.getString("end_time"));
				mrec.setGender(rs.getString("gender").charAt(0));
				mrec.setMilitary_service(rs.getInt("military_service"));
				mrec.setIntroduce(rs.getString("introduce"));
				mrec.setM_id(rs.getString("m_id"));
				mrec.setRecruitment_phone(rs.getString("recruitment_phone"));
				mrec.setRecruitment_email(rs.getString("recruitment_email"));
				mrec.setZipcode(rs.getString("zipcode"));
				mrec.setAchievement(rs.getString("achievement"));
				mrec.setCareer(rs.getInt("career"));
				mrec.setName(rs.getString("name"));
				mrec.setPhone(rs.getString("phone"));
				mrec.setIs_post(rs.getInt("is_post"));
				mrec.setDelflag(rs.getInt("delflag"));

				list.add(mrec);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}
	
	public ArrayList<ManageResumeVo> loadResumeList(Connection con, PostCondition pc, PageInfo pi) {
		ArrayList<ManageResumeVo> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		int startRow = (pi.getCurrentPage()- 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		try {
			query = "SELECT RESUME_ID, PROFILE_IMAGE_SRC, ACHIEVEMENT, DISABILITY, MILTARY_SERVICE, CAREER, " + 
					"BUSINESS_TYPE, WORKABLE_DAYS, IS_POST, INTRODUCE, INTRODUCE_TITLE, PRI_RESUME, " + 
					"WORK_TIME, DELFLAG, M_ID, NAME, BIRTH, PHONE, EMAIL, ADDRESS, ADDRESS_DETAIL, " + 
					"MEMBER_TYPE, GENDER " + 
					"FROM (SELECT ROWNUM RNUM, P.* FROM " + 
					"(SELECT RESUME_ID, PROFILE_IMAGE_SRC, ACHIEVEMENT, DISABILITY, MILTARY_SERVICE, CAREER, " + 
					"BUSINESS_TYPE, WORKABLE_DAYS, IS_POST, INTRODUCE, INTRODUCE_TITLE, PRI_RESUME, " + 
					"WORK_TIME, DELFLAG, R.M_ID, NAME, BIRTH, PHONE, EMAIL, ADDRESS, ADDRESS_DETAIL, " + 
					"MEMBER_TYPE, GENDER " + 
					"FROM RESUME R " + 
					"JOIN MEMBER M ON (R.M_ID = M.M_ID) " + 
					"WHERE (M.NAME LIKE '%' || ? || '%' " + 
					"OR R.M_ID LIKE '%' || ? || '%' ) " + 
					"AND (INTRODUCE LIKE '%' || ? || '%' " +
					"OR INTRODUCE_TITLE LIKE '%' || ? || '%') ";
			
			if(-1 != pc.getIs_post()) {
				query += "AND IS_POST != ? ";
			}
			if(-1 != pc.getDelflag()) {
				query += "AND DELFLAG != ? ";
			}
			
			query += "ORDER BY WORKABLE_DAYS) P) "
					+ "WHERE RNUM BETWEEN ? AND ? ";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, pc.getUserName());
			pstmt.setString(2, pc.getUserName());
			pstmt.setString(3, pc.getContent());
			pstmt.setString(4, pc.getContent());
			
			if(-1 != pc.getIs_post()) {
				pstmt.setInt(5, pc.getIs_post());
				if(-1 != pc.getDelflag()) {
					pstmt.setInt(6, pc.getDelflag());
					pstmt.setInt(7, startRow);
					pstmt.setInt(8, endRow);
				}else {
					pstmt.setInt(6, startRow);
					pstmt.setInt(7, endRow);
				}
			}else if(-1 != pc.getDelflag()){
				pstmt.setInt(5, pc.getDelflag());
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
			}else {
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<ManageResumeVo>();
			ManageResumeVo mrec = null;
			while (rs.next()) {
				mrec = new ManageResumeVo();
				mrec.setId(rs.getString("m_id"));
				mrec.setResume_id(rs.getInt("resume_id"));
				mrec.setIntroduce_title(rs.getString("introduce_title"));
				mrec.setIs_post(rs.getInt("is_post"));
				mrec.setMember_type(rs.getString("member_type"));
				mrec.setPri_resume(rs.getString("pri_resume").charAt(0));
				mrec.setProfile_image_src(rs.getString("profile_image_src"));
				mrec.setAchievement(rs.getString("achievement"));
				mrec.setDisability(rs.getInt("disability"));
				mrec.setMiltary_service(rs.getInt("miltary_service"));
				mrec.setCareer(rs.getInt("career"));
				mrec.setBusiness_type("business_type");
				mrec.setWorkable_days(rs.getDate("workable_days"));
				mrec.setName(rs.getString("name"));
				mrec.setBirth(rs.getString("birth"));
				mrec.setPhone(rs.getString("phone"));
				mrec.setEmail(rs.getString("email"));
				mrec.setAddress(rs.getString("address"));
				mrec.setAddressDetail(rs.getString("address_detail"));
				mrec.setIntroduce(rs.getString("introduce"));
				mrec.setWorkTime(rs.getString("work_time"));
				mrec.setGender(rs.getString("gender").charAt(0));
				mrec.setDelflag(rs.getInt("DELFLAG"));
				list.add(mrec);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	
	public int recruitmentListCount(Connection con, PostCondition pc) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT COUNT(*) AS LISTCOUNT " 
					+ "FROM RECRUITMENT R " 
					+ "JOIN MEMBER M ON (R.M_ID = M.M_ID) " 
					+ "WHERE( M.NAME LIKE '%' || ? || '%' "
					+ "OR R.M_ID LIKE '%' || ? || '%' "
					+ "OR RECRUITMENT_NAME LIKE '%' || ? || '%') "
					+ "AND (INTRODUCE LIKE '%' || ? || '%' "
					+ "OR RECRUITMENT_TITLE LIKE '%' || ? || '%') ";
			if(-1 != pc.getIs_post()) {
				query += "AND IS_POST != ? ";
			}
			if(-1 != pc.getDelflag()) {
				query += "AND DELFLAG != ? ";
			}
			query += "ORDER BY WORK_DAY";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, pc.getUserName());
			pstmt.setString(2, pc.getUserName());
			pstmt.setString(3, pc.getUserName());
			pstmt.setString(4, pc.getContent());
			pstmt.setString(5, pc.getContent());
			
			if(-1 != pc.getIs_post()) {
				pstmt.setInt(6, pc.getIs_post());
				if(-1 != pc.getDelflag()) {
					pstmt.setInt(7, pc.getDelflag());
				}
			}else if(-1 != pc.getDelflag()) {
				pstmt.setInt(6, pc.getDelflag());
			}
			
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
		
		return result;
	}

	public int resumeListCount(Connection con, PostCondition pc) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT COUNT(*) AS LISTCOUNT " + 
					"FROM RESUME R " + 
					"JOIN MEMBER M ON (R.M_ID = M.M_ID) " +
					"WHERE (M.NAME LIKE '%' || ? || '%' " + 
					"OR R.M_ID LIKE '%' || ? || '%' ) " + 
					"AND (INTRODUCE LIKE '%' || ? || '%' " +
					"OR INTRODUCE_TITLE LIKE '%' || ? || '%') ";
			if(-1 != pc.getIs_post()) {
				query += "AND IS_POST != ? ";
			}
			if(-1 != pc.getDelflag()) {
				query += "AND DELFLAG != ? ";
			}
			query += "ORDER BY WORKABLE_DAYS";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, pc.getUserName());
			pstmt.setString(2, pc.getUserName());
			pstmt.setString(3, pc.getContent());
			pstmt.setString(4, pc.getContent());
			
			if(-1 != pc.getIs_post()) {
				pstmt.setInt(5, pc.getIs_post());
				if(-1 != pc.getDelflag()) {
					pstmt.setInt(6, pc.getDelflag());
				}
			}else if(-1 != pc.getDelflag()) {
				pstmt.setInt(5, pc.getDelflag());
			}
			
			
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
		
		return result;
	}

	public int deleteRecruitment(Connection con, int r_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RECRUITMENT SET DELFLAG = 1 WHERE RECRUITMENT_ID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, r_no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public ManageRecruitmentVo selectedRecruitment(Connection con, int r_no) {
		ManageRecruitmentVo mrec = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, R.ZIPCODE, "
					+ "WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME, "
					+ "PAY,R.GENDER, MILITARY_SERVICE, INTRODUCE,M. M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE, "
					+ "RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,M.NAME,M.PHONE "
					+ "FROM RECRUITMENT R JOIN MEMBER M ON (M.M_ID = R.M_ID) "
					+ "WHERE RECRUITMENT_ID = ? ";
			
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, r_no);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mrec = new ManageRecruitmentVo();
				mrec.setRecruitment_id(rs.getString("recruitment_id"));
				mrec.setRecruitment_image_src(rs.getString("recruitment_image_src"));
				mrec.setRecruitment_name(rs.getString("recruitment_name"));
				mrec.setRecruitment_title(rs.getString("recruitment_title"));
				mrec.setAddress(rs.getString("address"));
				mrec.setAddress_detail(rs.getString("address_detail"));
				mrec.setBusiness_type(rs.getString("business_type"));
				mrec.setR_latitude(rs.getDouble("r_latitude"));
				mrec.setR_longitude(rs.getDouble("r_longitude"));
				mrec.setPay(rs.getInt("pay"));
				mrec.setWork_day(rs.getDate("work_day"));
				mrec.setStart_work_time(rs.getString("start_time"));
				mrec.setEnd_work_time(rs.getString("end_time"));
				mrec.setGender(rs.getString("gender").charAt(0));
				mrec.setMilitary_service(rs.getInt("military_service"));
				mrec.setIntroduce(rs.getString("introduce"));
				mrec.setM_id(rs.getString("m_id"));
				mrec.setRecruitment_phone(rs.getString("recruitment_phone"));
				mrec.setRecruitment_email(rs.getString("recruitment_email"));
				mrec.setZipcode(rs.getString("zipcode"));
				mrec.setAchievement(rs.getString("achievement"));
				mrec.setCareer(rs.getInt("career"));
				mrec.setName(rs.getString("name"));
				mrec.setPhone(rs.getString("phone"));
				mrec.setIs_post(rs.getInt("is_post"));
				mrec.setDelflag(rs.getInt("delflag"));
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return mrec;		
	}

	public int resetRecruitment(Connection con, int r_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RECRUITMENT SET DELFLAG = 0 WHERE RECRUITMENT_ID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, r_no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int deleteReume(Connection con, int r_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RESUME SET DELFLAG = 1 WHERE RESUME_ID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, r_no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int resetResume(Connection con, int r_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RESUME SET DELFLAG = 0 WHERE RESUME_ID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, r_no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ManageResumeVo selectedResume(Connection con, int r_no) {
		ManageResumeVo mres = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			query = "SELECT RESUME_ID, PROFILE_IMAGE_SRC, ACHIEVEMENT, DISABILITY, MILTARY_SERVICE, CAREER, "
					+ "BUSINESS_TYPE, WORKABLE_DAYS, IS_POST, INTRODUCE, INTRODUCE_TITLE, PRI_RESUME, "
					+ "WORK_TIME, DELFLAG, M.M_ID, NAME, BIRTH, PHONE, EMAIL, ADDRESS, ADDRESS_DETAIL, "
					+ "MEMBER_TYPE, GENDER "
					+ "FROM RESUME R JOIN MEMBER M ON (M.M_ID = R.M_ID) "
					+ "WHERE RESUME_ID = ? ";
			
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, r_no);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mres = new ManageResumeVo();
				mres.setId(rs.getString("m_id"));
				mres.setResume_id(rs.getInt("resume_id"));
				mres.setIntroduce_title(rs.getString("introduce_title"));
				mres.setIs_post(rs.getInt("is_post"));
				mres.setMember_type(rs.getString("member_type"));
				mres.setPri_resume(rs.getString("pri_resume").charAt(0));
				mres.setProfile_image_src(rs.getString("profile_image_src"));
				mres.setAchievement(rs.getString("achievement"));
				mres.setDisability(rs.getInt("disability"));
				mres.setMiltary_service(rs.getInt("miltary_service"));
				mres.setCareer(rs.getInt("career"));
				mres.setBusiness_type("business_type");
				mres.setWorkable_days(rs.getDate("workable_days"));
				mres.setName(rs.getString("name"));
				mres.setBirth(rs.getString("birth"));
				mres.setPhone(rs.getString("phone"));
				mres.setEmail(rs.getString("email"));
				mres.setAddress(rs.getString("address"));
				mres.setAddressDetail(rs.getString("address_detail"));
				mres.setIntroduce(rs.getString("introduce"));
				mres.setWorkTime(rs.getString("work_time"));
				mres.setGender(rs.getString("gender").charAt(0));
				mres.setDelflag(rs.getInt("DELFLAG"));
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return mres;
	}
	
}
