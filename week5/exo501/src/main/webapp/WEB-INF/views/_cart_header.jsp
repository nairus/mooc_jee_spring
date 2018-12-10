<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</div>
Panier
<c:choose>
    <c:when test="${cart.articles.size() > 0}">
       <ul>
        <c:forEach var="article" items="${cart.articles}">
            <li>${article.name} <fmt:formatNumber type="currency" currencySymbol="€">${article.price / 100}</fmt:formatNumber></li>
        </c:forEach>
      </ul>
    </c:when>
    <c:otherwise><strong>aucun article</strong></c:otherwise>
</c:choose>
<a class="btn btn-primary" href="cart/1/validate.html">Commander</a>
<div>