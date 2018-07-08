package com.what.semi.recruitment.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.LocalPageInfo;
import com.what.semi.recruitment.model.dao.RecruitmentDao;
import com.what.semi.recruitment.model.vo.RecruitmentVo;
import com.what.semi.resume.model.vo.MyResumeVo;

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

/*	public String getDefaultResume(String id) {
		Connection con = JDBCTemplate.getConnection();

		String resume = new RecruitmentDao().getDefaultResume(con, id);

		JDBCTemplate.close(con);
		return resume;
	}*/

	public int byDateListTotalCount(String dateStr) {
		Connection con = JDBCTemplate.getConnection();

		int ListCount = new RecruitmentDao().selectByDateListTotalCount(con, dateStr);

		JDBCTemplate.close(con);

		return ListCount;
	}

	public int writeRecruitment(RecruitmentVo rec) {
		Connection con = JDBCTemplate.getConnection();

		int result = new RecruitmentDao().insertRecruitment(con, rec);
		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int myRecListTotalCount(String id) {
		Connection con = JDBCTemplate.getConnection();

		int result = new RecruitmentDao().selectMyListTotalCount(con, id);

		JDBCTemplate.close(con);

		return result;
	}

	public ArrayList<RecruitmentVo> loadMyRecList(int currentPage, int limit, String id) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RecruitmentVo> list = new RecruitmentDao().selectMyRecList(con, id, currentPage, limit);
		JDBCTemplate.close(con);
		return list;
	}

	public RecruitmentVo selectRecruitment(String recId) {
		Connection con = JDBCTemplate.getConnection();
		RecruitmentVo rec = new RecruitmentDao().selectRecruitment(con, recId);
		JDBCTemplate.close(con);
		return rec;
	}

	public int selectMachingListTotalCount(MyResumeVo myResumeVo) {
		Connection con = JDBCTemplate.getConnection();

		int ListCount = new RecruitmentDao().selectMachingListTotalCount(con, myResumeVo);

		JDBCTemplate.close(con);

		return ListCount;
	}

	public ArrayList<RecruitmentVo> loadMatchingSearchList(int currentPage, int limit, MyResumeVo myResumeVo) {
		Connection con = JDBCTemplate.getConnection();

		ArrayList<RecruitmentVo> list = new RecruitmentDao().loadMatchingSearchList(con, currentPage, limit,
				myResumeVo);

		JDBCTemplate.close(con);

		return list;
	}

	public int updateRecruitment(RecruitmentVo rec) {
		Connection con = JDBCTemplate.getConnection();

		int result = new RecruitmentDao().updateRecruitment(con, rec);
		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int deleteRec(String recId) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new RecruitmentDao().deleteRec(con,recId);
		if(result != 0) {
			JDBCTemplate.commit(con);
		}else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public String searchIdByRecId(String recId) {
		Connection con = JDBCTemplate.getConnection();
		
		String memId = new RecruitmentDao().searchIdByRecId(con,recId);
		if(memId != null) {
			JDBCTemplate.commit(con);
		}else {
			JDBCTemplate.rollback(con);
		}
		
		return memId;
	}
}