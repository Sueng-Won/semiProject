package com.what.semi.qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.PageInfo;
import com.what.semi.qna.model.vo.QnaVo;

public class QnaDao {
	

	public int insertQna(Connection con, String content, String category, String id) {
		int result = 0;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
			query = "INSERT INTO QNA "
					+ "VALUES(QNA_seq.NEXTVAL,?,?,SYSDATE,DEFAULT,?)";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, content);
				pstmt.setString(2, category);
				pstmt.setString(3, id);
				
				result = pstmt.executeUpdate();
				
				System.out.println("INSERT 성공");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		return result;
	}

	public ArrayList<QnaVo> selectQna(Connection con, String id, PageInfo pi) {
		ArrayList<QnaVo> result = new ArrayList<QnaVo>();
		int currentPage = pi.getCurrentPage();
		int limit = pi.getLimit();
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "SELECT * FROM " + 
				"(SELECT ROWNUM RNUM, P.* " + 
				"FROM(SELECT Q_NO, CONTENT, CATEGORY, REPORTING_DATE, M_ID " + 
				"FROM QNA " + 
				"WHERE M_ID = ? " + 
				"ORDER BY REPORTING_DATE) P) " + 
				"WHERE RNUM BETWEEN ? AND ? ";	
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			QnaVo temp = null;
			while(rs.next()) {
				temp = new QnaVo();
				temp.setQ_no(rs.getInt("q_no"));
				temp.setContent(rs.getString("content"));
				temp.setCategory(rs.getString("category"));
				temp.setReporting_date(rs.getDate("reporting_date"));
				temp.setM_id(id);
				
				result.add(temp);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int QnaCount(Connection con, String id) {
		int result = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
			query = "SELECT COUNT(*) AS LISTCOUNT "
					+ "FROM QNA " 
					+ "WHERE M_ID = ? ";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					result = rs.getInt("listcount");
				}
				
				System.out.println("SELECT 성공");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		return result;
	}

	public int membersQnaCount(Connection con, QnaVo qv) {
		int result = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
			query = "SELECT COUNT(*) AS LISTCOUNT "
					+ "FROM QNA Q "
					+ "JOIN MEMBER M ON (Q.M_ID = M.M_ID) ";
			
			if(null != qv.getM_id()) {
				query += "WHERE Q.M_ID LIKE '%' || ? || '%' OR M.NAME LIKE '%' || ? || '%'";
			}
			
			try {
				pstmt = con.prepareStatement(query);
				if(null != qv.getM_id()) {
					pstmt.setString(1, qv.getM_id());
					pstmt.setString(2, qv.getM_id());
				}
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					result = rs.getInt("listcount");
				}
				
				System.out.println("admin count 성공 -> "+result);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		return result;
	}

	public ArrayList<QnaVo> selectMembersQna(Connection con, PageInfo pi, QnaVo qv) {
		ArrayList<QnaVo> result = new ArrayList<QnaVo>();
		int currentPage = pi.getCurrentPage();
		int limit = pi.getLimit();
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		String m_id = qv.getM_id();
		String category = qv.getCategory();
		int is_checked = qv.getIs_checked();
		
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "SELECT * FROM " + 
				"(SELECT ROWNUM RNUM, P.* " + 
				"FROM(SELECT Q_NO, CONTENT, CATEGORY, REPORTING_DATE, Q.M_ID, IS_CHECKED, M.NAME, M.MEMBER_TYPE " + 
				"FROM QNA Q " + 
				"JOIN MEMBER M ON (M.M_ID = Q.M_ID) " + 
				"WHERE CATEGORY LIKE '%' || ? || '%' " + 
				"AND IS_CHECKED = ? ";
		
		if(null != qv.getM_id()) {
			query += "AND (NAME LIKE '%' || ? || '%' "
					+ "OR Q.M_ID LIKE '%' || ? || '%') ";
		}
		
		query += "ORDER BY REPORTING_DATE) P) " + 
				"WHERE RNUM BETWEEN ? AND ? ";	
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.setInt(2, is_checked);
			if(null != m_id) {
				pstmt.setString(3, m_id);
				pstmt.setString(4, m_id);
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
			}else {
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				qv = new QnaVo();
				qv.setQ_no(rs.getInt("q_no"));
				qv.setContent(rs.getString("content"));
				qv.setCategory(rs.getString("category"));
				qv.setReporting_date(rs.getDate("reporting_date"));
				qv.setName(rs.getString("name"));
				qv.setMember_type(rs.getString("member_type"));
				qv.setM_id(m_id);
				result.add(qv);	
			}
			System.out.println("admin select 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateAnswer(Connection con, String answer, int q_no) {
		int result = 0;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
			query = "UPDATE QNA SET ANSWER = ?, IS_CHECKED = 1 WHERE Q_NO = ?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, answer);
				pstmt.setInt(2, q_no);
				
				result = pstmt.executeUpdate();
				
				System.out.println("qna update 성공");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		return result;
	}
}
