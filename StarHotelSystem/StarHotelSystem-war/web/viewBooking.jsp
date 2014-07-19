
<!DOCTYPE html>
<%@page import="java.util.ArrayList" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Star Hotel System</title>

    <!-- Bootstrap core CSS -->
    <link href="/StarHotelSystem-war/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/StarHotelSystem-war/bootstrap/css/starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/StarHotelSystem-war/bootstrap/js/ie-emulation-modes-warning.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/StarHotelSystem-war/bootstrap/js/ie10-viewport-bug-workaround.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
        <%
            ArrayList data = (ArrayList) request.getAttribute("data");
            ArrayList checkin = (ArrayList) request.getAttribute("checkin");
            ArrayList checkout = (ArrayList) request.getAttribute("checkout");
            ArrayList payment = (ArrayList) request.getAttribute("payment");
            ArrayList status = (ArrayList) request.getAttribute("status");
            System.out.println(data.size());
        %>
        
  </head>

  <body style="background: #D4D6D4;">
      <div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <li active><a class="navbar-brand" href="/StarHotelSystem-war/StarHotelSystem/index">Star Hotel System</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/StarHotelSystem-war/StarHotelSystem/search">Search</a></li>
            <li><a href="/StarHotelSystem-war/StarHotelSystem/shoppingCart">Shopping Cart</a></li>
            <li class="active"><a href="/StarHotelSystem-war/StarHotelSystem/viewBooking">View Booking</a></li>
          </ul>
            
            <ul class="nav navbar-nav navbar-right">
        <li><a href="/StarHotelSystem-war/StarHotelSystem/logout">Logout</a></li>
      </ul>
            
        </div><!--/.nav-collapse -->
      </div>
    </div>
      </div>
      
      <div class="container text-center">
          <h1>View All Bookings</h1>
         <div class="row">
                  <form action="shopping" method="post">
        
        <table class="table table-striped">

            <tr><td>Booking Reference Id</td><td>Check-In Date</td><td>Check-Out Date</td>
                <td>Price</td><td>Status</td></tr>

            <% for (int i = 0; i < data.size(); i++) {%>
            <tr>
                <td>
                    <p> <%=  data.get(i)%></p>
                </td>
                <td>
                    <p> <%=  checkin.get(i)%></p>
                </td>
                <td>
                    <p> <%=  checkout.get(i)%></p>
                </td>
                <td>
                    <p> <%=  status.get(i)%></p>
                </td>
                <td>
                    <p> <%=  payment.get(i)%></p>
                </td>
            </tr>
            <% }%>

        </table>

      </div>
      </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="/StarHotelSystem-war/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
