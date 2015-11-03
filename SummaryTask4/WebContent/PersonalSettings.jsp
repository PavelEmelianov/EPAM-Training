<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<body><%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div align="center" >
		<h3><fmt:message key="pers_inf_page"/></h3>
		<table border="1" style="text-align: center;">
			<tr>
				<td><fmt:message key="users_jsp.login"/>:</td>
				<td>${sessionScope.user.login}</td>
			</tr>
			<tr>
				<td><fmt:message key="f_name"/>:</td>
				<td>${sessionScope.user.fName}</td>
			</tr>
			<tr>
				<td><fmt:message key="l_name"/>:</td>
				<td>${sessionScope.user.lName}</td>
			</tr>
			<tr>
				<td><fmt:message key="e_mail"/>:</td>
				<td>${sessionScope.user.eMail}</td>
			</tr>
		</table>
			<br><fmt:message key="test_results"/>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="result"> <input
					type="submit" value="<fmt:message key="admin_jsp.show_results"/>">

			</form>
		<br> <form action="controller" method="post">
				<input type="hidden" name="command" value="homepage"> <input
					type="submit" value="<fmt:message key="users_jsp.homepage"/>">

			</form>

	</div>

</body>
</html>