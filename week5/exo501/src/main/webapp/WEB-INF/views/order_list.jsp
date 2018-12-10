<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="_header.jsp" %>

<h1>Liste des commandes</h1>

<c:choose>
    <c:when test="${orders.size() > 0}">
        <ul>
        <c:forEach var="order" items="${orders}">
            <li>
                <fmt:formatDate value="${order.createdOn}" timeStyle="short" type="both" /> - 
                ${order.currentStatus} - 
                <fmt:formatNumber type="currency" currencySymbol="€">${order.amount / 100}</fmt:formatNumber>
            </li>
        </c:forEach>
        </ul>
    </c:when>
    <c:otherwise><strong>aucune commande</strong></c:otherwise>
</c:choose>

<%@include file="_footer.jsp" %>