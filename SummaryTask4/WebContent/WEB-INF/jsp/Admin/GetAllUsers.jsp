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
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<div align="center">
	<h3><fmt:message key="users_jsp.users_list"/></h3>
		<table border="1" style="text-align: center;">
			<tr>
				<td><fmt:message key="users_jsp.id"/></td>
				<td><fmt:message key="users_jsp.login"/></td>
				<td><fmt:message key="users_jsp.name"/></td>
				<td><fmt:message key="users_jsp.role"/></td>
				<td><fmt:message key="users_jsp.blocking"/></td>
			</tr>
			<c:forEach var="bean" items="${sessionScope.usersList}">
				<tr>
					<td>${bean.id}</td>
					<td>${bean.login}</td>
					<td>${bean.fName} ${bean.lName}</td>
					<c:if test="${bean.roleId eq 0}">
						<td><fmt:message key="users_jsp.admin"/></td>
					</c:if>
					<c:if test="${bean.roleId eq 1}">
						<td><fmt:message key="users_jsp.student"/></td>
					</c:if>
					<td><c:if test="${bean.roleId eq 1}">
							<form action="controller" method="post">
								<input type="hidden" name="command" value="block">
								<c:if test="${bean.blocked eq true}">
									<input type="hidden" name="login" value="${bean.login}">
									<input type="submit" value="<fmt:message key="users_jsp.unblock"/>">
								</c:if>
								<c:if test="${bean.blocked eq false}">
									<input type="hidden" name="login" value="${bean.login}">
									<input type="submit" value="<fmt:message key="users_jsp.block"/>">
								</c:if>

							</form>
						</c:if>
				</tr>

			</c:forEach>
		</table>
	<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="homepage"><br>  <input
						type="submit" value="<fmt:message key="users_jsp.homepage"/>">
				</div>
			</form>
	</div>
	
</body>
</html>