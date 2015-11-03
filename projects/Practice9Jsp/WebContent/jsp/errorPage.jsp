<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="Error">
	<h3 style='color: red'>Error</h3>	
	${requestScope['javax.servlet.error.message']}	
	<c:forEach items="${requestScope.errors}" var="message">
		${message}<br/>
	</c:forEach>	
	<u:goBack/>
</u:wrapper>