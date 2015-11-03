<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>Login</title>
<body>
	<div align="center"> 
		<h3>Login and password required</h3>
		<table>
			<tr>
				<td>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="login">
						<fieldset>
							<legend>Login</legend>
							<input name="login"><br>
						</fieldset>
						<fieldset>
							<legend>Password</legend>
							<input type="password" name="password">
						</fieldset>
						<br>
						<div align="center">
							<input type="submit" value="Confirm">
						</div>
					</form>
					<div align="center">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="RegistrationPage">
							<input type="submit" value="Registration">

						</form>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>