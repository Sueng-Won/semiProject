package com.what.semi.qna.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.common.template.PageInfo;
import com.what.semi.qna.model.dao.QnaDao;
import com.what.semi.qna.model.vo.QnaVo;
import com.what.semi.recruitment.model.dao.RecruitmentDao;

public class QnaService {

	public int insertQna(String content, String category, String id) {
		Connection con = JDBCTemplate.getConnection();
		int result = -1;
		result = new QnaDao().insertQna(con,content,category,id);
		
		if(result>0) {
			
			JDBCTemplate.commit(con);
			System.out.println("성공");
		}else {
			JDBCTemplate.rollback(con);
			System.out.println("실패");
		}
		
		JDBCTemplate.close(con);
		return result;
	}

	public ArrayList<QnaVo> selectQna( PageInfo pi, String id) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<QnaVo> result = new QnaDao().selectQna(con,id, pi);
		
		JDBCTemplate.close(con);
		return result;
	}

	public int QnaCount(String id) {
		Connection con = JDBCTemplate.getConnection();
		int ListCount = new QnaDao().QnaCount(con, id);
		JDBCTemplate.close(con);

		return ListCount;
	}

	public int membersQnaCount(QnaVo qv) {
		Connection con = JDBCTemplate.getConnection();
		int ListCount = new QnaDao().membersQnaCount(con, qv);
		JDBCTemplate.close(con);

		return ListCount;
	}

	public ArrayList<QnaVo> selectMembersQna(PageInfo pi, QnaVo qv) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<QnaVo> result = new QnaDao().selectMembersQna(con, pi, qv);
		
		JDBCTemplate.close(con);
		return result;
	}

	public void updateAnswer(String answer, int q_no) {
		Connection con = JDBCTemplate.getConnection();
		int result = -1;
		result = new QnaDao().updateAnswer(con,answer,q_no);
		
		if(result>0) {
			JDBCTemplate.commit(con);
			System.out.println("성공");
		}else {
			JDBCTemplate.rollback(con);
			System.out.println("실패");
		}
		
		JDBCTemplate.close(con);
	}
}
