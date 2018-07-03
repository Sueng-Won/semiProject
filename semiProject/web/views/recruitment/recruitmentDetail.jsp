<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function writeRecruitment() {
		$("#writeRecruitment").submit();
	}

	$(function() {
		var count = $("#ta").text().length;
		if (count > 510) {
			var minus = count - 510;
			var mod = (minus / 85) + 1;
			var he = (mod * 25) + 150;
			$("#ta").css("height", he + "px");
		}
		console.log($("#ta").height());
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
					<div>업체명</div>
					<div>
						<h1>구인 게시글 제목</h1>
					</div>
				</div>
				<div class="title_space"></div>
				<div class="line">
					<div class="title_image">
						<div>
							<img src="/sp/images/building.jpeg">
						</div>
					</div>
					<div class="sub_image">
						<div>
							<img src="/sp/images/calendar2.jpeg">
						</div>
						<div>08 / 01</div>
					</div>
					<div class="sub_image">
						<div>
							<img src="/sp/images/pay2.jpeg">
						</div>
						<div>100,000원</div>
					</div>
					<div class="sub_image">
						<div>
							<img src="/sp/images/time2.jpeg">
						</div>
						<div>9:00~21:00</div>
					</div>
				</div>
				<div class="space"></div>
				<div class="line">
					<div style="float: left;">
						<b>모집조건</b>
						<table>
							<tr>
								<td>모집분야</td>
								<td>배달업무</td>
							</tr>
							<tr>
								<td>희망성별</td>
								<td>무관</td>
							</tr>
							<tr>
								<td>희망경력</td>
								<td>무관</td>
							</tr>
							<tr>
								<td>희망학력</td>
								<td>무관</td>
							</tr>
							<tr>
								<td>희망나이</td>
								<td>22~60</td>
							</tr>
							<tr>
								<td>병역우대</td>
								<td>무관</td>
							</tr>
						</table>
					</div>
					<div style="float: right; margin-right: 50px;">
						<b>채용자정보</b>
						<table>
							<tr>
								<td>채용자</td>
								<td>알*몬</td>
							</tr>
							<tr>
								<td>연락처</td>
								<td>로그인 후 확인 가능합니다.</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="space"></div>
				<div class="line">
					<div id="ta">상세내용</div>
				</div>
				<div class="space"></div>
				<div class="line">지도</div>
				<div class="space"></div>
				<div class="line">
					<div align="center">
						<button onclick="writeRec();"
							class="btn btn-default bg-dark text-white">지원하기</button>
					</div>
			</div>
			</div>
		</div>
		<!-- /.row -->


	</div>
	<!-- /.row -->
</div>
<!-- /.container -->

<%@include file="/views/common/footer.jsp"%>