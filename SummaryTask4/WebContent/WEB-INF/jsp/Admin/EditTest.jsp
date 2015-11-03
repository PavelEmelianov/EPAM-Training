<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="http://journaldev.com/jsp/tlds/MyTags" prefix="MyTags"%>
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
<fmt:message key="result_jsp.theme_name"/>: ${sessionScope.themeName}<br>
		<fmt:message key="result_jsp.test_name"/>: ${sessionScope.testName}<br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="refactorPage"> <input
				type="hidden" name="name" value="test"> <input type="submit"
				value="<fmt:message key="change_name"/>">
		</form>
		<br> <fmt:message key="result_jsp.test_difficulty"/>: ${sessionScope.testDifficulty}<br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="refactorPage"> <input
				type="hidden" name="name" value="testDifficulty"> <input
				type="submit" value="<fmt:message key="change_difficulty"/>">
		</form>
		<br>
		<fmt:message key="test_time"/>: <MyTags:formatToHour number="${sessionScope.testTime}"/> <fmt:message key="hours"/> <MyTags:formatToMin number="${sessionScope.testTime}"/> <fmt:message key="min"/> ${sessionScope.testTime%60} <fmt:message key="sec"/> <br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="refactorPage"> <input
				type="hidden" name="name" value="testTime"> <input
				type="submit" value="<fmt:message key="change_time"/>">
		</form>
		<br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="refactorPage">
							<input type="hidden" name="name" value="addQuestion"><input
				type="submit" value="<fmt:message key="add_question"/>">
		</form>
		<br>
		<table border="1" style="text-align: center;">
			<tr>
				<td><fmt:message key="question_name"/></td>
				<td><fmt:message key="admin_jsp.theme_edit"/></td>
				<td><fmt:message key="admin_jsp.theme_delete"/></td>

			</tr>
			<c:forEach var="bean" items="${sessionScope.questionList}">
				<tr>
					<td>${bean.name}</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="questionId" value="${bean.id}">
							<input type="hidden" name="questionName" value="${bean.name}">
							<input type="hidden" name="command" value="editQuestion">
							<input type="submit" value="<fmt:message key="edit_question"/>">
						</form>
					</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="questionId" value="${bean.id}">
							<input type="hidden" name="command" value="refactorPage">
							<input type="hidden" name="name" value="deleteQuestion">
							<input type="submit" value="<fmt:message key="delete_question"/>">
						</form>
					</td>
				</tr>

			</c:forEach>
		</table>
		<br> <form action="controller" method="post">
							<input type="hidden" name="command" value="getTests">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form>
	</div>

</body>
</html>