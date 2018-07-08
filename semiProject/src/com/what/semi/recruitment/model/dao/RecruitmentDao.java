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
			query = "select RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, ZIPCODE, "
					+ "WORK_DAY, R_LATITUDE, R_LONGITUDE,START_TIME, "
					+ "END_TIME, PAY, GENDER, MILITARY_SERVICE, INTRODUCE, M_ID, IS_POST,DELFLAG,"
					+ "RECRUITMENT_NAME, RECRUITMENT_PHONE,RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,"
					+ "NAME,PHONE FROM (SELECT ROWNUM RNUM, P.* FROM "
					+ "(SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, r.ADDRESS, r.ADDRESS_DETAIL, r.ZIPCODE,"
					+ " WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME,"
					+ " PAY,r.GENDER, MILITARY_SERVICE, INTRODUCE,m. M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE,"
					+ "RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,M.NAME,M.PHONE "
					+ "FROM RECRUITMENT R JOIN MEMBER M ON (M.M_ID = R.M_ID) "
					+ "WHERE IS_POST != 0 AND DELFLAG = 0 ORDER BY WORK_DAY DESC) P) WHERE RNUM BETWEEN " + startRow
					+ " AND " + endRow;
			// System.out.println(query);
			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo rec = null;
			while (rs.next()) {
				rec = new RecruitmentVo();
				rec.setRecruitment_id(rs.getString("RECRUITMENT_ID"));
				rec.setRecruitment_image_src(rs.getString("RECRUITMENT_IMAGE_SRC"));
				rec.setRecruitment_name(rs.getString("recruitment_name"));
				rec.setRecruitment_title(rs.getString("recruitment_title"));
				rec.setAddress(rs.getString("address"));
				rec.setAddress_detail(rs.getString("address_detail"));
				rec.setBusiness_type(rs.getString("business_type"));
				rec.setR_latitude(rs.getDouble("r_latitude"));
				rec.setR_longitude(rs.getDouble("r_longitude"));
				rec.setPay(rs.getInt("pay"));
				rec.setWork_day(rs.getDate("work_day"));
				rec.setStart_work_time(rs.getString("start_time"));
				rec.setEnd_work_time(rs.getString("end_time"));
				rec.setGender(rs.getString("gender").charAt(0));
				rec.setMilitary_service(rs.getInt("military_service"));
				rec.setIntroduce(rs.getString("introduce"));
				rec.setM_id(rs.getString("m_id"));
				rec.setRecruitment_phone(rs.getString("RECRUITMENT_PHONE"));
				rec.setRecruitment_email(rs.getString("RECRUITMENT_email"));
				rec.setZipcode(rs.getString("zipcode"));
				rec.setAchievement(rs.getString("achievement"));
				rec.setCareer(rs.getInt("career"));
				rec.setName(rs.getString("name"));
				rec.setPhone(rs.getString("phone"));
				rec.setIs_post(rs.getInt("is_post"));
				rec.setDelflag(rs.getInt("delflag"));

				list.add(rec);
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
			query = "SELECT COUNT(*) AS LISTCOUNT " + "FROM RECRUITMENT " + "WHERE IS_POST != 0 " + "AND DELFLAG = 0 "
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
			query = "SELECT COUNT(*) AS LISTCOUNT " + "FROM RECRUITMENT " + "WHERE (R_LATITUDE > ? AND R_LATITUDE < ?) "
					+ "AND (R_LONGITUDE > ? AND R_LONGITUDE < ?) " + "AND IS_POST != 0 " + "AND DELFLAG = 0 "
					+ "ORDER BY WORK_DAY";
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, minLatitude);
			pstmt.setDouble(2, maxLatitude);
			pstmt.setDouble(3, minLongitude);
			pstmt.setDouble(4, maxLongitude);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + "BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL,   "
					+ "ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE,  " + "START_WORK_TIME, END_WORK_TIME, PAY,  "
					+ "GENDER, MILITARY_SERVICE, INTRODUCE,  "
					+ "M_ID, IS_POST,DELFLAG, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE  "
					+ "FROM (SELECT ROWNUM RNUM, P.*  " + "FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC,  "
					+ "BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL,  "
					+ "R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE,  " + "START_WORK_TIME, END_WORK_TIME, PAY,  "
					+ "R.GENDER, MILITARY_SERVICE, INTRODUCE,  "
					+ "R.M_ID, IS_POST,DELFLAG, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE  " + "FROM RECRUITMENT R  "
					+ "JOIN MEMBER M ON (M.M_ID = R.M_ID)  " + "WHERE (IS_POST != 0  " + "AND DELFLAG =0 "
					+ "AND (R_LATITUDE > ? AND R_LATITUDE < ?)  " + "AND (R_LONGITUDE > ? AND R_LONGITUDE < ?)) "
					+ "ORDER BY WORK_DAY DESC) P)  " + "WHERE RNUM BETWEEN ? AND ?";
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
			while (rs.next()) {
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
			query = "select RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, ZIPCODE, "
					+ "WORK_DAY, R_LATITUDE, R_LONGITUDE,START_TIME, "
					+ "END_TIME, PAY, GENDER, MILITARY_SERVICE, INTRODUCE, M_ID, IS_POST,DELFLAG,"
					+ "RECRUITMENT_NAME, RECRUITMENT_PHONE,RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,"
					+ "NAME,PHONE FROM (SELECT ROWNUM RNUM, P.* FROM "
					+ "(SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, r.ADDRESS, r.ADDRESS_DETAIL, r.ZIPCODE,"
					+ " WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME,"
					+ " PAY,r.GENDER, MILITARY_SERVICE, INTRODUCE,m. M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE,"
					+ "RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,M.NAME,M.PHONE " + "FROM RECRUITMENT R "
					+ "JOIN MEMBER M ON (M.M_ID = R.M_ID) "
					+ "WHERE IS_POST != 0 AND DELFLAG =0 AND TO_CHAR(WORK_DAY,'MM/DD/RRRR') IN (" + dateStr + ")"
					+ "ORDER BY WORK_DAY DESC) P) " + "WHERE RNUM BETWEEN " + startRow + " AND " + endRow;
			// query = "SELECT * FROM RECRUITMENT";
			//System.out.println(query);

			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo rec = null;
			while (rs.next()) {
				rec = new RecruitmentVo();
				rec = new RecruitmentVo();
				rec.setRecruitment_id(rs.getString("RECRUITMENT_ID"));
				rec.setRecruitment_image_src(rs.getString("RECRUITMENT_IMAGE_SRC"));
				rec.setRecruitment_name(rs.getString("recruitment_name"));
				rec.setRecruitment_title(rs.getString("recruitment_title"));
				rec.setAddress(rs.getString("address"));
				rec.setAddress_detail(rs.getString("address_detail"));
				rec.setBusiness_type(rs.getString("business_type"));
				rec.setR_latitude(rs.getDouble("r_latitude"));
				rec.setR_longitude(rs.getDouble("r_longitude"));
				rec.setPay(rs.getInt("pay"));
				rec.setWork_day(rs.getDate("work_day"));
				rec.setStart_work_time(rs.getString("start_time"));
				rec.setEnd_work_time(rs.getString("end_time"));
				rec.setGender(rs.getString("gender").charAt(0));
				rec.setMilitary_service(rs.getInt("military_service"));
				rec.setIntroduce(rs.getString("introduce"));
				rec.setM_id(rs.getString("m_id"));
				rec.setRecruitment_phone(rs.getString("RECRUITMENT_PHONE"));
				rec.setRecruitment_email(rs.getString("RECRUITMENT_email"));
				rec.setZipcode(rs.getString("zipcode"));
				rec.setAchievement(rs.getString("achievement"));
				rec.setCareer(rs.getInt("career"));
				rec.setName(rs.getString("name"));
				rec.setPhone(rs.getString("phone"));
				rec.setIs_post(rs.getInt("is_post"));
				rec.setDelflag(rs.getInt("delflag"));

				// System.out.println(temp);
				list.add(rec);
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
					+ "WHERE IS_POST != 0 AND DELFLAG = 0 AND TO_CHAR(WORK_DAY,'MM/DD/RRRR') IN (" + dateStr + ")"
					+ "ORDER BY WORK_DAY DESC";
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

		String query = "INSERT INTO RECRUITMENT VALUES (SEQ_RECRUITMENT.NEXTVAL" + ",?,?,?,?," + "?,?,?,?,?,"
				+ "?,?,TO_DATE(?,'RRRR-MM-DD'),TO_DATE(?,'HH24-MI'),TO_DATE(?,'HH24-MI')," + "?,?,?,?,?," + "?,?,1,0)";
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, rec.getRecruitment_image_src());
			pstmt.setString(2, rec.getRecruitment_name());
			pstmt.setString(3, rec.getRecruitment_phone());
			pstmt.setString(4, rec.getRecruitment_email());
			pstmt.setString(5, rec.getAddress());
			pstmt.setString(6, rec.getAddress_detail());
			pstmt.setString(7, rec.getZipcode());
			pstmt.setDouble(8, rec.getR_latitude());
			pstmt.setDouble(9, rec.getR_longitude());
			pstmt.setString(10, rec.getBusiness_type());
			pstmt.setInt(11, rec.getCareer());
			pstmt.setDate(12, rec.getWork_day());
			pstmt.setString(13, rec.getStart_work_time());
			pstmt.setString(14, rec.getEnd_work_time());
			pstmt.setInt(15, rec.getPay());
			pstmt.setString(16, String.valueOf(rec.getGender()));
			pstmt.setInt(17, rec.getMilitary_service());
			pstmt.setString(18, rec.getAchievement());
			pstmt.setString(19, rec.getRecruitment_title());
			pstmt.setString(20, rec.getIntroduce());
			pstmt.setString(21, rec.getM_id());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectMyListTotalCount(Connection con, String id) {
		int result = -1;

		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			stmt = con.createStatement();
			query = "SELECT COUNT(*) AS LISTCOUNT " + "FROM RECRUITMENT "
					+ "WHERE IS_POST != 0 AND DELFLAG = 0 AND M_id='" + id + "'" + "ORDER BY WORK_DAY";
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

	public ArrayList<RecruitmentVo> selectMyRecList(Connection con, String id, int currentPage, int limit) {
		ArrayList<RecruitmentVo> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			stmt = con.createStatement();
			query = "select RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, ZIPCODE, "
					+ "WORK_DAY, R_LATITUDE, R_LONGITUDE,START_TIME, "
					+ "END_TIME, PAY, GENDER, MILITARY_SERVICE, INTRODUCE, M_ID, IS_POST,DELFLAG,"
					+ "RECRUITMENT_NAME, RECRUITMENT_PHONE,RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,"
					+ "NAME,PHONE FROM (SELECT ROWNUM RNUM, P.* FROM "
					+ "(SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, r.ADDRESS, r.ADDRESS_DETAIL, r.ZIPCODE,"
					+ " WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME,"
					+ " PAY,r.GENDER, MILITARY_SERVICE, INTRODUCE,m. M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE,"
					+ "RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,M.NAME,M.PHONE " + "FROM RECRUITMENT R "
					+ "JOIN MEMBER M ON (M.M_ID = R.M_ID) " + "WHERE R.M_ID='" + id + "' AND DELFLAG=0 " + "ORDER BY WORK_DAY DESC) P) "
					+ "WHERE RNUM BETWEEN " + startRow + " AND " + endRow;
			// query = "SELECT * FROM RECRUITMENT";
			//System.out.println(query);

			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo rec = null;
			while (rs.next()) {
				rec = new RecruitmentVo();
				rec = new RecruitmentVo();
				rec.setRecruitment_id(rs.getString("RECRUITMENT_ID"));
				rec.setRecruitment_image_src(rs.getString("RECRUITMENT_IMAGE_SRC"));
				rec.setRecruitment_name(rs.getString("recruitment_name"));
				rec.setRecruitment_title(rs.getString("recruitment_title"));
				rec.setAddress(rs.getString("address"));
				rec.setAddress_detail(rs.getString("address_detail"));
				rec.setBusiness_type(rs.getString("business_type"));
				rec.setR_latitude(rs.getDouble("r_latitude"));
				rec.setR_longitude(rs.getDouble("r_longitude"));
				rec.setPay(rs.getInt("pay"));
				rec.setWork_day(rs.getDate("work_day"));
				rec.setStart_work_time(rs.getString("start_time"));
				rec.setEnd_work_time(rs.getString("end_time"));
				rec.setGender(rs.getString("gender").charAt(0));
				rec.setMilitary_service(rs.getInt("military_service"));
				rec.setIntroduce(rs.getString("introduce"));
				rec.setM_id(rs.getString("m_id"));
				rec.setRecruitment_phone(rs.getString("RECRUITMENT_PHONE"));
				rec.setRecruitment_email(rs.getString("RECRUITMENT_email"));
				rec.setZipcode(rs.getString("zipcode"));
				rec.setAchievement(rs.getString("achievement"));
				rec.setCareer(rs.getInt("career"));
				rec.setName(rs.getString("name"));
				rec.setPhone(rs.getString("phone"));
				rec.setIs_post(rs.getInt("is_post"));
				rec.setDelflag(rs.getInt("delflag"));

				// System.out.println(temp);
				list.add(rec);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}

		return list;
	}

	public RecruitmentVo selectRecruitment(Connection con, String recId) {
		RecruitmentVo rec = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			stmt = con.createStatement();
			query = "select RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, r.ADDRESS, r.ADDRESS_DETAIL,r. ZIPCODE, "
					+ "WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME,"
					+ " PAY, r.GENDER, MILITARY_SERVICE, INTRODUCE, m.M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE,RECRUITMENT_EMAIL,"
					+ "RECRUITMENT_TITLE,ACHIEVEMENT,CAREER ,m.name,m.phone" + " FROM RECRUITMENT r "
					+ "join MEMBER m on (m.m_id=r.m_id)" + " where recruitment_id='" + recId + "'";
			// query = "SELECT * FROM RECRUITMENT";
			// System.out.println(query);

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				rec = new RecruitmentVo();
				rec.setRecruitment_id(rs.getString("RECRUITMENT_ID"));
				rec.setRecruitment_image_src(rs.getString("RECRUITMENT_IMAGE_SRC"));
				rec.setRecruitment_name(rs.getString("recruitment_name"));
				rec.setRecruitment_title(rs.getString("recruitment_title"));
				rec.setAddress(rs.getString("address"));
				rec.setAddress_detail(rs.getString("address_detail"));
				rec.setBusiness_type(rs.getString("business_type"));
				rec.setR_latitude(rs.getDouble("r_latitude"));
				rec.setR_longitude(rs.getDouble("r_longitude"));
				rec.setPay(rs.getInt("pay"));
				rec.setWork_day(rs.getDate("work_day"));
				rec.setStart_work_time(rs.getString("start_time"));
				rec.setEnd_work_time(rs.getString("end_time"));
				rec.setGender(rs.getString("gender").charAt(0));
				rec.setMilitary_service(rs.getInt("military_service"));
				rec.setIntroduce(rs.getString("introduce"));
				rec.setM_id(rs.getString("m_id"));
				rec.setRecruitment_phone(rs.getString("RECRUITMENT_PHONE"));
				rec.setRecruitment_email(rs.getString("RECRUITMENT_email"));
				rec.setZipcode(rs.getString("zipcode"));
				rec.setAchievement(rs.getString("achievement"));
				rec.setCareer(rs.getInt("career"));
				rec.setName(rs.getString("name"));
				rec.setPhone(rs.getString("phone"));
				rec.setIs_post(rs.getInt("is_post"));
				rec.setDelflag(rs.getInt("delflag"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}

		return rec;
	}

	public int selectMachingListTotalCount(Connection con, MyResumeVo myResumeVo) {
		int result = -1;
		String businessType = myResumeVo.getBusiness_type();
		Date workableDay = myResumeVo.getWorkable_days();
		String gender = String.valueOf(myResumeVo.getGender());
		int miltaryService = myResumeVo.getMiltary_service();
		System.out.println(businessType + " / " + workableDay + " / " + gender + " / " + miltaryService);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		try {
			query = "SELECT COUNT(*) AS LISTCOUNT " + "FROM RECRUITMENT " + "WHERE (IS_POST != 0 " + "AND DELFLAG = 0"
					+ "AND BUSINESS_TYPE = ? " + "AND WORK_DAY = ? " + "AND GENDER = ? " + "AND MILITARY_SERVICE = ?)"
					+ "ORDER BY WORK_DAY";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, businessType);
			pstmt.setDate(2, workableDay);
			pstmt.setString(3, gender);
			pstmt.setInt(4, miltaryService);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
		String businessType = myResumeVo.getBusiness_type();
		Date workableDay = myResumeVo.getWorkable_days();
		String gender = String.valueOf(myResumeVo.getGender());
		int miltaryService = myResumeVo.getMiltary_service();
		ArrayList<RecruitmentVo> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, " + "BUSINESS_TYPE, ADDRESS, ADDRESS_DETAIL, "
					+ "ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, " + "START_WORK_TIME, END_WORK_TIME, PAY, "
					+ "GENDER, MILITARY_SERVICE, INTRODUCE, "
					+ "M_ID, IS_POST,DELFLAG, NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE "
					+ "FROM (SELECT ROWNUM RNUM, P.* " + "FROM (SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, "
					+ "BUSINESS_TYPE, R.ADDRESS, R.ADDRESS_DETAIL, " + "R.ZIPCODE, WORK_DAY, R_LATITUDE, R_LONGITUDE, "
					+ "START_WORK_TIME, END_WORK_TIME, PAY, " + "R.GENDER, MILITARY_SERVICE, INTRODUCE, "
					+ "R.M_ID, IS_POST,DELFLAG, M.NAME, RECRUITMENT_NAME, RECRUITMENT_TITLE " + "FROM RECRUITMENT R "
					+ "JOIN MEMBER M ON (M.M_ID = R.M_ID) " + "WHERE (IS_POST != 0 " + "AND DELFLAG = 0 "
					+ "AND BUSINESS_TYPE = ? " + "AND WORK_DAY = ? " + "AND R.GENDER = ? "
					+ "AND MILITARY_SERVICE = ? )" + "ORDER BY WORK_DAY DESC) P) " + "WHERE RNUM BETWEEN ? AND ?";
			// System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, businessType);
			pstmt.setDate(2, workableDay);
			pstmt.setString(3, gender);
			pstmt.setInt(4, miltaryService);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			rs = pstmt.executeQuery();
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo temp = null;
			while (rs.next()) {
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

	public int updateRecruitment(Connection con, RecruitmentVo rec) {
		int result = -1;

		PreparedStatement pstmt = null;

		String query = "UPDATE RECRUITMENT SET"
				+ " Recruitment_image_src=?,Recruitment_name=?,Recruitment_phone=?,Recruitment_email=?,"
				+ "Address=?,Address_detail=?,Zipcode=?,R_latitude=?,R_longitude=?,"
				+ "Business_type=?,Career=?,Work_day=TO_DATE(?,'RRRR-MM-DD'),Start_work_time=TO_DATE(?,'HH24-MI'),End_work_time=TO_DATE(?,'HH24-MI'),"
				+ "Pay=?,Gender=?,Military_service=?,Achievement=?,Recruitment_title=?,"
				+ "Introduce=?,M_id=? where recruitment_id=?";
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, rec.getRecruitment_image_src());
			pstmt.setString(2, rec.getRecruitment_name());
			pstmt.setString(3, rec.getRecruitment_phone());
			pstmt.setString(4, rec.getRecruitment_email());
			pstmt.setString(5, rec.getAddress());
			pstmt.setString(6, rec.getAddress_detail());
			pstmt.setString(7, rec.getZipcode());
			pstmt.setDouble(8, rec.getR_latitude());
			pstmt.setDouble(9, rec.getR_longitude());
			pstmt.setString(10, rec.getBusiness_type());
			pstmt.setInt(11, rec.getCareer());
			pstmt.setDate(12, rec.getWork_day());
			pstmt.setString(13, rec.getStart_work_time());
			pstmt.setString(14, rec.getEnd_work_time());
			pstmt.setInt(15, rec.getPay());
			pstmt.setString(16, String.valueOf(rec.getGender()));
			pstmt.setInt(17, rec.getMilitary_service());
			pstmt.setString(18, rec.getAchievement());
			pstmt.setString(19, rec.getRecruitment_title());
			pstmt.setString(20, rec.getIntroduce());
			pstmt.setString(21, rec.getM_id());
			pstmt.setString(22, rec.getRecruitment_id());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateRecIsPost(Connection con) {
		int result= -1;
		Statement stmt = null;
		String query = null;
		
		try {
			stmt=con.createStatement();
			query = "UPDATE RECRUITMENT SET IS_POST=0 WHERE WORK_DAY<SYSDATE";
			result=stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(stmt);
		}
		return result;
	}

	public int deleteRec(Connection con, String recId) {
		int result = -1;

		PreparedStatement pstmt = null;

		String query = "UPDATE RECRUITMENT SET DELFLAG = 1 WHERE RECRUITMENT_ID = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, recId);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<RecruitmentVo> loadSameBusiness(Connection con, String m_id) {
		ArrayList<RecruitmentVo> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			stmt = con.createStatement();
			query = "SELECT RECRUITMENT_ID, RECRUITMENT_IMAGE_SRC, BUSINESS_TYPE, r.ADDRESS, r.ADDRESS_DETAIL, r.ZIPCODE,"
					+ " WORK_DAY, R_LATITUDE, R_LONGITUDE, TO_CHAR(START_WORK_TIME,'HH24:MI') AS START_TIME, TO_CHAR(END_WORK_TIME,'HH24:MI') AS END_TIME,"
					+ " PAY,r.GENDER, MILITARY_SERVICE, INTRODUCE,m. M_ID, IS_POST,DELFLAG, RECRUITMENT_NAME, RECRUITMENT_PHONE,"
					+ "RECRUITMENT_EMAIL,RECRUITMENT_TITLE,ACHIEVEMENT,CAREER,M.NAME,M.PHONE " + "FROM RECRUITMENT R "
					+ "JOIN MEMBER M ON (M.M_ID = R.M_ID) " + "WHERE R.M_ID='" + m_id + "' AND DELFLAG=0 AND IS_POST=1 " + "ORDER BY WORK_DAY DESC";
			// query = "SELECT * FROM RECRUITMENT";
			//System.out.println(query);

			rs = stmt.executeQuery(query);
			list = new ArrayList<RecruitmentVo>();
			RecruitmentVo rec = null;
			while (rs.next()) {
				rec = new RecruitmentVo();
				rec = new RecruitmentVo();
				rec.setRecruitment_id(rs.getString("RECRUITMENT_ID"));
				rec.setRecruitment_image_src(rs.getString("RECRUITMENT_IMAGE_SRC"));
				rec.setRecruitment_name(rs.getString("recruitment_name"));
				rec.setRecruitment_title(rs.getString("recruitment_title"));
				rec.setAddress(rs.getString("address"));
				rec.setAddress_detail(rs.getString("address_detail"));
				rec.setBusiness_type(rs.getString("business_type"));
				rec.setR_latitude(rs.getDouble("r_latitude"));
				rec.setR_longitude(rs.getDouble("r_longitude"));
				rec.setPay(rs.getInt("pay"));
				rec.setWork_day(rs.getDate("work_day"));
				rec.setStart_work_time(rs.getString("start_time"));
				rec.setEnd_work_time(rs.getString("end_time"));
				rec.setGender(rs.getString("gender").charAt(0));
				rec.setMilitary_service(rs.getInt("military_service"));
				rec.setIntroduce(rs.getString("introduce"));
				rec.setM_id(rs.getString("m_id"));
				rec.setRecruitment_phone(rs.getString("RECRUITMENT_PHONE"));
				rec.setRecruitment_email(rs.getString("RECRUITMENT_email"));
				rec.setZipcode(rs.getString("zipcode"));
				rec.setAchievement(rs.getString("achievement"));
				rec.setCareer(rs.getInt("career"));
				rec.setName(rs.getString("name"));
				rec.setPhone(rs.getString("phone"));
				rec.setIs_post(rs.getInt("is_post"));
				rec.setDelflag(rs.getInt("delflag"));

				// System.out.println(temp);
				list.add(rec);
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