<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
<style type="text/css">
.error {
	color : red
}
</style>
</head>
<body>
<h1>사원정보 입력</h1>
<c:url value="/hr/insert" var="actionURL" scope="page" />
<form:form action="${actionURL}" modelAttribute="emp">
<table border="1">
<tr>
	<th>EMPLOYEE_ID</th>
	<td><form:input path="employeeId" />
		<form:errors path="employeeId" class="error" /></td>
</tr>
<tr>
	<th>FIRST_NAME</th>
	<td><form:input path="firstName" />
		<form:errors path="firstName" class="error" /></td>
</tr>
<tr>
	<th>LAST_NAME</th>
	<td><form:input path="lastName" required="required"/>
		<form:errors path="lastName" class="error" /></td>
</tr>
<tr>
	<th>EMAIL</th>
	<td><form:input path="email" />
		<form:errors path="email" class="error" /></td>
</tr>
<tr>
	<th>PHONE_NUMBER</th>
	<td><form:input path="phoneNumber" />
		<form:errors path="phoneNumber" class="error" /></td>
</tr>
<tr>
	<th>HIRE_DATE</th>
	<td><form:input path="hireDate" type="date" required="required" />
		<form:errors path="hireDate" class="error" /></td>
</tr>
<tr>
	<th>JOB_ID</th>
	<td>
		<select name="jobId">
		<c:forEach var="job" items="${jobList}">
			<option value="${job.jobId}">${job.title}</option>
		</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<th>SALARY</th>
	<td><form:input path="salary" type="number" />
		<form:errors path="salary" class="error" /></td>
</tr>
<tr>
	<th>COMMISSION_PCT</th>
	<td><form:input path="commissionPct" type="number" />
		<form:errors path="commissionPct" class="error" /></td>
</tr>
<tr>
	<th>MANAGER_ID</th>
	<td>
		<select name="managerId">
		<c:forEach var="manager" items="${managerList}">
			<option value="${manager.managerId}">${manager.firstName}</option>
		</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<th>DEPARTMENT_ID</th>
	<td>
		<form:select path="departmentId">
		<c:forEach var="department" items="${deptList}">
			<option value="${department.departmentId}">
				${department.departmentName}
			</option>
		</c:forEach>
		</form:select>
	</td>
</tr>
<tr>
	<th>&nbsp;</th>
	<td>
		<input type="submit" value="저장"> 
		<input type="reset" value="취소">
	</td>
</tr>
</table>
</form:form>
</body>
</html>