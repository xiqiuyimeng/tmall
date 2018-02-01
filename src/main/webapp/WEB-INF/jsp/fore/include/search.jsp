<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
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
<a href="forehome"> <img id="logo" src="img/site/logo.gif"
	class="logo">
</a>
 <form action="foresearch" method="post">
	<div class="searchDiv">
		<input id="keyword" name="keyword" type="text" placeholder="时尚男鞋 太阳镜">
		<button type="submit" class="searchButton">搜索</button>
		
	</div>
</form>