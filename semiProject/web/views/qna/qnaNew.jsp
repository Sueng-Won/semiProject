<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
   
<meta charset="UTF-8">
<title>문의 게시판</title>
   <link href="/sp/css/shop-homepage.css" rel="stylesheet">
   <link href="/sp/vendor/bootstrap/css/bootstrap.min.css"rel="stylesheet">
    <link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/sp/css/qnaNew.css" rel="stylesheet">
    <script src="/sp/vendor/jquery/jquery.min.js"></script>
</head>
<body>
   

<form class="fixed-top" method="get" action="" style="padding: 20px 28px 20px 28px; overflow-y:scroll; height:700px;">
   <div class="allwp">
   
   <div>문의 페이지
      <div class="progress" style="height: 1.5px; ">
           <div class="progress-bar"></div>
      </div>
   </div>

   <div class="top" style="-webkit-margin-before: -1em;">
         <ul>
             <li class="m1"><a>문의 하기</a></li>
             
             <li><a href="/sp/views/qna/myQna.jsp">나의 문의내역</a></li>
             
             
         </ul>
    </div>
    
    <div class="top2" style="-webkit-margin-before: -1.5em;">
          <ul>
              <li>문의좀 하지마 시벌넘아</li>
              <li>니가 좀 알아봐</li>
          </ul>
    </div>
   
   <table class="tableA" style="-webkit-margin-before: -1.5em;">
      <tbody >
         <tr>
            <th>
               <label>
                  <span class="star">*</span> 문의유형
               </label>
            </th>
            <td>
               <select name="" id="" class="slt sltL" title="대분류 선택" style="width:180px;">
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
               <input type="text" name="email" id="email" class="ipt" placeholder="답변 받을 메일 주소를 입력해 주세요" style="width:94%" maxlength="50">
            </td>
         </tr>
                       
         <tr>
            <th class="loc_1">
               <label>전화번호</label>
            </th>
            <td>
               <input type="text" name="phone" id="phone" class="ipt" maxlength="20">
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
       <input type="checkbox" name="PimsAgree" id="PimsAgree" class="chk" value="">
       <label for="PimsAgree">개인정보수집 및 이용안내에 동의합니다.</label>
    </p>
    </div>
    <p class="btn">
       <button type="button" id="btn_submit" class="bBtn bg-dark">보내기</button>
       <a href="javascript:self.close();" class="cBtn">취소</a>
    </p>
    </div>
</form>

</body>
</html>