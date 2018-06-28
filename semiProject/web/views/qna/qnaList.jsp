<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<style>
.btn-link{
	cursor: pointer;
}
</style>
	<div class="container" style="min-height: 800px">	<!-- 내용을 담아놓을 컨테이너 -->

      <div class="row">

      <%@include file="/views/common/nav.jsp" %>
	      <div class="col-lg-9 mt-4">
	    <!-- Page Content -->
		  <div class="list-group">
		     
		     <div class="list-group-item">
		     	<h4>[질문 제목1]</h4>
		     	<p>내용</p>
		     		
				<button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				   	답변확인 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
				  <li>답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용</li>
				</ul>
		      </div>
		      
		     <div class="list-group-item">
		     	<h4>[질문 제목2]</h4>
		     	<p>내용</p>
		     		
				<button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				   	답변확인 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
				  <li>답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용답변내용</li>
				</ul>
		      </div>
		      
			</div>
			<button class="btn btn-lg btn-dark ml-2 mt-3">문의하기</button>
          </div>
			
		</div>
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
<%@include file="/views/common/footer.jsp"%>