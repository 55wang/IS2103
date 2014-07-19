<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="refresh" content="10; url=/StarHotelSystem-war/StarHotelSystem/login" />
    <title>Star Hotel System</title>

    <!-- Bootstrap core CSS -->
    <link href="/StarHotelSystem-war/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/StarHotelSystem-war/bootstrap/css/starter-template.css" rel="stylesheet">

    </head>
    
    <body>
        <%
            ArrayList data = (ArrayList)request.getAttribute("data");
        %>
        <div class="container">
             <div class="jumbotron text-center">
        <p class="lead">Status: <%= (String)data.get(3)%></p>
        <p>Redirecting you to the login page in 10 seconds</p>
        <p>Click to go <a href="/StarHotelSystem-war/StarHotelSystem/login">login page</a> now!</p>
        </div>
    </div>
        
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="/StarHotelSystem-war/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>