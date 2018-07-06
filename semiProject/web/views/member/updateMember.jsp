<%@page import="com.what.semi.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>

<%
	MemberVo member = (MemberVo)request.getAttribute("member");
	String lower = member.getMember_type().toLowerCase();
%>

<style>
	label{
		color: white;
	}
</style>

<script type="text/javascript">
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
	
	function update(){
		var okFlag = false;
		
		//이름 유효성 검사
		
		if($("#name").val()==""){
			alert("이름을 입력해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		if($("#name").val().length < 2){
			alert("이름은 2자리 이상 입력해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//이메일 유효성 검사
		
		if($("#email").val()==""){
			alert("이메일을 입력해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		var email = $("#email").val();
		var speEmail = email.search(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i);
		
		if(speEmail<0){
			alert("잘못된 이메일 형식입니다.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		
		//휴대폰번호 유효성 검사
		
		if($("#phone").val()==""){
			alert("휴대폰번호를 입력해주세요.");
			okFlag = false;
			return false;
		}else{
			okFlag = true;
		}
		var phone = $("#phone").val();
		var spePhone = phone.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);
		
		if(spePhone>=0){
			alert("휴대폰번호에 특수문자는 사용할 수 없습니다.");
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
		
		if(okFlag){
			$("#updateFrm").submit();
		}
	}
	
	function updatePw(){
		location.href = "/sp/views/member/updatePw.jsp";
	}
	
</script>

    <!-- Page Content -->
    <div class="container" style="min-height: 800px;">
    
      <div class="row">
		<%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9 mt-lg-auto">
        	<div class="row mt-4">
        	  <div class="col-lg-3"></div>
			  <div class="col-lg-6 bg-dark">
			  <br><br>
        		<h3 align="center" class="text-white-50">회원정보수정</h3>
        		<br>
			  <form id="updateFrm" method="post" action="/sp/updateMember.do">
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
			    </div>
			    <div class="input-group">
			    
			    	<!-- 사용자 이름 -->
			      <input type="text" class="form-control mb-1" name="name" placeholder="이름" value="<%=member.getName()%>"/>
			      
			    </div>
			    
			    <div class="input-group">
			    	<!-- 사용자 이메일 -->
			      <input type="email" class="form-control mb-1" name="email" placeholder="email" value="<%=member.getEmail()%>"/>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 사용자 전화번호 -->
			    	<input type="text" class="form-control mb-1" name="phone" placeholder="휴대폰번호(-를 빼고 입력하세요)" value="<%=member.getPhone()%>"/>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 주소검색을 통해 입력받은 우편번호 저장 input -->
			      <input type="hidden" name="zipcode" id="zipcode" value="<%=member.getZipcode()%>"/>
			      <input type="hidden" name="latitude" id="latitude" value="<%=member.getLatitude()%>"/>
			      <input type="hidden" name="longitude" id="longitude" value="<%=member.getLongitude()%>"/>
			      	<!-- 주소 -->
			      <input type="text" class="form-control mb-1" name="address" id="address" placeholder="주소" value="<%=member.getAddress()%>"/>
			      <span class="input-group-btn">
			        <button class="btn btn-light text-dark ml-1" type="button" onclick="openAddressPopup(); ">주소 검색</button>
			      </span>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 상세주소 입력 -->
			      <input type="text" class="form-control mb-1" name="addressDetail" id="addressDetail" placeholder="상세주소" value="<%=member.getAddress_detail()%>"/>
			    </div>
			    <input type="button" class="btn btn-dark btn-sm btn-block" value="회원정보수정" onclick="update();"/>
			    <input type="button" class="btn btn-dark btn-sm btn-block" value="비밀번호변경" onclick="updatePw();"/>
			    <br>
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
		$("#rTypeValue").val(value);
	}
	$(function(){
		typeSelect($("#<%=member.getMember_type()%>"));
	});
</script>
<%@include file="/views/common/footer.jsp"%>