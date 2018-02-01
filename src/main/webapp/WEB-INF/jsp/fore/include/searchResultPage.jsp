<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>模仿天猫商城-搜索结果</title>
<div id="searchResult">
	<div class="searchResultDiv">
		<div class="searchProducts">
			<c:forEach items="${ps }" var="p">
				<div class="productUnit" price="${promotePrice }">
					<a href="foreproduct?pid=${p.id }">
						<img class="productImage" src="img/productSingle/${p.firstProductImage.id }.jpg">
					</a>
					<span class="productPrice">￥<fmt:formatNumber type="number" value="${p.promotePrice }" minFractionDigits="2"/></span>
					<a class="productLink" href="foreproduct?pid=${p.id }">
					${fn:substring(p.name,0,50) }
					</a>
					<a class="tmallLink" href="foreproduct?pid=${p.id }">天猫专卖</a>
					<div class="productInfo">
						<span class="monthDeal">月成交<span class="productDealNumber">${p.saleCount }笔</span></span>.
						<span class="productReview">评价<span class="productReviewNumber">${p.reviewCount }</span></span>
						<span class="wangwang"><img src="img/site/wangwang.png"></span>
					</div>
				</div>
			</c:forEach>
			<c:if test="${empty ps }">
				<div class="noMatch">没有满足条件的产品</div>
			</c:if>
		</div>
	</div>
</div>