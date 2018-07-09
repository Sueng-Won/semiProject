package com.what.semi.resume.model.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.what.semi.common.template.JDBCTemplate;
import com.what.semi.member.model.vo.MemberVo;
import com.what.semi.resume.model.vo.MyResumeVo;

public class MyResumeDao {

	public ArrayList<MyResumeVo> selectMyInfo(Connection con, String id) {
		PreparedStatement pstmt = null;
		String query = null;
		ResultSet rs = null;
		ArrayList<MyResumeVo> userType = null;
		
		try {
			query = "SELECT MILTARY_SERVICE, BUSINESS_TYPE, WORKABLE_DAYS, GENDER, PRI_RESUME, RESUME_ID, INTRODUCE_TITLE, IS_POST, MEMBER_TYPE FROM RESUME R JOIN MEMBER M ON (R.M_ID = M.M_ID) WHERE M.M_ID=? AND MEMBER_TYPE='JS' AND R.DELFLAG=0";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(query);
			userType = new ArrayList<MyResumeVo>();
			MyResumeVo temp = null;
			
			while(rs.next()){
				temp = new MyResumeVo();
				temp.setId(id);
				temp.setResume_id(rs.getInt("resume_id"));
				temp.setIntroduce_title(rs.getString("introduce_title"));
				temp.setIs_post(rs.getInt("is_post"));
				temp.setMember_type(rs.getString("member_type"));
				temp.setPri_resume(rs.getString("pri_resume").charAt(0));
				temp.setMiltary_service(rs.getInt("miltary_service"));
				temp.setBusiness_type(rs.getString("business_type"));
				temp.setWorkable_days(rs.getDate("workable_days"));
				temp.setGender(rs.getString("gender").charAt(0));
				userType.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return userType;
	}

	public int updatePri_resumeY(int resume_id, String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query =null;
		try {
			query = "UPDATE RESUME SET PRI_RESUME=? WHERE RESUME_ID=? AND M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, resume_id);
			pstmt.setString(3, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updatePri_resumeN(int resume_id, String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query =null;
		try {
			query = "UPDATE RESUME SET PRI_RESUME=? WHERE RESUME_ID!=? AND M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "N");
			pstmt.setInt(2, resume_id);
			pstmt.setString(3, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateIs_post0(String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query="";
		
		try {
			query = "UPDATE RESUME SET IS_POST=0 WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateIs_post1(String id, Connection con) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query="";
		
		try {
			query = "UPDATE RESUME SET IS_POST=1 WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public MyResumeVo selectMemberInfo(String userId, Connection con) {
		PreparedStatement pstmt = null;
		MyResumeVo member = null;
		ResultSet rs = null;
		String query =null;
		
		
		try {
			query = "SELECT NAME, PHONE, EMAIL, ADDRESS FROM MEMBER WHERE M_ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				member = new MyResumeVo();
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}

	public int insertResume(MyResumeVo resume, Connection con) {
		int result= -1;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "INSERT INTO RESUME VALUES(RESUME_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?, DEFAULT, ?, DEFAULT)";

			pstmt = con.prepareStatement(query);
			
			
			pstmt.setString(1, resume.getProfile_image_src());
			pstmt.setString(2, resume.getAchievement());
			pstmt.setInt(3, resume.getDisability());
			pstmt.setInt(4, resume.getMiltary_service());
			pstmt.setInt(5, resume.getCareer());
			pstmt.setString(6, resume.getBusiness_type());
			pstmt.setDate(7, resume.getWorkable_days());
			pstmt.setString(8, resume.getId());
			pstmt.setString(9, resume.getIntroduce());
			pstmt.setString(10, resume.getIntroduce_title());
			pstmt.setString(11, resume.getWorkTime());
			
			
			result = pstmt.executeUpdate();
			
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public MyResumeVo selectMyResume(Connection con, String userId, int resume_id) {
		MyResumeVo member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			query ="SELECT WORK_TIME, INTRODUCE_TITLE, INTRODUCE, WORKABLE_DAYS, BUSINESS_TYPE, CAREER, MILTARY_SERVICE, DISABILITY, ACHIEVEMENT, NAME, PHONE, EMAIL, ADDRESS, PROFILE_IMAGE_SRC FROM RESUME R  JOIN MEMBER M ON(R.M_ID=M.M_ID) WHERE R.M_ID=? AND R.RESUME_ID=?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, resume_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				member = new MyResumeVo();
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setProfile_image_src(rs.getString("profile_image_src"));
				member.setResume_id(resume_id);
				member.setAchievement(rs.getString("achievement"));
				member.setMiltary_service(rs.getInt("miltary_service"));
				member.setCareer(rs.getInt("career"));
				member.setBusiness_type(rs.getString("business_type"));
				member.setWorkable_days(rs.getDate("workable_days")); //데이트 ->스트링
				//  WORKABLE_DAYS
				member.setIntroduce(rs.getString("introduce"));
				member.setIntroduce_title(rs.getString("introduce_title"));
				member.setWorkTime(rs.getString("work_time"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return member;
	}

	public int updateResume(MyResumeVo resume, Connection con) {
		int result= -1;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE RESUME SET PROFILE_IMAGE_SRC = ?, ACHIEVEMENT = ?, DISABILITY = ?, MILTARY_SERVICE = ?, CAREER = ?, BUSINESS_TYPE = ?, WORKABLE_DAYS = ?, INTRODUCE = ?, INTRODUCE_TITLE = ?, WORK_TIME = ? WHERE RESUME_ID =? ";
			pstmt = con.prepareStatement(query);
			
			
			
			pstmt.setString(1, resume.getProfile_image_src());
			pstmt.setString(2, resume.getAchievement());
			pstmt.setInt(3, resume.getDisability());
			pstmt.setInt(4, resume.getMiltary_service());
			pstmt.setInt(5, resume.getCareer());
			pstmt.setString(6, resume.getBusiness_type());
			pstmt.setDate(7, resume.getWorkable_days());
			pstmt.setString(8, resume.getIntroduce());
			pstmt.setString(9, resume.getIntroduce_title());
			pstmt.setString(10, resume.getWorkTime());
			pstmt.setInt(11, resume.getResume_id());
			
			
			result = pstmt.executeUpdate();
			
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteResume(String userId, String resume_id, Connection con) {
		int result= -1;
		PreparedStatement pstmt = null;
		String query = null;
		
		try {
			query = "UPDATE RESUME SET DELFLAG=1 WHERE RESUME_ID=? AND M_ID=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, resume_id);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int selectJSTotalCount(Connection con) {
		//페이지 처리 쿼리..
		int result = -1;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			stmt = con.createStatement();
			query = "SELECT COUNT(*) AS JSLISTCOUNT FROM MEMBER M JOIN RESUME R ON(M.M_ID =R.M_ID) WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				result = rs.getInt("jslistcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return result;
	}

	public ArrayList<MyResumeVo> selectJSList(Connection con, int currentPage, int limit) {
		ArrayList<MyResumeVo> result = null;
		PreparedStatement pstmt = null;
		String query = "";
		ResultSet rs = null;
		try {
			query = "SELECT  RESUME_ID, NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,4), 'RRRR')))+1 AS AGE, RTRIM(SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 3))) AS TRIMADR, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM (SELECT ROWNUM RNUM, P.* FROM (SELECT NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,2), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE, RESUME_ID FROM RESUME R JOIN MEMBER M ON(R.M_ID=M.M_ID) WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3 AND DELFLAG!=1 ORDER BY AGE DESC) P) WHERE RNUM BETWEEN ? AND ?";
			
			pstmt = con.prepareStatement(query);
			
			int startRow = (currentPage - 1) * limit + 1; 
			int endRow = startRow + limit - 1;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
		
			MyResumeVo temp = null;
			result = new ArrayList<MyResumeVo>();
			while(rs.next()){
				temp = new MyResumeVo();
				temp.setName(rs.getString("name"));
				temp.setIntroduce_title(rs.getString("INTRODUCE_TITLE"));;
				temp.setCareer(rs.getString("career").charAt(0));
				temp.setBusiness_type(rs.getString("business_type"));
				temp.setAddress(rs.getString("TRIMADR"));	
				temp.setGender(rs.getString("gender").charAt(0));
				temp.setAge(rs.getInt("age"));
				temp.setResume_id(rs.getInt("resume_id"));
				System.out.println(rs.getInt("resume_id"));
				result.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<MyResumeVo> selectResumeCondition(Connection con, int currentPage, int limit, String searchJob,
		String searchLocal, String searchworkTime) {
		ArrayList<MyResumeVo> result = null;
		PreparedStatement pstmt = null;
		String query = "";
		ResultSet rs = null;
		
		
		query = "SELECT  NAME ,RESUME_ID"
		+", BIRTH, AGE"
		+", RTRIM(SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 3))) AS TRIMADR"
		+", GENDER"
		+", MEMBER_TYPE"
		+", IS_BLACK_LIST"
		+", CAREER, BUSINESS_TYPE"
		+", WORKABLE_DAYS"
		+", WORK_TIME"
		+", INTRODUCE_TITLE"
		+ " ,DISABILITY"
		+" FROM ("
        +" SELECT ROWNUM RNUM, P.*" 
        +" FROM ("  
                    +" SELECT NAME "
                    +", BIRTH"
                    +", AGE"
                    +", ADDRESS"
                    +" , GENDER"
                    +" , MEMBER_TYPE"
                    +" , IS_BLACK_LIST"
                    +" , CAREER, BUSINESS_TYPE"
                    +" ,  WORKABLE_DAYS"
                    +" , WORK_TIME"
                    +" , INTRODUCE_TITLE" 
                    +" , RESUME_ID"
                    + " ,DISABILITY"
                    +"  FROM (SELECT RESUME_ID"
                    +" , M_ID"
                    +" , INTRODUCE_TITLE"
                    +" , BUSINESS_TYPE"
                    +" , CAREER"
                    +" , WORK_TIME"
                    +" , WORKABLE_DAYS "
                    + " ,DISABILITY"
            +" FROM RESUME "
            +" WHERE DELFLAG != 1";
    		
			String searchworkTimeArr[] = searchworkTime.split(",");
			
			if(!(searchworkTime.equals(""))){
				 if(!(searchworkTime.equals("상관없음")))
		            {
		            	query = query +" AND (";
		            	for (int i=0; i<searchworkTimeArr.length; i++){
		            		if(searchworkTimeArr[i] == searchworkTimeArr[0]){
		            			query = query +" WORK_TIME LIKE '%" + searchworkTimeArr[i] +"%'";
		            		}else{
		            		query = query +" OR  WORK_TIME LIKE '%" + searchworkTimeArr[i] +"%'";
		            		}
		        		}
		            	query = query +" )";
		            }
			}
           
            
            
            //AND (WORK_TIME LIKE '%오전%' OR WORK_TIME LIKE '%오후%' OR WORK_TIME LIKE '%저녁%' OR WORK_TIME LIKE '%야간%')"
            query = query +" ) R" 
                    +" JOIN  (SELECT M_ID"
                    +" , NAME"
                    +" , BIRTH"
                    +" , TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,4), 'RRRR')))+1 AS AGE"
                    +" , ADDRESS"
                    +" , GENDER"
                    +" , MEMBER_TYPE"
                    +" , IS_BLACK_LIST" 
                    +" ,RTRIM(SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2))) AS ADR"
                            +" FROM MEMBER" 
                    +" ) M "
                    +" ON(R.M_ID=M.M_ID)" 
                    +" WHERE MEMBER_TYPE='JS'" 
                    +" AND IS_BLACK_LIST<3 ";          
		
		if(!(searchJob.equals(""))){
			query= query+ " AND BUSINESS_TYPE IN ("+searchJob+")";
		}
		if(!(searchLocal.equals(""))){
			query= query+ " AND ADR IN ("+searchLocal+")";
		}
		
		query= query+ " ORDER BY AGE DESC) P) WHERE RNUM BETWEEN ? AND ?";
		
		
		
		
		
//		
//		query = "SELECT SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2)) AS ADR, NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,4), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM (SELECT ROWNUM RNUM, P.* FROM (SELECT NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,2), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM RESUME R JOIN MEMBER M ON(R.M_ID=M.M_ID) WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3 ORDER BY AGE DESC) P) WHERE RNUM BETWEEN ? AND ?";
//		if(!(searchJob.equals(""))){
//		query="SELECT SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2)) AS ADR, NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,4), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM (SELECT ROWNUM RNUM, P.* FROM (SELECT NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,2), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM RESUME R  JOIN MEMBER M ON(R.M_ID=M.M_ID)  WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3 AND BUSINESS_TYPE IN ("+searchJob+") ORDER BY AGE DESC) P) WHERE RNUM BETWEEN ? AND ?";
//		}
//		if(!(searchLocal.equals(""))){
//		query="SELECT SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2)) AS ADR, NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,4), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM (SELECT ROWNUM RNUM, P.* FROM (SELECT NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,2), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE, SELECT SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2)) AS ADR FROM RESUME R  JOIN MEMBER M ON(R.M_ID=M.M_ID)  WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3 AND ADR IN ("+searchLocal+") ORDER BY AGE DESC) P) WHERE RNUM BETWEEN ? AND ?";
//		}
//		if(!(searchJob.equals(""))&&!(searchLocal.equals(""))){
//		query="SELECT SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2)) AS ADR, NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,4), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE FROM (SELECT ROWNUM RNUM, P.* FROM (SELECT NAME, BIRTH, TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(BIRTH,1,2), 'RRRR')))+1 AS AGE, ADDRESS, GENDER, MEMBER_TYPE, IS_BLACK_LIST, CAREER, BUSINESS_TYPE, WORKABLE_DAYS, WORK_TIME, INTRODUCE_TITLE, SELECT SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2)) AS ADR FROM RESUME R  JOIN MEMBER M ON(R.M_ID=M.M_ID)  WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3 AND BUSINESS_TYPE ("+searchJob+") AND ADR IN ("+searchLocal+") ORDER BY AGE DESC) P) WHERE RNUM BETWEEN ? AND ?";
//
//		}
		try {
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			
			int startRow = (currentPage - 1) * limit + 1; 
			int endRow = startRow + limit - 1;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
		
			MyResumeVo temp = null;
			result = new ArrayList<MyResumeVo>();
			while(rs.next()){
				temp = new MyResumeVo();
				temp.setName(rs.getString("name"));
				temp.setIntroduce_title(rs.getString("INTRODUCE_TITLE"));;
				temp.setCareer(rs.getInt("career"));
				temp.setBusiness_type(rs.getString("business_type"));
				temp.setAddress(rs.getString("TRIMADR"));	
				temp.setGender(rs.getString("gender").charAt(0));
				temp.setAge(rs.getInt("age"));
				temp.setResume_id(rs.getInt("resume_id"));
				temp.setDisability(rs.getInt("disability"));
				result.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectJSTotalCount(Connection con, String searchJob, String searchLocal, String searchworkTime) {
		//페이지 처리 쿼리..
				int result = -1;
				java.sql.Statement stmt = null;
				ResultSet rs = null;
				String query = "";
				
				query = "SELECT COUNT(*) AS JSLISTCOUNT "
						+" FROM (SELECT M_ID,"
                                +" MEMBER_TYPE,"
							      +" IS_BLACK_LIST "
								  +",RTRIM(SUBSTR(ADDRESS, 1, INSTR(ADDRESS, ' ', 1, 2))) AS ADR "
								   +" FROM MEMBER ) M "
                       +" JOIN ("
						 +" SELECT *"
		                      +" FROM (SELECT RESUME_ID"
		                     +", M_ID"
		                     +", INTRODUCE_TITLE"
		                     +", BUSINESS_TYPE"
		                     +", CAREER"
		                     +", WORK_TIME"
		                     +", WORKABLE_DAYS"
		                   +" FROM RESUME"
		                   +" WHERE DELFLAG!=1 ";
		                     String searchworkTimeArr[] = searchworkTime.split(",");
				
				if(!(searchworkTime.equals(""))){
					 if(!(searchworkTime.equals("상관없음")))
			            {
			            	query = query +" AND (";
			            	for (int i=0; i<searchworkTimeArr.length; i++){
			            		if(searchworkTimeArr[i] == searchworkTimeArr[0]){
			            			query = query +" WORK_TIME LIKE '%" + searchworkTimeArr[i] +"%'";
			            		}else{
			            		query = query +" OR  WORK_TIME LIKE '%" + searchworkTimeArr[i] +"%'";
			            		}
			        		}
			            	query = query +" )";
			            }
				}
				
		             query += ")) R"
						+" ON(M.M_ID =R.M_ID)"
						+" WHERE MEMBER_TYPE='JS' AND IS_BLACK_LIST<3";
				
				if(!(searchJob.equals(""))){
					query= query+ " AND BUSINESS_TYPE IN ("+searchJob+")";
				}
				if(!(searchLocal.equals(""))){
					query= query+ " AND ADR IN ("+searchLocal+")";
				}
				
				try {
					System.out.println(query);
					stmt = con.createStatement();
					
					rs = stmt.executeQuery(query);
					
					while(rs.next()){
						result = rs.getInt("jslistcount");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					JDBCTemplate.close(rs);
					JDBCTemplate.close(stmt);
				}
				return result;
	}
	
}
