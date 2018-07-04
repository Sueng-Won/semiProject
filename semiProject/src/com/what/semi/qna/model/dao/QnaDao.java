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
			pstmt.setInt(2, currentPage);
			pstmt.setInt(3, limit);
			
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
}
