<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String nickname = (String) session.getAttribute("nickname");

ArrayList<RecruitmentVo> list = 
	(ArrayList<RecruitmentVo>)request.getAttribute("list"); 

PageInfo pi = (PageInfo)request.getAttribute("pi");
int listCount = pi.getTotalCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
%>
<div class="header">
	<%@include file="header.jsp"%>
</div>
<!-- Page Content -->
<div class="container">
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
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="Third slide">
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

				<%for(RecruitmentVo rv : list) {%><!-- for문을 통해 해당 게시물들의 개수에 맞게 생성 -->
				<div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
					<div class="card h-100">
						<a href="#"><img class="card-img-top"
							src="http://placehold.it/300x300" alt=""></a>
						<div class="card-body">
							<h4 class="card-title">
								<a href="#"><%=rv.getRecruitment_title() %></a>
								<!-- 게시물 이름 -->
							</h4>
							<h5>
								시급 :
								<%=rv.getPay()%></h5>
							<p class="card-text"><%=rv.getWork_day() %></p>
						</div>
						<div class="card-footer">
							<small class="text-muted"><%=rv.getAddress() %></small>
						</div>
					</div>
				</div>

				<%} %>
				<!-- /.row -->

			</div>
			<!-- /.col-lg-9 -->

			<!--====================================	페이지선택버튼	 ==================================  -->
			<div class="btn-toolbar mb-1" role="toolbar">
				<div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);"
						type="button" class="btn btn-default bg-dark text-white"><</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
					<%if(currentPage != i){ %>
					<button onclick="movePage();" type="button"
						class="btn btn-default bg-dark text-white">1</button>
					<%}else{ %>
					<button type="button" class="btn btn-default bg-dark text-white">1</button>

					<%} %>
					<%} %>
					<button
						onclick="movePage(<%=currentPage==maxPage?maxPage:maxPage+1%>);"
						type="button" class="btn btn-default bg-dark text-white">></button>
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
<div class="footer">
<%@include file="footer.jsp"%>
</div>