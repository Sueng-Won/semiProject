<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="com.what.semi.member.model.vo.MemberVo"%>
<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@page import="java.util.ArrayList"%>
<%
	RecruitmentVo rec = (RecruitmentVo) request.getAttribute("rec");
	int state = (int) request.getAttribute("state");
	int contId = (int) request.getAttribute("contId");

	/* rec.setRecruitment_image_src(null); */
%>
<%@include file="/views/common/header.jsp"%>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">

	$(function() {
		var count = $("#ta").text().length;
		if (count > 510) {
			var minus = count - 510;
			var mod = (minus / 85) + 1;
			var he = (mod * 25) + 150;
			$("#ta").css("height", he + "px");
		}
		
	});
	
	function applyBtn() {
		location.href="/sp/searchSameBusiness.do?recId="+<%=rec.getRecruitment_id()%>;
	}
	
	function acceptBtn() {
		location.href="/sp/myWorkedList.do?contId="+<%=contId%>;
	}
</script>
<style>
.line {
	width: 800px;
	margin-bottom: 20px;
	margin-left: 60px;
	float: center;
}

.title_line {
	width: 800px;
	margin-bottom: 20px;
	float: center;
}

.title_space {
	width: 800px;
	height: 30px;
	border-top: 2px #5D5D5D solid;
}

.space {
	width: 800px;
	height: 30px;
	border-top: 1px #cccccc solid;
}

.title_image {
	width: 220px;
	float: left;
}

.sub_image {
	width: 170px;
	float: left;
	margin-top: 20px;
}

table {
	margin-top: 10px;
}

td {
	padding-right: 10px;
}

tr {
	height: 30px;
}

#ta {
	width: 680px;
	height: 150px;
}
</style>
<!-- Page Content -->
<div class="container" style="min-height: 1000px">
	<div class="row">

		<%@include file="/views/common/nav.jsp"%>

		<!-- /.col-lg-3 -->
		<div class="col-lg-9 mt-lg-auto">
			<div class="row mt-4" style="margin-left: 50px;">
				<div class="title_line">
					<div>
						<h5><%=rec.getRecruitment_name()%></h5>
					</div>
					<div>
						<h1><%=rec.getRecruitment_title()%></h1>
					</div>
				</div>
				<div class="title_space"></div>
				<div class="line">
					<div class="title_image">
						<div>
							<img
								src="<%if (rec.getRecruitment_image_src() == null) {%>/sp/images/building.jpeg<%} else {%>/sp/images/recruitmentImg/<%=rec.getRecruitment_image_src()%><%}%>"
								width="128px" height="128px">
						</div>
					</div>
					<div class="sub_image">
						<div>
							<img src="/sp/images/calendar2.jpeg">
						</div>
						<div><%=rec.getWork_day()%></div>
					</div>
					<div class="sub_image">
						<div>
							<img src="/sp/images/pay2.jpeg">
						</div>
						<div><%=rec.getPay()%>원
						</div>
					</div>
					<div class="sub_image">
						<div>
							<img src="/sp/images/time2.jpeg">
						</div>
						<div><%=rec.getStart_work_time()%>~<%=rec.getEnd_work_time()%></div>
					</div>
				</div>
				<div class="space"></div>
				<div class="line">
					<div style="float: left;">
						<b>모집조건</b>
						<table>
							<tr>
								<td>모집분야</td>
								<td><%=rec.getBusiness_type()%></td>
							</tr>
							<tr>
								<td>희망성별</td>
								<td>
									<%
										if (rec.getGender() == 'M') {
									%>남성우대<%
										} else if (rec.getGender() == 'F') {
									%>여성우대<%
										} else {
									%>무관<%
										}
									%>
								</td>
							</tr>
							<tr>
								<td>희망경력</td>
								<td>
									<%
										if (rec.getCareer() == 0) {
									%>무관<%
										} else {
									%>필요<%
										}
									%>
								</td>
							</tr>
							<tr>
								<td>희망학력</td>
								<td><%=rec.getAchievement()%></td>
							</tr>
							<tr>
								<td>병역우대</td>
								<td>
									<%
										if (rec.getMilitary_service() == 0) {
									%>무관<%
										} else {
									%>군필우대<%
										}
									%>
								</td>
							</tr>
						</table>
					</div>
					<div style="float: right; margin-right: 50px;">
						<b>채용자정보</b>
						<table>
							<tr>
								<td>채용자</td>
								<%
									if (rec.getName().length() >= 3) {
								%>
								<td><%=rec.getName().charAt(0)%><%
										for (int i = 0; i < rec.getName().length() - 2; i++) {
									%>*<%
										}
									%><%=rec.getName().charAt(rec.getName().length() - 1)%></td>
								<%
									} else {
								%>
								<td><%=rec.getName().charAt(0)%>*</td>
								<%
									}
								%>
							</tr>
							<tr>
								<td>연락처</td>
								<td><%=rec.getRecruitment_phone()%></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="space"></div>
				<div class="line">
					<div id="ta"><%=rec.getIntroduce()%></div>
				</div>
				<div class="space"></div>
				<div class="line">
					<div id="map" style="width: 670px; height: 400px;"></div>
				</div>
				<div class="space"></div>
				<div class="line">
					<div align="center">
						<%
							if (state == 0) {
						%>
						<button onclick="acceptBtn();"
							class="btn btn-default bg-dark text-white">해당 업체 수락하러가기</button>
						<%
							} else {
						%>
						<button onclick="applyBtn();"
							class="btn btn-default bg-dark text-white">해당 업체에 지원하기</button>
						<%
							}
						%>
					</div>

				</div>
			</div>
		</div>
		<!-- /.row -->


	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
<script type="text/javascript">
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
   
   center: new daum.maps.LatLng(<%=rec.getR_latitude()%>, <%=rec.getR_longitude()%>), // 지도의 중심좌표
   level: 3 // 지도의 확대 레벨

};

// 지도를 생성합니다
   var map = new daum.maps.Map(mapContainer, mapOption); 

   var content = '<div><%=rec.getRecruitment_name()%></div>';
	var latlng = new daum.maps.LatLng(<%=rec.getR_latitude()%>,<%=rec.getR_longitude()%>);

	var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new daum.maps.Size(24, 35);

	// 마커 이미지를 생성합니다    
	var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);

	// 마커를 생성합니다
	var marker = new daum.maps.Marker({
		map : map, // 마커를 표시할 지도
		position : latlng, // 마커를 표시할 위치
		image : markerImage
	// 마커 이미지 
	});

	// 마커에 표시할 인포윈도우를 생성합니다 
	var infowindow = new daum.maps.InfoWindow({
		content : content
	// 인포윈도우에 표시할 내용
	});

	daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map,
			marker, infowindow));
	daum.maps.event
			.addListener(marker, 'mouseout', makeOutListener(infowindow));
</script>
<%@include file="/views/common/footer.jsp"%>
