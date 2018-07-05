<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@page import="com.what.semi.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>[오늘뭐해?]홈페이지</title>
<link href="/sp/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/sp/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>

<!-- Custom styles for this template -->
<link href="/sp/css/shop-homepage.css" rel="stylesheet">

<link href="/sp/css/daum.css" rel="stylesheet">
<script src="/sp/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
<%MyResumeVo member = (MyResumeVo) request.getAttribute("member");%>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	function writeResume() {
		$("#writeResume").submit();
	}
</script>
</head>
<body>
<!-- Page Content -->
<div class="container" style="min-height: 1000px">
	<div class="row">
		<!-- /.col-lg-3 -->
		<div class="col-lg-9 mt-lg-auto">
			<div class="row mt-4">
				<div class="col-lg-2"></div>
				<div class="col-lg-8 bg-dark">
					<h3 align="center" class="text-white-50">이력서</h3>
					<br>
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
									<input type="text" class="form-control mb-1" name="name"
										placeholder="<%=member.getName()%>" readonly />
								</div>

								<!-- 사용자 전화번호 -->
								<div class="input-group" style="min-height: 33%">
									<input type="text" class="form-control mb-1" name="phone"
										placeholder="<%=member.getPhone()%>" readonly />
								</div>

								<!-- 사용자 이메일 -->
								<div class="input-group" style="min-height: 33%">
									<input type="email" class="form-control mb-1" name="email"
										placeholder="<%=member.getEmail()%>" readonly />
								</div>
							</div>
						</div>
						<div class="input-group">
							<!-- 주소 -->
							<input type="text" class="form-control mb-1 mr-1" name="address"
								id="address" placeholder="<%=member.getAddress()%>" readonly />
						</div>
						<div class="row">
							<div class="btn-group mt-1 ml-3">
								<P readonly class="caret" id="acBtn"><%=member.getAchievement() %></p>
							
							</div>

							<div class="btn-group mt-1 ml-1">
								<button type="button" style="min-width: 130px"
									class="btn btn-lg btn-dark dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret" id="dBtn"><%=member.getDisability()==1?"장애":"비장애" %></span>
								</button>

							</div>

							<div class="btn-group mt-1 ml-1">
								<button type="button" style="min-width: 130px"
									class="btn btn-lg btn-dark dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false">
									<span id="mBtn" class="caret"><%=member.getMiltary_service()==1?"군필":"미필" %></span>
								</button>

								
							</div>
						</div>

						<br>

						<h4 class="text-white-50">근무 희망 사항</h4>

						<div class="row">
							<div class="col-3">
							<!-- 근무희탕타입 -->
							<p><%=member.getBusiness_type() %></p>
								
							</div>

							<div class="col-2">
							<p><%=member.getCareer()==1?"있음":"없음" %></p>
								
							</div>

							<div class="col-7">
								<div class="mt-1 ml-3" style="max-height: 33%">
									<h5 class="text-white">근무가능일</h5>
								</div>
								<div class="mt-1 btn btn-md btn-dark" style="max-height: 33%">
									<%=member.getWorkable_days() %>
								</div>

							</div>
						</div>

						<br> <input type="hidden" name="workTime" id="workTime" />
						<!-- 희망근무시간대 -->
						<div class="btn-group-toggle ml-3" id="workTimeCkb">
							<div class="text-white">희망 근무시간대</div>
							<p><%=member.getWorkTime() %></p>
							<label class="checkbox-inline btn-dark btn-md mr-3"> <input
								type="checkbox" name="workTime[]" class="inlineCheckbox"
								value="오전" onclick="checkBoxSelector(this, 0);"> 오전
							</label> <label class="checkbox-inline btn-dark btn-md mr-3"> <input
								type="checkbox" name="workTime[]" class="inlineCheckbox"
								value="오후" onclick="checkBoxSelector(this, 1);"> 오후
							</label> <label class="checkbox-inline btn-dark btn-md mr-3"> <input
								type="checkbox" name="workTime[]" class="inlineCheckbox"
								value="저녁" onclick="checkBoxSelector(this, 2);"> 저녁
							</label> <label class="checkbox-inline btn-dark btn-md mr-3"> <input
								type="checkbox" name="workTime[]" class="inlineCheckbox"
								value="야간" onclick="checkBoxSelector(this, 3);"> 야간
							</label> <label class="checkbox-inline btn-dark btn-md mr-3"> <input
								type="checkbox" name="workTime[]" class="inlineCheckbox"
								value="상관없음" onclick="checkBoxSelector(this, 4);"> 상관없음
							</label>
						</div>


						<div class="input-group">
							<input type="text" class="form-control mb-1 mr-1"
								name="introduce_title" id="introduce_title"
								placeholder="<%=member.getIntroduce_title()%>" />
						</div>
						<div>
							<textarea name="introduce" id="introduce" class="col-12" rows="5"
								placeholder="<%=member.getIntroduce()%>"></textarea>
						</div>

				</div>
				<!-- /.col-lg-6 -->
				<div class="col-lg-2"></div>
			</div>
		</div>
		<!-- /.row -->


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
<!-- Bootstrap core JavaScript -->
<!--  <script src="/sp/vendor/jquery/jquery.min.js"></script> -->
<script src="/sp/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>