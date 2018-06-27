<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>

<%
	boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
	String idv = request.getParameter("id");
%>

<style>
	label{
		color: white;
	}
</style>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/sp/vendor/bootstrap/datepicker/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="/sp/vendor/bootstrap/datepicker/bootstrap-datepicker.css"/>


<script type="text/javascript">
	
	function memberJoin() {
		$("#joinForm").submit();
	}
	
	function validate() {
		if($("#userPwd").val() != $("#userPwd2").val()){
			$("#passChkSpan").text("입력하신 비밀번호가 일치하지 않습니다.");
			$("#userPwd2").focus();
			
			return false;
		}
		//다른 값들을 체크 하는 로직 추가(유효성 검사 로직 추가 영역)
		return true;
	}
	
	    
	
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
		$("#joinForm").submit();
	}
	
	function checkId(){
		var id = $("#id").val();
		location.href="/sp/checkId.do?id="+id;
		
		
	}
	
	$(function(){
		var id = "<%=idv%>";
		var flag = <%=flag%>;
		if(id!="null"){
			if(!flag){
				alert("중복된 아이디 입니다.");
				$("#id").val("");
			}
			else{
				alert("사용할 수 있는 아이디 입니다.");
				$("#id").val(id);
			}
		}
		else{
			
		}
	})
	
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
        		<h3 align="center" class="text-white-50">회원가입</h3>
        		<br>
			  <form id="joinForm" method="post" action="/sp/signIn.do">
			  <div class="btn-group mt-3">
			    	<button type="button" id="tBtn" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				    	<span class="caret">회원타입</span>
					</button>
			    	<input type="hidden" name="rTypeValue" id="rTypeValue">
					  <ul class="dropdown-menu bg-dark" role="menu">
					    <li><button type="button" onclick="typeSelect(this);" name="js" class="btn btn-dark btn-sm btn-block" value="JS">구직자</button></li>
					    <li><button type="button" onclick="typeSelect(this);" name="bo" class="btn btn-dark btn-sm btn-block" value="BO">업주</button></li>
					    <li class="divider"></li>
					  </ul>
					  
			    <div class="input-group">
				    	<!-- 사용자 성별 -->
				    	<div class="btn-group mb-1 btn-group-toggle" data-toggle="buttons">
	  						<label class="btn btn-secondary active">
	    					<input type="radio" name="gender" value="M" autocomplete="off" checked>남
	  						</label>
	  						<label class="btn btn-secondary">
	    					<input type="radio" name="gender" value="F" autocomplete="off">여
	  						</label>
						</div>
			    </div>
			    </div>
			   
			    <div class="input-group">
			    
			    	<!-- 사용자 아이디 -->
			      <input type="text" class="form-control mb-1" name="id" id="id" placeholder="아이디"/>
			      <input type="hidden" name="idHd" value=""/>
			      <span class="input-group-btn">
			        <button class="btn btn-light text-dark ml-1" type="button" onclick="checkId();">중복 확인</button>
			      </span>
			    </div>
			    
			    <div class="input-group">
			    
			    	<!-- 사용자 비밀번호 -->
			      <input type="password" class="form-control mb-1" name="pw" placeholder="비밀번호"/>
			      
			    </div>
			    
			    <div class="input-group">
			    
			    	<!-- 사용자 이름 -->
			      <input type="text" class="form-control mb-1" name="name" placeholder="이름"/>
			      
			    </div>
			    
			    <div class="input-group">
			    	<!-- 사용자 이메일 -->
			      <input type="email" class="form-control mb-1" name="email" placeholder="email"/>
			    </div>
			    <div class="input-group">
			    	<!-- 사용자 생년월일 -->
			    	<input type="date" class="form-control mb-1" name="birth" placeholder="생년월일"/>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 사용자 전화번호 -->
			    	<input type="text" class="form-control mb-1" name="phone" placeholder="휴대폰번호(-를 빼고 입력하세요)"/>
			    </div>
			    
			    <div class="input-group">
			    	<!-- 주소검색을 통해 입력받은 우편번호 저장 input -->
			      <input type="hidden" name="zipcode" id="zipcode"/>
			      <input type="hidden" name="latitude" id="latitude"/>
			      <input type="hidden" name="longitude" id="longitude"/>
			      	<!-- 주소 -->
			      <input type="text" class="form-control mb-1" name="address" id="address" placeholder="주소"/>
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
		$("#rTypeValue").val(value);
	}
</script>
<%@include file="/views/common/footer.jsp"%>