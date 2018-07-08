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
int delflag = condition.getDelflag();
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
			$("#postListForm").submit();
		}
		
		function isPost(obj) {
			var text = $(obj).text();
			var value = $(obj).val();
			console.log(text, value);
			$("#is_PostBtn").text(text);
			$("#is_Post").val(value);
			$("#postListForm").submit();
		}
		
		function delflagChange(obj) {
			var text = $(obj).text();
			var value = $(obj).val();
			console.log(text, value);
			$("#delflagBtn").text(text);
			$("#delflag").val(value);
			$("#postListForm").submit();
		}
		
		function movePage(pageNum) {
			$("#currentPage").val(pageNum);
			$("#postListForm").submit();
		}
		
		
		function deleteRecruitment(obj) {
			$.ajax({
				url : "/sp/adminDeleteRec.do",
				type : "get",
				data : {r_no : obj},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("게시물 삭제중 오류가 발생하였습니다.")
				},
				complete : function() {
					$("#postListForm").submit();
				}
			});
		}
		
		function resetRecruitment(obj) {
			$.ajax({
				url : "/sp/adminResetRec.do",
				type : "get",
				data : {r_no : obj},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("게시물 복구중 오류가 발생하였습니다.")
				},
				complete : function() {
					$("#postListForm").submit();
				}
			});
		}
		
		function deleteResume(obj) {
			$.ajax({
				url : "/sp/adminDeleteResume.do",
				type : "get",
				data : {r_no : obj},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("게시물 삭제중 오류가 발생하였습니다.")
				},
				complete : function() {
					$("#postListForm").submit();
				}
			});
		}
		
		function resetResume(obj) {
			$.ajax({
				url : "/sp/adminResetResume.do",
				type : "get",
				data : {r_no : obj},
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert("게시물 복구중 오류가 발생하였습니다.")
				},
				complete : function() {
					$("#postListForm").submit();
				}
			});
		}
		
		
		function newRecruitmentPage(r_no){
	        var win = window.open("/sp/manageRecFormView.do?r_no="+r_no,"_blank","width=1000, height=900, left=auto,top=auto");
	        var interval = window.setInterval(function() {
	        	try {
	                if (win.closed) {
	                    window.clearInterval(interval);
	                    $("#postListForm").submit();
	                }
	            }
	            catch (e) {
	            }
	        }, 10);
	    }; 
	    
		function newResumePage(r_no){
	        var win = window.open("/sp/manageResFormView.do?r_no="+r_no,"_blank","width=1000, height=900, left=auto,top=auto");
	        var interval = window.setInterval(function() {
	        	try {
	                if (win.closed) {
	                    window.clearInterval(interval);
	                    $("#postListForm").submit();
	                }
	            }
	            catch (e) {
	            }
	        }, 10);
	    }; 
		
