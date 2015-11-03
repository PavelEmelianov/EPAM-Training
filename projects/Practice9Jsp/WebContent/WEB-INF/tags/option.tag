<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="value" %>
<c:choose>
	<c:when test="${value == roleName}">
		<option selected value="${value}">${value}</option>
	</c:when>
	<c:otherwise>
		<option value="${value}">${value}</option>		
	</c:otherwise>
</c:choose>