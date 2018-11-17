<%@ page import="webcart.Cart" %>
<%
    Cart myCart = (webcart.Cart)request.getAttribute("cart");
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Exo 103 - Panier</title>
    </head>
    <body>
        <h1>Panier</h1>
        <%=myCart.getHtml()%>
        <form action="/exo103/cart" method="POST">
            <div>REF: <input type="text" name="ref"></div>
            <div>QTY: <input type="text" name="qty"></div>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>