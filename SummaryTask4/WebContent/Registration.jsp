<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<body>

	<div align="center">
				<h1>Registration page</h1>
				<h3>Latin symbols and digits are allowed, cyrillic symbols are allowed for "First name" and "Last name" fields </h3>
		<table>
			<tr><td>
				<form action="controller" method="post">
					<fieldset>
						<legend>Login</legend>
						<input name="setLogin" value="${requestScope.setlogin}" /><br />
					</fieldset>
					<br />
					<fieldset>
						<legend>Password</legend>
						<input type="password" name="setPassword" value="${requestScope.setPassword}" />
					</fieldset>
					<br />
					<fieldset>
						<legend>Confirm password</legend>
						<input type="password" name="confirmPassword" value="${requestScope.confirmPassword}" />
					</fieldset>
					<br />
					<fieldset>
						<legend>First name</legend>
						<input name="setFName" value="${requestScope.setFName}" />
					</fieldset>
					<br />
					<fieldset>
						<legend>Last name</legend>
						<input name="setLName" value="${requestScope.setLName}" />
					</fieldset>
					<br />
					<fieldset>
						<legend>E-mail</legend>
						<input name="setEMail" value="${requestScope.setEMail}" />
					</fieldset>
					<br/>
					<div align="center">
					<input type ="hidden" name="command" value="registration">
					<input type ="submit" value="Register"></div>
				</form>
				<form action ="controller" method="post">
				<div align="center">
				<input type ="hidden" name="command" value="anycommand">
				<input type ="submit" value="Login page">	</div>
				</form></td>
		</tr>
		</table>
	</div>
</body>
</html>