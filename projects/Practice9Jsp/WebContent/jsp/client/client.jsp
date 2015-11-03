<%@ include file="/jsp/jspf/directives.jspf" %>

<u:wrapper title="List Clients">
	<u:loggedAs/>	
	<table border='1'>
		<tr>		
			<th>Full Name</th>
			<th>Email</th>
			<th>Action</th>				
		</tr>					
		<c:forEach items="${beans}" var="bean">
			<tr>
				<td>${bean.fullName}</td>				
				<td>${bean.email}</td>				
				<td style="text-align: center">
					<c:if test="${currentUser.login == bean.login}">
						<a href='EditClient?login=${bean.login}'>edit</a>
					</c:if>
				</td>			
			</tr>
		</c:forEach>
	</table>			
	<u:goBack/>	
</u:wrapper>