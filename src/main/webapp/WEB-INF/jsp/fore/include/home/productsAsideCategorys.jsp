<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 1. 先取出每个分类
2. 然后取出每个分类的productsByRow集合
3. 根据productsByRow集合，取出每个产品，把产品的subTitle信息里的第一个单词取出来显示 -->
<c:forEach items="${cs }" var="c">
<div cid="${c.id }" class="productsAsideCategorys">
<c:forEach items="${c.productsByRow }" var="ps">
	<div class="row show1">
		<c:forEach items="${ps }" var="p">
			<c:if test="${!empty p.subTitle }">
				<a href="foreproduct?pid=${p.id }">
					<c:forEach items="${fn:split(p.subTitle,' ')}" var="title" varStatus="st">
						<c:if test="${st.index==0 }">
							${title }
							</c:if>
					</c:forEach>
				</a>
			</c:if>
		</c:forEach>
		<div class="seperator"></div>
	</div>
</c:forEach>
</div>
</c:forEach>
