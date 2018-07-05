<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	boolean searchFlag = Boolean.parseBoolean(request.getParameter("searchFlag"));
    	boolean activeFlag = Boolean.parseBoolean(request.getParameter("activeFlag"));
    %>
<%@include file="/views/common/header.jsp" %>
<script type="text/javascript">
		function searchPw(){
			$("#searchPwFrm").submit();
		}
</script>
	<div class="container" style="min-height: 800px;">
    	<div class="row">
			<%@include file="/views/common/nav.jsp" %>
				<!-- /.col-lg-3 -->
        		<div class="col-lg-9 mt-lg-auto">
        			<div class="row mt-4">
        	  			<div class="col-lg-3"></div>
			  			<div class="col-lg-6 bg-dark">
			  				<br><br>
			  				<%if(!activeFlag){ %>
								<form action="/sp/searchPw.do" method="post" id="searchPwFrm">
								<input type="text" id="id" name="id" placeholder="아이디"/>
								<input type="text" id="email" name="email" placeholder="이메일 입력"/>
								<input type="button" onclick="searchPw();" value="비밀번호 찾기"/>
								</form>
							<%}else{ 
								if(searchFlag){%>
									<h3 align="center" class="text-white">입력하신 메일로 새 비밀번호를 전송하였습니다.</h3>
								<%}else{ %>
									<h3 align="center" class="text-white">회원가입이 되어있지 않거나, 입력하신 정보가 틀렸습니다.</h3>
									<button onclick="/views/member/searchPw.jsp" class="btn btn-dark btn-sm btn-inline">이전으로 돌아가기</button>
							<%}} %>
						</div>
					</div>
				</div>
		<!-- /.row -->
     	</div>
    <!-- /.row -->
    </div>
<%@include file="/views/common/footer.jsp"%>