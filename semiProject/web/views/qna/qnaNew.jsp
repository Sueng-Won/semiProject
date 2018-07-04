<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id = (String)session.getAttribute("id"); 
	String email = (String)session.getAttribute("email");
	String phone = (String)session.getAttribute("phone");
%>
<!DOCTYPE html >
<!-- 브랜치 생성 -->
<html>
<head>
<meta charset="UTF-8">

<style>
.top {
    padding-top: 15px;
}

.top ul {
    height: 44px;
    border-bottom: 1px solid #363d54;
    list-style: none;
    padding: 0;
    margin: 0;	
    display: block;
    -webkit-margin-before: 1.5em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
    -webkit-padding-start: 0px;
}

.top ul li.m1 a{
    height: 44.5px;
    border: 1px solid #363d54;
    border-bottom: 0;
    color: #222;
    background: #fff;
}


.top ul li {
    float: left;
    margin-right: -1px;
    list-style: none;
    display: list-item;
    text-align: -webkit-match-parent;
}

.top ul li a {
    display: block;
    padding: 0 25px;
    height: 43px;
    line-height: 43px;
    border: 1px solid #d5d5d5;
    border-bottom: 0;
    text-align: center;
    font-size: 14px;
    color: #777;
    background: #fcfcfc;
    text-decoration: none;
    letter-spacing: -1px;
}

.top2 {
    padding: 30px 0 20px 10px;
    font-size: 12px;
    -webkit-margin-before: -1em;
}

.star{
	color : orangered;
}

.tableA {
    border: none;
    width: 100%;
}

tbody {
    display: table-row-group;
    vertical-align: middle;
    border-color: inherit;
}

tr {
    display: table-row;
    vertical-align: inherit;
    border-color: inherit;
}

.tableA tr th {
    padding: 5px 0 11px 12px;
    font-size: 15px;
    font-weight: bold;
    background: #fff;
    border-bottom: 1px solid #eeeeee;
    text-align: left;
}

.tableA tr td {
    border-bottom: 1px solid #eeeeee;
    text-align: left;
    padding: 15px 12px 16px 17px;
    box-sizing: border-box;
}

.slt{
	height: 32px;
    padding: 0 0 0 10px;
    border: 1px solid #dbdee1;
    font: 14px "맑은고딕", "Malgun Gothic",Dotum, "돋움", sans-serif;
    color: #494949;
    background: #fdfdfd;
   
}

.top2 ul li {
    padding: 1px 0;
    color: #6e6e6e;
}

.tableA tr td .txWp textarea {
    width: 100%;
    height: 250px;
    color: rgb(73, 73, 73);
    padding: 10px;
    font: 14px 맑은고딕, "Malgun Gothic", Dotum, 돋움, sans-serif;
    resize: none;
}
.privacyWp {
    margin-top: 20px;
    font-size: 12px;
}

.privacyWp .privacy {
    padding: 17px 12px;
    border: 1px solid #f0f0f0;
    background: #fafafa;
    font-size: 12px;
}

.privacyWp .privacy h2 {
    margin: 0;
    padding: 0 0 8px 0;
    border: none;
    background: none;
    font-size: 13px;
}

p {
    padding: 0;
    margin: 0;
    display: block;
    
}

.privacyWp .agree {
    font-weight: bold;
    font-size: 14px;
    padding: 14px 0 0 3px;
}

.chk{
	width: 13px;
    height: 13px;
    margin: 0 6px 0 0;
    vertical-align: middle;
}

.btn .bBtn {
    display: inline-block;
    width: 132px;
    height: 46px;
    margin-left: 5px;
    padding-bottom: 2px;
    text-align: center;
    color: rgb(255, 255, 255);
    vertical-align: middle;
    font: bold 16px 맑은고딕, "Malgun Gothic", Dotum, 돋움, sans-serif;
    border-radius: 2px;
    overflow: visible;
    margin: 0;
    cursor: pointer;
    align-items: flex-start;
    
}

