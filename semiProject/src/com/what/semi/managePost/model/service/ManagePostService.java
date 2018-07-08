package com.what.semi.managePost.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.PageInfo;
import com.what.semi.managePost.model.dao.ManagePostDao;
import com.what.semi.managePost.model.vo.ManageRecruitmentVo;
import com.what.semi.managePost.model.vo.ManageResumeVo;
import com.what.semi.managePost.model.vo.PostCondition;

public class ManagePostService {

	public int managePostCount(PostCondition pc) {
		Connection con = JDBCTemplate.getConnection();
		int result = 0;
		if("JS".equals(pc.getMember_type())) {
			result += new ManagePostDao().resumeListCount(con, pc);
		}else if("BO".equals(pc.getMember_type())) {
			result += new ManagePostDao().recruitmentListCount(con, pc);
		}
		JDBCTemplate.close(con);
		return result;
	}

	public ArrayList<ManageRecruitmentVo> loadRecruitmentList(PostCondition pc, PageInfo pi) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<ManageRecruitmentVo> list = new ManagePostDao().loadRecruitmentList(con, pc, pi);
		
		JDBCTemplate.close(con);
		return list;
	}

	public ArrayList<ManageResumeVo> loadResumeList(PostCondition pc, PageInfo pi) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<ManageResumeVo> list = new ManagePostDao().loadResumeList(con, pc, pi);
		
		JDBCTemplate.close(con);
		return list;
	}
	
	public int deleteRecruitment(int r_no) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ManagePostDao().deleteRecruitment(con, r_no);
		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}

	public int resetRecruitment(int r_no) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new ManagePostDao().resetRecruitment(con, r_no);
		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}
	
	public ManageRecruitmentVo selectedRecruitment(int r_no) {
		Connection con = JDBCTemplate.getConnection();
		
		ManageRecruitmentVo mrv = new ManagePostDao().selectedRecruitment(con, r_no);
		
		JDBCTemplate.close(con);
		return mrv;
	}

	public int deleteReume(int r_no) {
		Connection con = JDBCTemplate.getConnection();

		int result = new ManagePostDao().deleteReume(con, r_no);
		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);

		return result;
	}
	
	public int resetResume(int r_no) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new ManagePostDao().resetResume(con, r_no);
		if (result != 0) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}
}
