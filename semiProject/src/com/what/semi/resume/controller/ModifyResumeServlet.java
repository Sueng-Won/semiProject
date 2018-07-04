package com.what.semi.resume.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.what.semi.common.file.MyRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/modifyResume.do")
public class ModifyResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyResumeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				String introduce = mRequest.getParameter("introduce");
				System.out.println("소개"+introduce);
				String dValue = mRequest.getParameter("dValue");
				System.out.println("장애"+dValue);
				String achievement = mRequest.getParameter("achievement");
				System.out.println("학력"+achievement);
				int mValue = Integer.parseInt(mRequest.getParameter("mValue"));
				System.out.println("병역"+mValue);
				int career = Integer.parseInt(mRequest.getParameter("career"));
				System.out.println("경력"+career);
				String workTime = mRequest.getParameter("workTime");
				System.out.println("희망 근무"+workTime);
				String business_type = mRequest.getParameter("business_type");
				System.out.println("희망근무분야"+business_type);
				
				
				//장애 dValue 
				//학력 achievement
				//병역 사항 mValue 1, 0
				//비니지스 타입 business_type
				//경력 career
				//근무 가능일 dateD  ???
				//희망 근무 시간 workTime
				// 파일 정보 객체 변환

	}

}
