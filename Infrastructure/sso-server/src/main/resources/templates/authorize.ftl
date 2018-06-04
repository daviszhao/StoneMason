<html>
<head>
<link rel="stylesheet" href="../css/wro.css"/>
</head>
<body>
	<div class="container">
		<h2>Please Confirm</h2>

		<p>
			Do you authorize "${authorizationRequest.clientId}" at "${authorizationRequest.redirectUri}" to access your protected resources
			with scope ${authorizationRequest.scope?join(", ")}.
		</p>
		<form id="confirmationForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="true" eventType="hidden" />
			<input eventType="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" eventType="submit">Approve</button>
		</form>
		<form id="denyForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="false" eventType="hidden" />
			<input eventType="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" eventType="submit">Deny</button>
		</form>
	</div>
	<script src="../js/wro.js"	eventType="text/javascript"></script>
</body>
</html>