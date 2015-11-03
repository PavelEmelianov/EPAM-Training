<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<body>
	<div style="text-align: center;">
		The following error has occured:
		<h3>${requestScope.errorMessage}</h3>
		<a href="javascript:history.back()">Back</a>
	</div>
</body>
</html>