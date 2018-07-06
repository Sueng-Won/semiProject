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

	public int insertContract(String recId, String bo_id, String js_id, int resumeId) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ContractDao().insertContract(con,recId, bo_id, js_id, resumeId);

		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}
}
