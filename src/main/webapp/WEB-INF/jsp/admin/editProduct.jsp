<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
$(function(){
	$("#editForm").submit(function(){
		if(!checkEmpty("name","产品名称"))
			return false;
//		if(!checkEmpty("subTitle","小标题"))
//			return false;
		if(!checkNumber("originalPrice","原价格"))
			return false;
		if(!checkNumber("promotePrice","优惠价格"))
			return false;
		if(!checkInt("stock","库存"))
			return false;
		return true;
	});
});
</script>
<title>编辑产品</title>
</head>
<body>
<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li><a href="admin_product_list?cid=${p.category.id }">${p.category.name }</a></li>
		<li class="active">${p.name }</li>
		<li class="active">编辑产品</li>
	</ol>
	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑产品</div>
		<div class="panel-body">
			<form action="admin_product_update" method="post" id="editForm">
				<table class="editTable">
					<tr>
						<td>产品名称</td>
						<td><input id="name" name="name" value="${p.name }" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>小标题</td>
						<td><input id="subTitle" name="subTitle" type="text" value="${p.subTitle }" class="form-control"></td>
					</tr>
					<tr>
						<td>原价格</td>
						<td><input id="originalPrice" name="originalPrice" type="text" value="${p.originalPrice }" class="form-control"></td>
					</tr>
					<tr>
						<td>优惠价格</td>
						<td><input id="promotePrice" name="promotePrice" type="text" value="${p.promotePrice }" class="form-control"></td>
					</tr>
					<tr>
						<td>库存</td>
						<td><input id="stock" name="stock" type="text" value="${p.stock }" class="form-control"></td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${p.id }">
							<input type="hidden" name="cid" value="${p.category.id }">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>