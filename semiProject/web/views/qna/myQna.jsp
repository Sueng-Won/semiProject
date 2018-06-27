<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
	
<meta charset="UTF-8">
<title>나의 문의내역</title>
	<link href="/sp/css/shop-homepage.css" rel="stylesheet">
	<link href="/sp/vendor/bootstrap/css/bootstrap.min.css"rel="stylesheet">
    <link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/sp/css/myQna.css" rel="stylesheet">
    <script src="/sp/vendor/jquery/jquery.min.js"></script>
</head>
<body>
	

<form class="fixed-top" method="get" action="" style="padding: 20px 28px 20px 28px; overflow-y:scroll; height:700px;">
	<div class="allwp">
		<div>나의 문의내역
			<div class="progress" style="height: 1.5px; ">
		  		<div class="progress-bar"></div>
			</div>
		</div>
	
		<div class="top" style="-webkit-margin-before: -1em;">
	         <ul>
	             <li><a href="/sp/views/qna/qnaNew.jsp">문의 하기</a></li>
	             <li class="m2"><a>나의 문의내역</a></li>
	         </ul>
	    </div>
	    
	    <div class="top2" style="-webkit-margin-before: -1.5em;">
	          <ul>
	              <li>답변 보내기 귀찮다</li>
	              <li>회원 탈퇴해</li>
	          </ul>
	    </div>
    
		<div class="myqna">
		<table class="tbl">
			<caption>
				<span class="skip">나의 문의내역</span>
			</caption>
			
			<colgroup>
				<col width="19%">
				<col width="420px">
				<col width="20%">
			</colgroup>
			
			<thead>
				<tr>
				<th scope="col">문의일</th>
				<th scope="col">문의내역</th>
				<th scope="col">처리상태</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
				<td colspan="3" class="none">최근 작성한 문의 내역이 없습니다.</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
</form>
</body>
</html>