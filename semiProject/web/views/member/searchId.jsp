<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<%
	boolean searchFlag = Boolean.parseBoolean(request.getParameter("searchFlag"));
	boolean activatedFlag = Boolean.parseBoolean(request.getParameter("activatedFlag"));
	String resultId = request.getParameter("resultId");
	
	System.out.println(resultId);
	System.out.println(searchFlag);
	System.out.println(activatedFlag);
	
%>
<script type="text/javascript">
		function searchId(){
			$("#searchIdFrm").submit();
		}
<div class="container" style="min-height: 800px;">
      <div class="row">
		<%@include file="/views/common/nav.jsp" %>
	<%if(!activatedFlag){ %>
	<form action="/sp/searchId.do" method="post" id="searchIdFrm">
		<input type="text" id="email" name="email" placeholder="이메일 입력"/>
		<input type="button" onclick="searchId();" value="아이디 찾기"/>
	</form>
	<%} else { %>
	아이디 : <%=resultId%>입니다.
	<%} %>
<!-- /.row -->
      </div>
      <!-- /.row -->
    </div>
<%@include file="/views/common/footer.jsp"%>