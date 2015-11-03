<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p style="color: red">
<c:forEach items="${errors}" var="error">
	${error}<br/>
</c:forEach>
</p>