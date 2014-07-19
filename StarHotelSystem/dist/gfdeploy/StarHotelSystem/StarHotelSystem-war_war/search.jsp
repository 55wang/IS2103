<!DOCTYPE html>
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
<link href="/StarHotelSystem-war/bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet">

<link href="/StarHotelSystem-war/bootstrap/css/bootstrap-datepicker.css" rel="stylesheet">
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
  </head>

  <body style="background: #D4D6D4;">

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
            <li class="active"><a href="/StarHotelSystem-war/StarHotelSystem/search">Search</a></li>
            <li><a href="/StarHotelSystem-war/StarHotelSystem/shoppingCart">Shopping Cart</a></li>
            <li><a href="/StarHotelSystem-war/StarHotelSystem/viewBooking">View Booking</a></li>
          </ul>
            <ul class="nav navbar-nav navbar-right">
        <li><a href="/StarHotelSystem-war/StarHotelSystem/logout">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
      <div class="container col-sm-6 col-sm-offset-3 col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 text-center">
<form role="form" action="result" method="POST">
    <h1>Search Available Room</h1>
    <h3 class="lead">Enter check-in and check-out date </h3>
  <div class="form-group">
    <label>Check-In Date</label>
    <div class='input-group date' id='datetimepicker1' data-date-format="YYYY-MM-DD">
                    <input type='text' class="form-control" name="checkin" placeholder="Check-In Date" required />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
  </div>
  <div class="form-group">
    <label>Check-Out Date</label>
    <div class='input-group date' id='datetimepicker2' data-date-format="YYYY-MM-DD">
                    <input type='text' class="form-control" name="checkout" placeholder="Check-Out Date" required />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
  </div>

  <button type="submit" class="btn btn-default">Submit</button>
</form>
      </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="/StarHotelSystem-war/bootstrap/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="/StarHotelSystem-war/bootstrap/js/moment.js"></script>
    <script type="text/javascript" src="/StarHotelSystem-war/bootstrap/js/bootstrap-datetimepicker.js"></script>
    
    <script type="text/javascript" src="/StarHotelSystem-war/bootstrap/js/bootstrap-datepicker.js"></script>
    
            <script type="text/javascript">
            $( document ).ready(function() {
        $('#datetimepicker1').datetimepicker();        
        $('#datetimepicker2').datetimepicker();
            });
        </script>
  </body>
</html>
