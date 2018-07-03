<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>
<%
	ArrayList<MyResumeVo> userType = (ArrayList<MyResumeVo>) request.getAttribute("userType");
	int is_post = 0;
	boolean userTypeFlag= false;
	if(0!=userType.size()){
		is_post = userType.get(0).getIs_post();
		userTypeFlag = true;
	}
%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<style>
.btn-link {
	cursor: pointer;
}

.wrap {
	padding-top: 20px;
}

.buttonDiv { 
	text-align: center;
}
.pri_resumeArea{
	text-align:center;
	border:1px solid black;
}
#tableHead{
	text-align:center;
}
.tableArea{
	padding-top:30px;
}
.previewResume{
	cursor:pointer;
}
</style>
<script>
	function updateRecruit() {
	location.href = "/sp/views/recruitment/recruitmentForm.jsp";
	}

	function updateResume() {
	location.href = "/sp/views/resume/memberResume.jsp";
	}

	function modifyIs_post(){
		var result = confirm("이력서의 상태를 수정 하시겠습니까?");
		if(result){
			if(<%=userTypeFlag%>){
			location.href="/sp/modifyIs_post.do?id=<%=id%>"+"&is_post=<%=is_post%>";
			}else{
				alert("등록된 이력서가 없습니다!!");
			}
		}
	}
	function movePage() {
		location.href = "/sp/views/resume/memberResume.jsp";
	}
	function updateState(){
		
		$("#postNum").submit();
	}
	function modifyResume(resume_id){
		location.href = "/sp/selectedResume.do?resume_id="+resume_id+"&userId=<%=id%>";
	}
	function deleteResume(resume_id){
		var result = confirm("해당 이력서를 삭제 하시겠습니까?");
		if(result){
			location.href = "/sp/deleteResume.do?resume_id="+resume_id+"&userId=<%=id%>";	
		}
	}

	$(function() {
		$("#popbutton").click(function() {
			if(<%=userTypeFlag%>){
				$('div.modal').modal();			
			}else{
				alert("등록된 이력서가 없습니다!!");
			}
		})
		
		$("#resumeForm").click(function(){
			if(<%=userType.size()%>>=2){
				alert("이력서는 2건까지 작성가능합니다.");
			}else{
				location.href = "/sp/getMemberInfo.do?userId=<%=id%>";
			}
		});
		$(".previewResume").on("click", function(){
			alert("asdf");
		});
	});
</script>
<div class="container" style="min-height: 800px">
	<!-- 내용을 담아놓을 컨테이너 -->

	<div class="row">

		<%@include file="/views/common/nav.jsp"%>
		<div class="col-lg-9 wrap">
			<!-- Page Content -->
			<h1>이력서/업체관리</h1>
			<hr>
			<div class="row">
				<div class="col-lg-6 expArea">
					<dl>
						<dt>이력서 설정시 유의사항</dt>
						<dd>이력서는 총 2건 작성 가능합니다</dd>
						<dd>이력서는 대표이력서 하나만 공개 가능합니다!!</dd>
						<dd>아래의 대표이력서 설정 을 통해서 확인해 주세요</dd>
					</dl>
					</div>
				<div class="col-lg-6 pri_resumeArea">
				 <h1>현재상태 </h1>
				 <p><%=is_post==1?"공개중":"비공개" %></p>
				 <button class="btn btn-success" onclick="modifyIs_post();"> 수정하기</button>
				</div>
			</div>
			<div class="row tableArea">
			<table class="table">
				<tr id="tableHead">
					<th colspan="5">이력서 목록 <%=userType.size() %>/ 2</th>
				</tr>
				<%if(userTypeFlag){ %>
				<%for (int i = 0; i < userType.size(); i++) { %>
				<tr>
					<td>
						<h5><%=userType.get(i).getIntroduce_title()%></h5>
					</td>
					<td>
						<p><strong><%=userType.get(i).getPri_resume() == 'Y' ? "대표이력서" : ""%></strong></p>
					</td>
					<td>
						<button type="button" class="btn btn-default btn-xs btn-info" onclick="modifyResume(<%=userType.get(i).getResume_id()%>);">수정</button>
					</td>
					<td>
						<button type="button" class="btn btn-default btn-xs btn-info" onclick="deleteResume(<%=userType.get(i).getResume_id()%>);">삭제</button>
					</td>
					<td>
						<img class="previewResume" src="/sp/images/resume.png" height="40px"/>
					</td>
				</tr>
				<% } %>
				<%}else{ %>
							<tr>
								<td colspan="5"><h4>등록된 이력서가 없습니다.</h4></td>
							</tr>
				<%} %>
					</table>
					</div>
			</div>
			<div class="col-lg-12 buttonDiv">
				<button id="popbutton" class="btn btn-success"> 대표 이력서 설정</button>
				<button id ="resumeForm" class = "btn btn-success">이력서 쓰기</button>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
<!-- 팝업 모달영역 -->
<div class="modal fade" id="layerpop">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<!-- 닫기(x) 버튼 -->
				<!-- header title -->
				<h4 class="modal-title">이력서 선택</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
			</div>
			<!-- body -->
			<div class="modal-body">
				<form id="postNum" method="get" action="/sp/modifyState.do">
				<input type="hidden" name="userId" value="<%=id%>"/>
				<div class="radio">
				<%for (int i = 0; i < userType.size(); i++) {%>
				<%if(userType.get(i).getPri_resume()=='Y' || userType.size()!=2){ %>
					<label for="post<%=i%>"><%=userType.get(i).getIntroduce_title()%>
						<input id="post<%=i %>" type="radio" value="<%=userType.get(i).getResume_id()%>" name="resume_id" checked/>
					</label><br>
				<%}else{ %>
					<label for="post<%=i%>"><%=userType.get(i).getIntroduce_title()%>
						<input id="post<%=i %>" type="radio" value="<%=userType.get(i).getResume_id()%>" name="resume_id"/>
					</label><br>
					<%} %>
				<%} %>
				</div>
				</form>
				<hr>
				<button type="button" class="btn btn-success" onclick="updateState();">수정</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button> 
			</div>
		</div>
	</div>
</div>
<%@include file="/views/common/footer.jsp"%>