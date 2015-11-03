<%@ include file="/WEB-INF/include/head.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session" />

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resources" />

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${param.locale}" scope="session" />
<div align="center">

	<%-- goto back to the settings--%>
	<h3><fmt:message key="change_language"/></h3>
	<br>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="homepage"> <input
			type="submit" value="<fmt:message key="to_homepage"/>">
	</form>
	
	
</div>

