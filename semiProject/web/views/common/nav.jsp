<%@ page language="java" contentType="text/html; charset=UTF-8"
<<<<<<< HEAD
    pageEncoding="UTF-8"%>
    
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<div class="col-lg-3">
	<h1 class="my-4">
	<form id="loginFrm" action="/sp/login.do" method="POST">
	<a id="kakao-login-btn"></a>
	<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('62e514dd123055704e6cbbfc833bf5ae');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        // 로그인 성공시, API를 호출합니다.
        Kakao.API.request({
          url: '/v1/user/me',
          success: function(res) {
            alert(JSON.stringify(res));
            var sPerson = JSON.stringify(res);
            var oPerson = JSON.parse(sPerson);
            
            var id = oPerson.id;
            var name= oPerson.properties.nickname;
            $("input[name=hd]").val(id);
            $("input[name=name]").val(name);
            $("#loginFrm").submit();
          },
          fail: function(error) {
            alert(JSON.stringify(error));
          }
        });
      },
      fail: function(err) {
        alert(JSON.stringify(err));
      }
    });
  //]]>
</script>
<a id="kakao-logout-btn"></a>
<script>
//<![CDATA[
// 카카오 로그인 버튼을 생성합니다.
Kakao.Auth.createLoginButton({
  container: '#kakao-logout-btn',
  success: function(authObj) {
    // 로그인 성공시, API를 호출합니다.
     Kakao.Auth.logout(function() { console.log("logged out."); });
    alert("로그아웃되었습니다.");
    location.href="/sp/index.jsp";
  },
  fail: function(err) {
    alert(JSON.stringify(err));
  }
});
//]]>
//브랜치 테스트
//안녕안녕
</script>
=======
	pageEncoding="UTF-8"%>
<%
	//String nickname = (String) session.getAttribute("nickname");
%>
<style>
.btn {
	color: white;
}
>>>>>>> refs/heads/test

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
<<<<<<< HEAD
		<button type="button" class="btn btn-lg btn-block btn-dark">맞춤알바</button>
		<!-- <a onclick="loadLocalList();" class="list-group-item">지역알바</a> --> <!-- 지역 알바로 이동 -->
		<button type="button" onclick="loadLocalList();" class="btn btn-lg btn-block btn-dark">지역알바</button>
=======
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

>>>>>>> refs/heads/test
		<!-- <a href="#" class="list-group-item">일별알바</a>
		<a href="#" class="list-group-item">구직자정보</a> -->
		<button type="button" class="btn btn-lg btn-block btn-dark">일별알바</button>
		<button type="button" class="btn btn-lg btn-block btn-dark">구직자정보</button>
	</div>
</div>