<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function updateRecruit(){
		location.href = "/sp/views/recruitment/recruitmentForm.jsp";
	}
	
	function updateResume() {
		location.href = "/sp/views/resume/memberResume.jsp";
	}
</script>
<style>
.btn-link{
	cursor: pointer;
}
</style>
	<div class="container" style="min-height: 800px">	<!-- 내용을 담아놓을 컨테이너 -->

      <div class="row">

      <%@include file="/views/common/nav.jsp" %>
	      <div class="col-lg-9">
	    <!-- Page Content -->
		     <div class="media mt-4 border rounded bg-light">
			  	<div class="media-left media-middle">
			    	<a href="/sp/views/recruitment/recruitmentForm.jsp">
			     	 <img class="media-object btn-link" onclick="updateRecruit();" src="http://placehold.it/150x150" alt="...">
			    	</a>
			  	</div>
			  	<div class="media-body">
			    	<h4 class="media-heading btn-link" onclick="updateRecruit();">[구인게시글 제목]</h4>
			    	세부사항
			  	</div>
			</div>
			
			<div class="media mt-4 border rounded bg-light">
			  	<div class="media-left media-middle">
			     	 <img class="media-object btn-link" onclick="updateResume();" src="http://placehold.it/150x150" alt="...">
			  	</div>
			  	<div class="media-body">
			    	<h4 class="media-heading btn-link" onclick="updateResume();">[이력서 제목]</h4>
			    	세부사항
			  	</div>
			</div>
			
		</div>
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
<%@include file="/views/common/footer.jsp"%>