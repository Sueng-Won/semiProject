<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<!-- 병합이 하고싶어요 -->
<%
	boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
	String idv = request.getParameter("id");
	String tBtn = request.getParameter("tBtn");
	String gender = request.getParameter("gender");
%>
<!-- master brench 생성 -->
<style>
	label{
		color: white;
	}
</style>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/sp/vendor/bootstrap/datepicker/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="/sp/vendor/bootstrap/datepicker/bootstrap-datepicker.css"/>


<script type="text/javascript">

	var id = "<%=idv%>";
	var gettedTBtn = "<%=tBtn%>";
	var convertedTBtn;
	if(gettedTBtn == "구직자"){
		convertedTBtn = "JS";
	}
	if(gettedTBtn == "업주"){
		convertedTBtn = "BO";
	}
	var gettedGender = "<%=gender%>";
	var flag = <%=flag%>;
	var searchAddr;
	
	function openAddressPopup() {
		var themeObj = {
     		   bgColor: "#162525", //바탕 배경색
     		   searchBgColor: "#162525", //검색창 배경색
     		   contentBgColor: "#162525", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
     		   pageBgColor: "#162525", //페이지 배경색
     		   textColor: "#FFFFFF", //기본 글자색
     		   queryTextColor: "#FFFFFF", //검색창 글자색
     		   //postcodeTextColor: "", //우편번호 글자색
     		   //emphTextColor: "", //강조 글자색
     		   outlineColor: "#444444" //테두리
     		};
		new daum.Postcode({
     	   theme: themeObj,
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
                
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#zipcode").val(data.zonecode);//5자리 새우편번호 사용
                $("#address").val(fullAddr);

                // 커서를 상세주소 필드로 이동한다.
                $("#addressDetail").focus();
                searchAddr = fullAddr;
            },
		    onclose: function(state) {
		        //state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
		        if(state === 'FORCE_CLOSE'){
		            //사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.
	
		        } else if(state === 'COMPLETE_CLOSE'){
		            //사용자가 검색결과를 선택하여 팝업창이 닫혔을 경우, 실행될 코드를 작성하는 부분입니다.
		            //oncomplete 콜백 함수가 실행 완료된 후에 실행됩니다.
		            console.log('검색 종료', searchAddr); 
		         // 주소-좌표 변환 객체를 생성합니다
		        	var geocoder = new daum.maps.services.Geocoder();

		        	// 주소로 좌표를 검색합니다
		        	geocoder.addressSearch(searchAddr, function(result, status) {

		        	    // 정상적으로 검색이 완료됐으면 
		        	     if (status === daum.maps.services.Status.OK) {
		        	    	 $("#latitude").val(result[0].y);
		        	    	 $("#longitude").val(result[0].x);
		        	    	 
		        	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
		        			console.log(coords);
							
		        	        // 결과값으로 받은 위치를 마커로 표시합니다
		        	        
		        	    } 
		        	});    
		        }
		    }
            
        }).open();
		
	}
	
	function signIn(){
		var okFlag = false;
		
		//아이디 유효성 검사
		
		if($("#iid").val() == ""){
	        alert("아이디를 입력해주세요.");
	        $("#iid").focus();
	        okFlag = false;
	        return false;
	    }else{
			okFlag = true;
		}
		
		if(!flag){
			alert("아이디를 중복체크 해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		var id = $("#iid").val();
		var numId = id.search(/[0-9]/g);
		var engId = id.search(/[a-z]/ig);
		var speId = id.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);
		
		if(id.length < 6 || id.length > 15){
			alert("아이디는 6자리 ~ 15자리 이내로 입력해주세요.");
			$("#iid").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		if(speId >= 0){
			alert("아이디는 특수문자를 사용할 수 없습니다.");
			$("#iid").focus();
			okFlag = false;
			return false;
		}else{
			okFlag - true;
		}
		
		if(numId < 0 || engId < 0){
			alert("아이디는 영문,숫자를 혼합하여 입력해주세요.");
			$("#iid").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//비밀번호 유효성 검사
		
		if($("#ipw").val()==""||$("#ipw2").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#ipw").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		if($("#ipw").val() != $("#ipw2").val()){
			alert("기존 비밀번호와 일치하지 않습니다.");
			$("#ipw2").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		var pw = $("#ipw").val();
		var numPw = pw.search(/[0-9]/g);
		var engPw = pw.search(/[a-z]/ig);
		var spePw = pw.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);

		if(pw.length < 8 || pw.length > 20){
			alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.");
			$("#ipw").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}

		if(pw.search(/₩s/) != -1){
			alert("비밀번호는 공백없이 입력해주세요.");
			$("#ipw").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		} 
		 
		if(numPw < 0 || engPw < 0 || spePw < 0 ){
			alert("비밀번호는 영문,숫자,특수문자를 혼합하여 입력해주세요.");
			$("#ipw").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//이름 유효성 검사
		
		if($("#name").val()==""){
			alert("이름을 입력해주세요.");
			$("#name").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		if($("#name").val().length < 2){
			alert("이름은 2자리 이상 입력해주세요.");
			$("#name").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//이메일 유효성 검사
		
		if($("#email").val()==""){
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		var email = $("#email").val();
		var speEmail = email.search(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i);
		
		if(speEmail<0){
			alert("잘못된 이메일 형식입니다.");
			$("#email").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//생일 유효성 검사
		
		if($("#birth").val()==""){
			alert("생일을 입력해주세요.");
			$("#birth").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		var birth = $("#birth").val();
		var year = Number(birth.substr(0,4));
	    var today = new Date(); // 날자 변수 선언
	    var yearNow = today.getFullYear();
	    var adultYear = yearNow-20;
	 
	 
	    if (year < 1900 || year > adultYear){
	         alert("년도를 확인하세요. "+adultYear+"년생 이전 출생자만 등록 가능합니다.");
	         $("#birth").focus();
	         return false;
	    }
		
		//휴대폰번호 유효성 검사
		
		if($("#phone").val()==""){
			alert("휴대폰번호를 입력해주세요.");
			$("#phone").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		var phone = $("#phone").val();
		var spePhone = phone.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);
		
		if(spePhone>=0){
			alert("휴대폰번호에 특수문자는 사용할 수 없습니다.");
			$("#phone").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//주소 유효성 검사
		
		if($("#address").val()==""){
			alert("주소 검색 버튼을 눌러 주소를 입력해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//상세주소 유효성 검사
		
		if($("#addressDetail").val()==""){
			alert("상세주소를 입력해주세요.");
			$("#addressDetail").focus();
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//회원 타입 유효성 검사
		
		if($("#rTypeValue").val()==""){
			alert("회원구분을 선택해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//성별 유효성 검사
		
		if($("#gender").val()==""){
			alert("성별을 선택해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		if(okFlag){
			$("#joinForm").submit();
		}
	}
	
	function checkId(){
		var id = $("#iid").val();
		var tBtn = $("#tBtn").val();
		var gender = $('input[name="gender"]:checked').val();
		
		var numId = id.search(/[0-9]/g);
		var engId = id.search(/[a-z]/ig);
		var speId = id.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);
		
		if(id.length < 6 || id.length > 15){
			alert("아이디는 6자리 ~ 15자리 이내로 입력해주세요.");
			$("#iid").focus();
			okFlag = false;
			return false;
		}
		
		if(speId >= 0){
			alert("아이디는 특수문자를 사용할 수 없습니다.");
			$("#iid").focus();
			okFlag = false;
			return false;
		}
		
		if(numId < 0 || engId < 0){
			alert("아이디는 영문,숫자를 혼합하여 입력해주세요.");
			$("#iid").focus();
			okFlag = false;
			return false;
		}
		
		location.href="/sp/checkId.do?id="+id+"&tBtn="+tBtn+"&gender="+gender;
	}
	
	$(function(){
		if(id!="null"){
			if(!flag){
				alert("중복된 아이디 입니다.");
				$("#iid").val("");
				$("#tBtn").text(gettedTBtn);
				$("#rTypeValue").val(convertedTBtn);
				$("#"+gettedGender).prop("checked", true);
				$("#"+gettedGender+"Label").attr("class","btn btn-secondary active");
			}
			else{
				alert("사용할 수 있는 아이디 입니다.");
				$("#iid").val(id);
				$("#tBtn").text(gettedTBtn);
				$("#rTypeValue").val(convertedTBtn);
				$("#"+gettedGender).prop("checked", true);
				$("#"+gettedGender+"Label").attr("class","btn btn-secondary active");
			}
		}
		else{
			
		}
	});
	
</script>

    <!-- Page Content -->
    <div class="container" style="min-height: 800px;">
    
      <div class="row">
		<%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="col-lg-6 mt-lg-auto">
        	<div class="row mt-4">
        	  <div class="col-lg-3"></div>
			  <div class="col-lg-9 bg-dark">
			  <br><br>
        		<h3 align="center" class="text-white-50">회원가입</h3>
        		<br>
			  <form id="joinForm" method="post" action="/sp/signIn.do">
			  <div class="btn-group mt-3">
			    	<button type="button" id="tBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				    	<span class="caret">회원타입</span>
					</button>
			    	<input type="hidden" name="rTypeValue" id="rTypeValue">
					  <ul class="dropdown-menu bg-dark" role="menu">
					    <li><button type="button" onclick="typeSelect(this);" id="JS" name="js" class="btn btn-dark btn-sm btn-block" value="JS">구직자</button></li>
					    <li><button type="button" onclick="typeSelect(this);" id="BO" name="bo" class="btn btn-dark btn-sm btn-block" value="BO">업주</button></li>
					    <li class="divider"></li>
					  </ul>
					  
			    <div class="input-group">
				    	<!-- 사용자 성별 -->
				    	<div class="btn-group mb-1 btn-group-toggle" data-toggle="buttons">
	  						<label class="btn btn-secondary" id="MLabel">
	    					<input type="radio" id="M" name="gender" value="M" autocomplete="off">남
	  						</label>
	  						<label class="btn btn-secondary" id="FLabel">
	    					<input type="radio" id="F" name="gender" value="F" autocomplete="off">여
	  						</label>
						</div>
			    </div>
			    </div>
			   
			    <div class="input-group">
			    
			    	<!-- 사용자 아이디 -->
			      <input type="text" class="form-control mb-1" name="id" id="iid" placeholder="아이디(6~15자리 영문,숫자)"/>
			      <span class="input-group-btn">
			        <button class="btn btn-light text-dark ml-1" type="button" onclick="checkId();">중복 확인</button>
			      </span>
			    </div>
			    
			    <div class="input-group">
			    
			    	<!-- 사용자 비밀번호 -->
			      <input type="password" class="form-control mb-1" name="pw" id="ipw" placeholder="비밀번호(6~20자리 영문,숫자,기호)"/>
			      
			      
			    </div>
			    
			    <div class="input-group">
			    
			    	<!-- 사용자 비밀번호 -->
			      <input type="password" class="form-control mb-1" name="pw" id="ipw2" placeholder="비밀번호확인"/>
			      
			      
			    </div>
			    
			    <div class="input-group">
			    
			    	<!-- 사용자 이름 -->
			      <input type="text" class="form-control mb-1" id="name" name="name" placeholder="이름(2글자 이상)"/>
			      
			    </div>
			    
			    <div class="input-group">
			    	<!-- 사용자 이메일 -->
			      <input type="email" class="form-control mb-1" id="email" name="email" placeholder="email"/>
			    </div>
			    <div class="input-group">
			    	<!-- 사용자 생년월일 -->
			    	<input type="date" class="form-control mb-1" id="birth" name="birth" placeholder="생년월일"/>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 사용자 전화번호 -->
			    	<input type="text" class="form-control mb-1" id="phone" name="phone" placeholder="휴대폰번호(-를 빼고 입력하세요)"/>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 주소검색을 통해 입력받은 우편번호 저장 input -->
			      <input type="hidden" name="zipcode" id="zipcode"/>
			      <input type="hidden" name="latitude" id="latitude"/>
			      <input type="hidden" name="longitude" id="longitude"/>
			      	<!-- 주소 -->
			      <input type="text" class="form-control mb-1" name="address" id="address" placeholder="주소" readonly/>
			      <span class="input-group-btn">
			        <button class="btn btn-light text-dark ml-1" type="button" onclick="openAddressPopup();">주소 검색</button>
			      </span>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 상세주소 입력 -->
			      <input type="text" class="form-control mb-1" name="addressDetail" id="addressDetail" placeholder="상세주소"/>
			    </div>
			    <input type="button" class="btn btn-dark btn-sm btn-block" value="회원가입" onclick="signIn();"/>
			  </form>
			    
			  </div><!-- /.col-lg-6 -->
			  <div class="col-lg-3"></div>
        	</div>
		</div><!-- /.row -->
        
        
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <script type="text/javascript">
	function typeSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#tBtn").text(text);
		$("#tBtn").val(value);
		$("#rTypeValue").val(value);
	}
</script>
<%@include file="/views/common/footer.jsp"%>