.btn .cBtn {
    display: inline-block;
    width: 132px;
    height: 44px;
    text-align: center;
    vertical-align: middle;
    font: bold 16px/40px 맑은고딕, "Malgun Gothic", Dotum, 돋움, sans-serif;
    border-width: 1px;
    border-style: solid;
    border-color: rgb(221, 221, 221);
    border-image: initial;
    border-radius: 2px;
    background: rgb(245, 248, 250);
    text-decoration: none;   
}

.top ul li.m1 a{
    height: 44.5px;
    border: 1px solid #363d54;
    border-bottom: 0;
    color: #222;
    background: #fff;
}


.top ul li {
    float: left;
    margin-right: -1px;
    list-style: none;
    display: list-item;
    text-align: -webkit-match-parent;
}

.top ul li a {
    display: block;
    padding: 0 25px;
    height: 43px;
    line-height: 43px;
    border: 1px solid #d5d5d5;
    border-bottom: 0;
    text-align: center;
    font-size: 14px;
    color: #777;
    background: #fcfcfc;
    text-decoration: none;
    letter-spacing: -1px;
}

.top2 {
    padding: 30px 0 20px 10px;
    font-size: 12px;
    -webkit-margin-before: -1em;
}

.star{
   color : orangered;
}

.tableA {
    border: none;
    width: 100%;
}

tbody {
    display: table-row-group;
    vertical-align: middle;
    border-color: inherit;
}

tr {
    display: table-row;
    vertical-align: inherit;
    border-color: inherit;
}

.tableA tr th {
    padding: 5px 0 11px 12px;
    font-size: 15px;
    font-weight: bold;
    background: #fff;
    border-bottom: 1px solid #eeeeee;
    text-align: left;
}

.tableA tr td {
    border-bottom: 1px solid #eeeeee;
    text-align: left;
    padding: 15px 12px 16px 17px;
    box-sizing: border-box;
}

.slt{
   height: 32px;
    padding: 0 0 0 10px;
    border: 1px solid #dbdee1;
    font: 14px "맑은고딕", "Malgun Gothic",Dotum, "돋움", sans-serif;
    color: #494949;
    background: #fdfdfd;
   
}

.top2 ul li {
    padding: 1px 0;
    color: #6e6e6e;
}

.tableA tr td .txWp textarea {
    width: 100%;
    height: 250px;
    color: rgb(73, 73, 73);
    padding: 10px;
    font: 14px 맑은고딕, "Malgun Gothic", Dotum, 돋움, sans-serif;
    resize: none;
}
.privacyWp {
    margin-top: 20px;
    font-size: 12px;
}

.privacyWp .privacy {
    padding: 17px 12px;
    border: 1px solid #f0f0f0;
    background: #fafafa;
    font-size: 12px;
}

.privacyWp .privacy h2 {
    margin: 0;
    padding: 0 0 8px 0;
    border: none;
    background: none;
    font-size: 13px;
}

.privacyWp .agree {
    font-weight: bold;
    font-size: 14px;
    padding: 14px 0 0 3px;
}

.chk{
   width: 13px;
    height: 13px;
    margin: 0 6px 0 0;
    vertical-align: middle;
}

.btn{
	padding: 40px 0 62px 0;
	margin: 0;
    display: block;
    -webkit-margin-before: 0em;
    -webkit-margin-after: 0em;
    -webkit-margin-start: -50px;
    -webkit-margin-end: 0px;
} 
</style>


<title>문의 게시판</title>
   <link href="/sp/css/shop-homepage.css" rel="stylesheet">
   <link href="/sp/vendor/bootstrap/css/bootstrap.min.css"rel="stylesheet">
    <link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- 화면 상단 아이콘 이미지 -->
	<link rel="shortcut icon" href="/sp/images/icon.png">
    <!-- <link href="/sp/css/qnaNew.css" rel="stylesheet"> -->
    <script src="/sp/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript">
   	   
    	// 별표 확인
    	function starList(){	
    		 var PimsAgree = document.form.PimsAgree.checked;
    		 var target = document.getElementById("slt");
    		 var num = 0;
    		/*  var email = document.getElementById("email").value;
  			 var emailchk = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  			 */
    		 if(PimsAgree == true){
       		 num = 1;
       		 } 
    		 
             if(target.options[target.selectedIndex].value == 0){
            	 alert("문의 유형을 선택하셔야 합니다.");
             }else if($('#Contents').val()==0){
            	 alert("문의 내용을 입력하셔야 합니다.");
             }/* else if(emailchk.test(email)==false){
            	alert("이 메일형식이 올바르지 않습니다.");
   				return false; */
             else if(num==1){
            	 document.form.submit();
             }else{
       		  alert("개인정보 약관에 동의하셔야 합니다.");
     	  	 }   
             
    	}

    </script>
