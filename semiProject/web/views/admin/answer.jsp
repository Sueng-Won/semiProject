<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int q_no = (int)request.getAttribute("q_no");
   String answer = (String)request.getAttribute("answer");
   String content = (String)request.getAttribute("content");
   
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

span {
    font-weight: bold;
    font-size: 15px;
    color: #ff6633;
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
         
    $(function(){
       $("#btn_submit").click(function(){
         $.ajax(
         {
            url : "/sp/answerQna.do",
            type : "post",
            complete : function(){
               close();
            }
         });
      });
    }); 
    </script>
</head>
<body>
   

<form id="qnaFrm" name="form" class="fixed-top" method="post" action="/sp/answerQna.do"  style="padding: 20px 28px 20px 28px; overflow-y:scroll; height:700px;">
   <div class="allwp">
   <div>답변 페이지
      <div class="progress" style="height: 1.5px; ">
           <div class="progress-bar"></div>
      </div>
   </div>

    <div class="top" style="-webkit-margin-before: -1em;">
         <ul>
             <li class="m1"><a>답변 하기</a></li>
         </ul>
    </div>
    
    <div class="top2" style="-webkit-margin-before: -1.5em;">
          
    </div>
   
   <table class="tableA" style="-webkit-margin-before: -1.5em;">
      <tbody>
          <tr>
            <td colspan="3">
                 <dl>
                    <dt style="-webkit-margin-before: 1em;"><span class="mr-2">Q.</span><%= content%></dt>
                 </dl>
            </td>
         </tr>
      
         <tr>
            <th class="loc">
               <label>답변하기</label>
            </th>
            <td>
               <div class="txWp">
               <textarea name="answer" id="answer" maxlength="3000"></textarea>
               </div>
            </td>
         </tr>
      </tbody>   
   </table>
   
      <div class="row">
         <div class="col-4"></div>
       <p class="btn col-4">
          <input type="hidden" name="q_no" value="<%=q_no %>"/>
          <input id="btn_submit" class="bBtn bg-dark" value="보내기" type="submit"/>
          <a href="javascript:self.close();" class="cBtn">취소</a>
       </p>   
     </div>
     
  </div>
</form>

</body>
</html>