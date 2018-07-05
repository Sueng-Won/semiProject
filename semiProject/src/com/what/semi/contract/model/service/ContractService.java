package com.what.semi.contract.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.contract.model.dao.ContractDao;
import com.what.semi.contract.model.vo.ContractVo;

public class ContractService {
	public ArrayList<ContractVo> selectMyWorkedList(String id) {
		Connection con = JDBCTemplate.getConnection();

		ArrayList<ContractVo> list = new ContractDao().selectMyWorkedList(con,id);

		JDBCTemplate.close(con);

		return list;
	}
}
