<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)session.getAttribute("name");
	System.out.println("이름 = "+name);
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="adminHeader.jsp" %>
  </head>
  <body>
	<div class="container" style="min-height: 700px">	<!-- 내용을 담아놓을 컨테이너 -->

      <div class="row">
    <!-- Navigation -->
    <%@include file="adminNav.jsp" %>
    
    	</div>
    </div>

    <%@include file="adminFooter.jsp" %>
  </body>

</html>