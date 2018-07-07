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

}
