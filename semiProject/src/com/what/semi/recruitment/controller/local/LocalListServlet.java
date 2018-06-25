package com.what.semi.recruitment.controller.local;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.LocalPageInfo;
import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.recruitment.model.service.RecruitmentService;
import com.what.semi.recruitment.model.vo.RecruitmentVo;


@WebServlet("/localList.do")
public class LocalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocalListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecruitmentService rs = new RecruitmentService();	//DAO에 접근할 서비스 객체 생성
		PageInfo pi = null;
		
		String userId = null;								//request로부터 회원 아이디를 받아올 변수선언
		ArrayList<Double> userSpot = null;					//DAO를 통해 받아온 유저의 위경도를 저장할 ArrayList
		double userRatitude = 0;					//파싱한 위도를 저장할 변수 선언
		double userLogitude = 0;					//파싱한 경도를 저장할 변수 선언
		
		int mapLevel = 4;
		
		if(null != request.getParameter("mapLevel")) {
			mapLevel = Integer.parseInt(request.getParameter("mapLevel"));
		}
		
		LocalPageInfo lpi = null;							//위,경도를 저장할 객체 선언
		double minLatitude = 37.49872435130918;				//request로 받아온 지도의 최하단 위도를 저장할 변수선언
		double maxLatitude = 37.49951157565128;				//request로 받아온 지도의 최상단 위도를 저장할 변수선언
		double minLongitude = 127.03118958659454;			//request로 받아온 지도의 좌측 경도를 저장할 변수선언
		double maxLongitude = 127.03529786758172;			//request로 받아온 지도의 우측 경도를 저장할 변수선언
		
		double centerLatitude = minLatitude+(maxLatitude-minLatitude);		//지도의 중심 위도
		double centerLongitude = minLongitude +(maxLongitude-minLongitude);	//지도의 중심 경도
		
		ArrayList<RecruitmentVo> list = null;				//업주정보를 담아올 리스트 선언
		
		//넘어온 위경도 값이 있을 경우를 체크(페이지를 이용중인 경우)
		if( null != request.getParameter("sLatitude")
			&& null != request.getParameter("nLatitude")
			&& null != request.getParameter("wLongitude")
			&& null != request.getParameter("eLongitude")) {
			
			minLatitude = Double.parseDouble(request.getParameter("sLatitude"));
			maxLatitude = Double.parseDouble(request.getParameter("nLatitude"));
			minLongitude = Double.parseDouble(request.getParameter("wLongitude"));
			maxLongitude = Double.parseDouble(request.getParameter("eLongitude"));
			centerLatitude = Double.parseDouble(request.getParameter("centerLatitude"));
			centerLongitude = Double.parseDouble(request.getParameter("centerLongitude"));
			
			System.out.println("minLatitude="+minLatitude+" maxLatitude="+maxLatitude
					+" minLongitude="+minLongitude+" maxLongitude="+maxLongitude);
			lpi = new LocalPageInfo(minLatitude, maxLatitude, minLongitude, maxLongitude);
			pi = PageTemplate.LocalPaging(request, rs, lpi);
			list = rs.loadLocalRecruitmentList(pi.getCurrentPage(), pi.getLimit(), lpi);
			System.out.println("페이지 이용중 실행 로직");
		}else if(null != request.getParameter("userId")		//넘어온 위경도 값이 없을 경우(최초 페이지 이동시) 회원일 경우
				&& null != request.getParameter("userType")){
			userId = request.getParameter("userId");
			userSpot = rs.userSpot(userId);		//유저의 아이디를 통해 해당 사용자의 좌표를 불러옴
			userRatitude = userSpot.get(0);		//유저 위도
			userLogitude = userSpot.get(1);		//유저 경도
			System.out.println("최초 접속시 회원인 경우 로직");
		}else {														//회원이 아니며 페이지에 처음 들어왔을 경우
			lpi = new LocalPageInfo(minLatitude, maxLatitude, minLongitude, maxLongitude);
			
			pi = PageTemplate.LocalPaging(request, rs, lpi);
			list = rs.loadLocalRecruitmentList(pi.getCurrentPage(), pi.getLimit(), lpi);
			System.out.println("최초 접속시 회원이 아닌 경우 로직");
			System.out.println(lpi.toString());
			System.out.println(pi.toString());
		}
		System.out.println("센터 좌표: "+centerLatitude+"/"+centerLongitude);
		System.out.println(list.size());
		String url = "";
		
		if(null != list) {		//검색한 내용이 있을경우
			url = "views/local/localSearch.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			System.out.println(pi.toString());
			request.setAttribute("centerLatitude", centerLatitude);
			request.setAttribute("centerLongitude", centerLongitude);
			request.setAttribute("mapLevel", mapLevel);
		}else if(null != userSpot){
			url = "views/local/localSearch.jsp";
			request.setAttribute("userRatitude", userRatitude);
			request.setAttribute("userLogitude", userLogitude);
			request.setAttribute("mapLevel", mapLevel);
		}else {
			url = "index.jsp";
			System.out.println("지도 검색중 오류발생");
		}
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}
