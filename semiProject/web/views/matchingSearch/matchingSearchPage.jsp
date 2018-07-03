<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
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

ArrayList<MyResumeVo> resumes = (ArrayList<MyResumeVo>) request.getAttribute("resume");
%>

<%@include file="/views/common/header.jsp" %>
    <!-- Page Content -->
    <div class="container" style="min-height: 800px">	<!-- 내용을 담아놓을 컨테이너 -->

      <div class="row">

        <%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9">
			<%if(null != resumes){ %>
			<form action="/sp/matchingSearch.do" method="post">
				<div class="btn-group mt-3">
				    	<button type="button" id="rBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span class="caret">이력서 선택</span>
						</button>
				    	<input type="hidden" name="resumeId" id="resumeId">
						  <ul class="dropdown-menu bg-dark" role="menu">
						  	<%for(MyResumeVo resume : resumes) {%>
						    <li><button type="button" onclick="resumeSelect(this);" name="resume" class="btn btn-dark btn-sm btn-block" value="<%=resume.getResume_id()%>"><%=resume.getIntroduce_title() %></button></li>
						    <%} %>
						  </ul>
						<input type="submit" class="btn btn-dark ml-1" value="검색"/>
				</div>
			</form>
			<%}else{ %>
			<div align="center" class="bg-dark text-white">작성된 이력서가 없습니다.</div>
			<%} %>
			<!--======================================	구인게시물	======================================== -->
          <div class="row mt-4" style="min-height: 800px">
          
            <%for(RecruitmentVo rv : list) {%><!-- for문을 통해 해당 게시물들의 개수에 맞게 생성 -->
	            <div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4" style="max-height: 400px">
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
	            
			<%} %>
          <!-- /.row -->
          
        </div>
        <!-- /.col-lg-9 -->
        
        <!--====================================	페이지선택버튼	 ==================================  -->
	        <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);" type="button" class="btn btn-default bg-dark text-white">◀</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
						<%if(currentPage != i){ %>
						<button onclick="movePage();" type="button" class="btn btn-default bg-dark text-white"><%=i %></button>
						<%}else{ %>
						<button type="button" class="btn btn-default bg-dark text-white"><%=i %></button>
						
						<%} %>
					<%} %>
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:maxPage+1%>);" type="button" class="btn btn-default bg-dark text-white">▶</button>
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
		location.href = "/sp/matchingSearch.do?currentPage="+pageNum;
	}
	
	function resumeSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#rBtn").text(text);
		$("#resumeId").val(value);
	}
</script>
<%@include file="/views/common/footer.jsp"%>