package com.what.semi.recruitment.controller;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.oreilly.servlet.MultipartRequest;
import com.what.semi.recruitment.RecruitmentRenamePolicy;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;

/**
 * Servlet implementation class WriteRecruitmentServlet
 */
@WebServlet("/writeRecruitment.do")
public class WriteRecruitmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteRecruitmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드/다운로드 -> cos.jar
		// 1. 파일 사이즈 설정
		int maxSize = 1024 * 1024 * 10; // 10메가 1025키바 X 1024 = 1메가
		// 2. 파일 전송 시 enctype 정상적으로 설정 되었는지 확인
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			/*view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg", "전송 데이터의 타입을 확인하세요");
			view.forward(request, response);*/
			System.out.println("전송 데이터타입 오류");
		}
		// 3. 파일 저장 경로 설정
		String root = request.getServletContext().getRealPath("/");
		//System.out.println(root);
		String path = root + "images/recruitmentImg";

		MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8");

		// 4.전송 값을 변수에 저장
		//객체 2 -> 게시판에 추가할 객체, attachment 추가할 객체(list)
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat tf = new SimpleDateFormat("yyyyMMdd/HHmm");
		
		String name = mRequest.getParameter("name");
		String phone = mRequest.getParameter("phone");
		String email = mRequest.getParameter("email");
		String zipcode = mRequest.getParameter("zipcode");
		double latitude = Double.parseDouble(mRequest.getParameter("latitude"));
		double longitude = Double.parseDouble(mRequest.getParameter("longitude"));
		String address = mRequest.getParameter("address");
		String addressDetail = mRequest.getParameter("addressDetail");
		String business_type = mRequest.getParameter("business_type");
		/*int career = 0;
		String careerStr = mRequest.getParameter("career");
		switch(careerStr){
		case "경험무관":career=0; break;
		case "6개월 이하":career=-1; break;
		case "6개월~1년":career=1; break;
		case "1~2년":career=2; break;
		case "3~5년":career=3; break;
		case "5~10년":career=5; break;
		case "10년 이상":career=10; break;
		}*/
		java.sql.Date workdate=null;
		java.sql.Date starttime=null;
		java.sql.Date endtime=null;
		
		try {
			workdate = new java.sql.Date(((Date)df.parse(mRequest.getParameter("workdate"))).getTime());
			starttime = new java.sql.Date(((Date)df.parse(mRequest.getParameter("starttime"))).getTime());
			endtime = new java.sql.Date(((Date)df.parse(mRequest.getParameter("endtime"))).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pay = Integer.parseInt(mRequest.getParameter("pay"));
		int m=-1;
		String mValue = mRequest.getParameter("mValue");
		switch(mValue){
		case "y":m=1; break;
		case "n":m=0; break;
		case "x":m=-1; break;
		}
		char gValue =mRequest.getParameter("gValue").charAt(0);
		String title = mRequest.getParameter("title");
		String introduce = mRequest.getParameter("introduce");
		String recImg = mRequest.getFilesystemName("recImg");
		
		RecruitmentVo rec = new RecruitmentVo();
		
		rec.setRecruitment_name(name);
		rec.setRecruitment_phone(phone);
		rec.setRecruitment_email(email);
		rec.setZipcode(zipcode);
		rec.setAddress(address);
		rec.setAddress_detail(addressDetail);
		rec.setBusiness_type(business_type);
		rec.setWork_day(workdate);
		rec.setStart_work_time(starttime);
		rec.setEnd_work_time(endtime);
		rec.setPay(pay);
		rec.setMilitary_service(m);
		rec.setRecruitment_title(title);
		rec.setIntroduce(introduce);
		rec.setRecruitment_image_src(recImg);
		
		//System.out.println(recImg);
		System.out.println(rec);
		
		
		
		int result = new RecruitmentService().writeRecruitment(rec);
		
		String url="";
		if(0<result){
			response.sendRedirect("/sp/views/member/manageResume.jsp");
		}else{
			/*url="views/common/errorPage.jsp";
			request.setAttribute("msg", "사진 게시판 게시글 작성 실패");
			view = request.getRequestDispatcher(url);
			view.forward(request, response);*/
			System.out.println("구인게시글 게시 오류");
		}
	}

}
