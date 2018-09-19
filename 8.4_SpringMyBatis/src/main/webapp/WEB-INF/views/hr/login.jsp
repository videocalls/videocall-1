<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

</head>
<body>
	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="login-form">
				<div class="row">
					<div class="input-field col s12 center">
						<!-- <img src="images/login-logo.png" alt="" class="circle responsive-img valign profile-image-login"/> -->
						<p class="center login-form-text">LOGIN</p>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<!-- <i class="mdi-social-person-outline prefix"></i> -->
						<i class="material-icons prefix">account_circle</i> <input
							id="username" name="username" type="text"
							style="background-image: url(&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGP6zwAAAgcBApocMXEAAAAASUVORK5CYII=&quot;); cursor: auto;" />
						<label for="username" data-error="wrong" class="center-align"
							data-success="right">Username</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<!-- <i class="mdi-action-lock-outline prefix"></i> -->
						<i class="material-icons prefix">vpn_key</i> <input id="password"
							name="password" type="password"
							style="background-image: url(&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGP6zwAAAgcBApocMXEAAAAASUVORK5CYII=&quot;);" />
						<label for="password">Password</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12 login-text">
						<input type="checkbox" id="test6" checked="checked" /> <label
							for="test6" class="pointer-events">Remember me</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<button type="submit" class="btn waves-effect waves-light col s12">Login</button>
					</div>
					<div class="input-field col s12">
						<a href="index.html"
							class="btn waves-effect waves-light col s12 light-blue darken-4">FACEBOOK
							Login</a>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s6 m6 l6">
						<p class="margin medium-small">
							<a href="register">Register Now!</a>
						</p>
					</div>
					<div class="input-field col s6 m6 l6">
						<p class="margin right-align medium-small">
							<a href="forgot-password">Forgot password ?</a>
						</p>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>