<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<%
	boolean searchFlag = Boolean.parseBoolean(request.getParameter("searchFlag"));
	boolean activatedFlag = Boolean.parseBoolean(request.getParameter("activatedFlag"));
	String resultId = request.getParameter("resultId");
%>
<script type="text/javascript">
		function searchId(){
			$("#searchIdFrm").submit();
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
							<%if(!activatedFlag){ %>
								<form action="/sp/searchId.do" method="post" id="searchIdFrm">
								<input type="text" id="email" name="email" placeholder="이메일 입력"/>
								<input type="button" onclick="searchId();" value="아이디 찾기"/>
								</form>
							<%} else { %>
								<%if(searchFlag){ %>
									<h3 align="center" class="text-white">아이디 : <%=resultId%>입니다.</h3>
								<%}else{ %>
									<h3 align="center" class="text-white">등록되지 않은 이메일입니다.</h3>
								<%} %>
							<%} %>
						</div>
					</div>
				</div>
		<!-- /.row -->
     	</div>
    <!-- /.row -->
    </div>
<%@include file="/views/common/footer.jsp"%>