</head>
<body>
   

<form id="qnaFrm" name="form" class="fixed-top" method="post" action="/sp/qna.do"  style="padding: 20px 28px 20px 28px; overflow-y:scroll; height:700px;">
   <div class="allwp">
   <div>문의 페이지
      <div class="progress" style="height: 1.5px; ">
           <div class="progress-bar"></div>
      </div>
   </div>

   <div class="top" style="-webkit-margin-before: -1em;">
         <ul>
             <li class="m1"><a>문의 하기</a></li>
             <li><a href="/sp/qna.do">나의 문의내역</a></li>
         </ul>
    </div>
    
    <div class="top2" style="-webkit-margin-before: -1.5em;">
          <ul>
              <li>문의 좀 하지마</li>
              <li>니가 좀 알아봐</li>
          </ul>
    </div>
   
   <table class="tableA" style="-webkit-margin-before: -1.5em;">
      <tbody>
         <tr>
            <th>
               <label>
                  <span class="star">*</span> 문의유형
               </label>
            </th>
            <td>
               <select name="slt" id="slt" class="slt sltL" title="대분류 선택" style="width:180px;">
               <option value="0">대분류 선택</option>
               <option value="1">회원정보</option>
               <option value="2">이력서관리</option>
               <option value="3">구직활동관리</option>
               <option value="4">공고등록관리</option>
               <option value="5">유료서비스</option>
               <option value="6">오류/의견</option>
               <option value="7">기타</option>
               </select>
            </td>
         </tr>
                        
         <tr>
            <th class="loc">
               <label>
                  <span class="star">*</span> 문의내용
               </label>
            </th>
            <td>
               <div class="txWp">
               <textarea name="Contents" id="Contents" maxlength="3000"></textarea>
               </div>
            </td>
         </tr>
                        
         <tr>
            <th>
               <label>
                  <span class="star" >*</span> 이메일
               </label>
            </th>
            <td>
               <input type="text" name="email" id="email" class="ipt" style="width:94%" maxlength="50" value="<%=email %>" readonly/>
            </td>
         </tr>
                       
         <tr>
            <th class="loc_1">
               <label>전화번호</label>
            </th>
            <td>
               <input type="text" name="phone" id="phone" class="ipt" maxlength="20" value="<%=phone %>"/>
            </td>
         </tr>
      </tbody>   
   </table>
   
   <div class="privacyWp">
	    <div class="privacy">
	    <h2>개인정보수집 및 이용안내</h2>
	    <p><b>문의에 대한 처리 및 답변</b>을 위해 <b>이메일, 계정정보, 전화번호(선택), IP주소, 브라우저 및 OS 정보</b>가 수집되며,수집된 정보는 <b>평생 보관</b>합니다.<br>이에 동의하지 않을 경우 문의/제안 등록이 불가하며, 선택항목은 입력하지 않더라도 서비스이용에 제한을 두지 않습니다.</p>
	    </div>
	    
	    <p class="agree">
	       <input type="checkbox" name="PimsAgree" id="PimsAgree" class="chk">
	       <label for="PimsAgree">개인정보수집 및 이용안내에 동의합니다.</label>
	    </p>
   </div>
   	<div class="row">
   		<div class="col-4"></div>
	    <p class="btn col-4">
	       <input id="btn_submit" class="bBtn bg-dark" value="보내기" onclick="starList();"/>
	       <a href="javascript:self.close();" class="cBtn">취소</a>
	    </p>
	   	<div class="col-4"></div>
  	</div>
  </div>
</form>

</body>
</html>