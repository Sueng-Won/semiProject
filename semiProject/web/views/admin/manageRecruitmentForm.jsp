<%@page import="com.what.semi.managePost.model.vo.ManageRecruitmentVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/sp/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
   <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>

    <!-- Custom styles for this template -->
    <link href="/sp/css/shop-homepage.css" rel="stylesheet">
	
    <link href="/sp/css/daum.css" rel="stylesheet">
    <script src="/sp/vendor/jquery/jquery.min.js"></script>
<title>게시물 상세</title>
<%
	ManageRecruitmentVo rec = (ManageRecruitmentVo) request.getAttribute("rec");
	/* rec.setRecruitment_image_src(null); */
%>
<script type="text/javascript">
	$(function() {
		$("#deleteBtn").on("click", function() {
			$.ajax({
				url : "/sp/adminDeleteResume.do",
				type : "get",
				data : {r_no : <%=rec.getRecruitment_id()%>},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("게시물 삭제중 오류가 발생하였습니다.")
				},
				complete : function() {
					close();
				}
			});
		});
		
		$("#resetBtn").on("click", function() {
			$.ajax({
				url : "/sp/adminResetResume.do",
				type : "get",
				data : {r_no : <%=rec.getRecruitment_id()%>},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("게시물 복구중 오류가 발생하였습니다.")
				},
				complete : function() {
					close();
				}
			});
		});
		
		$("#returnBtn").on("click", function() {
			close();
		});
	});
	
	
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
</head>

<body>
	<div class="container">
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
							<td><%=rec.getName()%></td>
						</tr>
						<tr>
							<td>연락처</td>
							<td>
								<%=rec.getRecruitment_phone()%>
							</td>
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
				<div id="map" style="width:670px;height:400px;"></div>
			</div>
			<div class="space"></div>
			<div class="line">
				<div align="center">
					<%if(rec.getDelflag() != 1){ %>
					<button id="deleteBtn"
						class="btn btn-dark text-white mr-2">게시물 삭제</button>
					<%}else{ %>
					<button id="resetBtn"
						class="btn btn-info text-white mr-2">게시물 복구</button>
					<%} %>
					<button id="returnBtn"
						class="btn btn-default bg-dark text-white">돌아가기</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
   
   center: new daum.maps.LatLng(<%=rec.getR_latitude()%>, <%=rec.getR_longitude()%>), // 지도의 중심좌표
   level: 3, // 지도의 확대 레벨
   marker : {
	   position : latlng,
	   text : '<%=rec.getRecruitment_name()%>'
   }
};

// 지도를 생성합니다
   var map = new daum.maps.StaticMap(mapContainer, mapOption); 

   var latlng = new daum.maps.LatLng(<%=rec.getR_latitude()%>, <%=rec.getR_longitude()%>);
      

       
</script>
</body>
</html>