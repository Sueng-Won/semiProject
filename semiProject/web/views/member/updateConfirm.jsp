<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<script type="text/javascript">
	$(function(){
		setTimeout("location.href='/sp/index.jsp'",2000);
	})
</script>
 <div class="container" style="min-height: 800px;">
      <div class="row">
		<%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9 mt-lg-auto">
        	<div class="row mt-4">
        	  <div class="col-lg-3"></div>
			  <div class="col-lg-6 bg-dark">
			  <br><br>
        		<h3 align="center" class="text-white-50">수정이 완료 되었습니다.</h3>
        		<h3 align="center" class="text-white-50">2초 후 메인페이지로 이동합니다.</h3>
			  </div><!-- /.col-lg-6 -->
			  <div class="col-lg-3"></div>
        	</div>
		</div><!-- /.row -->
      </div>
      <!-- /.row -->
    </div>
<%@include file="/views/common/footer.jsp"%>