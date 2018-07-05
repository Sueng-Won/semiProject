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

	public ArrayList<ContractVo> selectMyWorkedList(Connection con, String id) {
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
			query = "SELECT C_NO,STATE,C_DATE,START_WORK_TIME,END_WORK_TIME,RECRUITMENT_ID,BO_ID,JS_ID,RESUME_ID "
					+ "FROM CONTRACT where JS_ID=" + id
					+"order by c_DATE";
			// System.out.println(query);
			rs = stmt.executeQuery(query);
			list = new ArrayList<ContractVo>();
			ContractVo cont = null;
			while (rs.next()) {
				cont = new ContractVo();
				cont.setC_no(rs.getInt("c_no"));
				cont.setState(rs.getInt("state"));
				cont.setContract_date(rs.getDate("c_date"));
				cont.setStart_work_time(rs.getString("start_work_time"));
				cont.setEnd_work_time(rs.getString("end_work_time"));
				cont.setRecruitment_id(rs.getString("recruitment_id"));
				cont.setBo_id(rs.getString("bo_id"));
				cont.setJs_id(rs.getString("js_id"));
				cont.setResume_id(rs.getInt("resume_id"));

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
