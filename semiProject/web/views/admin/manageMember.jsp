<%@page import="com.what.semi.common.template.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* PageInfo pi = (PageInfo)request.getAttribute("pi");
int listCount = pi.getTotalCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage(); */
 %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="adminHeader.jsp" %>
<script type="text/javascript">
	
		function mType(obj) {
			var text = $(obj).text();
			var value = $(obj).val();
			$("#mBtn").text(text);
			$("#mtypeValue").val(value);
		}
		function isReport(obj) {
			var text = $(obj).text();
			var value = $(obj).val();
			$("#rBtn").text(text);
			$("#reportValue").val(value);
		}

</script>
  </head>
  <body>
	<div class="container" style="min-height: 800px">	<!-- 내용을 담아놓을 컨테이너 -->
		
		<div class="row">
    <!-- Navigation -->
    		<%@include file="adminNav.jsp" %>
    		
    		<div class="col-lg-9 mt-4">
    			<form method="post" action="/sp/blackList.do">
    			<div class="row mb-4">
    			
    				<div class="btn-group col-2">
					    <button type="button" id="rBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <span class="caret">신고 유무</span>
						</button>
					    <input type="hidden" name="reportValue" id="reportValue">
						<ul class="dropdown-menu bg-dark" role="menu">
							<li><button type="button" onclick="isReport(this);" name="isReport" class="btn btn-dark btn-sm btn-block" value="N">회원 전체</button></li>
							<li><button type="button" onclick="isReport(this);" name="isReport" class="btn btn-dark btn-sm btn-block" value="O">신고 회원</button></li>
							<li><button type="button" onclick="isReport(this);" name="isReport" class="btn btn-dark btn-sm btn-block" value="X">미신고 회원</button></li>
							<li class="divider"></li>
						</ul>
					</div>
    			
					<div class="btn-group col-2">
					    <button type="button" id="mBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <span class="caret">회원타입</span>
						</button>
					    <input type="hidden" name="mTypeValue" id="mTypeValue">
						<ul class="dropdown-menu bg-dark" role="menu">
							<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="NO">회원 전체</button></li>
							<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="JS">구직 회원</button></li>
							<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="BO">업체 회원</button></li>
						</ul>
					</div>
					<div class="input-group col-4">
					<input type="text" class="form-control mb-1" name="keyword" id="keyword" placeholder="회원명 입력"/>
				      <span class="input-group-btn">
				        <input class="btn btn-dark ml-1" type="submit" id="searchMember" value="검색">
				      </span>
					</div>
    			</div>
    			</form>
    			
    			
			<div class="list-group" id="blackList">
		     
		     <div class="list-group-item">
		     	<div class="row">
		     		<div class="col-3 btn-link">고재준</div>
		     		<div class="col-3">구직자</div>
		     		<div class="col-3">신고 3회</div>
		     		
		     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
					  <button type="button" class="btn btn-dark btn-sm">BLACKLIST 등록</button>
					  <div class="btn-group" role="group">
					    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					      	사유
					      <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu" role="menu">
					      <li>임금 미지불</li>
					      <li>근무시간 임의 변경</li>
					    </ul>
					  </div>
					</div>
		     	</div>
		      </div>
		      
		      <div class="list-group-item">
		     	<div class="row">
		     		<div class="col-lg-3 col-4 btn-link">고재준</div>
		     		<div class="col-lg-3 col-4">구직자</div>
		     		<div class="col-lg-3 col-4">신고 3회</div>
		     		
		     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
					  <button type="button" class="btn btn-dark btn-sm">BLACKLIST 등록</button>
					  <div class="btn-group" role="group">
					    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					      	사유
					      <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu" role="menu">
					      <li class="text-center">신고사유 1</li>
					      <li class="text-center">신고사유 2</li>
					    </ul>
					  </div>
					</div>
		     	</div>
		      </div>
		      
			</div>
			
          
          
        
        <!--====================================	페이지선택버튼	 ==================================  -->
	        <%-- <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);" type="button" class="btn btn-default bg-dark text-white">◀</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
						<%if(currentPage != i){ %>
						<button onclick="movePage();" type="button" class="btn btn-default bg-dark text-white">1</button>
						<%}else{ %>
						<button type="button" class="btn btn-default bg-dark text-white">1</button>
						
						<%} %>
					<%} %>
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:maxPage+1%>);" type="button" class="btn btn-default bg-dark text-white">▶</button>
			  </div>
			</div> --%>
		<!--=========================================================================================-->
        </div>
    	</div>
    </div>

    <%@include file="adminFooter.jsp" %>
  </body>
</html>
