<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String nickname = (String) session.getAttribute("nickname");
	ArrayList<RecruitmentVo> list = (ArrayList<RecruitmentVo>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int listCount = pi.getTotalCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<script>
function recDetail(i){
	location.href="/sp/recruitmentDetail.do?recId="+i+"&currentPage="+<%=currentPage%>;
}
</script>
<style>
.card_he {
	height: 400px;
}
h4{
		cursor: pointer;
	}
</style>
<%@include file="header.jsp"%>
<!-- Page Content -->
<div class="container" style="min-height: 800px">
	<!-- 내용을 담아놓을 컨테이너 -->

	<div class="row">

		<%@include file="nav.jsp"%>
		<!-- /.col-lg-3 -->
		<div class="col-lg-9">
			<!--==================================   광고      ==================================================  -->
			<div id="carouselExampleIndicators" class="carousel slide my-4"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img class="d-block img-fluid rounded mx-auto"
							src="http://placehold.it/900x350" alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid rounded mx-auto"
							style="max-height: 320px" src="http://placehold.it/900x350"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid rounded mx-auto"
							src="http://placehold.it/900x350" alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
			<!--========================================================================================  -->


			<!--======================================	구인게시물	======================================== -->
			<div class="row">

				<%
					for (int i = 0; i < list.size(); i++) {
				%><!-- for문을 통해 해당 게시물들의 개수에 맞게 생성 -->
				<div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
					<div class="card card_he"
						onclick="recDetail(<%=list.get(i).getRecruitment_id()%>);">
						<img src="<%if (list.get(i).getRecruitment_image_src() == null) {%>/sp/images/building.jpeg<%} else {%>/sp/images/recruitmentImg/<%=list.get(i).getRecruitment_image_src()%><%}%>"
							width="180px" height="180px">
						<div class="card-body">
							<h4 class="card-title btn-link" onclick="recDetail('<%=list.get(i).getRecruitment_id()%>');">
	                    	<%=list.get(i).getRecruitment_title() %><!-- 게시물 이름 -->
	                  		</h4>
							<h6><%=list.get(i).getRecruitment_name()%></h6>
							<h5>
								시급 :
								<%=list.get(i).getPay()%></h5>
							<p class="card-text"><%=list.get(i).getWork_day()%></p>
						</div>
						<div class="card-footer">
							<small class="text-muted"><%=list.get(i).getAddress()%></small>
						</div>
					</div>
				</div>

				<%
					}
				%>
				<!-- /.row -->

			</div>
			<!--============================================================================== -->

			<!-- /.col-lg-9 -->

			<!--====================================	페이지선택버튼	 ==================================  -->
			<div class="btn-toolbar mb-1" role="toolbar">
				<div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage == 1 ? 1 : currentPage - 1%>);"
						type="button" class="btn btn-default bg-dark text-white">◀</button>
					<%
						for (int i = startPage; i <= endPage; i++) {
					%>
					<%
						if (currentPage != i) {
					%>
					<button onclick="movePage();" type="button"
						class="btn btn-default bg-dark text-white"><%=i %></button>
					<%
						} else {
					%>
					<button type="button" class="btn btn-default bg-dark text-white"><%=i %></button>

					<%
						}
					%>
					<%
						}
					%>
					<button
						onclick="movePage(<%=currentPage == maxPage ? maxPage : currentPage + 1%>);"
						type="button" class="btn btn-default bg-dark text-white">▶</button>
				</div>
			</div>
			<!--=========================================================================================-->
		</div>
		<!-- /.row -->
	</div>
</div>
<!-- /.container -->
<script type="text/javascript">
	function movePage(pageNum) {
		location.href = "/sp/indexList.do?currentPage="+pageNum;
	}
</script>
<%@include file="footer.jsp"%>