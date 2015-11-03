<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<title>Student page</title>
<style type="text/css">
form {
	width: auto;
	margin: 0 auto;
}
</style>


</head>
<body>
	<table style="width: 100%">
		<tr>
			<td>
				<div align="center">
					<div style="float: right;">
						${sessionScope.user.fName} ${sessionScope.user.lName} (
						<fmt:message key="users_jsp.student" />
						) <a href="controller?command=logout"><fmt:message
								key="admin_jsp.logout" /></a>
					</div>
					<div style="float: left;">
						<fmt:message key="student_homepage" />
						<form action="settings.jsp" method="post">
							<input type="submit"
								value="<fmt:message key="index_jsp.link.settings"/>">
						</form>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div align="center">
		<br>
		<br>
		<br>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="settings"> <input
				type="submit" value="<fmt:message key="personal_information"/>">
		</form>

		<br>
		<h3>
			<fmt:message key="theme_to_pass" />
		</h3>
		<br>


		<table border="1" style="text-align: center;">
			<tr>
				<td><fmt:message key="result_jsp.theme_name" /></td>
				<td><fmt:message key="show" /></td>
			</tr>
			<c:forEach var="bean" items="${sessionScope.themesList}">
				<c:if test="${bean.blocked eq false }">
					<tr>
						<td>${bean.name}</td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="theme" value="${bean.id}"> <input
									type="hidden" name="themeName" value="${bean.name}"> <input
									type="hidden" name="command" value="getTests"> <input
									type="submit" value="<fmt:message key="show_tests"/>">
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>