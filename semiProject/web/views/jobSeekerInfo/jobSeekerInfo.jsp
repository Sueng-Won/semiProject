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

#jobSeekerInfo:hover {
	cursor: pointer;
	background: lightblue;
}

.info1 {
	margin-top: 10px;
}

.resultList {
	border: 1px solid black;
	display: inline;
	margin-right: 5px;
}

.selectedList {
	background: lightblue;
}

.selectedList li span {
	align: right;
}

#jobList li:hover {
	background: lightgray;
}
</style>
<script>
	function movePage(pageNum) {
		location.href = "/tsp/jobseekerinfo.do?currentPage=" + pageNum;
	}
	function removeOption(obj, id) {
		// 				console.log(id);
		$(id).removeClass("selectedList");
		$(id).children().toggleClass("glyphicon glyphicon-ok");
		obj.parentNode.remove();
		//var str = obj.parentNode.innerText.slice(0,-1);

	}
	$(function() {
		$("#jobSeeker").addClass("active");
		//var keyWordJobArr = new Array();
		$(".localList")	.on("click",function() {
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
<%--	admCodeNm 시포함구역 lowestAdmCodeNm 최하위 행정구역  --%>
					                  	for ( var key in dataList) {
												var $li = $("<li class='localList2' id=local"+key+">");
											for (i = 0; i < $resultList.length; i++) {
												if ($resultList[i].innerText.indexOf(" ") != -1) {
														if (($resultList[i].innerText.split(" ")[1].slice(0,-1)) == dataList[key].lowestAdmCodeNm) {
																				$li.text(dataList[key].lowestAdmCodeNm);
																				$li.addClass("selectedList");
																				$li.append($span);
																			}
																		}

																	}
																	$li.text(dataList[key].lowestAdmCodeNm);
																	$localArea2.append($li);
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

								var keyWordLocalArr = new Array();

							} else {
								$currentLocalList.toggleClass("selectedList");
								$currentLocalList.children().toggleClass(
										"glyphicon glyphicon-ok");

								for (i = 0; i < $resultList.length; i++) {
									if (($resultList[i].innerText.split(" ")[1]
											.slice(0, -1)) == keyWord) {
										$resultList[i].remove();
									}
								}
							}
							$(".localList2").each(function() {
								console.log($(this));
								console.log($(this).hasClass("selectedList"));
								if ($(this).hasClass("selectedList")) {
									keyWordLocalArr.push($(this).text());
								}
							});
							console.log(keyWordLocalArr);
							$("#searchLocal").val(keyWordLocalArr);

							/* $.ajax({
								url : "/sp/getResumeInfo.do",
								type :"post", 
								data : {}
								success : function(data){
									
								},
								error : function(e){
									console.log(e);
								}
							}); */

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
								$currentLocalList.children().toggleClass(
										"glyphicon glyphicon-ok");

								for (i = 0; i < $resultList.length; i++) {
									if (($resultList[i].innerText.slice(0, -1)) == keyWord) {
										$resultList[i].remove();
									}
								}
							}
							/* 강사님 로직 */
							var keyWordJobArr = new Array();
							$(".jobList").each(function() {
								console.log($(this));
								if ($(this).hasClass("selectedList")) {
									keyWordJobArr.push($(this).text());
								}
							});
							console.log(keyWordJobArr);
							$("#searchJob").val(keyWordJobArr);
							
							 $.ajax({
							url : "/sp/getResumeCondition.do",
							type :"post", 
							data : {searchJob : $("#searchJob").val(),
									 searchLocal : $("#searchLocal").val()},
							
							success : function(data){
								console.log(data);
								
							},
							error : function(e){
								console.log(e);
							}
						}); 

						});
	});
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
							<li class="localList" value="11">서울<span></span></li>
							<li class="localList" value="26">부산<span></span></li>
							<li class="localList" value="27">대구<span></span></li>
							<li class="localList" value="28">인천<span></span></li>
							<li class="localList" value="29">광주<span></span></li>
							<li class="localList" value="30">대전<span></span></li>
							<li class="localList" value="31">울산<span></span></li>
							<li class="localList" value="36">세종시<span></span></li>
							<li class="localList" value="41">경기도<span></span></li>
							<li class="localList" value="42">강원도<span></span></li>
							<li class="localList" value="43">충북<span></span></li>
							<li class="localList" value="44">충남<span></span></li>
							<li class="localList" value="45">전북<span></span></li>
							<li class="localList" value="46">전남<span></span></li>
							<li class="localList" value="47">경북<span></span></li>
							<li class="localList" value="48">경남<span></span></li>
							<li class="localList" value="50">제주도<span></span></li>
						</ul>
					</div>
				</div>
				<div class="col-xs-3 col-lg-3">
					<h4>상세지역</h4>
					<div class="searchResultWrap"
						style="overflow-x: hidden; height: 90px;">
						<ul class="list-unstyled" id="localArea2" class="localArea2">
							<li class="none"><span>지역을선택</span></li>
						</ul>
					</div>
				</div>
			<div class="col-xs-3 col-lg-3">
				<h4>업종</h4>
				<div class="resultJobWrap" style="overflow-x: hidden; height: 90px;">
					<ul class="list-unstyled" id="jobList">
						<li class="jobList" id="youtong">유통<span></span></li>
						<li class="jobList" id="sangsan">생산<span></span></li>
						<li class="jobList" id="service">서비스<span></span></li>
						<li class="jobList" id="jejo">제조<span></span></li>
						<li class="jobList" id="education">교육<span></span></li>
						<li class="jobList" id="sisik">시식<span></span></li>
					</ul>
				</div>
			</div>

			<div class="col-xs-3 col-lg-3">
				<h4>상세업종</h4>
				<div class="calender" style="overflow-x: hidden; height: 90px;">
					<ul class="list-unstyled" id="jobList">
						<li>달력</li>
					</ul>
				</div>
			</div>
			
		</div><br><br>
			<div class="row">
			<h4>검색 조건</h4>
			<input type="hidden" name="searchLocal" id="searchLocal" /> 
			<input type="hidden" name="searchJob" id="searchJob" />
			<div class="searchResult"></div>
		</div>
			<div class="row">
			<%if (null != jsList) {%>
			<%for (MyResumeVo js : jsList) {%>
			<div class="col-lg-6" id="jobSeekerInfo">
				<div class="info1 row">
					<div class="col-lg-4">
						<p>
							<%if (js.getGender() == 'N') {%>
							<img src="views/images/man.png" width=100px; height=100px; />
							<%} else {%>
							<img src="views/images/woman.png" width=100px; height=100px; />
							<%}	%>
						</p>
					</div>
					<div class="col-lg-8">
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
			<%
				}
			%>
			<%
				} else {
			%>
			<div class="col-md-12" id="jobSeekerInfo2">
				<h1>널포인트 익셉션발생</h1>
			</div>
			<%
				}
			%>
		</div>
		<!--  end .row -->
		<div class="row" text-align="center">
			<nav>
				<ul class="pagination">
					<%if (pi.getCurrentPage() == 1) {	%>
					<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
					<%} else {%>
					<li><a href="/tsp/jobseekerinfo.do?currentPag=1"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<%}	%>
					<%for (int i = pi.getStartPage(); i <= pi.getEndPage(); i++) {	%>
					<%	if (pi.getCurrentPage() == i) {		%>
					<li class="active"><a><span aria-hidden="true"><%=i%></span></a></li>
					<%	} else {	%>
					<li><a href="/tsp/jobseekerinfo.do?currentPage=<%=i%>"><%=i%></a></li>
					<%	}	%>
					<%	}		%>
					<%if (pi.getCurrentPage() == pi.getEndPage()) {	%>
					<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
					<%} else {%>
					<li><a
						href="/tsp/jobseekerinfo.do?currentPage=<%=pi.getEndPage()%>">
							<span aria-hidden="true">&raquo;</span>
					</a></li>
					<%} %>
				</ul>
			</nav>
		<hr>
		</div><!-- end .row -->
			<!-- end of Page Content -->
		</div>
		<!-- end of col-lg-9  -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->

<%@include file="/views/common/footer.jsp"%>