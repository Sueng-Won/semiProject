<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function memberJoin() {
		$("#writeResume").submit();
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
        		<h3 align="center" class="text-white-50">이력서</h3>
        		<br>
			  <form id="writeResume" method="post" action="">
			  	<div class="row">
			  		<div class="col-3 mb-1">
				  		<img alt="" style="max-width: 130px;" src="http://placehold.it/300x400">
			  		</div>
				  	<div class="col-9">
				  	
				    	<!-- 사용자 이름 -->
					    <div class="input-group" style="min-height: 33%">
					      	<input type="text" class="form-control mb-1" name="name" placeholder="이름"/>
					    </div>
					    
				    	<!-- 사용자 전화번호 -->
					    <div class="input-group" style="min-height: 33%">
					      	<input type="text" class="form-control mb-1" name="phone" placeholder="전화번호"/>
					    </div>
					    
					    <!-- 사용자 이메일 -->
					    <div class="input-group" style="min-height: 33%">
						    <input type="email" class="form-control mb-1" name="email" placeholder="email"/>
					    </div>
				  	</div>
			  	</div>
			    
			    
			    <div class="input-group">
			    	<!-- 주소검색을 통해 입력받은 우편번호 저장 input -->
			      <input type="hidden" name="zipcode" id="zipcode"/>
			      
			      
			      	<!-- 주소 -->
			      <input type="text" class="form-control mb-1 mr-1" name="address" id="address" placeholder="주소" readonly/>
			      
			    </div>
			    
			    <div class="input-group">
			    	<!-- 상세주소 입력 -->
			      <input type="text" class="form-control mb-1" name="addressDetail" id="addressDetail" placeholder="상세주소" readonly/>
			    </div>
			    
			    <div class="row">
			    
				    <div class="btn-group mt-1 ml-3">
				    	<button type="button" style="min-width: 130px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span class="caret" id="acBtn">학력</span>
						</button>
						
				    	<input type="hidden" name="acValue" id="acValue"/><!-- 장애여부에 대한 값을 저장할 hidden input -->
				    	
						<ul class="dropdown-menu bg-dark" role="menu">
						  <li><button type="button" onclick="acSelect(this);" name="achievement" class="btn btn-dark btn-sm btn-block" value="ele">초졸</button></li>
						  <li><button type="button" onclick="acSelect(this);" name="achievement" class="btn btn-dark btn-sm btn-block" value="mid">중졸</button></li>
						  <li><button type="button" onclick="acSelect(this);" name="achievement" class="btn btn-dark btn-sm btn-block" value="high">고졸</button></li>
						  <li><button type="button" onclick="acSelect(this);" name="achievement" class="btn btn-dark btn-sm btn-block" value="uni">대졸</button></li>
						  <li class="divider"></li>
						</ul>
						  
				    </div>
				    
				    <div class="btn-group mt-1 ml-1">
				    	<button type="button" style="min-width: 130px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span class="caret" id="dBtn">장애여부</span>
						</button>
						
				    	<input type="hidden" name="dValue" id="dValue"/><!-- 장애여부에 대한 값을 저장할 hidden input -->
				    	
						<ul class="dropdown-menu bg-dark" role="menu">
						  <li><button type="button" onclick="dSelect(this);" name="disability" class="btn btn-dark btn-sm btn-block" value="y">장애있음</button></li>
						  <li><button type="button" onclick="dSelect(this);" name="disability" class="btn btn-dark btn-sm btn-block" value="n">장애없음</button></li>
						  <li class="divider"></li>
						</ul>
				    </div>
				    
				    <div class="btn-group mt-1 ml-1">
				    	<button type="button" style="min-width: 130px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span id="mBtn"  class="caret">병역사항</span>
						</button>
						
				    	<input type="hidden" name="mValue" id="mValue"><!-- 병역여부에 대한 값을 저장할 hidden input -->
				    	
						  <ul class="dropdown-menu bg-dark" role="menu">
						    <li><button type="button" onclick="mSelect(this);" name="miltary" class="btn btn-dark btn-sm btn-block" value="y">군필</button></li>
						    <li><button type="button" onclick="mSelect(this);" name="miltary" class="btn btn-dark btn-sm btn-block" value="n">면제</button></li>
						    <li class="divider"></li>
						  </ul>
				    </div>
			    </div>
			    
			    <br>
			    
			    <h4 class="text-white-50">근무 희망 사항</h4>
			    
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
				    	<div class="mt-1 ml-3" style="max-height: 33%">
					    	<h5 class="text-white">근무가능일</h5>
				    	</div>
				    	<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
				    		<label>시작일</label>
							<input type="date" class="btn-dark" name="dateD"/>
				    	</div>
				    	<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
				    		<label>종료일</label>
							<input type="date" class="btn-dark" name="dateD"/>
				    	</div>
				    </div>
			    </div>
			    
			    <br>
			    
			    <div class="btn-group-toggle ml-3">
			    	<div class="text-white">근무시간</div>
			    	<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" id="inlineCheckbox1" value="option1"> 오전
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" id="inlineCheckbox2" value="option2"> 오후
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" id="inlineCheckbox3" value="option3"> 저녁
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" id="inlineCheckbox3" value="option3"> 야간
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" id="inlineCheckbox3" value="option3"> 풀타임
					</label>
			    </div>
			    
			    <div>
			    	<textarea class="col-12" rows="5" placeholder="자기소개 [150자 이내로 입력해주세요]"></textarea>
			    </div>
			    
			    <div>
			    	<button class="btn btn-light text-dark mt-4 mb-2" onclick="">이력서 생성</button>
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
	function acSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#acBtn").text(text);
		$("#acValue").val(value);
	}
	function dSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#dBtn").text(text);
		$("#dValue").val(value);
	}
	function mSelect(obj) {
		var text = $(obj).text();
		var value = $(obj).val();
		$("#mBtn").text(text);
		$("#mValue").val(value);
	}
</script>
<%@include file="/views/common/footer.jsp"%>