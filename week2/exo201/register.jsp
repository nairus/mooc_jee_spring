<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
// Récupération de la map des erreurs du formulaire (champ => message d'erreur).
Map<String, String> errors = (Map<String, String>)request.getAttribute("errors");
if (null == errors) {
    errors = new HashMap<>();
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

        <label for="inputFirstName" class="sr-only">Firstname</label>
        <input name="inputFirstName" id="inputFirstName"
            class="form-control <%if(errors.containsKey("inputFirstName")){%>is-invalid<%}%>"
            placeholder="firstname" required="" autofocus="" type="text">

        <label for="inputLastName" class="sr-only">Lastname</label>
        <input name="inputLastName" id="inputLastName" class="form-control <%if(errors.containsKey("inputLastName")){%>is-invalid<%}%>" placeholder="lastname" required="" type="text">

        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="inputEmail" id="inputEmail" class="form-control <%if(errors.containsKey("inputEmail")){%>is-invalid<%}%>" placeholder="Email address" required="" type="email">

        <label for="inputPassword" class="sr-only">Password</label>
        <input name="inputPassword" id="inputPassword" class="form-control <%if(errors.containsKey("inputPassword")){%>is-invalid<%}%>" placeholder="Password" required="" type="password">

        <label for="inputPasswordConfirm" class="sr-only">Confirm Password</label>
        <input name="inputPasswordConfirm" id="inputPasswordConfirm" class="form-control <%if(errors.containsKey("inputPasswordConfirm")){%>is-invalid<%}%>" placeholder="confirm password" required="" type="password">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Register !</button>
      </form>

</div>


  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="<%= ctxPath %>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>