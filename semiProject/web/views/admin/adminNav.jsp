<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script>
    function logout(){
		location.href="/sp/views/member/logout.jsp";
	}
    </script>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/sp/blackList.do">오늘뭐해?</a>
        <p class="text-right text-white">관리자 페이지</p>
      </div>
		</nav>
	   	<div class="col-lg-3">
		<br><br>
		<div class="list-group">
			<table>
				<tr>
					<td>
						<h5 class="text-center">관리자님 환영합니다</h5>
						<button type="button" class="btn btn-dark btn-sm btn-block" onclick="logout();">로그아웃</button>
					<td>
				</tr>
			</table>
		</div>
		<br><br>
		<div class="list-group">
			<button type="button" onclick="manageMember();" class="btn btn-lg btn-block btn-dark">사용자 관리</button>
			<button type="button" class="btn btn-lg btn-block btn-dark">게시물 관리</button>
			<button type="button" class="btn btn-lg btn-block btn-dark">Q&A 관리</button>
		</div>
	</div>
		
		
      