<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.what.semi.contract.model.vo.ContractVo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
ContractVo cont = (ContractVo)request.getAttribute("cont");
RecruitmentVo rec = (RecruitmentVo)request.getAttribute("rec");
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
int result = (int)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="/sp/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>

<!-- Custom styles for this template -->
<link href="/sp/css/shop-homepage.css" rel="stylesheet">

<link href="/sp/css/daum.css" rel="stylesheet">
<script src="/sp/vendor/jquery/jquery.min.js"></script>
<script>
	function writeTime() {
		$.ajax({
			url : "/sp/timeStamp.do",
			type : "get",
			data : {
				contId : <%=cont.getC_no()%>
			},
			success : function(data) {
				console.log(data);
				var bool = confirm('근무시간이 기록되었습니다. 확인하시겠습니까?');
				if(bool){
				location.href="myWorkedList.do?contId=" + data.contId + "&currentPage=" + data.currentPage;
				}else{
					self.close();
				}
			},
			error : function(e) {
				console.log("error", e);
			}
		});
	}

	<%-- $(function() {
		if(<%=result%>!=0){
			alert("근무시간이 기록되었습니다.");
			self.close();
		}
	}); --%>
</script>
<style type="text/css">
.space {
	width: 800px;
	height: 30px;
}
</style>
</head>
<body>
	<%@include file="header.jsp"%>
	<div>
		<div class="space"></div>
		<%if(rec!=null){ %>
		<div align="center">
			<%if(cont.getStart_work_time()==null){ %>
			<%=cont.getJs_id() %>님,
			<<%=rec.getRecruitment_name() %>>에서
			<%=sf.format(nowTime) %>로 근무를 시작하시겠습니까?<br>
			<br>
			<button type="button" class="btn btn-default bg-dark text-white"
				onclick="writeTime();">근무시작하기</button>
			<%}else{ %> <%=cont.getJs_id() %>님, <<%=rec.getRecruitment_name() %>>
			에서 <%=sf.format(nowTime)%> 로 근무를 마치시겠습니까?<br>
			<br>
			<button type="button" class="btn btn-default bg-dark text-white"
				onclick="writeTime();">근무끝내기</button>
			<%
				}
			%>
			
		</div>
		<%
			}
		%>
		<div class="space"></div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>