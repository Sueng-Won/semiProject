<%@page import="com.what.semi.managePost.model.vo.PostCondition"%>
<%@page import="com.what.semi.managePost.model.vo.ManageResumeVo"%>
<%@page import="com.what.semi.managePost.model.vo.ManageRecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ManageRecruitmentVo> recruitmentList = 
		(ArrayList<ManageRecruitmentVo>) request.getAttribute("recruitment");
ArrayList<ManageResumeVo> resumeList = (ArrayList<ManageResumeVo>) request.getAttribute("resume");
	
PageInfo pi = (PageInfo)request.getAttribute("pi");

int listCount = pi.getTotalCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();

PostCondition condition = (PostCondition) request.getAttribute("condition");
int isPost = condition.getIs_post();
String content = condition.getContent();
String memberType = condition.getMember_type();
String keyword = condition.getUserName();
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
			$("#member_type").val(value);
		}
		
		function isPost(obj) {
			var text = $(obj).text();
			var value = $(obj).val();
			console.log(text, value);
			$("#is_PostBtn").text(text);
			$("#is_Post").val(value);
		}
		
		function movePage(pageNum) {
			$("#currentPage").val(pageNum);
			$("#postListForm").submit();
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
    			<h2 align="center" class="mb-3">게시물 관리</h2>
    			<form id="postListForm" method="post" action="/sp/managePost.do">
    			<div class="row mb-4">
    				
			   		
					<div class="btn-group col-6 mb-1">
					    <button type="button" id="mBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    <%="".equals(memberType)?"회원 타입": ("BO".equals(memberType)?"업체 회원":("JS".equals(memberType)?"구직 회원":"회원 전체")) %>
						    <span class="caret"></span>
						</button>
					    <input type="hidden" name="member_type" id="member_type" value="<%=memberType%>">
						<ul class="dropdown-menu bg-dark" role="menu">
							<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="NO">회원 전체</button></li>
							<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="JS">구직 회원</button></li>
							<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="BO">업체 회원</button></li>
						</ul>
					</div>
					
					<div class="btn-group col-6 mb-1">
					    <button type="button" id="is_PostBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    <%=0 == isPost?"종료 게시물":(1 == isPost?"등록 게시물":"전체 게시물") %>
						    <span class="caret"></span>
						</button>
					    <input type="hidden" name="is_Post" id="is_Post" value="<%=isPost%>">
						<ul class="dropdown-menu bg-dark" role="menu">
							<li><button type="button" onclick="isPost(this);" class="btn btn-dark btn-sm btn-block">전체 게시물</button></li>
							<li><button type="button" onclick="isPost(this);" class="btn btn-dark btn-sm btn-block" value="0">종료 게시물</button></li>
							<li><button type="button" onclick="isPost(this);" class="btn btn-dark btn-sm btn-block" value="1">등록 게시물</button></li>
						</ul>
					</div>
					
					
					<div class="input-group col-6 mb-1">
						<input type="text" class="form-control mb-1" name="content" id="content" value="<%=content %>" placeholder="구직 내용 입력"/>
					</div>
					
					<div class="col-6"></div>
					
					<div class="input-group col-6 mb-1">
						<input type="text" class="form-control mb-1" name="keyword" id="keyword" value="<%=keyword %>" placeholder="회원명/아이디 입력"/>
				      	<span class="input-group-btn">
				        	<input class="btn btn-dark ml-1" type="submit" id="searchMember" value="검색">
				      	</span>
					</div>
    			</div>
    			<input type="hidden" name="currentPage" id="currentPage"/>
    			<input type="hidden" name="deleteResume" id="deleteResume"/>
    			<input type="hidden" name="deleteRecruitment" id="deleteRecruitment"/>
    			
    			</form>
    			
    			
			<div class="list-group" id="blackList" style="min-height: 500px">
		     <%if(null != request.getAttribute("resume")){ %>
		     	<%for(ManageResumeVo mv : resumeList){ %>
			     <div class="list-group-item">
			     	<div class="row">
			     		<div class="col-3"><%=mv.getName() %>(<%=mv.getId() %>)</div>
			     		<div class="col-3">구직자</div>
			     		<div class="col-3 btn-link" onclick=""><%=mv.getIntroduce_title() %></div>
			     		
			     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
						  <button onclick="deleteResume('<%=mv.getResume_id()%>');" class="btn btn-dark btn-sm">게시물 삭제</button>
						</div>
			     	</div>
			      </div>
		      	<%} %>
		      <%}else if(null != request.getAttribute("recruitment")){ %>
		      	<%for(ManageRecruitmentVo mv : recruitmentList){ %>
			     <div class="list-group-item">
			     	<div class="row">
			     		<div class="col-3"><%=mv.getName() %>(<%=mv.getM_id() %>)</div>
			     		<div class="col-3">구직자</div>
			     		<div class="col-3 btn-link" onclick=""><%=mv.getRecruitment_title() %></div>
			     		
			     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
						  <button onclick="deleteRecruitment('<%=mv.getRecruitment_id()%>');" class="btn btn-dark btn-sm">게시물 삭제</button>
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
