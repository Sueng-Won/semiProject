package com.what.semi.resume.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.resume.model.dao.MyResumeDao;
import com.what.semi.resume.model.vo.MyResumeVo;

public class MyResumeService {

	public ArrayList<MyResumeVo> selectMyInfo(String id) {
		
		//이력서 목록 조회하기
		Connection con = JDBCTemplate.getConnection();
		
		
		ArrayList<MyResumeVo> userType =  new MyResumeDao().selectMyInfo(con, id);
		
		JDBCTemplate.close(con);
		return userType;
	}

	public int updatePri_resume(int resume_id, String id) {
		
		
		Connection con = JDBCTemplate.getConnection();
		//선택된 이력서를 대표이력서로 설정하기
		int result = new MyResumeDao().updatePri_resumeY(resume_id, id, con);
		//선택되지 않은 이력서를 대표로 설정하지 않기
		int result2 = new MyResumeDao().updatePri_resumeN(resume_id, id, con);
		
		if(0<result && 0<result2){
			JDBCTemplate.commit(con);
		}else{
			JDBCTemplate.rollback(con);
		}
		
		
		return result;
	}

	public int updateIs_post(String id, int is_post){ 
		
		Connection con = JDBCTemplate.getConnection();
		//현재 is_post 상태에 따라 다른 다오를 호출 
		int result = -1;
		
		if(is_post==1){
			result = new MyResumeDao().updateIs_post0(id, con);
		}else{
			result = new MyResumeDao().updateIs_post1(id, con);
		}
		
		if(0<result){
			JDBCTemplate.commit(con);
		}else{
			JDBCTemplate.rollback(con);
		}
		
		JDBCTemplate.close(con);
		
		
		return result;
	}

	public MyResumeVo selectMemberInfo(String userId) {
		Connection con = JDBCTemplate.getConnection();
		
		MyResumeVo member = new MyResumeDao().selectMemberInfo(userId, con);
		
		JDBCTemplate.close(con);
		
		
		return member;
	}

	public int insertResume(MyResumeVo resume) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new MyResumeDao().insertResume(resume, con);
		
		if(0<result){
			JDBCTemplate.commit(con);
		}else{
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public MyResumeVo selectMyResume(String userId, int resume_id) {
		
		//저장된 이력서의 정보 가져오기
		
		Connection con = JDBCTemplate.getConnection();
		
		MyResumeVo member = new MyResumeDao().selectMyResume(con, userId, resume_id);
		
		
		JDBCTemplate.close(con);
		
		return member;
	}

	public int updateResume(MyResumeVo resume) {
		//여럭서 업데이트
		Connection con = JDBCTemplate.getConnection();
		
		int result = new MyResumeDao().updateResume(resume, con);
		
		if(0<result){
			JDBCTemplate.commit(con);
		}else{
			JDBCTemplate.rollback(con);
		}
			JDBCTemplate.close(con);
		return result;
	}

	public int deleteResume(String userId, String resume_id) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new MyResumeDao().deleteResume(userId, resume_id, con);
		
		if(0<result){
			JDBCTemplate.commit(con);
		}else{
			JDBCTemplate.rollback(con);
		}
			JDBCTemplate.close(con);
		
		return result;
	}
	public int selectJSTotalCount() {
		//페이지..
		//1. 커넥션 연결
				Connection con = JDBCTemplate.getConnection();
				//2. dao 메소드 호출
				int listCount = new MyResumeDao().selectJSTotalCount(con);
				//3. 자원 반납(close)
				JDBCTemplate.close(con);
				//4. 해당 결과 리턴
				return listCount;
	}

	public ArrayList<MyResumeVo> selectJSList(int currentPage, int limit) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<MyResumeVo> jsList = new MyResumeDao().selectJSList(con, currentPage, limit);
		
		JDBCTemplate.close(con);
		
		return jsList;
	}

	public ArrayList<MyResumeVo> selectResumeCondition(int currentPage, int limit, String[] searchJob,
			String[] searchLocal) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<MyResumeVo> jsList = new MyResumeDao().selectResumeCondition(con, currentPage, limit, searchJob, searchLocal);
		
		JDBCTemplate.close(con);
		
		return jsList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
