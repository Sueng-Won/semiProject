<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    	.btn{
    		color: white;
    	}
    </style>
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

	<div><h5 id="text">로그인이 필요합니다.</h5></div>
<script>
	$(function(){
		<%if(name!=null){%>
			$("#text").text("<%=name%>님 환영합니다!");
		<%}%>
	});
	/* $("#text").text(name+"님 환영합니다!"); */
</script>
	<input type="hidden" name="hd" value=""/>
	<input type="hidden" name="name" value=""/>
</form>
	</h1>
	<div class="list-group">
		<!-- <a href="#" class="list-group-item active">맞춤알바</a> -->
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">맞춤알바</button>
		<!-- <a onclick="loadLocalList();" class="list-group-item">지역알바</a> --> <!-- 지역 알바로 이동 -->
		<button type="button" onclick="loadLocalList();" class="btn btn-default btn-lg btn-block bg-dark">지역알바</button>
		<!-- <a href="#" class="list-group-item">일별알바</a>
		<a href="#" class="list-group-item">구직자정보</a> -->
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">일별알바</button>
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">구직자정보</button>
	</div>
</div>