<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://journaldev.com/jsp/tlds/MyTags" prefix="MyTags"%>

<html>
<head>
<title>Tests page</title>

<style type="text/css">
form {
	width: auto;
	margin: 0 auto;
}
</style>
</head>

<body><%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div align="center">
		<form action="controller" method="post">
			<fmt:message key="sort_by"/> <select name="sort">
				<option value="name"><fmt:message key="admin_jsp.theme_name"/></option>
				<option value="diff"><fmt:message key="difficulty"/></option>
				<option value="qcount"><fmt:message key="questions_count"/></option>
			</select> <input type="hidden" name="command" value="getTests"> <input
				type="submit" value="<fmt:message key="sort"/>">
		</form>
	</div>
	<br>
	<table style="width: 100%">
		<tr>
			<td>
				<div align="center">
					<c:if test="${sessionScope.user.roleId eq 0 }">
	<fmt:message key="result_jsp.theme_name"/>: ${sessionScope.themeName}<br>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="refactorPage">
							<input type="hidden" name="name" value="theme"> <input
								type="submit" value="<fmt:message key="change_name"/>">
						</form>
						<br>
					</c:if>

					<c:if test="${sessionScope.user.roleId eq 0 }">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="refactorPage">
							<input type="hidden" name="name" value="addTest"> <input
								type="submit" value="<fmt:message key="add_test"/>">
						</form>
						<br>
					</c:if>
					<table border="1" style="text-align: center;">
						<tr>
							<td><fmt:message key="result_jsp.test_name"/></td>
							<td><fmt:message key="result_jsp.test_difficulty"/></td>
							<td><fmt:message key="questions_count"/></td>
							<td><fmt:message key="test_time"/></td>
							<td><fmt:message key="pass"/></td>
							<c:if test="${sessionScope.user.roleId eq 0 }">
								<td><fmt:message key="edit"/></td>
								<td><fmt:message key="delete"/></td>
							</c:if>
						</tr>
						<c:forEach var="bean" items="${sessionScope.tests}">
							<tr>
								<td>${bean.name}</td>
								<td>${bean.difficulty}</td>
								<td>${bean.questionsCount}</td>
								<td><MyTags:formatToHour number="${bean.timeForTest}" />
									<fmt:message key="hours"/> <MyTags:formatToMin number="${bean.timeForTest}" /> <fmt:message key="min"/>
									${bean.timeForTest%60} <fmt:message key="sec"/></td>
								<td><form action="controller" method="post">
										<input type="hidden" name="command" value="passTest">
										<input type="hidden" name="questionId" value="0"><input
											type="hidden" name="testId" value="${bean.id}"> <input
											type="submit" value="<fmt:message key="Pass_test"/>">
									</form></td>
								<c:if test="${sessionScope.user.roleId eq 0 }">
									<td><form action="controller" method="post">
											<input type="hidden" name="testId" value="${bean.id}">
											<input type="hidden" name="testName" value="${bean.name}">
											<input type="hidden" name="testDifficulty"
												value="${bean.difficulty}"> <input type="hidden"
												name="testTime" value="${bean.timeForTest}"> <input
												type="hidden" name="command" value="editTest"> <input
												type="submit" value="<fmt:message key="edit_test"/>">
										</form></td>
									<td><form action="controller" method="post">
											<input type="hidden" name="command" value="refactorPage">
											<input type="hidden" name="name" value="deleteTest">
											<input type="hidden" name="testId" value="${bean.id}">
											<input type="submit" value="<fmt:message key="delete_test"/>">
										</form>
								</c:if>
							</tr>
						</c:forEach>
					</table>
					<br>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="homepage"> <input
							type="submit" value="<fmt:message key="settings_jsp.link.back_to_main_page"/>">
					</form>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>