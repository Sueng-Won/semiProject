<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<RecruitmentVo> list = (ArrayList<RecruitmentVo>) request.getAttribute("list");

	/* java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM / dd"); */
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int listCount = pi.getTotalCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>

<%@include file="/views/common/header.jsp"%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function writeRec(){
		location.href = "/sp/views/recruitment/recruitmentForm.jsp";
	}
	
	function recruitmentDetail(i){
		location.href="/sp/recruitmentDetail.do?recId="+i+"&currentPage="+<%=currentPage%>;
	}
		
	function modifyRec(i){
		location.href = "/sp/updateRecForm.do?recId="+i;
	}
	
	function deleteRec(i){
		location.href = "/sp/deleteRecruitment.do?recId="+i;
	}

</script>
<style>
.btn-link {
	cursor: pointer;
}

.list-table {
	border-top: 2px #5D5D5D solid;
	margin-top: 20px;
	padding-left: 100px;
	padding-right: 20px;
	width: 700px;
}

.list-table tr {
	border-top: 1px #cccccc solid;
}

.table-td {
	padding: 5px;
	min-width: 80px;
}

.table-td-location {
	padding: 5px;
	min-width: 100px;
}

.td-title {
	width: 350px;
}
</style>
<div class="container" style="min-height: 800px">
	<!-- 내용을 담아놓을 컨테이너 -->

	<div class="row">

		<%@include file="/views/common/nav.jsp"%>
		<div class="col-lg-9" style='padding-top: 50px; padding-left: 30px;'>
			<h3>구인게시글 관리</h3>
			<!-- Page Content -->
			<!-- <div class="media mt-4 border rounded bg-light">
		     
			  	  <div class="media-left media-middle">
			    	<a href="/sp/views/recruitment/recruitmentForm.jsp">
			     	 <img class="media-object btn-link" onclick="updateRecruit();" src="http://placehold.it/150x150" alt="...">
			    	</a>
			  	</div>
			  	<div class="media-body">
			    	<h4 class="media-heading btn-link" onclick="updateRecruit();">[구인게시글 제목]</h4>
			    	세부사항
			  	</div>
			
			</div> -->

			<table class="list-table">
				<tbody>
					<tr>
						<th><div class="table-td">게시여부</div></th>
						<th><div class="table-td">근무형태</div></th>
						<th><div class="td-title">기업명/글제목</div></th>
						<th><div class="table-td-location">지역</div></th>
						<th><div class="table-td">급여</div></th>
						<td style="width:20px;">
						</td>
						<td style="width:20px;">
						</td>
					</tr>
					<%
						for (int i = 0; i < list.size(); i++) {
							String[] locationArr = list.get(i).getAddress().split(" ");
							String location = locationArr[0] + " " + locationArr[1];
					%>
					<tr>
						<td class="table-td" onclick="recruitmentDetail(<%=list.get(i).getRecruitment_id()%>);">
							<%
								if (list.get(i).getIs_post() == 0) {
							%>기간만료<%
								} else {
							%>게시중<%
								}
							%>
						</td>
						<%-- <td class="table-td"><%=df.format(list.get(i).getWork_day()) %></td> --%>
						<td class="table-td" onclick="recruitmentDetail(<%=list.get(i).getRecruitment_id()%>);"><%=list.get(i).getBusiness_type()%></td>
						<td class="td-title" onclick="recruitmentDetail(<%=list.get(i).getRecruitment_id()%>);"><b><%=list.get(i).getRecruitment_name()%></b><br><%=list.get(i).getRecruitment_title()%></td>
						<td class="table-td-location" onclick="recruitmentDetail(<%=list.get(i).getRecruitment_id()%>);"><%=location%></td>
						<td class="table-td" onclick="recruitmentDetail(<%=list.get(i).getRecruitment_id()%>);"><%=list.get(i).getPay()%>원
						</td>
						<td>
							<button type="button" class="btn btn-default btn-xs btn-info"
								onclick="modifyRec(<%=list.get(i).getRecruitment_id()%>);">수정</button>
						</td>
						<td>
							<button type="button" class="btn btn-default btn-xs btn-info"
								onclick="deleteRec(<%=list.get(i).getRecruitment_id()%>);">삭제</button>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>

			<!--====================================	페이지선택버튼	 ==================================  -->
			<div class="btn-toolbar mb-1" role="toolbar">
				<div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage == 1 ? 1 : currentPage - 1%>);"
						type="button" class="btn btn-default bg-dark text-white"><</button>
					<%
						for (int i = startPage; i <= endPage; i++) {
					%>
					<%
						if (currentPage != i) {
					%>
					<button onclick="movePage(<%=i%>);" type="button"
						class="btn btn-default bg-dark text-white"><%=i%></button>
					<%
						} else {
					%>
					<button type="button" class="btn btn-default bg-dark text-white"><%=i%></button>

					<%
						}
					%>
					<%
						}
					%>
					<button
						onclick="movePage(<%=currentPage == maxPage ? maxPage : maxPage + 1%>);"
						type="button" class="btn btn-default bg-dark text-white">></button>
				</div>

			</div>
			<div align="right">
				<button onclick="writeRec();"
					class="btn btn-default bg-dark text-white">구인글 작성하기</button>
			</div>
			<!--=========================================================================================-->

		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
<script type="text/javascript">
	function movePage(pageNum) {
		location.href = "/sp/myRecruitmentList.do?currentPage="+pageNum;
	}
</script>
<%@include file="/views/common/footer.jsp"%>