<%@ page import="webcart.Cart" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Exo 103 - Panier</title>
    </head>
    <body>
        <h1>Panier</h1>
        <%
            Cart myCart = (webcart.Cart)session.getAttribute("cart");
            if (null != myCart && !myCart.isEmpty()) {
                myCart.print(response.getWriter());
            }
        %>
        <form action="/exo103/cart" method="POST">
            <div>REF: <input type="text" name="ref"></div>
            <div>QTY: <input type="text" name="qty"></div>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>