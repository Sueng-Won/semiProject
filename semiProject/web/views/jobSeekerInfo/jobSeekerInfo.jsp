<%@page import="com.what.semi.resume.model.vo.MyResumeVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>
<%
	ArrayList<MyResumeVo> jsList = (ArrayList<MyResumeVo>) request.getAttribute("jsList");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<style>
.resultJobWrap, .resultLocalWrap, .searchResultWrap {
	border: 1px solid black;
}
.searchResult{
	float:left;
}

#pageHeader {
	padding-top: 20px;
}

#jobSeekerInfo {
	border: 1px solid black;
}

#jobSeekerInfo:hover, #jobList li:hover, #dateList li:hover, #localArea1 li:hover,
#localArea2 li:hover {
	cursor: pointer;
	background: lightGray;
}

.info1 {
	margin-top: 10px;
}
.textLine p{
	line-height:40%;
}

.resultList {
	border: 1px solid black;
	display: inline;
	margin-right: 5px;
}

.selectedList {
	background: lightblue;
}
.pageArea{
	text-align:center;
}
.workTime{
	padding-top:15px;
}
</style>
<script>
	function movePage(pageNum) {
		location.href = "/tsp/jobseekerinfo.do?currentPage=" + pageNum;
	}
	function removeOption(obj, id) {
		// 				console.log(id);
		$(id).removeClass("selectedList");
		obj.parentNode.remove();
		//var str = obj.parentNode.innerText.slice(0,-1);
		
		//여기닷
		drawList();
	}
	function movePage(index){
		location.href = "/sp/jobSeekerInfo.do?currentPage="+index;
	}
	function movePageAjax(index){
		/* 강사님 로직 */
		var keyWordJobArr = new Array();
		$(".jobList").each(function() {
			if ($(this).hasClass("selectedList")) {
				keyWordJobArr.push("'"+$(this).text()+"'");
			}
		});
		$("#searchJob").val(keyWordJobArr);
		
		var keyWordLocalArr = new Array();
		var $selectedText = $("#localArea1 .selectedList").text();
		$(".localList2").each(function() {
			if ($(this).hasClass("selectedList")) {
				keyWordLocalArr.push("'"+$selectedText+" "+$(this).text()+"'");
			}
		});
		$("#searchLocal").val(keyWordLocalArr);
		
		
		
		$.ajax({
			url : "/sp/getResumeCondition.do?currentPage="+index,
			type :"get", 
			data : {searchJob : $("#searchJob").val(),
					 searchLocal : $("#searchLocal").val(),
					 searchworkTime : $("#workTime").val()},
					 
			success : function(data){
				var pageArea = $(".pageArea");
				var $finalResult = $(".finalResult");
				
				$finalResult.children().remove();
				pageArea.children().remove();
				//새로만들어주기로직태그들
					for(var key in data){
						var user = data[key];
						if(key=="pi"){
							/* currentPage1
							endPage2
							limit5
							maxPage2
							startPage1
							totalCount5 */
							console.log(user);
							
							for(var i=user.startPage; i<=user.maxPage; i++){
								var $button = $("<button class='btn btn-info btn-xs' onclick='movePageAjax("+i+")'>");
								if(i==user.currentPage){
									$button.attr("disabled", true)
								}
								$button.text(i);
								pageArea.append($button);
								
							}
						}
						
						if(key=="jsList" && keyWordJobArr.length>0){
								
								for(var i in user){
									var $div = $("<div class='col-lg-6' id='jobSeekerInfo'>");
									var $div2 = $("<div class='info1 row'>");
									var $div3 = $("<div class='col-lg-4'>");
									var $div4 = $("<div class='col-lg-8 textLine'>");
									var $p1= $("<p>");
									var $p2= $("<p>");
									var $p3= $("<p>");
									var $p4= $("<p>");
									var $p5=$("<p>");
									var $p6=$("<p>");
									var $img = $("<img width=100px; height=100px>");
									if(user[i].gender=='F'){
										$img.attr("src","images/resume/woman.png");
									}else{
										$img.attr("src","images/resume/man.png");
									}
									$p1.append($img); // p1 <--이미지를 p1에 붙임
									/* $p2.text(user[i].name,+"("+user[i].gender=='F'?"여":"남"+")"+user[i].age); */
									$p2.text(user[i].name);
									$p3.text(user[i].gender=='F'?"여":"남");
									$p3.text(user[i].age);
									$p4.text(user[i].business_type);
									$p5.text(user[i].career==1?'있음':'없음');
									/* $p4.text(user[i].business_type+", 경력 : "+user[i].career==1?'있음':'없음'); */
									$p6.text(user[i].address);
									
									
									$div3.append($p1); //div4 col-lg-4에 붙임
									
									$div4.append($p2);
									$div4.append($p3);
									$div4.append($p4);
									$div4.append($p5); 
									$div4.append($p6);//div4 col-lg-8에 붙임
									
									$div2.append($div3);
									$div2.append($div4);
									
									$div.append($div2);
									$div.append($div2);
									$finalResult.append($div);
								}
					}else{
							
							for(var i=0; i<user.length; i++){
								var $div = $("<div class='col-lg-6' id='jobSeekerInfo'>");
								var $div2 = $("<div class='info1 row'>");
								var $div3 = $("<div class='col-lg-4'>");
								var $div4 = $("<div class='col-lg-8 textLine'>");
								var $p1= $("<p>");
								var $p2= $("<p>");
								var $p3= $("<p>");
								var $p4= $("<p>");
								var $p5=$("<p>");
								var $p6=$("<p>");
								var $img = $("<img width=100px; height=100px>");
								if(user[i].gender=='F'){
									$img.attr("src","images/resume/woman.png");
								}else{
									$img.attr("src","images/resume/man.png");
								}
								$p1.append($img); // p1 <--이미지를 p1에 붙임
								/* $p2.text(user[i].name,+"("+user[i].gender=='F'?"여":"남"+")"+user[i].age); */
								$p2.text(user[i].name);
								$p3.text(user[i].gender=='F'?"여":"남");
								$p3.text(user[i].age);
								$p4.text(user[i].business_type);
								$p5.text(user[i].career==1?'있음':'없음');
								/* $p4.text(user[i].business_type+", 경력 : "+user[i].career==1?'있음':'없음'); */
								$p6.text(user[i].address);
								
								
								$div3.append($p1); //div4 col-lg-4에 붙임
								
								$div4.append($p2);
								$div4.append($p3);
								$div4.append($p4);
								$div4.append($p5); 
								$div4.append($p6);//div4 col-lg-8에 붙임
								
								$div2.append($div3);
								$div2.append($div4);
								
								$div.append($div2);
								$div.append($div2);
								$finalResult.append($div);
						}
				  }
				}
			},
			error : function(e){
				console.log(e);
			}
		}); 
	}
	$(function() {
		$("#jobSeeker").addClass("active");
		//var keyWordJobArr = new Array();
		$(".localList").on("click",function() {
							var $localArea2 = $("#localArea2");
							var $localList = $(".localList");
							var $resultList = $(".resultList");
							var $selectedList = $(this);
							var $selectedSpan = $(this).children();
							$localArea2.empty();

							$localList.removeClass("selectedList");
							$selectedList.addClass("selectedList");
							var localCode = $(this).val();
							var parameter = "?"+ encodeURIComponent("authkey")+ "="	+ encodeURIComponent("582d1a08bfffd1151cb954");
							parameter += "&" + encodeURIComponent("admCode")
									+ "=" + encodeURIComponent(localCode);

							$.ajax({
										url : "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admSiList.json"
												+ parameter,
										type : "get",
										success : function(data) {
											$.each(data,	function(index,	item) {
																var dataList = item.admVOList;
																var selectList1;
																$(".localList").each(function() {
																	if ($(this).hasClass("selectedList")) {
																		selectList1 = $(this).text();
																	}
																});
<%--	admCodeNm 시포함구역 lowestAdmCodeNm 최하위 행정구역  --%>
					                  	for (var key in dataList) {
											var $li = $("<li class='localList2' id=local"+key+">");
											
											for (i = 0; i < $resultList.length; i++) {
												if ($resultList[i].innerText.indexOf(" ") != -1) {
														if (($resultList[i].innerText.split(" ")[1].slice(0,-1)) == dataList[key].lowestAdmCodeNm 
																&& $resultList[i].innerText.split(" ")[0]==selectList1) {
																				$li.text(dataList[key].lowestAdmCodeNm);
																				$li.addClass("selectedList");
																			}
																		}

																	}
																	if(dataList[key].lowestAdmCodeNm.indexOf(" ")==-1 ){
																	$li.text(dataList[key].lowestAdmCodeNm);
																	$localArea2.append($li);
																		
																	}
																}
															});
										},
										error : function(e) {
											console.log(e);
											console.log("뭐야" + e);
										}
									});
											<%-- end of ajax --%>
										});
<%-- end of option function --%>
			$(document).on("click",".localList2", function() {
							var keyWord = $(this).text();
							var $resultList = $(".resultList");
							var $selectedText = $("#localArea1 .selectedList").text();
							var $currentLocalList = $(this);

							if (!($currentLocalList.hasClass("selectedList"))) {
								$currentLocalList.toggleClass("selectedList");
								
								var $div = $("<div class='resultList' >");
								var $button = $("<button type='button' onclick='removeOption(this,"+ $(this).attr("id") + ");'>");
								var $span = $("<span aria-hidden='true'>x</span>");
								var $searchResult = $(".searchResult");

								$div.text($selectedText + " " + keyWord);
								$button.append($span);
								$div.append($button);
								$searchResult.append($div);


							} else {
								$currentLocalList.toggleClass("selectedList");

								for (i = 0; i < $resultList.length; i++) {
									if (($resultList[i].innerText.split(" ")[1]
											.slice(0, -1)) == keyWord) {
										$resultList[i].remove();
									}
								}
							}
							//드로우리스트 메소드로 뺌
							drawList();

						});
		$(".jobList").click(function() {

							var keyWord = $(this).text();

							var $resultList = $(".resultList");
							var $currentLocalList = $(this);

							if (!($currentLocalList.hasClass("selectedList"))) {
								$currentLocalList.toggleClass("selectedList");

								var $div = $("<div class='resultList' ></div>");
								var $button = $("<button type='button'  onclick='removeOption(this,"+ $(this).attr("id") + ");'>");
								var $span = $("<span aria-hidden='true'>x</span>");
								var $searchResult = $(".searchResult");

								//keyWordJobArr.push(keyWord);

								$div.text(keyWord);
								$button.append($span);
								$div.append($button);
								$searchResult.append($div);

							} else {
								//var itemtoRemove = keyWord;
								//keyWordJobArr.splice($.inArray(itemtoRemove, keyWordJobArr),1);

								$currentLocalList.toggleClass("selectedList");

								for (i = 0; i < $resultList.length; i++) {
									if (($resultList[i].innerText.slice(0, -1)) == keyWord) {
										$resultList[i].remove();
									}
								}	
							}
							drawList();
						});
		
		 $(".dateList").on("click",function(){
			 $dateList = $(".dateList");

			 $dateList.removeClass("selectedList");
			 
			 if($(this).hasClass("selectedList")){
				 $(this).removeClass("selectedList");
			 }else{
				 $(this).addClass("selectedList");
			 }
			 console.log($(this).val());
			 $("#searchDate").val($(this).val());
			 drawList();
		 });
	});
	
	//리스트 그리기 메소드
	function drawList() {
		/* 강사님 로직 */
		var keyWordJobArr = new Array();
		$(".jobList").each(function() {
			if ($(this).hasClass("selectedList")) {
				keyWordJobArr.push("'"+$(this).text()+"'");
			}
		});
		$("#searchJob").val(keyWordJobArr);
		
		var keyWordLocalArr = new Array();
		var $selectedText = $("#localArea1 .selectedList").text();
		$(".localList2").each(function() {
			if ($(this).hasClass("selectedList")) {
				keyWordLocalArr.push("'"+$selectedText+" "+$(this).text()+"'");
			}
		});
		$("#searchLocal").val(keyWordLocalArr);
		
		var keyWordDateValue;
		$(".dateList").each(function() {
			if ($(this).hasClass("selectedList")) {
				$("#searchDate").val($(this).val());
			}
		});
		
		
		$.ajax({
			url : "/sp/getResumeCondition.do",
			type :"get", 
			data : {searchJob : $("#searchJob").val(),
					 searchLocal : $("#searchLocal").val(),
					 searchworkTime : $("#workTime").val(),
					 searchDate : $("#searchDate").val()},
					 
			success : function(data){
				var pageArea = $(".pageArea");
				var $finalResult = $(".finalResult");
				
				$finalResult.children().remove();
				pageArea.children().remove();
				//새로만들어주기로직태그들
					for(var key in data){
						var user = data[key];
						console.log(user);
						if(key=="pi"){
							/* currentPage1
							endPage2
							limit5
							maxPage2
							startPage1
							totalCount5 */
							console.log(user);
							
							for(var i=user.startPage; i<=user.maxPage; i++){
								var $button = $("<button class='btn btn-info btn-xs' onclick='movePageAjax("+i+")'>");
								if(i==user.currentPage){
									$button.attr("disabled", true)
								}
								$button.text(i);
								pageArea.append($button);
							}
						}else{
							if(key=="jsList" && keyWordJobArr.length>0){
								console.log(user);
								for(var i in user){
									var $div = $("<div class='col-lg-6' id='jobSeekerInfo'>");
									var $div2 = $("<div class='info1 row'>");
									var $div3 = $("<div class='col-lg-4'>");
									var $div4 = $("<div class='col-lg-8 textLine'>");
									var $p1= $("<p>");
									var $p2= $("<p>");
									var $p3= $("<p>");
									var $p4= $("<p>");
									var $p5=$("<p>");
									var $p6 =$("<p>");
									var $img = $("<img width=100px; height=100px>");
									if(user[i].gender=='F'){
										$img.attr("src","images/resume/woman.png");
									}else{
										$img.attr("src","images/resume/man.png");
									}
									$p1.append($img); // p1 <--이미지를 p1에 붙임
									/* $p2.text(user[i].name,+"("+user[i].gender=='F'?"여":"남"+")"+user[i].age); */
									$p2.text(user[i].name);
									$p3.text(user[i].gender=='F'?"여":"남");
									$p3.text(user[i].age);
									$p4.text(user[i].business_type);
									$p5.text(user[i].career==1?'있음':'없음');
									/* $p4.text(user[i].business_type+", 경력 : "+user[i].career==1?'있음':'없음'); */
									$p6.text(user[i].address);
									
									
									$div3.append($p1); //div4 col-lg-4에 붙임
									
									$div4.append($p2);
									$div4.append($p3);
									$div4.append($p4);
									$div4.append($p5); 
									$div4.append($p6);//div4 col-lg-8에 붙임
									
									$div2.append($div3);
									$div2.append($div4);
									
									$div.append($div2);
									$div.append($div2);
									$finalResult.append($div);
								}
					}else{
							
							for(var i=0; i<user.length; i++){
								var $div = $("<div class='col-lg-6' id='jobSeekerInfo'>");
								var $div2 = $("<div class='info1 row'>");
								var $div3 = $("<div class='col-lg-4'>");
								var $div4 = $("<div class='col-lg-8 textLine'>");
								var $p1= $("<p>");
								var $p2= $("<p>");
								var $p3= $("<p>");
								var $p4= $("<p>");
								var $p5=$("<p>");
								var $p6 =$("<p>");
								var $img = $("<img width=100px; height=100px>");
								if(user[i].gender=='F'){
									$img.attr("src","images/resume/woman.png");
								}else{
									$img.attr("src","images/resume/man.png");
								}
								$p1.append($img); // p1 <--이미지를 p1에 붙임
								/* $p2.text(user[i].name,+"("+user[i].gender=='F'?"여":"남"+")"+user[i].age); */
								$p2.text(user[i].name);
								$p3.text(user[i].gender=='F'?"여":"남");
								$p3.text(user[i].age);
								$p4.text(user[i].business_type);
								$p5.text(user[i].career==1?'있음':'없음');
								/* $p4.text(user[i].business_type+", 경력 : "+user[i].career==1?'있음':'없음'); */
								$p6.text(user[i].address);
								
								
								$div3.append($p1); //div4 col-lg-4에 붙임
								
								$div4.append($p2);
								$div4.append($p3);
								$div4.append($p4);
								$div4.append($p5); 
								$div4.append($p6);//div4 col-lg-8에 붙임
								
								$div2.append($div3);
								$div2.append($div4);
								
								$div.append($div2);
								$div.append($div2);
								$finalResult.append($div);
						}
						
				  }
						}
						
						
				}
			},
			error : function(e){
				console.log(e);
			}
		}); 
	}
	function test1(){
		var str = "경기도 남양주시 진건읍";
		console.log(str)
		console.log(str.indexOf(" "));
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
		
		drawList() ;

	}
	function moveUserResume(resume_id){
		location.href = "/sp/seeMyResume.do?resume_id="+resume_id;
	}
