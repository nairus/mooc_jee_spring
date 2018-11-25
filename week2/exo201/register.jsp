<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="user.User" %>
<%
// Récupération de la map des erreurs du formulaire (champ => message d'erreur).
Map<String, String> errors = (Map<String, String>)request.getAttribute("errors");
if (null == errors) {
    errors = new HashMap<>();
}

User newUser = (User)request.getAttribute("user");
String firstname = "";
String lastname = "";
String email = "";
if (null != newUser) {
    firstname = newUser.getFirstname();
    lastname = newUser.getLastname();
    email = newUser.getEmail();
}
%>
<!DOCTYPE html>
<html lang="en">
<% String ctxPath = request.getContextPath(); %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Register now !</title>

  <link href="<%= ctxPath %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/register.css" rel="stylesheet">
</head>

<body>

<div class="container">

      <form class="form-signin" action="/exo201/register" method="POST">

        <!-- check for error message and display this div -->
        <% if(!errors.isEmpty()){ %>
            <div class="alert alert-danger" role="alert">
                <ul>
                <% for (Map.Entry<String, String> error : errors.entrySet()) { %>
                    <li><%=error.getValue()%></li>
                <% } %>
                </ul>
            </div>
        <% } %>

        <h2 class="form-signin-heading">
        	<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
        	Register Now !
        </h2>

        <label for="firstname" class="sr-only">Firstname</label>
        <input name="firstname" id="firstname" value="<%=firstname%>"
            class="form-control <%if(errors.containsKey("firstname")){%>is-invalid<%}%>"
            placeholder="Firstname" required="" autofocus="" type="text">

        <label for="lastname" class="sr-only">Lastname</label>
        <input name="lastname" id="lastname" value="<%=lastname%>"
            class="form-control <%if(errors.containsKey("lastname")){%>is-invalid<%}%>"
            placeholder="Lastname" required="" type="text">

        <label for="email" class="sr-only">Email address</label>
        <input name="email" id="email" value="<%=email%>"
            class="form-control <%if(errors.containsKey("email")){%>is-invalid<%}%>"
            placeholder="Email address" required="" type="email">

        <label for="password" class="sr-only">Password</label>
        <input name="password" id="password"
            class="form-control <%if(errors.containsKey("password")){%>is-invalid<%}%>"
            placeholder="Password" required="" type="password">

        <label for="passwordConfirm" class="sr-only">Confirm Password</label>
        <input name="passwordConfirm" id="passwordConfirm"
            class="form-control <%if(errors.containsKey("passwordConfirm")){%>is-invalid<%}%>"
            placeholder="Confirm password" required="" type="password">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Register !</button>
      </form>

</div>


  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="<%= ctxPath %>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>