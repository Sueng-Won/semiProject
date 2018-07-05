package com.what.semi.contract.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.contract.model.dao.ContractDao;
import com.what.semi.contract.model.vo.ContractConditionVo;
import com.what.semi.contract.model.vo.ContractVo;

public class ContractService {

	public void insertContract(ContractVo cv) {
		Connection con = JDBCTemplate.getConnection();
		int result;
		
		//이력서/구인게시글의 데이터를 가지고있는 member의 id를 조회하여 list로 가져옴
		ArrayList<String> ids = new ContractDao().selectContractMember(con, cv);
		
		//해당하는 아이디를 contract에 적용
		for(String m_id : ids) {
			result = -1;
			cv.setM_id(m_id);
			result = new ContractDao().insertContract(con, cv);
			if(0 < result) {
				JDBCTemplate.commit(con);
			}else {
				JDBCTemplate.rollback(con);
			}
		}
		
		JDBCTemplate.close(con);
	}

	public void updateContract(ContractVo cv, ContractConditionVo ccv) {
		Connection con = JDBCTemplate.getConnection();
		int result;
		
		//이력서/구인게시글의 데이터를 가지고있는 member의 id를 조회하여 list로 가져옴
		ArrayList<Integer> c_nos = new ContractDao().selectContractList(con, cv);
		
		//해당하는 아이디를 contract에 적용
		for(int c_no : c_nos) {
			result = -1;
			cv.setC_no(c_no);
			result = new ContractDao().updateContract(con, cv, ccv);
			if(0 < result) {
				JDBCTemplate.commit(con);
			}else {
				JDBCTemplate.rollback(con);
			}
		}
		
		JDBCTemplate.close(con);
		
	}

}