</script>

<div class="container" style="min-height: 800px">
	<!-- 내용을 담아놓을 컨테이너 -->
	<div class="row">
		<%@include file="/views/common/nav.jsp"%>
		<div class="col-lg-9 wrap">
			<!-- Page Content -->
			<div class="row">
				<div class="col-lg-12" id="pageHeader">
					<h1>구직자 정보 찾기</h1>
					<hr>
				</div>
				<div class="col-xs-3 col-lg-3">
					<h4>지역</h4>
					<div class="resultLocalWrap"
						style="overflow-x: hidden; height: 90px;">
						<ul class="list-unstyled" id="localArea1">
							<li class="localList" value="11">서울</li>
							<li class="localList" value="26">부산</li>
							<li class="localList" value="27">대구</li>
							<li class="localList" value="28">인천</li>
							<li class="localList" value="29">광주</li>
							<li class="localList" value="30">대전</li>
							<li class="localList" value="31">울산</li>
							<li class="localList" value="36">세종시</li>
							<li class="localList" value="41">경기도</li>
							<li class="localList" value="42">강원도</li>
							<li class="localList" value="43">충북</li>
							<li class="localList" value="44">충남</li>
							<li class="localList" value="45">전북</li>
							<li class="localList" value="46">전남</li>
							<li class="localList" value="47">경북</li>
							<li class="localList" value="48">경남</li>
							<li class="localList" value="50">제주도</li>
						</ul>
					</div>
				</div>
				<div class="col-xs-3 col-lg-3">
					<h4>상세지역</h4>
					<div class="searchResultWrap"
						style="overflow-x: hidden; height: 90px;">
						<ul class="list-unstyled" id="localArea2" class="localArea2">
							<li class="none"><span >지역을 먼저 <br> 선택하세요!</span></li>
						</ul>
					</div>
				</div>
			<div class="col-xs-3 col-lg-3">
				<h4>업종</h4>
				<div class="resultJobWrap" style="overflow-x: hidden; height: 90px;">
					<ul class="list-unstyled" id="jobList">
						<li class="jobList" id="youtong">유통</li>
						<li class="jobList" id="sangsan">고객상담</li>
						<li class="jobList" id="service">서비스</li>
						<li class="jobList" id="jejo">사무직</li>
						<li class="jobList" id="education">교육</li>
						<li class="jobList" id="sisik">생산/건설</li>
					</ul>
				</div>
			</div>

			<div class="col-xs-3 col-lg-3">
				<h4>주 단위 인력</h4>
				<div class="resultJobWrap" style="overflow-x: hidden; height: 90px;">
					<ul class="list-unstyled" id="dateList">
						<li class="dateList" id="week1" value="7">1주 이내</li>
						<li class="dateList" id="week2" value="14">2주 이내</li>
						<li class="dateList" id="week3" value="21">3주 이내</li>
						<li class="dateList" id="week4" value="28">4주 이내</li>
						<li><button onclick="test1();">ㅌㅅㅌ</button></li>
					</ul>
				</div>
			</div>
			    <div class="workTime btn-group-toggle ml-3" id="workTimeCkb">
			    	<h4>근무시간대</h4>
			    	<label class="checkbox-inline btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="오전" onclick="checkBoxSelector(this, 0);"> 오전
					</label>
					<label class="checkbox-inline  btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="오후" onclick="checkBoxSelector(this, 1);"> 오후
					</label>
					<label class="checkbox-inline  btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="저녁" onclick="checkBoxSelector(this, 2);"> 저녁
					</label>
					<label class="checkbox-inline  btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="야간" onclick="checkBoxSelector(this, 3);"> 야간
					</label>	
					<label class="checkbox-inline  btn-md mr-3">
					  <input type="checkbox" name="workTime[]" class="inlineCheckbox" value="상관없음" onclick="checkBoxSelector(this, 4);"> 상관없음
					</label>
			    </div>
		</div><hr>
			<div class="row">
			<h4>검색 조건</h4>
			<input type="hidden" name="searchDate" id="searchDate"/>
			<input type="hidden" name="searchLocal" id="searchLocal" /> 
			<input type="hidden" name="searchJob" id="searchJob" />
			<input type="hidden" name="workTime" id="workTime"/> <!-- 희망근무시간대 -->
			<div class="searchResult"></div>
		</div>
			<div class="row finalResult">
			<%if (null != jsList) {%>
			<%for (MyResumeVo js : jsList) {%>
			<div class="col-lg-6" id="jobSeekerInfo">
				<div class="info1 row" onclick="moveUserResume(<%=js.getResume_id()%>);">
					<div class="col-lg-4">
						<p>
							<%if (js.getGender() == 'M') {%>
							<img src="images/resume/man.png" width=100px; height=100px; />
							<%} else {%>
							<img src="images/resume/woman.png" width=100px; height=100px; />
							<%}	%>
						</p>
					</div>
					<div class="col-lg-8 textLine">
						<p><%=js.getName()%>
							(<%=js.getGender() == 'F' ? '여' : '남'%>,
							<%=js.getAge()%>)
						</p>
						<p><%=js.getIntroduce_title()%></p>
						<p><%=js.getBusiness_type()%>, 경력
							<%=js.getCareer() == 'Y' ? "있음" : "없음"%></p>
						<p><%=js.getAddress()%></p>
					</div>
				</div>
			</div>
			<%	}		%>
			<%} else {		%>
			<div class="col-md-12" id="jobSeekerInfo2">
				<h1>널포인트 익셉션발생</h1>
			</div>
			<%	}	%>
		</div>
		<!--  end .row -->
						<div class="pageArea" >
								<%for(int i = pi.getStartPage() ; i <= pi.getEndPage() ; i++){ %>
									<%if(pi.getCurrentPage() == i){ %>
									<button class="btn btn-info btn-xs" disabled><%=i %></button>
									<%}else{ %>
									<button class="btn btn-info btn-xs" onclick="movePage(<%=i%>);"><%=i %></button>
										<%} %>
								<%} %>
								
							</div><!-- end .row -->
			<!-- end of Page Content -->
		</div>
		<!-- end of col-lg-9  -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->

<%@include file="/views/common/footer.jsp"%>