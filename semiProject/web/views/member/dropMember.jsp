<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<script type="text/javascript">
	function dropMember(){
		location.href = "/sp/dropMember.do";
	}
	function cancle(){
		location.href = "/sp/index.jsp";
	}
</script>
 <div class="container" style="min-height: 800px;">
      <div class="row">
		<%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9 mx-auto">
        	<div class="row mt-4">
        	  <div class="col-lg-3"></div>
			  <div class="col-lg-6 bg-dark">
			  <br><br>
        		<h3 align="center" class="text-white-50">정말 탈퇴하시겠습니까?</h3>
        		<br>
			  	<div class="input-group float-right">
			    <span class="input-group-btn">
			        <button class="btn btn-dark btn-sm btn-block" type="button" onclick="dropMember();">탈퇴</button>
			    </span>
				
				<span class="input-group-btn">
			        <button class="btn btn-dark btn-sm btn-block" type="button" onclick="cancle();">취소</button>
			    </span>
			    </div>
			  </div><!-- /.col-lg-6 -->
			  <div class="col-lg-3"></div>
        	</div>
		</div><!-- /.row -->
        
        
      </div>
      <!-- /.row -->
    </div>
<%@include file="/views/common/footer.jsp"%>