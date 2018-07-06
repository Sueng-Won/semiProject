<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@include file="/views/common/header.jsp" %>
	<%
    	Boolean flag_pw = Boolean.parseBoolean(request.getParameter("flag"));
    %>
	<script type="text/javascript">
		function confirm(){
			var okFlag = false;
			if($("#pw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#pw").focus();
				okFlag = false;
				return false;
			}else{
				okFlag = true;
			}
			if(okFlag){
				$("#pwCheckFrm").submit();
			}
		}
		function cancle(){
			location.href = "/sp/index.jsp";
		}
    	$(function(){
    		<%if(flag_pw){%>
    			alert("비밀번호가 일치하지 않습니다.");
    		<%}%>
    	});
    </script>
    <!-- Page Content -->
    <div class="container" style="min-height: 800px;">
    
      <div class="row">
		<%@include file="/views/common/nav.jsp" %>
        <!-- /.col-lg-3 -->
        <div class="mx-auto">
        	<div class="row mt-4">
        	  <div class="col-lg-3"></div>
			  <div class="col-lg-auto bg-dark">
			  <form id="pwCheckFrm" action="/sp/checkPw.do" method="POST">
			<table>
				<tr>
					<td colspan="2">
					<br>
						<input type="password" class="form-control mb-1" name="pw" id="pw" placeholder="비밀번호입력" /> 
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" size="15" value="회원정보수정" class="btn btn-dark btn-sm btn-inline" onclick="confirm();" />
					</td>
					<td>
						<input type="button" size="15" value="취소" class="btn btn-dark btn-sm btn-inline" onclick="cancle();" />
					</td>
				</tr>
				
					
				
			</table>
		</form>
			  </div><!-- /.col-lg-6 -->
			  <div class="col-lg-3"></div>
        	</div>
		</div><!-- /.row -->
        
        
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
<%@include file="/views/common/footer.jsp"%>