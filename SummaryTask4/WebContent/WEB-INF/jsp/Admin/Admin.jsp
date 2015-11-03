<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<html>
<head>
<title>Admin page</title>

<style type="text/css">
form {
	width: auto;
	margin: 0 auto;
}
</style>

</head>
<body><table style="width: 100%"><tr><td>

	<div align="center">
		<div style="float: right;">
			${sessionScope.user.fName} ${sessionScope.user.lName} (<fmt:message key="admin_jsp.admin"/>) <a
				href="controller?command=logout"><fmt:message key="admin_jsp.logout"/></a>
		</div>
		<div style="float: left;"><fmt:message key="admin_jsp.homepage"/> <form action = "settings.jsp" method="post">
	<input type="submit" value="<fmt:message key="index_jsp.link.settings"/>">
	</form></div>
	</div>
	</td></tr></table>
	<div align="center">
		
		<h3>
			<fmt:message key="admin_jsp.users"/>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="getUsers"> <input
					type="submit" value="<fmt:message key="admin_jsp.show_users"/>">
			</form>
			<br>
			<h3>
					<fmt:message key="admin_jsp.results"/>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="result"> <input
							type="submit" value="<fmt:message key="admin_jsp.show_results"/>">

					</form>
					<br>

				<form action="controller" method="post">
					<input type="hidden" name="command" value="refactorPage"> <input
						type="hidden" name="name" value="addTheme"> <input
						type="submit" value="<fmt:message key="admin_jsp.add_theme"/>">
				</form>
				<br>

					<h3>
					
						<fmt:message key="admin_jsp.pass_edit"/></h3><br>

						<table border="1" style="text-align: center;">
							<tr>
							
								<td><fmt:message key="admin_jsp.theme_name"/></td>
								<td><fmt:message key="admin_jsp.theme_status"/></td>
								<td><fmt:message key="admin_jsp.theme_edit"/></td>
								<td><fmt:message key="admin_jsp.theme_delete"/></td>
							</tr>
							<c:forEach var="bean" items="${sessionScope.themesList}">
								<tr>
								
									<td>${bean.name}</td>
									<td><form action="controller" method="post">
											<input type="hidden" name="command" value="blockTheme">
											<c:if test="${bean.blocked eq true}">
												<input type="hidden" name="themeId" value="${bean.id}">
												<input type="submit" value="<fmt:message key="admin_jsp.unblock"/>">
											</c:if>
											<c:if test="${bean.blocked eq false}">
												<input type="hidden" name="themeId" value="${bean.id}">
												<input type="submit" value="<fmt:message key="admin_jsp.block"/>">
											</c:if>
										</form></td>
									<td>
										<form action="controller" method="post">
											<input type="hidden" name="theme" value="${bean.id}">
											<input type="hidden" name="themeName" value="${bean.name}">
											<input type="hidden" name="command" value="getTests">
											<input type="submit" value="<fmt:message key="admin_jsp.pass_edit_tests"/>">
										</form>
										<td><form action = "controller" method ="post">
											<input type="hidden" name="command" value="refactorPage">
											<input type="hidden" name="theme" value="${bean.id}">
											 <input type="hidden" name="name" value="deleteTheme"> 
											<input type="submit" value="<fmt:message key="admin_jsp.delete_theme"/>">						
										</form>
								</tr>
							</c:forEach>
						</table>
	</div>
</body>
</html>

