<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/product" />
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
<div class="content">
    <h3><fmt:message key="PRODUCT_LIST"/></h3>

</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>