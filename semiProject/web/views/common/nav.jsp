<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    	.btn{
    		color: white;
    	}
    </style>
    
    <script>
    	function searchByDate(){
    		//location.href="/sp/views/byDate/searchByDate.jsp";
    	location.href="/sp/byDateList.do";
    	}
    </script>

<div class="col-lg-3">
	<h1 class="my-4">[로그인창]</h1>
	<div class="list-group">
		<!-- <a href="#" class="list-group-item active">맞춤알바</a> -->
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">맞춤알바</button>
		<!-- <a onclick="loadLocalList();" class="list-group-item">지역알바</a> --> <!-- 지역 알바로 이동 -->
		<button type="button" onclick="loadLocalList();" class="btn btn-default btn-lg btn-block bg-dark">지역알바</button>
		<!-- <a href="#" class="list-group-item">일별알바</a>
		<a href="#" class="list-group-item">구직자정보</a> -->
		<button type="button" onclick="searchByDate();" class="btn btn-default btn-lg btn-block bg-dark">일별알바</button>
		<button type="button" class="btn btn-default btn-lg btn-block bg-dark">구직자정보</button>
	</div>
</div>