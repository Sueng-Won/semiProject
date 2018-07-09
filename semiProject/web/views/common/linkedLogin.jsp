<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = (String) request.getAttribute("path");
%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>계약용 로그인</title>
<!-- Bootstrap core CSS -->
<link href="/sp/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

<style>
html, body {
	text-align: center;
	position: relative;
}

div {
	margin-top: 5%;
}
</style>
<script type="text/javascript">
	function login() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		if (pw == "") {
			if (id = "") {
				alert("아이디를 입력하세요");
			} else {
				alert("경호야 귀찮다고 비밀번호 안치고 그러면 안돼");
			}
		} else {
			$("#loginFrm").submit();
		}
	}

	function join() {
		location.href = "/sp/views/member/join.jsp";
	}
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<!-- /.col-lg-3 -->
			<div class="col-lg-9 mx-auto">
				<div class="row mt-3">
					<div class="col-lg-4"></div>
					<div class="col-lg-4">
						<form id="loginFrm" action="/sp/login.do" method="POST">
							<table>
								<tr>
									<td><input type="text" class="form-control mb-1" id="id"
										name="id" placeholder="아이디" /> <input type="password"
										class="form-control mb-1" id="pw" name="pw" placeholder="비밀번호" />
									</td>
									<td><input type="button" size="15" value="로그인"
										class="btn btn-dark btn-sm btn-block" onclick="login();" /> <input
										type="button" size="15" value="회원가입"
										class="btn btn-dark btn-sm btn-block" onclick="join();" /></td>
								</tr>
								<tr>
									<td colspan="2"><a href="/sp/views/member/searchId.jsp">아이디
											찾기</a> / <a href="/sp/views/member/searchPw.jsp">비밀번호 찾기</a></td>
								</tr>
								<tr>
									<td><input type="hidden" id="query" name="query"
										value="<%=path%>" /></td>
								</tr>
							</table>
						</form>
					</div>
					<!-- /.col-lg-6 -->
					<div class="col-lg-3"></div>
				</div>
			</div>
			<!-- /.row -->


		</div>
		<!-- /.row -->
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>