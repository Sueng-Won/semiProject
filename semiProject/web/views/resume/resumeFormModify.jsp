<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@page import="com.what.semi.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp" %>
<% MyResumeVo member = (MyResumeVo)request.getAttribute("member"); %>
<style>
	#userPhoto:hover{
		cursor:pointer;
	}
</style>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	function modifyResume() {
		$("#modifyResume").submit();
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
			  <form id="modifyResume" method="post" action="/sp/modifyResume.do" enctype="multipart/form-data">
			  	<div class="row">
			  		<div class="col-3 mb-1">
			  		<%if(null!=member.getProfile_image_src()){ %>
				  		<img alt="" height="140px" width="120px" src="/sp/profile_photo/<%=member.getProfile_image_src() %>" id="userPhoto">
						<input type="file" id="userPhotoInput" name="userPhoto"  onchange="printImage(this);"/> 
						<%}else{ %>
						<img alt="" height="140px" width="120px" src="http://placehold.it/300x400" id="userPhoto">
						<input type="file" id="userPhotoInput" name="userPhoto"  onchange="printImage(this);"/> 
						<%} %>
			  		</div>
				  	<div class="col-9">
				    	<!-- 사용자 이름 -->
					    <div class="input-group" style="min-height: 33%">
					      	<input type="text" class="form-control mb-1" name="name" placeholder="<%=member.getName()%>" readonly/>
					    </div>
					    
				    	<!-- 사용자 전화번호 -->
					    <div class="input-group" style="min-height: 33%">
					      	<input type="text" class="form-control mb-1" name="phone" placeholder="<%=member.getPhone()%>"readonly/>
					    </div>
					    
					    <!-- 사용자 이메일 -->
					    <div class="input-group" style="min-height: 33%">
						    <input type="email" class="form-control mb-1" name="email" placeholder="<%=member.getEmail()%>" readonly/>
					    </div>
				  	</div>
			  	</div>
			    <div class="input-group">
			      	<!-- 주소 -->
			      <input type="text" class="form-control mb-1 mr-1" name="address" id="address" placeholder="<%=member.getAddress() %>" readonly/>
			    </div>
			    <div class="row">
				    <div class="btn-group mt-1 ml-3">
				    	<button type="button" style="min-width: 130px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span class="caret" id="acBtn">학력</span>
						</button>
						
						<input type="hidden" name="achievement" id="achievementValue"/>
				    	
						<ul class="dropdown-menu bg-dark" role="menu">
						  <li><button type="button" onclick="acSelect(this);" class="btn btn-dark btn-sm btn-block" value="초졸">초졸</button></li>
						  <li><button type="button" onclick="acSelect(this);" class="btn btn-dark btn-sm btn-block" value="중졸">중졸</button></li>
						  <li><button type="button" onclick="acSelect(this);" class="btn btn-dark btn-sm btn-block" value="고졸">고졸</button></li>
						  <li><button type="button" onclick="acSelect(this);" class="btn btn-dark btn-sm btn-block" value="대졸">대졸</button></li>
						  <li class="divider"></li>
						</ul>
						  
				    </div>
				    
				    <div class="btn-group mt-1 ml-1">
				    	<button type="button" style="min-width: 130px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span class="caret" id="dBtn">장애여부</span>
						</button>
						
				    	<input type="hidden" name="dValue" id="dValue"/><!-- 장애여부에 대한 값을 저장할 hidden input -->
				    	
						<ul class="dropdown-menu bg-dark" role="menu">
						  <li><button type="button" onclick="dSelect(this);" name="disability" class="btn btn-dark btn-sm btn-block" value="1">장애있음</button></li>
						  <li><button type="button" onclick="dSelect(this);" name="disability" class="btn btn-dark btn-sm btn-block" value="0">장애없음</button></li>
						  <li class="divider"></li>
						</ul>
				    </div>
				    
				    <div class="btn-group mt-1 ml-1">
				    	<button type="button" style="min-width: 130px" class="btn btn-lg btn-dark dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					    	<span id="mBtn"  class="caret">병역사항</span>
						</button>
						
				    	<input type="hidden" name="mValue" id="mValue"><!-- 병역여부에 대한 값을 저장할 hidden input -->
				    	
						  <ul class="dropdown-menu bg-dark" role="menu">
						    <li><button type="button" onclick="mSelect(this);" name="miltary" class="btn btn-dark btn-sm btn-block" value="1">군필</button></li>
						    <li><button type="button" onclick="mSelect(this);" name="miltary" class="btn btn-dark btn-sm btn-block" value="0">면제</button></li>
						    <li class="divider"></li>
						  </ul>
				    </div>
			    </div>
			    
			    <br>
			    
			    <h4 class="text-white-50">근무 희망 사항</h4>
			    
			    <div class="row">
			    	<div class="col-3">
				    	<select name = "business_type" multiple class="custom-select-lg mt-1 ml-3 btn-dark" style="min-height: 150px">
						  <option disabled="disabled" class="text-white-50">[업종]</option>
						  <option value = "사무직">사무직</option>
						  <option value = "서비스">서비스</option>
						  <option value = "유통">유통/판매</option>
						  <option value = "음료">외식/음료</option>
						  <option value = "고객상담">고객상담</option>
						  <option value = "생산/건설">생산/건설</option>
						</select>
			    	</div>
			    	
			    	<div class="col-2">
				    	<select name="career" multiple class="custom-select-lg mt-1 ml-3 btn-dark" style="min-height: 150px">
						  <option disabled="disabled" class="text-white-50">[경력]</option>
						  <option value = "1">있음</option>
						  <option value = "0">없음</option>
						</select>
			    	</div>
			    	
				    <div class="col-7">
				    	<div class="mt-1 ml-3" style="max-height: 33%">
					    	<h5 class="text-white">근무가능일</h5>
				    	</div>
				    	<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
				    		<label>근무 가능 일</label>
							<input type="date" class="btn-dark" name="dateD"/>
				    	</div>
				    </div>
			    </div>
			    
			    <br>
			    
			    <input type="hidden" name="workTime" id="workTime"/> <!-- 희망근무시간대 -->
			    <div class="btn-group-toggle ml-3" id="workTimeCkb">
			    	<div class="text-white">희망 근무시간대</div>
			    	<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="오전" onclick="checkBoxSelector(this, 0);"> 오전
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="오후" onclick="checkBoxSelector(this, 1);"> 오후
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="저녁" onclick="checkBoxSelector(this, 2);"> 저녁
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="야간" onclick="checkBoxSelector(this, 3);"> 야간
					</label>
					<label class="checkbox-inline btn-dark btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="상관없음" onclick="checkBoxSelector(this, 4);"> 상관없음
					</label>
			    </div>
			    
			    	
			    <div class="input-group">
			      <input type="text" class="form-control mb-1 mr-1" name="introduce_title" id="introduce_title" placeholder="이력서 한마디[15자 이내로 입력해주세요]"/>
			    </div>
			    <div>
			    	<textarea name="introduce"  id="introduce" class="col-12" rows="5" placeholder="자기소개 [150자 이내로 입력해주세요]"></textarea>
			    </div>
			    
			    <div>
			    	<input type="hidden" name="resume_id" value=<%=member.getResume_id()%>>
			    	<input type="submit" class="btn btn-light text-dark mt-4 mb-2" onclick="modifyResume();"/>
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
		$("#achievementValue").val(value);
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
	$(function(){
		$("#userPhotoInput").hide();
		$("#userPhoto").click(function(){
			$("#userPhotoInput").click();
		});
	});
	function printImage(obj){
		if(obj.files && obj.files[0]){
			var reader = new FileReader();
			reader.onload=function(e){
					$("#userPhoto").attr("src", e.target.result);
				}
			}
			reader.readAsDataURL(obj.files[0]);
		}
	
	function checkBoxSelector(obj, index){
		var $chkBoxArr = $(".inlineCheckbox");
		
		var send_array = Array();
		var send_cnt = 0;
		
		
		if(index==4 && $(obj).prop("checked")){
			$chkBoxArr.each(function(index, value){
				if(index != 4){
					$(this).prop("checked", false);
				}
			});
		}else if(index!=4 && $(obj).prop("checked")){
			$chkBoxArr.eq(4).prop("checked", false);
		}
		
		for(i=0;i<$chkBoxArr.length;i++) {
		    if ($chkBoxArr[i].checked == true){
		        send_array[send_cnt] = $chkBoxArr[i].value;
		        send_cnt++;
		    }
		}
		var resultStr = send_array.join(",");
		
		$("#workTime").val(resultStr);
		console.log(resultStr);

	}
</script>
<%@include file="/views/common/footer.jsp"%>