<%@page language ="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>회원가입</title>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<div id="login-page" class="row">
  <div class="col s12 z-depth-4 card-panel">
    <form class="login-form">
      <div class="row">
        <div class="input-field col s12 center">
          <h4>Register</h4>
          <p class="center">Join to our community now !</p>
        </div>
      </div>

      <div class="row margin">
        <div class="input-field col s12">
          <!-- <i class="mdi-social-person-outline prefix"></i> -->
          <i class="material-icons prefix">account_circle</i>
          <input id="username" name="username" type="text"/>
          <label for="username">Username</label>
        </div>
      </div>

      <div class="row margin">
        <div class="input-field col s12">
          <!-- <i class="mdi-social-person-outline prefix"></i> -->
          <i class="material-icons prefix">email</i>
          <input id="email" name="email" type="text" style="cursor: auto;" />
          <label for="email">Email</label>
        </div>
      </div>

      <div class="row margin">
        <div class="input-field col s12">
          <!-- <i class="mdi-action-lock-outline prefix"></i> -->
          <i class="material-icons prefix">vpn_key</i>
          <input id="password" name="password" type="password" />
          <label for="password">Password</label>
        </div>
      </div>

      <div class="row margin">
        <div class="input-field col s12">
          <!-- <i class="mdi-action-lock-outline prefix"></i> -->
          <i class="material-icons prefix">vpn_key</i>
          <input id="password_a" name="cpassword" type="password" />
          <label for="password_a">Password again</label>
        </div>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <button type="submit" class="btn waves-effect waves-light col s12">REGISTER NOW</button>
          
        </div>
        <div class="input-field col s12">
          <p class="margin center medium-small sign-up">Already have an account? <a href="./login">Login</a></p>
        </div>
      </div>


    </form>
  </div>
</div>
</body>
</html>