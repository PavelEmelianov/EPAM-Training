<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="Edit User">
	<u:loggedAs/>	
	<u:listErrors/>	
	<form action="UpdateUser" method="post">
		<table>
			<u:row user="${userToEdit}" field="login" label="Login" readonly="true"/>
			<u:row user="${userToEdit}" field="password" label="Password"	/>
			<u:row user="${userToEdit}" field="fullName" label="Full Name" />
			<u:row user="${userToEdit}" field="email" label="Email" />
			<u:row label="Role">
				<select name="roleName">
					<u:option value="admin"/>
					<u:option value="client"/>
				</select>
			</u:row>
			<u:row type="submit" value="Update User"/>
		</table>
	</form>
	<u:goBack/>
</u:wrapper>