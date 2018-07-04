<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean searchFlag = Boolean.parseBoolean(request.getParameter("searchFlag"));
	boolean activatedFlag = Boolean.parseBoolean(request.getParameter("activatedFlag"));
	String resultId = request.getParameter("resultId");
	
	System.out.println(resultId);
	System.out.println(searchFlag);
	System.out.println(activatedFlag);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
<script src="/sp/vendor/jquery/jquery.min.js"></script>
</head>
<body>
	<%if(!activatedFlag){ %>
	<form action="/sp/searchId.do" method="post" id="searchIdFrm">
		<input type="text" id="email" name="email" placeholder="이메일 입력"/>
		<input type="button" onclick="searchId();" value="아이디 찾기"/>
	</form>
	<%} else { %>
	아이디 : <%=resultId%>입니다.
	<%} %>
	<script type="text/javascript">
		function searchId(){
			$("#searchIdFrm").submit();
		}
	</script>
</body>
</html>