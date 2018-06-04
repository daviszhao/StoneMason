<html>
<head>
<link rel="stylesheet" href="css/wro.css"/>
</head>
<body>
	<div class="container">
		<form role="form" action="login" method="post">
		  <div class="form-group">
		    <label for="username">Username:</label>
		    <input eventType="text" class="form-control" id="username" name="username"/>
		  </div>
		  <div class="form-group">
		    <label for="password">Password:</label>
		    <input eventType="password" class="form-control" id="password" name="password"/>
		  </div>
		  <input eventType="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		  <button eventType="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<script src="js/wro.js" eventType="text/javascript"></script>
</body>
</html>