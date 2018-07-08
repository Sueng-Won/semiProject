<%@page import="com.what.semi.blackList.model.vo.ConditionVo"%>
<%@page import="com.what.semi.blackList.model.vo.ReportVo"%>
<%@page import="com.what.semi.blackList.model.vo.BlackListVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<BlackListVo> list = (ArrayList<BlackListVo>) request.getAttribute("list");
PageInfo pi = (PageInfo)request.getAttribute("pi");

int listCount = pi.getTotalCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();

ConditionVo condition = (ConditionVo) request.getAttribute("condition");
String isReport = condition.getIsReport();
String memberType = condition.getMemberType();
String keyword = condition.getKeyword();
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
			$("#mTypeValue").val(value);
			$("#blackListForm").submit();
		}
		
		function Reported(obj) {
			var text = $(obj).text();
			var value = $(obj).val();
			console.log(text, value);
			$("#rBtn").text(text);
			$("#reportValue").val(value);
			$("#blackListForm").submit();
		}
		
		function movePage(pageNum) {
			$("#currentPage").val(pageNum);
			$("#blackListForm").submit();
		}
		function updateBList(userId) {
			console.log(userId);
			$("#blackListId").val(userId);
			$("#blackListForm").submit();
		}
		
		function deleteBList(userId) {
			console.log(userId);
			$("#deleteblackId").val(userId);
			$("#blackListForm").submit();
		}
</script>
  </head>
  <body>
	<div class="container" style="min-height: 800px">	<!-- 내용을 담아놓을 컨테이너 -->
		
		<div class="row">
    <!-- Navigation -->
    		<%@include file="adminNav.jsp" %>
    		
    		<div class="col-lg-9 mt-4">
    			<h2 align="center" class="mb-3">사용자 관리</h2>
    			<form id="blackListForm" method="post" action="/sp/blackList.do">
    			<div class="row mb-4">
    			
    				<div class="btn-group col-lg-3 col-md-4 mb-2" role="group" aria-label="...">
						<input class="btn btn-default btn-sm" type="submit" value="신고여부">
						<div class="btn-group" role="group">
						    <button type="button" id="rBtn" class="btn btn-sm btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <%="O".equals(isReport)?"신고 회원":("X".equals(isReport)?"미신고 회원":"회원 전체") %>
							    <span class="caret"></span>
							</button>
						    <input type="hidden" name="reportValue" id="reportValue" value="<%=isReport%>">
							<ul class="dropdown-menu bg-dark" role="menu">
								<li><button type="button" onclick="Reported(this);" name="isReport" class="btn btn-dark btn-sm btn-block" value="N">회원 전체</button></li>
								<li><button type="button" onclick="Reported(this);" name="isReport" class="btn btn-dark btn-sm btn-block" value="O">신고 회원</button></li>
								<li><button type="button" onclick="Reported(this);" name="isReport" class="btn btn-dark btn-sm btn-block" value="X">미신고 회원</button></li>
								<li class="divider"></li>
							</ul>
						</div>
					</div>
    			
					<div class="btn-group col-lg-3 col-md-4 mb-2" role="group" aria-label="...">
						<input class="btn btn-default btn-sm" type="submit" value="회원타입">
						<div class="btn-group" role="group">
						    <button type="button" id="mBtn" class="btn btn-sm btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <%="BO".equals(memberType)?"업체 회원":("JS".equals(memberType)?"구직 회원":"회원 전체") %>
							    <span class="caret"></span>
							</button>
						    <input type="hidden" name="mTypeValue" id="mTypeValue" value="<%=memberType%>">
							<ul class="dropdown-menu bg-dark" role="menu">
								<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="NO">회원 전체</button></li>
								<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="JS">구직 회원</button></li>
								<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="BO">업체 회원</button></li>
							</ul>
						</div>
					</div>
					<div class="input-group col-md-6 col-xs-5 col-12 mb-1">
					<input type="text" class="form-control mb-1" name="keyword" id="keyword" value="<%=keyword %>" placeholder="회원명/아이디 입력"/>
				      <span class="input-group-btn">
				        <input class="btn btn-dark ml-1" type="submit" id="searchMember" value="검색">
				      </span>
					</div>
    			</div>
    			<input type="hidden" name="currentPage" id="currentPage">
    			<input type="hidden" name="blackListId" id="blackListId">
    			<input type="hidden" name="deleteblackId" id="deleteblackId">
    			
    			</form>
    			
    			
			<div class="list-group" id="blackList" style="min-height: 500px">
		     <%if(0 < list.size() && !"null".equals(list)){ %>
		     	<%for(BlackListVo bv : list){ %>
			     <div class="list-group-item">
			     	<div class="row">
			     		<div class="col-3"><%=bv.getM_name() %>(<%=bv.getM_id() %>)</div>
			     		<div class="col-3"><%=bv.getM_type().equals("JS")?"구직자":"업주" %></div>
			     		<div class="col-3">신고 <%=bv.getTotalCount() %>회</div>
			     		
			     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
			     		  <%if(bv.getIs_black_list() == 0){ %>
						  <button onclick="updateBList('<%=bv.getM_id()%>');" class="btn btn-dark btn-sm">BLACKLIST 등록</button>
						  <%}else{ %>
						  <button onclick="deleteBList('<%=bv.getM_id()%>');" class="btn btn-info btn-sm">BLACKLIST 해제</button>
						  <%} %>
						  <div class="btn-group" role="group">
						    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						      	사유
						      <span class="caret"></span>
						    </button>
						    <ul class="dropdown-menu" role="menu">
						    	<%if(0 < bv.getReport().size()){ %>
						    	<%for(ReportVo rv : bv.getReport()){ %>
						      <li><%=rv.getReason() %> / <%=rv.getCount() %>회 -<%=rv.getB_date() %>-</li>
						      	<%} %>
						      	<%} %>
						    </ul>
						  </div>
						</div>
			     	</div>
			      </div>
		      	<%} %>
		      <%} %>
			</div>
			
          
          
        
        <!--====================================	페이지선택버튼	 ==================================  -->
	        <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
			  <%if(null != pi){ %>
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);" type="button" class="btn btn-default bg-dark text-white">◀</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
						<%if(currentPage != i){ %>
						<button onclick="movePage(<%=i %>);" type="button" class="btn btn-default bg-dark text-white"><%=i %></button>
						<%}else{ %>
						<button type="button" class="btn btn-default bg-dark text-white"><%=i %></button>
						
						<%} %>
					<%} %>
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:maxPage+1%>);" type="button" class="btn btn-default bg-dark text-white">▶</button>
				<%} %>
			  </div>
			</div>
		<!--=========================================================================================-->
        </div>
    	</div>
    </div>
    <%@include file="adminFooter.jsp" %>
  </body>
</html>
