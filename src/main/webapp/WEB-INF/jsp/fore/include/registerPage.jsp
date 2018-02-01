<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
$(function(){
	<c:if test="${!empty m}">
	$("span.errorMessage").html("${m}");
	$("div.registerErrorMessageDiv").css("visibility","visible");
	</c:if>
	
	$(".registerForm").submit(function(){
		if(0==$("#name").val().length){
			$("span.errorMessage").html("请输入用户名");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			return false;
		}
		if(0==$("#password").val().length){
			$("span.errorMessage").html("请输入密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			return false;
		}
		if(0==$("#repeatpassword").val().length){
			$("span.errorMessage").html("请再次输入密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			return false;
		}
		if($("#password").val()!=$("#repeatpassword").val()){
			$("span.errorMessage").html("两次密码输入不一致");
			$("div.registerErrorMessageDiv").css("visibility","visible");
			return false;
		}
		return true;
	});
});
</script>
<title>模仿天猫商城-注册页</title>
<form method="post" action="foreregister" class="registerForm">
	<div class="registerDiv">
		<div class="registerErrorMessageDiv">	
			<div class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
					<span class="errorMessage"></span>
			</div>
		</div>
		<table class="registerTable" align="center">
			<tr>
				<td class="registerTableLeftTD">会员名</td>
				<td class="registerTableRightTD"><input id="name" name="name" placeholder="会员名"></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密码</td>
				<td class="registerTableRightTD"><input id="password" name="password" type="password" placeholder="密码"></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">确认密码</td>
				<td class="registerTableRightTD"><input id="repeatpassword"  type="password" placeholder="再次输入密码"></td>
			</tr>
			<tr>
				<td colspan="2" class="registerButtonTD">
					<a href="registerSuccess.jsp"><button>提交</button></a>
				</td>
			</tr>
		</table>
	</div>
</form>

