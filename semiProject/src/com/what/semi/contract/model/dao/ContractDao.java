package com.what.semi.contract.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

public class ContractDao {

	public ArrayList<ContractVo> selectMyWorkedList(Connection con, int resume_id) {
		ArrayList<ContractVo> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		/*
		 * int startRow = (currentPage - 1) * limit + 1; int endRow = startRow +
		 * limit - 1;
		 */
		try {
			stmt = con.createStatement();
			query = "SELECT C_NO,STATE,C_DATE,START_WORK_TIME,END_WORK_TIME,RECRUITMENT_ID,C.M_ID,C.RESUME_ID "
					+ "FROM CONTRACT C JOIN RESUME R ON (R.RESUME_ID=C.RESUME_ID)" 
					+ "where c.resume_id=" + resume_id;
			// System.out.println(query);
			rs = stmt.executeQuery(query);
			list = new ArrayList<ContractVo>();
			ContractVo cont = null;
			while (rs.next()) {
				cont = new ContractVo();
				cont.setC_no(rs.getInt("c_no"));

				list.add(cont);
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
