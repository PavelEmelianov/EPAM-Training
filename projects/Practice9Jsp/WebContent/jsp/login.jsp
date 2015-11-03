<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="Authorization">
	<u:listErrors/>
	<form action='Login' method='post'>
		<table>
			<u:row label="Login" field="login"/>		
			<u:row label="Password" field="password"/>		
			<u:row type="submit" value="Authenticate"/>			
		</table>
	</form>
</u:wrapper>