<%
// check if authenticated !
Boolean authenticated = (Boolean) session.getAttribute("authenticated");
if (authenticated) {
%>
<h1>Welcome <%=session.getAttribute("login")%> !</h1>

[ <a href="auth?logout" style="color: red; text-decoration: none">DISCONNECT</a> ]
<% } else { %>
<div>Vous devez vous connecter en cliquant sur le lien ci-dessous!</div>
[ <a href="auth.jsp" style="color: red; text-decoration: none">CONNECT</a> ]

<% } %>
