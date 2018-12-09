<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="_header.jsp" %>
<%-- import required classes --%>

<c:if test="${categories.size() > 0 }">
	<ul class="categories">
	<%-- iterate through categories --%>
	<c:forEach var="category" items="${categories}">
		<li>
			<%-- set a link to each category content --%>
			<a href="category/${category.id}.html">
				<%-- 
					add an image related to category ID.
					Category images are located in /img/ and name catID.jpg (ID as 2 digits)
				 --%>
				<c:set var="formattedId">
				    <fmt:formatNumber type="number" minIntegerDigits="2" value="${category.id}" />
				</c:set>
				<img src="<%= ctxPath %>/img/cat${formattedId}.jpg"/><br/>
				<%-- Show category name --%>
				${category.name}
			</a>
	   </li>
	</c:forEach>
	</ul>
</c:if>
<%@include file="_footer.jsp" %>