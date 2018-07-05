package com.what.semi.contract.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.what.semi.contract.model.vo.ContractConditionVo;
import com.what.semi.contract.model.vo.ContractVo;

public class ContractDao {

	public ArrayList<String> selectContractMember(Connection con, ContractVo cv) {
		ArrayList<String> ids = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT M.M_ID FROM MEMBER M "
				+ "WHERE M.M_ID IN (SELECT R1.M_ID FROM RECRUITMENT R1 WHERE RECRUITMENT_ID = ?) "
				+ "OR M.M_ID IN (SELECT R2.M_ID FROM RESUME R2 WHERE RESUME_ID = ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, cv.getRecruitment_id());
			pstmt.setString(2, cv.getResume_id());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ids.add(rs.getString("m_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public int insertContract(Connection con, ContractVo cv) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CONTRACT "
				+ "VALUES(CONTRACT_SEQ.NEXTVAL,0,SYSDATE,DEFAULT,DEFAULT,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cv.getRecruitment_id());
			pstmt.setString(2, cv.getM_id());
			pstmt.setString(3, cv.getResume_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Integer> selectContractList(Connection con, ContractVo cv) {
		ArrayList<Integer> c_nos = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT C_NO FROM CONTRACT WHERE RECRUITMENT_ID = ? AND RESUME_ID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, cv.getRecruitment_id());
			pstmt.setString(2, cv.getResume_id());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				c_nos.add(rs.getInt("c_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c_nos;
	}

	public int updateContract(Connection con, ContractVo cv, ContractConditionVo ccv) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = "UPDATE CONTRACT SET "
				+ "C_DATE = SYSDATE ";
		
		//넘어온 조회 조건의 유무를 판단하여 쿼리를 추가
		if(null != ccv.getStart_work_time()) {		//시작시간 입력(sysdate)
			query += ", START_WORK_TIME = SYSDATE ";
			
		}
		if(null != ccv.getEnd_work_time()) {		//종료시간 입력(sysdate)
			query += ", END_WORK_TIME = SYSDATE ";
			
		}
		if(-1 != ccv.getState()) {					//계약상태 입력
			query += ", STATE = ? ";
		}
		
		query += "WHERE C_NO = ?";
				
		
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			if(-1 != ccv.getState()) {
				pstmt.setInt(1, ccv.getState());
				pstmt.setInt(2, cv.getC_no());
			}else {
				pstmt.setInt(1, cv.getC_no());
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
