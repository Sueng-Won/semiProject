<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.contract.model.vo.ContractVo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<ContractVo> myConList = (ArrayList<ContractVo>) request.getAttribute("myConList");
	ArrayList<RecruitmentVo> conRecList = (ArrayList<RecruitmentVo>) request.getAttribute("conRecList");

	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM / dd");
	/* PageInfo pi = (PageInfo) request.getAttribute("pi");
	int listCount = pi.getTotalCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage(); */
%>

<%@include file="/views/common/header.jsp"%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function writeRec() {
		location.href = "/sp/views/recruitment/recruitmentForm.jsp";
	}

	function modifyRec(i) {
		location.href = "/sp/updateRecForm.do?recId=" + i;
	}

	function deleteRec(i) {
		location.href = "/sp/deleteRecruitment.do?recId=" + i;
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
				<div id="conDetails<%=myConList.get(i).getC_no()%>"
					class="panel-collapse collapse in" role="tabpanel"
					aria-labelledby="headingOne">
			Anim pariatur cliche
					reprehenderit, enim eiusmod high life accusamus terry richardson ad
					squid. 3 wolf moon officia aute, non cupidatat skateboard dolor
					brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf
					moon tempor, sunt aliqua put a bird on it squid single-origin
					coffee nulla assumenda shoreditch et. Nihil anim keffiyeh
					helvetica, craft beer labore wes anderson cred nesciunt sapiente ea
					proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat
					craft beer farm-to-table, raw denim aesthetic synth nesciunt you
					probably haven't heard of them accusamus labore sustainable VHS.
				
				</div>
				<%
					}
				%>
			</table>

			<!--====================================	페이지선택버튼	 ==================================  -->
			<%-- <div class="btn-toolbar mb-1" role="toolbar">
				<div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage == 1 ? 1 : currentPage - 1%>);"
						type="button" class="btn btn-default bg-dark text-white"><</button>
					<%
						for (int i = startPage; i <= endPage; i++) {
					%>
					<%
						if (currentPage != i) {
					%>
					<button onclick="movePage();" type="button"
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

			</div> --%>


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
<%@include file="/views/common/footer.jsp"%>