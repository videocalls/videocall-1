<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true" isErrorPage="true"%>
<%
response.setStatus(200);
%>
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/common" />
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
<div class="content">
	<div class="jumbotron">
		<h2 style="color:red;"><fmt:message key="${message}"/></h2>
		<p>
		<%
		if(exception != null)
			out.println(exception.getMessage());
		%>
		</p>
		<div class="pc"><br><br><br><br><br><br><br><br><br></div>
		
		<p><a class="btn btn-primary btn-lg" href='<c:url value="/"/>' role="button"><fmt:message key="HOME"/></a></p>
	</div>
</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>