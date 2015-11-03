<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="Edit Client">
	<u:loggedAs/>	
	<u:listErrors/>	
	<form action="UpdateClient" method="post">
		<table>
			<u:row user="${userToEdit}" field="login" label="Login" readonly="true"/>
			<u:row user="${userToEdit}" field="password" label="Password"	/>
			<u:row user="${userToEdit}" field="fullName" label="Full Name" />
			<u:row user="${userToEdit}" field="email" label="Email" />
			<u:row type="submit" value="Update Client"/>
		</table>
	</form>
	<u:goBack/>
</u:wrapper>