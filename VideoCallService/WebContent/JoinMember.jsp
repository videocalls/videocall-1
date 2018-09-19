<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form>
			<p>
				<input type="text" name="email" id="email"> <input
					type="button" value="email 유효성 검사" onclick="validateEmail();">
			</p>
			<p id="msgbox"></p>
		</form>
	</div>
	<script type="text/javascript">
		function validateEmail() {
			var email = document.getElementById("email").value;
			var pattern = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			var msgbox = document.getElementById("msgbox");
			if (pattern.test(email)) {
				msgbox.innerHTML = "메일 주소가 올바르게 입력되었습니다.";
			} else {
				msgbox.innerHTML = "메일 주소가 유효하지 않습니다.";
			}
		}
	</script>

</body>
</html>