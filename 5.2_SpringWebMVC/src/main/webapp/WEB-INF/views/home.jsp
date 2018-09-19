<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="./hr/count">Employee count</a><br>
<a href="./hr/count?deptid=50">Dept 50 employee count</a>
</body>
</html>
