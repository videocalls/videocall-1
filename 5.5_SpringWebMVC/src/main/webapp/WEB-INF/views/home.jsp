<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>CoderBy</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#getEmp").click(function() {
		$("#result").html("");
		$.ajax({
			type: 'GET',
			url: './hr/json/'+$("#empid").val(),
			success: function(data) {
				var serverData = JSON.stringify(data);
				$("#result").html(serverData);
			},
			error : function(error) {
				console.log(error);
			}
		})
	})
	$("#getEmp2").click(function() {
		var formData = $('#form').serializeArray();
		console.log(JSON.stringify(formData));
		$("#result").html("");
		$.ajax({
			type: 'POST',
			url: './hr/ajax',
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			data: JSON.stringify(formData),
			success: function(data) {
				var serverData = JSON.stringify(data);
				$("#result").html(serverData);
			},
			error : function(error) {
				console.log(error);
			}
		})
	})
});
</script>
</head>
<body>
<h1>
	HR Sample  
</h1>
<a href="<c:url value='/hr'/>">사원 목록(동기)</a>
<form id="form">
<input type="text" id="empid" name="employeeId" value="100">
<input type="button" id="getEmp" value="사원정보(비동기 GET 방식)">
<input type="button" id="getEmp2" value="사원정보(비동기 POST 방식)">
</form>
<div id="result"></div>
</body>
</html>
