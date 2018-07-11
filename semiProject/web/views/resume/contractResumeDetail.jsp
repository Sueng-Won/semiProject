<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MyResumeVo member = (MyResumeVo) request.getAttribute("member");
	int state = (int) request.getAttribute("state");
	int contId = (int) request.getAttribute("contId");
%>
<%@include file="/views/common/header.jsp"%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script>
	function suggestBtn() {
		location.href = "searchSameJobseeker.do?resumeId="+<%=member.getResume_id()%>;
	}
	
	function acceptBtn() {
		location.href="/sp/myHiredList.do?contId="+<%=contId%>;
	}
</script>
<style>
.img {
	border: 1px solid black;
}

.page-header {
	text-align: center;
}

.wrap {
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

.innerWrap {
	border: 1px solid black;
	height: 150px;
	width: 700px;
	/* padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
	padding-top: 15px;
	padding-bottom: 15px; */
}

.contentsWrap {
	
}

.auth {
	color: lightGray;
}

div {
	list-style: none;
}

#profileImg {
	height: 120px;
}
</style>

<div class="container">
	<div class="row">
		<%@include file="/views/common/nav.jsp"%>
		<div class="col-lg-9 mt-lg-auto">
			<div class="row" style="margin: 30px 0 0 40px;">
				<div class="row page-header" style="width: 700px;">
					<h1>
						나의 이력서<hr> <small><%=member.getIntroduce_title()%></small><hr>
					</h1>
				</div>
				<div class="row" style="width: 700px;">
					<div class="col-md-4">
						<a class="thumbnail"><img id="profileImg"
							src="/sp/profile_photo/<%if (member.getProfile_image_src() == null) {%>default_photo.jpeg<%} else {%><%=member.getProfile_image_src()%><%}%>">
						</a>
					</div>
					<div class="col-md-8">
						<h4>
							<strong><%=member.getName()%></strong>
						</h4>
						<address>
							<%=member.getGender() == 'M' ? "남" : "여"%>,
							<%=member.getAge()%>(<%=member.getAge() - 1%>) /
							<%=member.getBirth()%><br>
							<%=member.getAddress()%><br> <abbr title="Phone">P:</abbr>
							<%=member.getPhone()%>
							<strong>Full Name</strong><br>
							<%=member.getEmail()%>
						</address>
					</div>
				</div>
			</div>
			<hr>
			<h3>희망 근무 조건</h3><br>
			<div class="row wrap">
					<div class="col-md-4">
						<p class="tit">
							<img src="/sp/images/resume/shop.png" />&nbsp;&nbsp;&nbsp;근무지
						</p>
						<strong><%=member.getAddress()%></strong>
					</div>

					<div class="col-md-4">
						<p class="tit">
							<img src="/sp/images/resume/id-card.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;업종
						</p>
						<strong><%=member.getBusiness_type()%></strong> <strong>경력
							:&nbsp;<%=member.getCareer() == 1 ? "있음" : "없음"%></strong>
					</div>
					
					<div class="col-md-4">
							<p class="tit">
								<img src="/sp/images/resume/medal.png" />&nbsp;&nbsp;&nbsp;학력
							</p>
							<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=member.getAchievement()%></strong>
					</div>
			</div>
			<br><hr>
		<h3>학력, 우대사항</h3><br>
			<div class="row wrap">
					<div class="col-md-4 col-md-offset-3">
						<p class="tit">
							<img src="/sp/images/resume/work_time.png" />&nbsp;&nbsp;&nbsp;희망시간
						</p>
						<strong><%=member.getWorkable_days()%></strong>
						 <strong><%=member.getWorkTime()%></strong>
					</div>
					<div class="col-md-4">
							<p class="tit">
								<img src="/sp/images/resume/dis.png" />&nbsp;장애여부
							</p>
							<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=member.getDisability() == 1 ? "장애" : "정상"%></strong>
					</div>
				</div>
					<br><hr>
				<h3>자기 소개서</h3><br>
			<div class="row wrap">
					<textarea class="form-control" rows="5" readonly><%=member.getIntroduce()%></textarea>
			</div>
			<div class="row wrap">
			<div class="col-md-12 auth">
				<h2 align="center">위 입력사항은 사실과 다름이 없습니다</h2>
				<p align="center">작성자: <%=member.getName() %></p>
			</div>
			</div>
			<div align="center">
			<%if(state==0){ %>
			<button onclick="acceptBtn();"
					class="btn btn-default bg-dark text-white">해당 이력서 수락하러가기</button>
			<%}else{ %>
				<button onclick="suggestBtn();"
					class="btn btn-default bg-dark text-white">해당 구직자에 제안하기</button>
			<%} %>
			</div>
		</div>
	</div>
</div>
<%@include file="/views/common/footer.jsp"%>
