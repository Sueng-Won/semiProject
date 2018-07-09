<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	boolean searchFlag = Boolean.parseBoolean(request.getParameter("searchFlag"));
    	boolean activeFlag = Boolean.parseBoolean(request.getParameter("activeFlag"));
    %>
<%@include file="/views/common/header.jsp" %>
<script type="text/javascript">
		function updatePw(){
			var okFlag = false;
			if($("#ipw").val()==""||$("#ipw2").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#ipw").focus();
				okFlag = false;
				return false;
			}else{
				okFlag = true;
			}
			if($("#ipw").val() != $("#ipw2").val()){
				alert("새 비밀번호와 확인 비밀번호가 다릅니다.");
				okFlag = false;
				return false;
			}else{
				okFlag = true;
			}
			
			var pw = $("#ipw").val();
			var numPw = pw.search(/[0-9]/g);
			var engPw = pw.search(/[a-z]/ig);
			var spePw = pw.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);

			if(pw.length < 6 || pw.length > 20){
				alert("비밀번호는 6자리 ~ 20자리 이내로 입력해주세요.");
				okFlag = false;
				return false;
			}else{
				okFlag = true;
			}

			if(pw.search(/₩s/) != -1){
				alert("비밀번호는 공백없이 입력해주세요.");
				okFlag = false;
				return false;
			}else{
				okFlag = true;
			}
			
			if(okFlag){
				$("#updatePwFrm").submit();
			}
		}
</script>
	<div class="container" style="min-height: 800px;">
    	<div class="row">
			<%@include file="/views/common/nav.jsp" %>
				<!-- /.col-lg-3 -->
        		<div class="col-lg-9 mt-lg-auto">
        			<div class="row mt-4">
        	  			<div class="col-lg-3"></div>
			  			<div class="col-lg-6 bg-dark">
			  				<br><br>
			  				<%if(!activeFlag){ %>
								<form action="/sp/updatePw.do" method="post" id="updatePwFrm">
									<div class="input-group">
				      					<input type="password" class="form-control mb-1" name="pw1" id="ipw" placeholder="새로운 비밀번호(6~20자리 영문,숫자,기호)"/>
				    				</div>
				    
				    				<div class="input-group">
								    	<input type="password" class="form-control mb-1" name="pw2" id="ipw2" placeholder="새로운 비밀번호확인"/>
								    </div>
								    
								    <div class="input-group">
								    	<input type="button" class="btn btn-dark btn-sm btn-block" value="비밀번호 변경" onclick="updatePw();"/>
								    </div>
								</form>
							<%}else{ 
								if(searchFlag){%>
									<h3 align="center" class="text-white">비밀번호 변경이 완료되었습니다.</h3>
								<%}else{ %>
									<h3 align="center" class="text-white">비밀번호 변경 에러</h3>
									<button onclick="/views/member/updateMember.jsp" class="btn btn-dark btn-sm btn-inline">이전으로 돌아가기</button>
							<%}} %>
						</div>
					</div>
				</div>
		<!-- /.row -->
     	</div>
    <!-- /.row -->
    </div>
<%@include file="/views/common/footer.jsp"%>