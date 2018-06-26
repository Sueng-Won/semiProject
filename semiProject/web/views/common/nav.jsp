<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	function loadLocalList() {
		location.href = "/sp/localList.do";
	}
</script>
<div class="col-lg-3">
	<h4 class="my-4">
	<%if(id == null){ %>
		<form id="loginFrm" action="/sp/login.do" method="POST">
			<table>
				
				<tr>
					<td>
						<input type="text" class="form-control mb-1" name="id" placeholder="아이디" /> 
						<input type="password" class="form-control mb-1" name="pw" placeholder="비밀번호" />
					</td>
					<td>
						<input type="button" size="15" value="로그인" class="btn btn-dark btn-sm btn-block" onclick="login();" /> 
						<input type="button" size="15" value="회원가입" class="btn btn-dark btn-sm btn-block" onclick="join();" />
					</td>
				</tr>
				
					
				
			</table>
		</form>
		<%} else { %>
			<%=id %>님 환영합니다 새퀴야
		<%} %>
	</h4>
	<div class="list-group">
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">맞춤알바</button>
		<!-- 지역 알바로 이동 -->
		<button type="button" onclick="loadLocalList();"
			class="btn btn-lg btn-block bg-dark">지역알바</button>

		

		<button type="button" class="btn btn-lg btn-block btn-dark">일별알바</button>
		<button type="button" class="btn btn-lg btn-block btn-dark">구직자정보</button>
	</div>
</div>