<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
		<script src="resources/js/angular.js"></script>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<nav class="navbar navbar-default">
  			<div class="container-fluid">
    			<div class="navbar-header">
      				<a class="navbar-brand" href="#">A B C D E F</a>
    			</div>
	    		<ul class="nav navbar-nav navbar-right">
				      <li><a href="#!register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				      <li ng-show="!userFlag"><a href="#!login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
				      <li ng-show="userFlag"><a href="#!logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
   				 </ul>
  			</div>
		</nav>
		<div ng-view></div>
	</body>
</html>