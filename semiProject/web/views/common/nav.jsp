<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//String nickname = (String) session.getAttribute("nickname");
%>
<style>
.btn {
	color: white;
}

#loginFrm {
	text-align: center;
}
</style>
<!-- <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script> -->
<!-- branch test -->
<script>
	function login() {
		$("#loginFrm").submit();
	}
	
	function join() {
		location.href = "/sp/views/member/join.jsp";
	}
	
</script>
<div class="col-lg-3">
	<h4 class="my-4">
		<form id="loginFrm" action="/sp/login.do" method="POST">
			<table>
				<tr>
					<td>
						<input type="text" class="form-control mb-1" name="id" placeholder="아이디" /> 
						<input type="password" class="form-control mb-1" name="pwd" placeholder="비밀번호" />
					</td>
					<td>
						<input type="button" size="15" value="로그인" class="btn btn-dark btn-sm btn-block" onclick="login();" /> 
						<input type="button" size="15" value="회원가입" class="btn btn-dark btn-sm btn-block" onclick="join();" />
					</td>
				</tr>
			</table>
		</form>
	</h4>
	<div class="list-group">
		<!-- <a href="#" class="list-group-item active">맞춤알바</a> -->
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">맞춤알바</button>
		<!-- <a onclick="loadLocalList();" class="list-group-item">지역알바</a> -->
		<!-- 지역 알바로 이동 -->
		<button type="button" onclick="loadLocalList();"
			class="btn btn-default btn-lg btn-block bg-dark">지역알바</button>

		<script type="text/javascript">
			function loadLocalList() {
				location.href = "/sp/localList.do";
			}
		</script>

		<!-- <a href="#" class="list-group-item">일별알바</a>
		<a href="#" class="list-group-item">구직자정보</a> -->
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">일별알바</button>
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">구직자정보</button>
	</div>
</div>