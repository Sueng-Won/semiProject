package com.what.semi.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.what.semi.common.filter.Sha512;
import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.vo.MemberVo;
//adsf
public class MemberDao {

	public int checkId(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT M_ID FROM MEMBER WHERE M_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("M_ID")!=null) {
					result = 1;
				}
				else {
					result = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	


	public int signIn(Connection conn, MemberVo member) {
		int result = 0;
		MemberVo mv = member;
		//System.out.println("mv = "+mv.toString());
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
		try {
			query = "INSERT INTO MEMBER "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mv.getId());
			//pstmt.setString(2, mv.getPw());
			pstmt.setString(2, Sha512.getSha512(mv.getPw()));
			pstmt.setString(3, mv.getName());
			pstmt.setString(4, mv.getBirth());
			pstmt.setString(5, mv.getPhone());
			pstmt.setString(6, mv.getEmail());
			pstmt.setString(7, mv.getAddress());
			pstmt.setString(8, mv.getAddress_detail());
			pstmt.setString(9, mv.getZipcode());
			pstmt.setString(10, mv.getMember_type());
			pstmt.setDouble(11, mv.getLatitude());
			pstmt.setDouble(12, mv.getLongitude());
			pstmt.setString(13, String.valueOf(mv.getGender()));
			pstmt.setInt(14, mv.getIs_black_list());
			
			pstmt.executeUpdate();
			conn.commit();
			result = 1;
			System.out.println("INSERT 성공");
		} catch (SQLException e) {
			System.out.println("INSERT 실패");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("롤백 할 수 없습니다.");
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int logIn(Connection conn, String id, String pw) {
		int result = 0;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT M_ID FROM MEMBER WHERE M_ID = ? AND PW = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("M_ID")!=null) {
					result = 1;
				}
				else {
					result = 0;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("롤백 할 수 없습니다.");
				e1.printStackTrace();
			}
		} finally {
			JDBCTemplate.close(rs);
		}
		
		return result;
	}

	public int drop(Connection conn, String id) {
		
		int result = 0;
		//System.out.println("mv = "+mv.toString());
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
		try {
			query = "DELETE FROM MEMBER "
					+ "WHERE M_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			result = 1;
			System.out.println("DELETE 성공");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DELETE 실패");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("롤백 할 수 없습니다.");
				e1.printStackTrace();
			}
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}



	public int checkPw(Connection conn,String id , String pw) {
		int result = 0;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT PW FROM MEMBER WHERE M_ID = ? AND PW = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("PW")!=null) {
					result = 1;
				}
				else {
					result = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}



	public MemberVo getMember(Connection conn, String id) {
		MemberVo member = new MemberVo();
		
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT * FROM MEMBER WHERE M_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("M_ID")!=null) {
					member.setName(rs.getString("NAME"));
					member.setEmail(rs.getString("EMAIL"));
					member.setPhone(rs.getString("PHONE"));
					member.setZipcode(rs.getString("ZIPCODE"));
					member.setLatitude(rs.getDouble("LATITUDE"));
					member.setLongitude(rs.getDouble("LONGITUDE"));
					member.setAddress(rs.getString("ADDRESS"));
					member.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
					member.setMember_type(rs.getString("MEMBER_TYPE"));
				}
				else {
					member = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}



	public int updateMember(Connection conn, MemberVo m) {
		int result = 0;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		String query = "";
		
		try {
			query = "UPDATE MEMBER SET NAME = ?, "
					+ "EMAIL = ?, "
					+ "PHONE = ?, "
					+ "ADDRESS = ?, "
					+ "ADDRESS_DETAIL = ?, "
					+ "ZIPCODE = ?, "
					+ "LATITUDE = ?, "
					+ "LONGITUDE = ?, "
					+ "MEMBER_TYPE = ? "
					+ "WHERE M_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getAddress_detail());
			pstmt.setString(6, m.getZipcode());
			pstmt.setDouble(7, m.getLatitude());
			pstmt.setDouble(8, m.getLongitude());
			pstmt.setString(9, m.getMember_type());
			pstmt.setString(10, m.getId());
			pstmt.executeUpdate();
			result = 1;
			System.out.println("UPDATE 성공");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("UPDATE 실패");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("롤백 할 수 없습니다.");
				e1.printStackTrace();
			}
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}



	public String searchId(Connection conn, String email) {
		String resultId = null;
		PreparedStatement pstmt = null;	//SQL문을 나타내는 객체
		ResultSet rs = null;
		String query = "";
		
		try {
			query = "SELECT M_ID FROM MEMBER WHERE EMAIL = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("M_ID")!=null) {
					resultId = rs.getString("M_ID");
				}
				else {
					resultId = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return resultId;
	}
}