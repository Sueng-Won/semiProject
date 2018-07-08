<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="com.what.semi.member.model.vo.MemberVo"%>
<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@page import="java.util.ArrayList"%>
<%
	RecruitmentVo rec = (RecruitmentVo) request.getAttribute("rec");
	int currentPage = (int) request.getAttribute("currentPage");
	MemberVo writer = (MemberVo) request.getAttribute("writer");
	String Mtype = (String) session.getAttribute("member_type");
	ArrayList<MyResumeVo> resumes = (ArrayList<MyResumeVo>) request.getAttribute("myResumes");
	int is_post = 0;
	boolean userTypeFlag = false;
	if (0 != resumes.size()) {
		is_post = resumes.get(0).getIs_post();
		userTypeFlag = true;
	}
	
	int contRe = (int) request.getAttribute("contRe");
	/* rec.setRecruitment_image_src(null); */
%>
<%@include file="/views/common/header.jsp"%>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
	function applyBtn() {
		if (<%=Mtype.equals("JS")%>) {
			if (<%=userTypeFlag%>) {
				$('div.modal').modal();
			} else {
				alert("등록된 이력서가 없습니다!!");
			}
		}else{
			alert("구직자가 아니면 지원할 수 없습니다.");
		}
	}

	function apply() {
		$("#postNum").submit();
	}

	$(function() {
		var count = $("#ta").text().length;
		if (count > 510) {
			var minus = count - 510;
			var mod = (minus / 85) + 1;
			var he = (mod * 25) + 150;
			$("#ta").css("height", he + "px");
		}
		
		if(<%=contRe%> != 0){
			alert("구직신청이 완료되었습니다.");
		}

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
									if (writer.getName().length() >= 3) {
								%>
								<td><%=writer.getName().charAt(0)%>
									<%
										for (int i = 0; i < writer.getName().length() - 3; i++) {
									%>*<%
										}
									%><%=writer.getName().charAt(writer.getName().length() - 1)%></td>
								<%
									} else {
								%>
								<td><%=writer.getName().charAt(0)%>*</td>
								<%
									}
								%>
							</tr>
							<tr>
								<td>연락처</td>
								<td>
									<%
										if (id == null) {
									%>로그인후 확인이 가능합니다.<%
										} else {
									%><%=rec.getRecruitment_phone()%> <%
 	}
 %>
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
					<%
						if (!(writer.getId().equals(id))) {
					%>
					<div align="center">
						<button onclick="applyBtn();"
							class="btn btn-default bg-dark text-white">지원하기</button>
					</div>
					<%
						}
					%>
					<div align="right">
						<button onclick="goList();"
							class="btn btn-default bg-dark text-white">목록으로</button>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->


	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
<!-- 팝업 모달영역 -->
<div class="modal fade" id="layerpop">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<!-- 닫기(x) 버튼 -->
				<!-- header title -->
				<h4 class="modal-title">이력서 선택</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
			</div>
			<!-- body -->
			<div class="modal-body">
				<form id="postNum" method="get" action="/sp/apply.do">
					<input type="hidden" name="userId" value="<%=id%>" /> <input
						type="hidden" name="bo_id" value="<%=writer.getId()%>" /> <input
						type="hidden" name="recId" value="<%=rec.getRecruitment_id()%>" />
						<input type="hidden" name="currentPage" value="<%=currentPage%>" />
					<div class="radio">
						<%
							for (int i = 0; i < resumes.size(); i++) {
						%>
						<%
							if (resumes.get(i).getPri_resume() == 'Y' || resumes.size() != 2) {
						%>
						<label for="post<%=i%>"><%=resumes.get(i).getIntroduce_title()%>
							<input id="post<%=i%>" type="radio"
							value="<%=resumes.get(i).getResume_id()%>" name="resume_id"
							checked /> </label><br>
						<%
							} else {
						%>
						<label for="post<%=i%>"><%=resumes.get(i).getIntroduce_title()%>
							<input id="post<%=i%>" type="radio"
							value="<%=resumes.get(i).getResume_id()%>" name="resume_id" /> </label><br>
						<%
							}
						%>
						<%
							}
						%>
					</div>
				</form>
				<hr>
				<button type="button" class="btn btn-success" onclick="apply();">지원</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
   
   center: new daum.maps.LatLng(<%=rec.getR_latitude()%>, <%=rec.getR_longitude()%>), // 지도의 중심좌표
   level: 3 // 지도의 확대 레벨

};

// 지도를 생성합니다
   var map = new daum.maps.Map(mapContainer, mapOption); 

   var content = '<div><%=rec.getRecruitment_name()%></div>';
   var latlng = new daum.maps.LatLng(<%=rec.getR_latitude()%>, <%=rec.getR_longitude()%>);
      
var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

       
       // 마커 이미지의 이미지 크기 입니다
       var imageSize = new daum.maps.Size(24, 35); 
       
       // 마커 이미지를 생성합니다    
       var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
      
       // 마커를 생성합니다
       var marker = new daum.maps.Marker({
           map: map, // 마커를 표시할 지도
           position: latlng, // 마커를 표시할 위치
           image : markerImage // 마커 이미지 
       });
       
       // 마커에 표시할 인포윈도우를 생성합니다 
       var infowindow = new daum.maps.InfoWindow({
           content: content // 인포윈도우에 표시할 내용
       });
       
       daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
       daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
</script>
<%@include file="/views/common/footer.jsp"%>