package com.what.semi.recruitment.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.recruitment.model.dao.RecruitmentDao;
import com.what.semi.recruitment.model.vo.RecruitmentVo;


public class RecruitmentService {
	
	public ArrayList<RecruitmentVo> loadRecruitmentList(int currentPage, int limit, String keyword) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().loadRecruitmentList(con, currentPage, limit);
		JDBCTemplate.close(con);
		
		return list;
	}
	
	public ArrayList<RecruitmentVo> loadRecruitmentList(int currentPage, int limit) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().loadRecruitmentList(con, currentPage, limit);
		JDBCTemplate.close(con);
		return list;
	}

	public int selectIndexListTotalCount() {	//검색 조건이 없을경우 게시물조회에 사용되는 메소드
		Connection con = JDBCTemplate.getConnection();
		
		int ListCount = new RecruitmentDao().selectIndexListTotalCount(con);
		
		JDBCTemplate.close(con);
		
		return ListCount;
	}

	public int selectLocalListTotalCount(String keyword) {
		Connection con = JDBCTemplate.getConnection();
		
		int ListCount = new RecruitmentDao().selectLocalListTotalCount(con, keyword);
		
		JDBCTemplate.close(con);
		
		return ListCount;	}

	public ArrayList<RecruitmentVo> selectByDateList(String dateStr) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().selectByDateList(con,dateStr);
		JDBCTemplate.close(con);
		return list;
	}

	



}
