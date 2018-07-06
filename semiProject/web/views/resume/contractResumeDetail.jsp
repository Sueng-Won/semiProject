<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MyResumeVo member = (MyResumeVo) request.getAttribute("member");
%>
<%@include file="/views/common/header.jsp"%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script>
	function suggestBtn() {
		//해당업체에 구인글이 있는지 검색
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
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
	padding-top: 15px;
	padding-bottom: 15px;
}

.auth {
	color: lightGray;
}

ul {
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
			<div class="row">

				<div class="page-header">
					<h1>
						나의 이력서<br> <small><%=member.getIntroduce_title()%></small>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3 col-md-3 col-xs-offset-1">
					<a class="thumbnail"><img id="profileImg"
						src="/sp/profile_photo/<%=member.getProfile_image_src()%>">
					</a>
				</div>
				<div class="col-xs-7 col-md-9 col-xs-offset-1">
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
			<hr>
			<div class="row wrap">
				<h3>희망 근무 조건</h3>
				<div class="innerWrap">
					<div class="contentsWrap col-xs-4">
						<ul>
							<li><p class="tit">
									<img src="/sp/images/resume/store.png" />근무지
								</p></li>
							<li><strong><%=member.getAddress()%></strong></li>
						</ul>
					</div>
					<div class="contentsWrap col-xs-4">
						<ul>
							<li><p class="tit">
									<img src="/sp/images/resume/business_type.png" />업종
								</p></li>
							<li><strong><%=member.getBusiness_type()%></strong></li>
							<li><strong>경력 :<%=member.getCareer() == 1 ? "있음" : "없음"%></strong></li>
						</ul>
					</div>
					<div class="contentsWrap col-xs-4">
						<ul>
							<li><p class="tit">
									<img src="/sp/images/resume/clock.png" />희망시간
								</p></li>
							<li><strong><%=member.getWorkable_days()%></strong></li>
							<li><strong><%=member.getWorkTime()%></strong></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row wrap">
				<h3>학력, 우대사항</h3>
				<div class="innerWrap">
					<div class="contentsWrap col-xs-6">
						<ul>
							<li><p class="tit">
									<img src="/sp/images/resume/achievement.png" />학력
								</p></li>
							<li><strong><%=member.getAchievement()%></strong></li>
						</ul>
					</div>
					<div class="contentsWrap col-xs-6">
						<ul>
							<li><p class="tit">
									<img src="/sp/images/resume/disability.png" />장애여부
								</p></li>
							<li><strong><%=member.getDisability() == 1 ? "장애" : "정상"%></strong></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row wrap">
				<h3>자기 소개서</h3>
				<div class="innerWrap">
					<textarea class="form-control" rows="5" readonly><%=member.getIntroduce()%></textarea>
				</div>
			</div>
			<div align="center">
				<button onclick="suggestBtn();"
					class="btn btn-default bg-dark text-white">해당 이력서에 제안하기</button>
			</div>
			<div class="col-xs-12 auth">
				<h2 align="center">위 입력사항은 사실과 다름이 없습니다</h2>
				<p align="center">작성자: XXX</p>
			</div>
		</div>
	</div>
</div>
<%@include file="/views/common/footer.jsp"%>
