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

<%@include file="/views/common/header.jsp" %>
    <!-- Page Content -->
    <div class="container" style="min-height: 800px">	<!-- 내용을 담아놓을 컨테이너 -->

      <div class="row">

        <%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9">
			
			
			<!--======================================	구인게시물	======================================== -->
          <div class="row">
          
            <%-- <%for(RecruitmentVo rv : list) {%><!-- for문을 통해 해당 게시물들의 개수에 맞게 생성 -->
	            <div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
	              <div class="card h-100">
	                <a href="#"><img class="card-img-top" src="http://placehold.it/300x300" alt=""></a>
	                <div class="card-body">
	                  <h4 class="card-title">
	                    <a href="#"><%=rv.getRecruitment_title() %></a><!-- 게시물 이름 -->
	                  </h4>
	                  <h5>시급 : <%=rv.getPay()%></h5>
	                  <p class="card-text"><%=rv.getWork_day() %></p>
	                </div>
	                <div class="card-footer">
	                  <small class="text-muted"><%=rv.getAddress() %></small>
	                </div>
	              </div>
	            </div>
	            
			<%} %> --%>
          <!-- /.row -->
          
        </div>
        <!-- /.col-lg-9 -->
        
        <!--====================================	페이지선택버튼	 ==================================  -->
	        <%-- <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);" type="button" class="btn btn-default bg-dark text-white">◀</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
						<%if(currentPage != i){ %>
						<button onclick="movePage();" type="button" class="btn btn-default bg-dark text-white">1</button>
						<%}else{ %>
						<button type="button" class="btn btn-default bg-dark text-white">1</button>
						
						<%} %>
					<%} %>
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:maxPage+1%>);" type="button" class="btn btn-default bg-dark text-white">▶</button>
			  </div>
			</div> --%>
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
<%@include file="/views/common/footer.jsp"%>