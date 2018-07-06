
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
		var id = $("#id").val();
		var pw = $("#pw").val();
		if(pw==""){
			if(id=""){
				alert("아이디를 입력하세요");
			}
			else{
				alert("경호야 귀찮다고 비밀번호 안치고 그러면 안돼");
			}
		}
		else{
			$("#loginFrm").submit();			
		}
	}

	function join() {
		location.href = "/sp/views/member/join.jsp";
	}
	
	function loadMachingList(){
		location.href="/sp/matchingSearch.do";
	}
	
	function loadLocalList() {
		location.href = "/sp/localList.do";
	}
	function searchByDateList() {
		location.href = "/sp/byDateList.do";
	}
	
	function logout(){
		location.href="/sp/views/member/logout.jsp";
	}
	function jobSeekerInfo(){
		location.href="/sp/jobSeekerInfo.do";
	}
</script>
<div class="col-lg-3">
<br><br>
<div class="list-group">
	<%if(id == null){ %>
		<form id="loginFrm" action="/sp/login.do" method="POST">
			<table>
				<tr>
					<td>
						<input type="text" class="form-control mb-1" id="id" name="id" placeholder="아이디" /> 
						<input type="password" class="form-control mb-1" id="pw" name="pw" placeholder="비밀번호" />
					</td>
					<td>
						<input type="button" size="15" value="로그인" class="btn btn-dark btn-sm btn-block" onclick="login();" /> 
						<input type="button" size="15" value="회원가입" class="btn btn-dark btn-sm btn-block" onclick="join();" />
					</td>
				</tr>
				<tr>
						<td colspan="2">
						<a href="/sp/views/member/searchId.jsp">아이디 찾기</a>
						/
						<a href="/sp/views/member/searchPw.jsp">비밀번호 찾기</a>
						</td>
				</tr>
			</table>
		</form>
		<%} else { %>
		<table>
		<tr>
		<td>
			<h5 class="text-center">
			<%if(member_type.equals("JS")){ %>
			[구직자]
			<%}else if(member_type.equals("BO")){ %>
			[업주]
			<%}else{ %>
			[관리자]
			<%} %>
			<%=id %>님 환영합니다</h5>
			<button type="button" class="btn btn-dark btn-sm btn-block" onclick="logout();">로그아웃</button>
		<td>
		</tr>
		</table>
		<%} %>
	</div>
	<br><br>
	<div class="list-group">
		<button type="button" onclick="loadMachingList();"
			class="btn btn-default btn-lg btn-block bg-dark">맞춤알바</button>
		<!-- 지역 알바로 이동 -->
		<button type="button" onclick="loadLocalList();"
			class="btn btn-lg btn-block bg-dark">지역알바</button>



		<button type="button" onclick="searchByDateList();"
			class="btn btn-lg btn-block btn-dark">일별알바</button>
		<button type="button" class="btn btn-lg btn-block btn-dark" onclick="jobSeekerInfo();">구직자정보</button>
	</div>
</div>