<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	String member_type = (String)session.getAttribute("member_type");
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>[오늘뭐해?]홈페이지</title>
    <!-- Bootstrap core CSS -->
    <link href="/sp/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    
   <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>

    <!-- Custom styles for this template -->
    <link href="/sp/css/shop-homepage.css" rel="stylesheet">
	
    <link href="/sp/css/daum.css" rel="stylesheet">
    <script src="/sp/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		function loadLocalList() {
			location.href = "/sp/localList.do";
		}
		
		
		//추후 회원의 구인게시물/이력서를 불러올 function - 회원정보를 parameter로 설정해야함
		function loadMyList() {
			location.href = "/sp/views/member/join.jsp"
		}
		
		//추후 회원정보변경 페이지를 불러올 function - 회원정보를 parameter로 설정해야함
		function updateMyInfo() {
			location.href = "/sp/views/recruitment/recruitmentForm.jsp"
		}
		//Q&A 게시판
	      function qnaNew(){
	          window.open("/sp/views/qna/qnaNew.jsp","_blank","width=600, height=700, left=auto,top=auto");
	       };
	</script>
	<style>
.card_he {
	height: 500px;
}
</style>
  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/sp/indexList.do">오늘뭐해?</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          <%if(id != null){ %>
            <li class="nav-item">
              <a class="nav-link" href="/sp/views/member/pwCheck.jsp">개인정보수정
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <%if(member_type.equals("JS")){ %>
            <li class="nav-item">
              <a class="nav-link" href="/sp/getResume.do">이력서관리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/sp/myWorkedList.do">근로내역</a>
            </li>
            <%} %>
            <%if(member_type.equals("BO")){ %>
            <li class="nav-item">
              <a class="nav-link" href="/sp/myRecruitmentList.do">업체정보관리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/sp/myHiredList.do">고용내역</a>
            </li>
            <%} %>
            <li class="nav-item">
              <a class="nav-link btn" style="text-align: left;" onclick="qnaNew();">Q&A</a>
            </li>
            <%-- <%if(member_type == "JS"){ %>
            <li class="nav-item">
              <a class="nav-link" href="#">근로내역</a>
            </li>
            <%} %>
            <%if(member_type == "BO"){ %>
            <li class="nav-item">
              <a class="nav-link" href="#">고용내역</a>
            </li>
            <%} %> --%>
            <li class="nav-item">
              <a class="nav-link" href="/sp/views/member/dropMember.jsp">회원탈퇴</a>
            </li>
            
           <%} %>
          </ul>
        </div>
      </div>
    </nav>
