<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.contract.model.vo.ContractVo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<ContractVo> myConList = (ArrayList<ContractVo>) request.getAttribute("myConList");
	ArrayList<RecruitmentVo> conRecList = (ArrayList<RecruitmentVo>) request.getAttribute("conRecList");
	int contId = (int) request.getAttribute("contId");

	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM / dd");

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
	function contractRecDetail(i) {
		location.href = "/sp/contractRecDetail.do?recId=" + i;
	}

	function accept(i) {
		location.href = "/sp/acceptRecruitment.do?contId="+i;
	}

	function reject(i) {
		location.href = "/sp/rejectRecruitment.do?contId=" + i+"&currentPage="+<%=currentPage%>;
	}
	
	$(function(){
		<%if (contId != -1) {%>
		console.log(<%=contId%>);
			$("#conDetails<%=contId%>").addClass('show');
		<%}%>
	});
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
			<h3>근로내역 관리</h3>
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

			<table class="list-table" id="myConTable">
				<tr>
					<th><div class='table-td'>계약일</div></th>
					<th><div class='td-title'>기업명</div></th>
					<th><div class='table-td'>계약상태</div></th>
					<td style='width: 20px;'></td>
				</tr>
				<%
					for (int i = 0; i < myConList.size(); i++) {
				%>
				<tr>
					<td class="table-td"><%=myConList.get(i).getContract_date()%></td>
					<%
						for (int j = 0; j < conRecList.size(); j++) {
								if (myConList.get(i).getRecruitment_id().equals(conRecList.get(j).getRecruitment_id())) {
					%>
					<td class="td-title"><b><%=conRecList.get(j).getRecruitment_name()%></b></td>
					<%
						}
							}
					%>
					<td class="table-td">
						<%
							if (myConList.get(i).getState() == 0) {
						%>요청중<%
							} else if (myConList.get(i).getState() == 1) {
						%>진행중<%
							} else {
						%>완료<%
							}
						%>
					</td>
					<td>
						<button type="button" class="btn btn-default btn-xs btn-info"
							data-toggle="collapse"
							data-target="#conDetails<%=myConList.get(i).getC_no()%>">상세보기</button>

					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="conDetails<%=myConList.get(i).getC_no()%>"
							class="collapse">
							<div style="margin: 0 50px 0 50px;">
								<div style="height: 50px;">
									<div style="float: left;">
										<button style="margin-top: 10px;"
											onclick="contractRecDetail(<%=myConList.get(i).getRecruitment_id()%>);">해당
											구인게시글 보기</button>
									</div>
									<div style="float: right;">
										<div>
											근무 시작 시간 :
											<%
											if (myConList.get(i).getStart_work_time() != null) {
										%><%=myConList.get(i).getStart_work_time()%>
											<%
												}
											%>
										</div>
										<div>
											근무 종료 시간 :
											<%
											if (myConList.get(i).getEnd_work_time() != null) {
										%><%=myConList.get(i).getEnd_work_time()%>
											<%
												}
											%>
										</div>
									</div>
								</div>
								<div align="right">
									<%
										if (myConList.get(i).getState() == 0) {
									%>
									<button onclick="accept(<%=myConList.get(i).getC_no()%>);"
										class="btn btn-default bg-dark text-white">수락</button>
									<button onclick="reject(<%=myConList.get(i).getC_no()%>);"
										class="btn btn-default bg-dark text-white">거절</button>
									<%
										} else if (myConList.get(i).getState() == 1) {
									%>
									진행중 입니다~^3^
									<%
										} else {
									%>
									<button
										onclick="addBlacklist(<%=myConList.get(i).getRecruitment_id()%>);"
										class="btn btn-default bg-dark text-white">신고하기</button>
									<%
										}
									%>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<%
					}
				%>
			</table>



			<!--====================================	페이지선택버튼	 ==================================  -->
			<div class="btn-toolbar mb-1" role="toolbar">
				<div class="btn-group" role="group">
					<button
						onclick="movePage(<%=currentPage == 1 ? 1 : currentPage - 1%>);"
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
			<!--=========================================================================================-->

		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
<script type="text/javascript">
	function movePage(pageNum) {
		location.href = "/sp/myWorkedList.do?currentPage="+pageNum;
	}
</script>
<%@include file="/views/common/footer.jsp"%>