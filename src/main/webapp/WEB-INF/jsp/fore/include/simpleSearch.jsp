<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(function(){
	$("button.searchButton").click(function(){
		var keyword=$("#keyword").val();
		if(0==keyword.length){
			alert("请输入要搜索的商品");
			return false;
		}
		return true;
	});	
});
</script>
<div>
	<a href=forehome>
		<img id="simpleLogo" class="simpleLogo" src="img/site/simpleLogo.png">
	</a>
	<form action="foresearch" method="post">
		<div class="simpleSearchDiv pull-right">
			<input id="keyword" type="text" placeholder="平衡车 原汁机" name="keyword">
			<button class="searchButton" type="submit">搜天猫</button>
			
		</div>
	</form>
	<div style="clear:both"></div>
</div>
