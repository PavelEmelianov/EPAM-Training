<%@ include file="/jsp/jspf/directives.jspf"%>

<u:wrapper title="Add User">
	<u:loggedAs />
	<u:listErrors/>
	<form action="AddUser" method="post">
		<table>
			<u:row user="${userToAdd}" field="login" label="Login" />
			<u:row user="${userToAdd}" field="password" label="Password"	/>
			<u:row user="${userToAdd}" field="fullName" label="Full Name" />
			<u:row user="${userToAdd}" field="email" label="Email" />
			<u:row label="Role">
				<select name="roleName">
					<u:option value="admin"/>
					<u:option value="client"/>
				</select>
			</u:row>
			<u:row type="submit" value="Add New User"/>
		</table>
	</form>
	<u:goBack />
</u:wrapper>
