<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<style type="text/css">
form {
	width: auto;
	margin: 0 auto;
}
</style>
</head>
<body><%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div align="center">
		<c:if test="${sessionScope.user.roleId eq 1}">
			<h3>${sessionScope.user.fName} ${sessionScope.user.lName} <fmt:message key="u_results"/></h3>
		</c:if>
		<c:if test="${sessionScope.user.roleId eq 0}">
			<h3><fmt:message key="test_results"/></h3>
		</c:if>
		<br>
		<table border="1" style="text-align: center;">
			<tr>

				<td><fmt:message key="result_jsp.user_name"/></td>
				<td><fmt:message key="result_jsp.theme_name"/></td>
				<td><fmt:message key="result_jsp.test_name"/></td>
				<td><fmt:message key="result_jsp.test_difficulty"/></td>
				<td><fmt:message key="result_jsp.date_time"/></td>
				<td><fmt:message key="result_jsp.result"/></td>
			</tr>
			<c:forEach var="bean" items="${sessionScope.testsResult}">
				<tr>
					<td>${bean.fName} ${bean.lName}</td>
					<td>${bean.themeName}</td>
					<td>${bean.testName}</td>
					<td>${bean.testDifficulty}</td>
					<td>${bean.date}</td>
					<td>${bean.result}%</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<c:if test="${sessionScope.user.roleId eq 1}">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="settings"> <input
					type="submit" value="<fmt:message key="users_jsp.homepage"/>">
			</form>
		</c:if>
		<c:if test="${sessionScope.user.roleId eq 0}">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="homepage"> <input
					type="submit" value="<fmt:message key="users_jsp.homepage"/>">
			</form>
		</c:if>

		
	</div>

</body>
</html>