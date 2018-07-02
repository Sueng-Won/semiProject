package com.what.semi.blackList.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.what.semi.blackList.model.vo.BlackListVo;
import com.what.semi.blackList.model.vo.ConditionVo;
import com.what.semi.blackList.model.vo.ReportVo;
import com.what.semi.common.template.JDBCTemplate;

public class BlackListDao {

	public int selectBlackListTotalCount(Connection con, ConditionVo condition) {
		int result = -1;
		
		String isReport = condition.getIsReport();			//신고 유무를 저장할 변수 선언
		String memberType = condition.getMemberType();		//회원 타입을 저장할 변수 선언
		String keyword = condition.getKeyword();			//검색할 회원명을 저장할 변수 선언
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		
		try {
			query = "SELECT COUNT(*) AS LISTCOUNT " + 
					"FROM (SELECT * " + 
					"FROM MEMBER M " + 
					"WHERE MEMBER_TYPE LIKE '%'|| ? || '%' " + 
					"AND M.NAME LIKE '%'|| ? || '%' ";
			
			switch (isReport) {  //신고 유무를 확인하는 switch문
			case "O":	//신고된 회원
				query += "AND M_ID IN (SELECT M_ID " + 
						"FROM BLACKLIST)";
				break;
			case "X":	//신고되지 않은 회원
				query += "AND M_ID NOT IN (SELECT M_ID " + 
						"FROM BLACKLIST)";
				break;
			}
			query += "ORDER BY M.NAME)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberType);
			pstmt.setString(2, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("listcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<BlackListVo> loadBlackList(Connection con, int currentPage, int limit, ConditionVo condition) {
		ArrayList<BlackListVo> list = null;
		
		String isReport = condition.getIsReport();			//신고 유무를 저장할 변수 선언
		String memberType = condition.getMemberType();		//회원 타입을 저장할 변수 선언
		String keyword = condition.getKeyword();			//검색할 회원명을 저장할 변수 선언
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		
		try {
			query = "SELECT M_ID, MEMBER_TYPE, NAME, TOTALCOUNT " + 
					"FROM(SELECT ROWNUM RNUM, P.* " + 
					"FROM (SELECT M.M_ID, M.MEMBER_TYPE, M.NAME, " + 
					"NVL((SELECT SUM(COUNT) FROM BLACKLIST B WHERE M.M_ID = B.M_ID),0) AS TOTALCOUNT " + 
					"FROM MEMBER M " + 
					"WHERE MEMBER_TYPE LIKE '%'|| ? || '%' " + 
					"AND M.NAME LIKE '%'|| ? || '%' ";
			
			switch (isReport) {  //신고 유무를 확인하는 switch문
			case "O":	//신고된 회원
				query += "AND M.M_ID IN (SELECT M_ID FROM BLACKLIST) ";
				break;
			case "X":	//신고되지 않은 회원
				query += "AND M.M_ID NOT IN (SELECT M_ID FROM BLACKLIST) ";
				break;
			}
			
			query += "ORDER BY M.NAME) P) " + 
					"WHERE RNUM BETWEEN ? AND ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberType);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, currentPage);
			pstmt.setInt(4, limit);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<BlackListVo>();
			BlackListVo temp;
			while(rs.next()) {
				temp = new BlackListVo(rs.getString("m_id"), 
						rs.getString("member_type"), 
						rs.getString("name"), 
						rs.getInt("totalcount"));
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

	public ArrayList<ReportVo> loadBlackListDetail(Connection con, BlackListVo blv) {
		ArrayList<ReportVo> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		String id = blv.getM_id();
		try {
			query = "SELECT REASON, COUNT, B_DATE " + 
					"FROM BLACKLIST " + 
					"WHERE M_ID = ? " + 
					"ORDER BY REASON";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ReportVo>();
			ReportVo temp = null;
			while(rs.next()) {
				temp = new ReportVo(rs.getString("reason"), 
						rs.getInt("COUNT"), 
						rs.getDate("B_DATE"));
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
