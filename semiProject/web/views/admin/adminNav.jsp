<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/sp/blackList.do">오늘뭐해?</a>
        <p class="text-right text-white">관리자 페이지</p>
      </div>
    </nav>
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
				<button type="button" onclick="manageMember();" class="btn btn-lg btn-block btn-dark">사용자 관리</button>
				<button type="button" class="btn btn-lg btn-block btn-dark">게시물 관리</button>
				<button type="button" class="btn btn-lg btn-block btn-dark">Q&A 관리</button>
			</div>
		</div>
		
		
      