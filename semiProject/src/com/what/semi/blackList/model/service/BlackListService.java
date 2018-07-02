package com.what.semi.blackList.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.blackList.model.dao.BlackListDao;
import com.what.semi.blackList.model.vo.BlackListVo;
import com.what.semi.blackList.model.vo.ConditionVo;
import com.what.semi.blackList.model.vo.ReportVo;
import com.what.semi.common.template.JDBCTemplate;

public class BlackListService {

	public int selectBlackListTotalCount(ConditionVo condition) {
		Connection con = JDBCTemplate.getConnection();
		
		int listCount = new BlackListDao().selectBlackListTotalCount(con, condition);
		
		JDBCTemplate.close(con);
		return listCount;
	}

	public ArrayList<BlackListVo> loadBlackList(int currentPage, int limit, ConditionVo condition) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<BlackListVo> list = null;
		ArrayList<ReportVo> rv = null;
		list = new BlackListDao().loadBlackList(con, currentPage, limit, condition);
		if(null != list) {
			for(BlackListVo blv : list) {
				rv = new BlackListDao().loadBlackListDetail(con, blv);
				blv.setReport(rv);
			}
		}
		JDBCTemplate.close(con);
		return list;
	}

}
