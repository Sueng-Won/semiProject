<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
 	 ArrayList<RecruitmentVo> list = 
		(ArrayList<RecruitmentVo>)request.getAttribute("list"); 
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getTotalCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();  
%>
<%@include file="/views/common/header.jsp"%>
<html>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="/sp/vendor/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/sp/vendor/jquery/js/datepicker.js"></script>
<script>
function recDetail(i){
	location.href="/sp/recruitmentDetail.do?recId="+i+"&currentPage="+<%=currentPage%>;
}
</script>
<style>
.content{
margin-top:50px;
}
h4{
		cursor: pointer;
	}
</style>
<body>
	<!-- Page Content -->
	<div class="container" style="min-height: 800px">
		<!-- 내용을 담아놓을 컨테이너 -->

		<div class="row">

			<%@include file="/views/common/nav.jsp"%>
			<!-- /.col-lg-3 -->
			<div class="col-lg-9">
			<div class="content">
				<!--==================================   광고      ==================================================  -->
				<div id="calendar" class="calendar">
					<div id="datepicker" align="center"></div>
					<div style="width:20px;"></div>
					<div align="right" style="margin-right:140px">
						<button onclick="searchByDate();"
							class="btn btn-default bg-dark text-white">검색</button>
					</div>
				</div>
				<!--========================================================================================  -->


				<!--======================================	구인게시물	======================================== -->
          <div class="row" style="min-height: 400px">
          
            <%for(int i =0; i<list.size();i++)  {
            System.out.print(list.get(i).getRecruitment_image_src());
            %><!-- for문을 통해 해당 게시물들의 개수에 맞게 생성 -->
	            <div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
	              <div class="card card_he"  onclick="recDetail(<%=list.get(i).getRecruitment_id()%>);">
	                <img src="<%if (list.get(i).getRecruitment_image_src() == null) {%>/sp/images/building.jpeg<%} else {%>/sp/images/recruitmentImg/<%=list.get(i).getRecruitment_image_src()%><%}%>"
							width="180px" height="180px">
	                <div class="card-body">
	                  <h4 class="card-title btn-link" onclick="recDetail('<%=list.get(i).getRecruitment_id()%>');">
	                    <%=list.get(i).getRecruitment_title() %><!-- 게시물 이름 -->
	                  </h4>
	                  <h6><%=list.get(i).getRecruitment_name() %></h6>
	                  <h5>시급 : <%=list.get(i).getPay()%></h5>
	                  <p class="card-text"><%=list.get(i).getWork_day() %></p>
	                </div>
	                <div class="card-footer">
	                  <small class="text-muted"><%=list.get(i).getAddress() %></small>
	                </div>
	              </div>
	            </div>
	            
			<%} %>
          <!-- /.row -->
          
        </div>
        <!--============================================================================== -->
        
        <!-- /.col-lg-9 -->
   
        <!--====================================	페이지선택버튼	 ==================================  -->
	        <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);" type="button" class="btn btn-default bg-dark text-white"><</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
						<%if(currentPage != i){ %>
						<button onclick="movePage();" type="button" class="btn btn-default bg-dark text-white"><%=i %></button>
						<%}else{ %>
						<button type="button" class="btn btn-default bg-dark text-white"><%=i %></button>
						
						<%} %>
					<%} %>
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:currentPage+1%>);" type="button" class="btn btn-default bg-dark text-white">></button>
			  </div>
			</div>  
				<!--=========================================================================================-->
			</div>
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.container -->
	<script type="text/javascript">
		function movePage(pageNum) {
			location.href = "/sp/searchByDateList.do?currentPage=" + pageNum;
		}
		
		function searchByDate(){
			var dateStr="'"+dates[0]+"'";
			for (var i = 1; i < dates.length; i++) {
				dateStr += ",'"+dates[i]+"'";
			}
			location.href="/sp/searchByDateList.do?dateStr="+dateStr;
		}
	</script>
	
	<%@include file="/views/common/footer.jsp"%>
	
</body>
</html>