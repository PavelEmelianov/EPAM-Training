<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<title>Edit test page</title>

<style type="text/css">
form {
	width: auto;
	margin: 0 auto;
}
</style>
</head>

<body><%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div align="center">
		<fmt:message key="question_name"/>: ${sessionScope.questionName}<br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="refactorPage"> <input
				type="hidden" name="name" value="question"> <input
				type="submit" value="<fmt:message key="change_name"/>">
		</form>
		<br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="refactorPage">
			 <input type="hidden" name="name" value="addAnswer">
				 <input
				type="submit" value="<fmt:message key="add_answer"/>">
		</form>
		<br>
		<table border="1" style="text-align: center;">
			<tr>
				<td><fmt:message key="answer_name"/></td>
				<td><fmt:message key="answer_status"/></td>
				<td><fmt:message key="edit"/></td>
				<td><fmt:message key="delete"/></td>
			</tr>
			<c:forEach var="bean" items="${sessionScope.answersList}">
				<tr>
					<td>${bean.name}</td>
					<td>
						<form action="controller" method="post">
						<input type="hidden" name="command" value="answerStatus">
						<input type="hidden" name="answerId" value="${bean.id}">
						<c:if test="${bean.correct eq true}">
							<input type="submit" value="<fmt:message key="correct"/>">
							</c:if>
								<c:if test="${bean.correct eq false}">
							<input type="submit" value="<fmt:message key="incorrect"/>">
							</c:if>
							
						</form>
					</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="refactorPage">
							<input type="hidden" name="name" value="answer"> <input
								type="hidden" name="answerId" value="${bean.id}"> <input
								type="submit" value="<fmt:message key="change_name"/>">
						</form>
					</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="answerId" value="${bean.id}">
							<input type="hidden" name="command" value="refactorPage">
							<input type="hidden" name="name" value="deleteAnswer">
							<input type="submit" value="<fmt:message key="delete_answer"/>">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br><form action="controller" method="get">
		<input type = "hidden" name="command" value="editTest">
		<input type="submit" value="<fmt:message key="users_jsp.homepage"/>">
		</form>
	</div>

</body>
</html>