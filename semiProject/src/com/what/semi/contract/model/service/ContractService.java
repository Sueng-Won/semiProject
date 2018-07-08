package com.what.semi.contract.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.contract.model.dao.ContractDao;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.recruitment.model.dao.RecruitmentDao;

public class ContractService {
	public ArrayList<ContractVo> selectMyContractList(int currentPage, int limit, String id) {
		Connection con = JDBCTemplate.getConnection();

		ArrayList<ContractVo> list = new ContractDao().selectMyContractList(con,id, currentPage, limit);

		JDBCTemplate.close(con);

		return list;
	}

	public int myContractListTotalCount(String id) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().selectMyConListTotalCount(con, id);

		JDBCTemplate.close(con);

		return result;
	}

	public int updateContractState(int contId, int state) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().updateContractState(con, contId,state);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int insertContract(String recId, String bo_id, String js_id, int resumeId, String demander) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().insertContract(con,recId, bo_id, js_id, resumeId,demander);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public ContractVo selectThisCon(String recId, String bo_id, String js_id, int resumeId) {
		Connection con = JDBCTemplate.getConnection();

		ContractVo thisCon = new ContractDao().selectThisCon(con,recId, bo_id, js_id, resumeId);

		JDBCTemplate.close(con);

		return thisCon;
	}

	public ArrayList<ContractVo> selectmyAppliedConList(String recId, String js_id) {
		Connection con = JDBCTemplate.getConnection();

		ArrayList<ContractVo> list = new ContractDao().selectMyContractList(con,recId, js_id);

		JDBCTemplate.close(con);

		return list;
	}

	public int updateExpriedContractState() {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().updateExpriedContractState(con);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int updateContractDate(int contId) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().updateContractDate(con, contId);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public ContractVo selectContract(int c_no) {
		Connection con = JDBCTemplate.getConnection();

		ContractVo cont = new ContractDao().selectContract(con,c_no);

		JDBCTemplate.close(con);

		return cont;
	}

	public int writeStartTime(int c_no) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().updateStartTime(con, c_no);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int writeEndTime(int c_no) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().updateEndTime(con, c_no);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int contractCurrentPage(String js_id, int contId) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().selectContractCurrentPage(con,js_id, contId);

		JDBCTemplate.close(con);

		return result;
	}
}
