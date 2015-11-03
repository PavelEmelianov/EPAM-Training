<%@ include file="/WEB-INF/include/head.jspf"%>

<html>
<body>
	<div align="center">
		<form action="changeLocale.jsp" method="post">
			<fmt:message key="settings_jsp.label.set_locale" />
			<select name="locale">
				<c:forEach items="${applicationScope.locales}" var="locale">
					<c:set var="selected"
						value="${locale.key == currentLocale ? 'selected' : '' }" />
					<option value="${locale.key}" ${selected}>${locale.value}</option>
				</c:forEach>
			</select> <input type="submit"
				value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
		</form>

		<form action="controller" method="post">
			<input type="hidden" name="command" value="homepage"> <input
				type="submit" value="<fmt:message key="users_jsp.homepage"/>">
		</form>
	</div>
</body>
</html>