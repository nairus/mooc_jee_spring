<%@ page import="java.util.*" %>
<%@ page import="person.Person" %>
<%
List<Person> persons = (List<Person>)request.getAttribute("persons");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">

<div class="panel panel-default">
    <div class="panel-heading">Liste des personnes</div>
    <div class="panel-body">
    <p>
        <!-- show count of persons -->
    </p>
    </div>
    <table class="table">
        <tr>
            <th>id</th>
            <th>pr&eacute;nom</th>
            <th>nom</th>
            <th>&acirc;ge</th>
        </tr>

        <!-- iteratée through persons ... -->
        <% for(Person p : persons) { %>
        <tr>
            <td><%=p.getId()%></td>
            <td><%=p.getFirstName()%></td>
            <td><%=p.getLastName()%></td>
            <td><%=p.getAge()%></td>
        </tr>
        <% } %>
    </table>

</div>



    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>