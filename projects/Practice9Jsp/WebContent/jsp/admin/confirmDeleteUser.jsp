<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="Confirm Delete User">
	<u:loggedAs/>		
	<form action='DeleteUser' method='post'>		
		<input type='hidden' name='login' value='${param.login}'>		
		<p>Are you shure to delete user with login ${param.login}?</p>
		<input name='action' type='submit' value='Yes'>
		<input name='action' type='submit' value='No'>
	</form>	
	<u:goBack/>
</u:wrapper>