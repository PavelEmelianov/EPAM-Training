<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="List Users">
	<u:loggedAs/>	
	<a href='AddUserView'>add</a>	
	<table border='1'>
		<tr>		
			<th>Login</th>
			<th>Full Name</th>
			<th>Email</th>
			<th>Role</th>
			<th colspan="2">Actions</th>			
		</tr>
					
		<c:forEach items="${beans}" var="bean">
			<tr>
				<td>${bean.login}</td>				
				<td>${bean.fullName}</td>				
				<td>${bean.email}</td>				
				<td>${bean.roleName}</td>
				<td><a href='ConfirmDeleteUserView?login=${bean.login}'>delete</a></td>				
				<td><a href='EditUser?login=${bean.login}'>edit</a></td>			
			</tr>
		</c:forEach>
	</table>
			
	<u:goBack/>	
</u:wrapper>