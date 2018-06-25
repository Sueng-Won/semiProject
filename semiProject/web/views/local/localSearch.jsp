<%@page import="com.what.semi.common.template.LocalPageInfo"%>
<%@page import="com.what.semi.common.template.PageInfo"%>
<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- branchtest -->
<%
	ArrayList<RecruitmentVo> list = 
		(ArrayList<RecruitmentVo>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getTotalCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	
	int mapLevel = (Integer)request.getAttribute("mapLevel");
	
	double centerLatitude = (Double) request.getAttribute("centerLatitude");
	double centerLongitude = (Double) request.getAttribute("centerLongitude");
%>

<%@include file="/views/common/header.jsp" %>
    <!-- Page Content -->
    
    <link href="/sp/css/daumMap.css" rel="stylesheet">
    <div class="container" style="min-height: 800px">
      <div class="row">
      <%@include file="/views/common/nav.jsp" %>
   
        <!-- /.col-lg-3 -->
        
        <!-- 지도API예제연습-->
        <div class="col-lg-9 my-4">
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
             <div class="nowMap">
            <div class="selArea">
            <button type="button" class="btnO" id="btn_map_area" onclick="map_area_move_view('')">
               <span>지역 직접선택</span>
            </button> 
            <!-- 레이어 열기 : btnO 적용 -->
            </div>
            
         <div id="dev_map_area_view" class="helpLayer" hidden="true">
            <div class="popCon">
                  <p class="title">* 지역을 선택하시면 <strong>중심지역(시청·구청·주민센터)</strong>으로 지도가 이동합니다.</p>
               <ul class="local">
                  <li id="dev_map_sicode_I000"><a href="javascript:map_areagu_search('I000')">서울</a></li>
                  <li id="dev_map_sicode_B000"><a href="javascript:map_areagu_search('B000')">경기</a></li>
                  <li id="dev_map_sicode_K000"><a href="javascript:map_areagu_search('K000')">인천</a></li>
                  <li id="dev_map_sicode_A000"><a href="javascript:map_areagu_search('A000')">강원</a></li>
                  <li id="dev_map_sicode_G000"><a href="javascript:map_areagu_search('G000')">대전</a></li>
                  <li id="dev_map_sicode_1000"><a href="javascript:map_areagu_search('1000')">세종</a></li>
                  <li id="dev_map_sicode_O000"><a href="javascript:map_areagu_search('O000')">충남</a></li>
                  <li id="dev_map_sicode_P000"><a href="javascript:map_areagu_search('P000')">충북</a></li>
                  <li id="dev_map_sicode_H000"><a href="javascript:map_areagu_search('H000')">부산</a></li>
                  <li id="dev_map_sicode_J000"><a href="javascript:map_areagu_search('J000')">울산</a></li>
                  <li id="dev_map_sicode_C000"><a href="javascript:map_areagu_search('C000')">경남</a></li>
                  <li id="dev_map_sicode_D000"><a href="javascript:map_areagu_search('D000')">경북</a></li>
                  <li id="dev_map_sicode_F000"><a href="javascript:map_areagu_search('F000')">대구</a></li>
                  <li id="dev_map_sicode_E000"><a href="javascript:map_areagu_search('E000')">광주</a></li>
                  <li id="dev_map_sicode_L000"><a href="javascript:map_areagu_search('L000')">전남</a></li>
                  <li id="dev_map_sicode_M000"><a href="javascript:map_areagu_search('M000')">전북</a></li>
                  <li id="dev_map_sicode_N000"><a href="javascript:map_areagu_search('N000')">제주</a></li>
               </ul>
                  <div id="dev_map_areagu_box" class="area">
                     <h3 class="skip">서울</h3>
                        <ul>
                        <li id="dev_map_gucode_I010"><a href="javascript:map_move_gu('37.51731','127.0475','315995','546551','I010');">강남구</a></li>
                        <li id="dev_map_gucode_I020"><a href="javascript:map_move_gu('37.53013','127.1237','322750','547908','I020');">강동구</a></li>
                        <li id="dev_map_gucode_I030"><a href="javascript:map_move_gu('37.63976','127.0255','314189','560160','I030');">강북구</a></li>
                        <li id="dev_map_gucode_I040"><a href="javascript:map_move_gu('37.55093','126.8496','298549','550478','I040');">강서구</a></li>
                        <li id="dev_map_gucode_I050"><a href="javascript:map_move_gu('37.47815','126.9515','307459','542295','I050');">관악구</a></li>
                        <li id="dev_map_gucode_I060"><a href="javascript:map_move_gu('37.53862','127.0824','319101','548885','I060');">광진구</a></li>
                        <li id="dev_map_gucode_I070"><a href="javascript:map_move_gu('37.49547','126.8875','301825','544282','I070');">구로구</a></li>
                        <li id="dev_map_gucode_I080"><a href="javascript:map_move_gu('37.45708','126.8957','302497','540013','I080');">금천구</a></li>
                        <li id="dev_map_gucode_I090"><a href="javascript:map_move_gu('37.65436','127.0564','316940','561753','I090');">노원구</a></li>
                        <li id="dev_map_gucode_I100"><a href="javascript:map_move_gu('37.66877','127.0471','316135','563360','I100');">도봉구</a></li>
                        <li id="dev_map_gucode_I110"><a href="javascript:map_move_gu('37.57453','127.0396','315365','552908','I110');">동대문구</a></li>
                        <li id="dev_map_gucode_I120"><a href="javascript:map_move_gu('37.51245','126.9395','306441','546114','I120');">동작구</a></li>
                        <li id="dev_map_gucode_I130"><a href="javascript:map_move_gu('37.56632','126.9015','303151','552131','I130');">마포구</a></li>
                        <li id="dev_map_gucode_I140"><a href="javascript:map_move_gu('37.57922','126.9368','306286','553527','I140');">서대문구</a></li>
                        <li id="dev_map_gucode_I150"><a href="javascript:map_move_gu('37.48357','127.0326','314639','542820','I150');">서초구</a></li>
                        <li id="dev_map_gucode_I160"><a href="javascript:map_move_gu('37.56346','127.0368','315103','551682','I160');">성동구</a></li>
                        <li id="dev_map_gucode_I170"><a href="javascript:map_move_gu('37.58941','127.0167','313360','554580','I170');">성북구</a></li>
                        <li id="dev_map_gucode_I180"><a href="javascript:map_move_gu('37.51452','127.1062','321185','546190','I180');">송파구</a></li>
                        <li id="dev_map_gucode_I190"><a href="javascript:map_move_gu('37.51701','126.8666','300006','546695','I190');">양천구</a></li>
                        <li id="dev_map_gucode_I200"><a href="javascript:map_move_gu('37.52644','126.896','302614','547710','I200');">영등포구</a></li>
                        <li id="dev_map_gucode_I210"><a href="javascript:map_move_gu('37.53253','126.9905','310973','548292','I210');">용산구</a></li>
                        <li id="dev_map_gucode_I220"><a href="javascript:map_move_gu('37.60278','126.9291','305642','556149','I220');">은평구</a></li>
                        <li id="dev_map_gucode_I230"><a href="javascript:map_move_gu('37.57304','126.9796','310063','552798','I230');">종로구</a></li>
                        <li id="dev_map_gucode_I240"><a href="javascript:map_move_gu('37.56385','126.9976','311639','551761','I240');">중구</a></li>
                        <li id="dev_map_gucode_I250"><a href="javascript:map_move_gu('37.60633','127.0926','320075','556391','I250');">중랑구</a></li>
                        </ul>
                  </div>
               <div id="dev_map_areagu_box" class="area"></div>
               <div id="dev_map_areadong_box" class="dong"></div>
               <div class="btns" id="gi_area_S_form" style="display:none;">
                  <button type="button" class="btnSch" onclick="map_move(gi_loc_lat, gi_loc_lng);$('div.partWrap ul li:first').click();map_area_move_view('0');"><span>검색</span></button>
                  <button type="button" class="btnCan" onclick="map_area_move_view('0');"><span>취소</span></button>
               </div>
            </div>
            <p class="popClose">
               <button class="btn btn-dark btn-sm btn-block ml-2" type="button" onclick="javascript:map_area_move_view('0');">
                  <span>닫기</span>
               </button>
            </p>
         </div>
         </div>
          </div>
          
      <div class="carousel-inner" role="listbox">
           <div id="map"  style="width:auto;height:400px;"></div>
            <p>* 원하는 위치로 지도를 움직인 후 검색을 클릭하세요. 
                 <button class="btn btn-dark btn-sm btn-block ml-2" id="btn1" onclick="localList();">
                      <span>검색</span>
                  </button>
            </p>
      </div>         
      
        <!-- 지도API예제연습-->
         
           <div class="row">
         <%for(RecruitmentVo rv : list) {%>
               <div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
                 <div class="card h-100">
                   <a href=""><img class="card-img-top" src="http://placehold.it/300x300" alt=""></a>
                   <div class="card-body">
                     <h4 class="card-title">
                       <a href=""><%=rv.getRecruitment_name() %></a><!-- 게시물 이름 -->
                     </h4>
                     <h5>시급 : <%=rv.getPay()%></h5>
                     <p class="card-text"><%=rv.getRecruitment_title() %></p>
                   </div>
                   <div class="card-footer">
                     <small class="text-muted"><%=rv.getAddress() %></small>
                   </div>
                 </div>
               </div>
         <%} %>
          </div>

          <!-- /.row -->
        <!--====================================   페이지선택버튼    ==================================  -->
           <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
					<button onclick="movePage(<%=currentPage==1?1:currentPage-1%>);" type="button" class="btn btn-default bg-dark text-white">◀</button>
					<%for(int i = startPage; i <= endPage; i++){ %>
						<%if(currentPage != i){ %>
						<button onclick="movePage();" type="button" class="btn btn-default bg-dark text-white">1</button>
						<%}else{ %>
						<button type="button" class="btn btn-default bg-dark text-white disabled">1</button>
						
						<%} %>
					<%} %>
					<button onclick="movePage(<%=currentPage==maxPage?maxPage:maxPage+1%>);" type="button" class="btn btn-default bg-dark text-white">▶</button>
			  </div>
			</div>
      <!--=========================================================================================-->

        </div>
        <!-- /.col-lg-9 -->
      </div>
      <!-- /.row -->
    
      

    </div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>
   <script type="text/javascript">
            
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
   mapOption = { 
	   
		center: new daum.maps.LatLng(<%=centerLatitude%>, <%=centerLongitude%>), // 지도의 중심좌표
		level: <%=mapLevel%> // 지도의 확대 레벨

   };
   
   // 지도를 생성합니다
   var map = new daum.maps.Map(mapContainer, mapOption); 
   
   // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
   var mapTypeControl = new daum.maps.MapTypeControl(); 
   
   // 지도 타입 컨트롤을 지도에 표시합니다
   // daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
   map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT); 
   
   // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    var mapTypeControl = new daum.maps.MapTypeControl(); 
    
   // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
   var zoomControl = new daum.maps.ZoomControl();
   map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
   
   // 마커를 표시할 위치와 title 객체 배열입니다 
      var positions = [
      <%int count=0;%>
      <%for(RecruitmentVo rv : list){%>
         <%if(count==0){%>   
         {content: '<div onclick=""><%=rv.getRecruitment_name()%></div>', 
         latlng: new daum.maps.LatLng(<%=rv.getR_latitude()%>, <%=rv.getR_longitude()%>) }
         <%}else{%>
         ,{content: '<div onclick=""><%=rv.getRecruitment_name()%></div>', 
         latlng: new daum.maps.LatLng(<%=rv.getR_latitude()%>, <%=rv.getR_longitude()%>) }
         
         <%}%>
         <%count++;%>
      <%}%>
      ];
      console.log('배열생성');
   
   // 마커 이미지의 이미지 주소입니다
   var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
       
   for (var i = 0; i < positions.length; i ++) {
       
       // 마커 이미지의 이미지 크기 입니다
       var imageSize = new daum.maps.Size(24, 35); 
       
       // 마커 이미지를 생성합니다    
       var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
       
      
       // 마커를 생성합니다
       var marker = new daum.maps.Marker({
           map: map, // 마커를 표시할 지도
           position: positions[i].latlng, // 마커를 표시할 위치
           image : markerImage // 마커 이미지 
       });
       
       // 마커에 표시할 인포윈도우를 생성합니다 
       var infowindow = new daum.maps.InfoWindow({
           content: positions[i].content // 인포윈도우에 표시할 내용
       });
       
       daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
       daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
   }
   
   // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
   function makeOverListener(map, marker, infowindow) {
       return function() {
           infowindow.open(map, marker);
       };
   }
   
   // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
   function makeOutListener(infowindow) {
       return function() {
           infowindow.close();
       };
   }
   
   function localList() {
          // 지도의 현재 중심좌표를 얻어옵니다 
          var center = map.getCenter(); 
          
          // 지도의 현재 영역을 얻어옵니다 
          var bounds = map.getBounds();
          
          // 영역의 남서쪽 좌표를 얻어옵니다 
          var swLatLng = bounds.getSouthWest(); 
          
          // 영역의 북동쪽 좌표를 얻어옵니다 
          var neLatLng = bounds.getNorthEast(); 
          
          // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
          var boundsStr = bounds.toString();
          location.href = "/sp/localList.do?sLatitude="+swLatLng.getLat()
                +"&wLongitude="+swLatLng.getLng()
                +"&nLatitude="+neLatLng.getLat()
                +"&eLongitude="+neLatLng.getLng()
                +"&centerLatitude="+center.getLat()
                +"&centerLongitude="+center.getLng()
                +"&mapLevel="+map.getLevel();

         
          
      }
   
   //지역선택 레이어 open/close
   function map_area_move_view(view_stat) {
      if(view_stat == "1"){   //open
         $("#dev_map_area_view").removeClass("hide");
         $("#btn_map_area").attr("class","btnC");
      } else if(view_stat == "0"){ //close
         $("#dev_map_area_view").addClass("hide");
         $("#btn_map_area").attr("class","btnO");
      } else {
         if($("#dev_map_area_view").hasClass("hide")) {
            $("#dev_map_area_view").removeClass("hide");
            $("#btn_map_area").attr("class","btnC");
         } else {
            $("#dev_map_area_view").addClass("hide");
            $("#btn_map_area").attr("class","btnO");
         }
      }
   }
   
   //구군 선택 이동
   function map_move_gu(loc_lat, loc_lng, loc_x, loc_y, gu_code) {
      document.form.map_gu.value = gu_code;

      $("#dev_map_areagu_box .on").removeClass("on");
      $("#dev_map_gucode_"+ gu_code).addClass("on");
      $("#gi_area_S_form").show();
      gi_loc_lat = loc_lat;
      gi_loc_lng = loc_lng;

      //map_move(loc_x, loc_y);
      map_areadong_search(gu_code);
   }
   
   //지역선택 레이어 close/open
   function map_area_move_view(view_stat) {
      if(view_stat == "1"){   //open
         $("#dev_map_area_view").removeClass("hide");
         $("#btn_map_area").attr("class","btnC");
      } else if(view_stat == "0"){ //close
         $("#dev_map_area_view").addClass("hide");
         $("#btn_map_area").attr("class","btnO");
      } else {
         if($("#dev_map_area_view").hasClass("hide")) {
            $("#dev_map_area_view").removeClass("hide");
            $("#btn_map_area").attr("class","btnC");
         } else {
            $("#dev_map_area_view").addClass("hide");
            $("#btn_map_area").attr("class","btnO");
         }
      }
   }
   
   // 동 선택 이동
   function map_move_dong(loc_lat, loc_lng, loc_x, loc_y, dong_code) {
      document.form.map_dong.value = dong_code;

      $("#dev_map_areadong_box .on").removeClass("on");
      $("#dev_map_dongcode_"+ dong_code).addClass("on");

      gi_loc_lat = loc_lat;
      gi_loc_lng = loc_lng;

      //map_move(loc_x, loc_y);
      //map_area_move_view('0');
   }
   
   //구군 검색
   function map_areagu_search(gval) {
      $("#dev_map_areadong_box").html("").addClass("hide");
      $(".local .on").removeClass("on");
      $("#dev_map_sicode_"+ gval).addClass("on");

      $.ajax({
         url:"/list/gi/mon_search_form_data.asp?sch_type=maparea&scd="+ gval +"&gcd="+ document.form.map_gu.value ,
         success:function(response, status, request){
         //   alert("aa code="+request.status+", message="+request.responseText)
            if(response != ""){
               $("#dev_map_areagu_box").html(response).removeClass("hide");
            }
         },
         error:function (request,status,error){
         //   alert("bb code="+request.status+", message="+request.responseText);
         }
      });
      $("#gi_area_S_form").hide();
   }
   
   //동 검색
   function map_areadong_search(gval) {
      $.ajax({
         url:"/list/gi/mon_search_form_data.asp?sch_type=mapareadong&gcd="+ gval +"&dcd="+ document.form.map_dong.value ,
         success:function(response, status, request){
         //   alert("aa code="+request.status+", message="+request.responseText)
            if(response != ""){
               $("#dev_map_areadong_box").html(response).removeClass("hide");
            }
         },
         error:function (request,status,error){
         //   alert("bb code="+request.status+", message="+request.responseText);
         }
      });
      $("#gi_area_S_form").show();
   }
   </script>
    <!-- /.container -->
<%@include file="/views/common/footer.jsp"%>