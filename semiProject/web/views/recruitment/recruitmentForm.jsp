<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function memberJoin() {
		$("#writeResume").submit();
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
	
	
	
</script>

    <!-- Page Content -->
    <div class="container"  style="min-height: 1000px">
      <div class="row">

		<%@include file="/views/common/nav.jsp" %>

        <!-- /.col-lg-3 -->
        <div class="col-lg-9 mt-lg-auto">
        	<div class="row mt-4">
        	  <div class="col-lg-2"></div>
			  <div class="col-lg-8 bg-dark">
			  <br><br>
        		<h3 align="center" class="text-white-50">구인 게시물</h3>
        		<br>
			  <form id="writeRecruitment" method="post" action="">
			  	<div class="row">
			  		<div class="col-3 mb-1">
				  		<img alt="" style="max-width: 130px;" src="http://placehold.it/300x400">
			  		</div>
				  	<div class="col-9">
				  	
				    	<!-- 업체명 -->
					    <div class="input-group" style="min-height: 33%">
					      	<input type="text" class="form-control mb-1" name="name" placeholder="업체명"/>
					    </div>
					    
				    	<!-- 업체 전화번호 -->
					    <div class="input-group" style="min-height: 33%">
					      	<input type="text" class="form-control mb-1" name="phone" placeholder="업체 연락처"/>
					    </div>
					    
					    <!-- 업체 이메일 -->
					    <div class="input-group" style="min-height: 33%">
						    <input type="email" class="form-control mb-1" name="email" placeholder="email"/>
					    </div>
				  	</div>
			  	</div>
			    
			    <!-- 업체 주소 -->
			    <div class="input-group">
			    	<!-- 주소검색을 통해 입력받은 우편번호 저장 input -->
			      <input type="hidden" name="zipcode" id="zipcode"/>
			      
			      	<!-- 주소를 통한 좌표값 저장 input -->
			      <input type="hidden" name="latitude" id="latitude"/>
			      <input type="hidden" name="longitude" id="longitude"/>
			      
			      	<!-- 주소 -->
			      <input type="text" class="form-control mb-1 mr-1" name="address" id="address" placeholder="사업장 주소" readonly/>
			      <span class="input-group-btn">
			        <button class="btn btn-light text-dark" type="button" onclick="openAddressPopup();">주소 검색</button>
			      </span>
			      
			    </div>
			    
			    <div class="input-group">
			    	<!-- 상세주소 입력 -->
			      <input type="text" class="form-control mb-1" name="addressDetail" id="addressDetail" placeholder="상세주소"/>
			    </div>
			    <br>
			    <div class="row">
			    	<div class="col-3">
				    	<select multiple class="custom-select-lg mt-1 ml-3 btn-dark" style="min-height: 150px">
						  <option disabled="disabled" class="text-white-50">[업종]</option>
						  <option>사무직</option>
						  <option>서비스</option>
						  <option>유통/판매</option>
						  <option>외식/음료</option>
						  <option>고객상담</option>
						  <option>생산/건설</option>
						</select>
			    	</div>
			    	
			    	<div class="col-3">
				    	<select multiple class="custom-select-lg mt-1 ml-3 btn-dark" style="min-height: 150px">
						  <option disabled="disabled" class="text-white-50">[경력]</option>
						  <option>~1년</option>
						  <option>1~2년</option>
						  <option>3~5년</option>
						  <option>5~10년</option>
						  <option>10년 이상</option>
						</select>
			    	</div>
			    	
				    <div class="col-6">
				    	<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
				    		<label>근무일</label>
							<input type="date" class="btn-dark" name="dateD"/>
				    	</div>
				    	<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
				    		<label>시작시간</label>
							<input type="time" class="btn-dark" name="dateD"/>
				    	</div>
				    	<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
				    		<label>종료시간</label>
							<input type="time" class="btn-dark" name="dateD"/>
				    	</div>
				    </div>
			    </div>
			    <br>
			    
			    <h4 class="text-white-50 ml-1">우대 사항</h4>
			    <div class="row">
				    <div class="btn-group mt-1 ml-1">
				    	<button type="button" style="min-width: 140px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span id="mBtn"  class="caret">병역사항</span>
						</button>
						
				    	<input type="hidden" name="mValue" id="mValue"><!-- 병역여부에 대한 값을 저장할 hidden input -->
				    	
						  <ul class="dropdown-menu bg-dark" role="menu">
						    <li><button type="button" onclick="mSelect(this);" name="miltary" class="btn btn-dark btn-sm btn-block" value="y">군필</button></li>
						    <li><button type="button" onclick="mSelect(this);" name="miltary" class="btn btn-dark btn-sm btn-block" value="n">면제</button></li>
						    <li class="divider"></li>
						  </ul>
				    </div>
				    
				    <div class="btn-group mt-1 ml-1">
				    	<button type="button" style="min-width: 100px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span class="caret" id="gBtn">성별</span>
						</button>
						
				    	<input type="hidden" name="gValue" id="gValue"/><!-- 성별에 대한 값을 저장할 hidden input -->
				    	
						<ul class="dropdown-menu bg-dark" role="menu">
						  <li><button type="button" onclick="gSelect(this);" name="gender" class="btn btn-dark btn-sm btn-block" value="m">남</button></li>
						  <li><button type="button" onclick="gSelect(this);" name="gender" class="btn btn-dark btn-sm btn-block" value="f">여</button></li>
						  <li class="divider"></li>
						</ul>
				    </div>
			    </div>
			    <br>
			    
			    <div>
			    	<textarea class="col-12" rows="5" placeholder="업체 소개 및 희망 인력"></textarea>
			    </div>
			    
			    <div>
			    	<button class="btn btn-light text-dark mt-4 mb-2" onclick="">구인 등록</button>
			    </div>
			    
			  </form>
			    
			  </div><!-- /.col-lg-6 -->
			  <div class="col-lg-2"></div>
        	</div>
		</div><!-- /.row -->
        
        
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <script type="text/javascript">
	
	function gSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#gBtn").text(text);
		$("#gValue").val(value);
	}
	function mSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#mBtn").text(text);
		$("#mValue").val(value);
	}
</script>
<%@include file="/views/common/footer.jsp"%>