package com.what.semi.resume.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.what.semi.common.file.MyRenamePolicy;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

@WebServlet("/modifyResume.do")
public class ModifyResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyResumeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//이력서 수정하기
		
		// 파일 업로드/다운로드 -> cos.jar
		// 1. 파일 사이즈 설정
		int maxSize = 1024 * 1024 * 10;
		// 2. 파일 전송 시 enctype 정상적으로 설정 되었는지 확인
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("파일전송타입 확ㅇ니");
		}
		// 3. 파일 저장 경로 설정
		String root = request.getServletContext().getRealPath("/");
		String path = root + "profile_photo/";
		// 4. request -> multipartrequest
		// filerenamePolicy -> FileRenamePolicy 상속 -> rename() override
		MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "UTF-8", new MyRenamePolicy());

		// 전송 값을 변수에 저장
		// 객체 2개 -> 게시판에 추가할 객체, attachment 추가할 객체(list)
		
		HttpSession session = request.getSession(false);
		
		String id = (String)session.getAttribute("id");
		
		String introduce = mRequest.getParameter("introduce");
		System.out.println("소개"+introduce);
		int dValue = Integer.parseInt(mRequest.getParameter("dValue"));
		System.out.println("장애"+dValue);
		String achievement = mRequest.getParameter("achievement");
		System.out.println("학력"+achievement);
		int mValue = Integer.parseInt(mRequest.getParameter("mValue"));
		System.out.println("병역"+mValue);
		int career = Integer.parseInt(mRequest.getParameter("career"));
		System.out.println("경력"+career);
		String workTime = mRequest.getParameter("workTime");
		System.out.println("희망 근무시간 "+workTime);
		String business_type = mRequest.getParameter("business_type");
		System.out.println("희망근무분야"+business_type);
		String fileName = mRequest.getFilesystemName("userPhoto");
		String introduce_title = mRequest.getParameter("introduce_title");
		int resume_id = Integer.parseInt(mRequest.getParameter("resume_id"));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dateD = null;
		try {
			dateD = new java.sql.Date(((java.util.Date)sdf.parse(mRequest.getParameter("dateD"))).getTime());
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//장애 dValue 
		//학력 achievement
		//병역 사항 mValue 1, 0
		//비니지스 타입 business_type
		//경력 career
		//근무 가능일 dateD  ???
		//희망 근무 시간 workTime
		// 파일 정보 객체 변환
		
		MyResumeVo resume = new MyResumeVo();
		
		resume.setIntroduce_title(introduce_title);
		resume.setIntroduce(introduce);
		resume.setDisability(dValue);
		resume.setAchievement(achievement);
		resume.setMiltary_service(mValue);
		resume.setCareer(career);
		resume.setWorkable_days(dateD);
		resume.setWorkTime(workTime);
		resume.setBusiness_type(business_type);
		resume.setProfile_image_src(fileName);
		resume.setId(id);
		resume.setResume_id(resume_id);
		
		int result = new MyResumeService().updateResume(resume);
		
		String url ="";
		if(0<result){
			url = "/sp/getResume.do";
			response.sendRedirect(url);
		}else{
			//에러 페이지 ?
			System.out.println("에러");
		}

	}

}
