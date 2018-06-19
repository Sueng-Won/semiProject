<%@page import="com.what.semi.recruitment.model.vo.RecruitmentVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%ArrayList<RecruitmentVo> list = 
	(ArrayList<RecruitmentVo>)request.getAttribute("list"); %>
<%@include file="/views/common/header.jsp" %>
    <!-- Page Content -->
    <div class="container">

      <div class="row">

		<%@include file="/views/common/nav.jsp" %>

        <!-- /.col-lg-3 -->
        
            <!-- 지도API예제연습-->
        <div class="col-lg-9">
          <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
            <div class="carousel-inner" role="listbox">
	        	<div id="map"  style="width:900px;height:400px;"></div>
	        
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=154d504288d7ddddd16f6867efe451af&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
				
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
		center: new daum.maps.LatLng(37.502, 127.026581), // 지도의 중심좌표
		level: 4 // 지도의 확대 레벨
	};
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();
	var positions;
	<%for(RecruitmentVo rv : list){%>
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === daum.maps.services.Status.OK) {
			
	        coords = new daum.maps.LatLng(result[0].y, result[0].x);

	        
	    } 
	});
	<%}%>
	var positions = [
	    {
	        title: '카카오', 
	        latlng: new daum.maps.LatLng(33.450705, 126.570677)
	    },
	    {
	        title: '생태연못', 
	        latlng: new daum.maps.LatLng(33.450936, 126.569477)
	    },
	    {
	        title: '텃밭', 
	        latlng: new daum.maps.LatLng(33.450879, 126.569940)
	    },
	    {
	        title: '근린공원',
	        latlng: new daum.maps.LatLng(33.451393, 126.570738)
	    }
	];

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
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
	        image : markerImage // 마커 이미지 
	    });
	}
</script>
            </div>
          </div>
        <!-- 지도API예제연습-->

          <div class="row">
			<%for(RecruitmentVo rv : list) {%>
            	<div class="col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
	              <div class="card h-100">
	                <a href="#"><img class="card-img-top" src="http://placehold.it/300x300" alt=""></a>
	                <div class="card-body">
	                  <h4 class="card-title">
	                    <a href="#"><%=rv.getRecruitment_id() %></a><!-- 게시물 이름 -->
	                  </h4>
	                  <h5>시급 : <%=rv.getPay()%></h5>
	                  <p class="card-text"><%=rv.getInroduce() %></p>
	                </div>
	                <div class="card-footer">
	                  <small class="text-muted"><%=rv.getAddress() %></small>
	                </div>
	              </div>
	            </div>
			<%} %>
          </div>
          <!-- /.row -->
        <!--====================================	페이지선택버튼	 ==================================  -->
	        <div class="btn-toolbar mb-1" role="toolbar">
			  <div class="btn-group" role="group">
					<button type="button" class="btn btn-default bg-dark text-white"><</button>
					<button type="button" class="btn btn-default bg-dark text-white">1</button>
					<button type="button" class="btn btn-default bg-dark text-white">2</button>
					<button type="button" class="btn btn-default bg-dark text-white">3</button>
					<button type="button" class="btn btn-default bg-dark text-white">></button>
			  </div>
			</div>
		<!--=========================================================================================-->

        </div>
        <!-- /.col-lg-9 -->
      </div>
      <!-- /.row -->
    
      

    </div>
    <!-- /.container -->
<%@include file="/views/common/footer.jsp"%>