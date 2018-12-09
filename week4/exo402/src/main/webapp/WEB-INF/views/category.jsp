<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="_header.jsp" %>
<%-- import required classes --%>

<ul class="articles">
<%-- Iterate through articles ... --%>
<c:forEach var="article" items="${articles}">
<li>
	<a href="#">
		<span class="price">
		  <c:set var="formattedPrice">
		  <fmt:formatNumber type="number" minIntegerDigits="2" value="${article.price}" />
		  </c:set>
			<%-- show price as X,XX --%>${formattedPrice} &euro;</span>
		<%-- 
			show product image, you can use 'https://static1.chronodrive.com'
			as base URL and img path to complete the image URL 
		--%>
		<img src="https://static1.chronodrive.com${article.img}"/><br/>
		<%-- show product name --%>${article.name} <br/>
	</a>
</li>
</c:forEach>

</ul>
<%@include file="_footer.jsp" %>