</script>
<style type="text/css">
</style>
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
    				
			   		<div class="btn-group col-lg-3 col-md-4 mb-2" role="group" aria-label="...">
						<button class="btn btn-default btn-sm">회원타입</button>
						<div class="btn-group" role="group">
						    <button type="button" id="mBtn" class="btn btn-sm btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <%="BO".equals(memberType)?"업체 회원":"구직 회원" %>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu bg-dark" role="menu">
								<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="JS">구직 회원</button></li>
								<li><button type="button" onclick="mType(this);" name="memberType" class="btn btn-dark btn-sm btn-block" value="BO">업체 회원</button></li>
							</ul>
						</div>
					</div>
					
					<div class="btn-group col-lg-3 col-md-4 mb-2" role="group" aria-label="...">
						<button class="btn btn-default btn-sm">게시여부</button>
						<div class="btn-group" role="group">
						    <button type="button" id="is_PostBtn" class="btn btn-sm btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <%=1 == isPost?"종료 게시물":(0 == isPost?"등록 게시물":"전체 게시물") %>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu bg-dark" role="menu">
								<li><button type="button" onclick="isPost(this);" class="btn btn-dark btn-sm btn-block">전체 게시물</button></li>
								<li><button type="button" onclick="isPost(this);" class="btn btn-dark btn-sm btn-block" value="0">등록 게시물</button></li>
								<li><button type="button" onclick="isPost(this);" class="btn btn-dark btn-sm btn-block" value="1">종료 게시물</button></li>
							</ul>
						</div>
					</div>
					
					<div class="btn-group col-lg-6 col-md-4 mb-2" role="group" aria-label="...">
						<button class="btn btn-default btn-sm">삭제여부</button>
						<div class="btn-group" role="group">
						    <button type="button" id="delflagBtn" class="btn btn-sm btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						    <%=0 == delflag?"삭제 게시물":(1 == delflag?"미삭제 게시물":"전체 게시물") %>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu bg-dark" role="menu">
								<li><button type="button" onclick="delflagChange(this);" class="btn btn-dark btn-sm btn-block">전체 게시물</button></li>
								<li><button type="button" onclick="delflagChange(this);" class="btn btn-dark btn-sm btn-block" value="1">미삭제 게시물</button></li>
								<li><button type="button" onclick="delflagChange(this);" class="btn btn-dark btn-sm btn-block" value="0">삭제 게시물</button></li>
							</ul>
						</div>
					</div>
					
					<div class="input-group col-6 mb-1">
						<input type="text" class="form-control mb-1" name="content" id="content" value="<%=content %>" placeholder="게시물 제목/내용 입력"/>
					</div>
					
					<div class="col-6"></div>
					
					<div class="input-group col-6 mb-1">
						<input type="text" class="form-control mb-1" name="keyword" id="keyword" value="<%=keyword %>" placeholder="회원명/아이디/업체명 입력"/>
				      	<span class="input-group-btn">
				        	<input class="btn btn-dark ml-1" type="submit" id="searchMember" value="검색">
				      	</span>
					</div>
    			</div>
				<input type="hidden" name="delflag" id="delflag" value="<%=delflag%>">
				<input type="hidden" name="is_Post" id="is_Post" value="<%=isPost%>">
				<input type="hidden" name="member_type" id="member_type" value="<%=memberType%>">
    			<input type="hidden" name="currentPage" id="currentPage"/>
    			<input type="hidden" name="deleteResume" id="deleteResume"/>
    			<input type="hidden" name="deleteRecruitment" id="deleteRecruitment"/>
    			
    			</form>
    			
    			
			<div class="list-group" id="postList" style="min-height: 500px">
		     <%if(null != request.getAttribute("resume")){ %>
		     	<%for(ManageResumeVo mv : resumeList){ %>
			     <div class="list-group-item">
			     	<div class="row">
			     		<div class="col-3"><%=mv.getName() %>(<%=mv.getId() %>)</div>
			     		<div class="col-3">구직자</div>
			     		<div class="col-3 btn-link" onclick="newResumePage(<%=mv.getResume_id()%>);"><%=mv.getIntroduce_title() %></div>
			     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
			     			<%if(mv.getDelflag() != 0){ %>
						  <button onclick="deleteResume('<%=mv.getResume_id()%>');" class="btn btn-dark btn-sm">게시물 삭제</button>
						  	<%}else{ %>
						  <button onclick="resetResume('<%=mv.getResume_id()%>');" class="btn btn-info btn-sm">게시물 복구</button>
						  	<%} %>
						</div>
			     	</div>
			      </div>
		      	<%} %>
		      <%}else if(null != request.getAttribute("recruitment")){ %>
		      	<%for(ManageRecruitmentVo mv : recruitmentList){ %>
			     <div class="list-group-item">
			     	<div class="row">
			     		<div class="col-3"><%=mv.getName() %>(<%=mv.getM_id() %>)</div>
			     		<div class="col-3">업주</div>
			     		<div class="col-3 btn-link" onclick="newRecruitmentPage(<%=mv.getRecruitment_id()%>);"><%=mv.getRecruitment_title() %></div>
			     		
			     		<div class="btn-group col-lg-3 col-12" role="group" aria-label="...">
			     			<%if(mv.getDelflag() != 1){ %>
						  <button onclick="deleteRecruitment('<%=mv.getRecruitment_id()%>');" class="btn btn-dark btn-sm">게시물 삭제</button>
						  	<%}else{ %>
						  <button onclick="resetRecruitment('<%=mv.getRecruitment_id()%>');" class="btn btn-info btn-sm">게시물 복구</button>
						  	<%} %>
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
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:currentPage+1%>);" type="button" class="btn btn-default bg-dark text-white">▶</button>
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
