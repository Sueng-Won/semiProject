package com.what.semi.recruitment.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.LocalPageInfo;
import com.what.semi.recruitment.model.dao.RecruitmentDao;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

public class RecruitmentService {

	public ArrayList<RecruitmentVo> loadLocalRecruitmentList(int currentPage, int limit, LocalPageInfo lpi) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().loadLocalRecruitmentList(con, currentPage, limit, lpi);
		JDBCTemplate.close(con);

		return list;
	}

	public ArrayList<RecruitmentVo> loadRecruitmentList(int currentPage, int limit) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().loadRecruitmentList(con, currentPage, limit);
		JDBCTemplate.close(con);
		return list;
	}

	public int selectIndexListTotalCount() { // 검색 조건이 없을경우 게시물조회에 사용되는 메소드
		Connection con = JDBCTemplate.getConnection();

		int ListCount = new RecruitmentDao().selectIndexListTotalCount(con);

		JDBCTemplate.close(con);

		return ListCount;
	}

	public int selectLocalListTotalCount(LocalPageInfo lpi) {
		Connection con = JDBCTemplate.getConnection();

		int ListCount = new RecruitmentDao().selectLocalListTotalCount(con, lpi);

		JDBCTemplate.close(con);

		return ListCount;
	}

	public ArrayList<RecruitmentVo> selectByDateList(String dateStr, int currentPage, int limit) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().selectByDateList(con, dateStr, currentPage, limit);
		JDBCTemplate.close(con);
		return list;
	}

public ArrayList<Double> userSpot(String userId) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<Double> spot = new RecruitmentDao().userSpot(con, userId);
		
		JDBCTemplate.close(con);
		
		return spot;
	}

	public int byDateListTotalCount(String dateStr) {
		Connection con = JDBCTemplate.getConnection();

		int ListCount = new RecruitmentDao().selectByDateListTotalCount(con, dateStr);

		JDBCTemplate.close(con);

		return ListCount;
	}

}
