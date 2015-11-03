<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<body>
<body><%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div align="center">
		<form action="controller" method="post">
	<c:if test="${requestScope.timeout ne true}">
			<c:if test="${sessionScope.singleQuestion ne 0 }">
		<h3><fmt:message key="question_n"/>
			${sessionScope.questionId+1}/${sessionScope.questionCount}</h3>
			</c:if>
		<c:if test="${sessionScope.singleQuestion ne 0 }">

				<table border="1" style="text-align: center;">
					<tr>
						<td>${requestScope.question.name}</td>
					</tr>
					<c:forEach var="bean" items="${sessionScope.answers}">
						<tr>

							<td><input type="checkbox" name="answer"
								value="${bean.name}">${bean.name}</td>

						</tr>
					</c:forEach>
				</table>
				
				<br>

			</c:if>
			<c:if test="${sessionScope.singleQuestion eq 0 }">
				<h3><fmt:message key="test_passed"/></h3>
			</c:if>
			</c:if>
			<c:if test="${requestScope.timeout eq true}">
			<h3><fmt:message key="test_timeout"/></h3>
			</c:if>
		<c:if test="${requestScope.timeout ne true}">
				<c:if test="${sessionScope.singleQuestion gt 1 }">
						<input type="submit" name="previous" value="<fmt:message key="previous_answer"/>">

						<input type="submit" name="next" value="<fmt:message key="next_answer"/>">
				</c:if>
				<c:if test="${sessionScope.singleQuestion gt 0 }">
					<input type="submit" name="commit" value="<fmt:message key="commit"/>">
				</c:if></c:if>
			<input type="submit" name="end" value="<fmt:message key="end_test"/>">
				<input type="hidden" name="command" value="passTest">
				<input type="hidden" name="qid" value="${requestScope.question.id}">
				<input type="hidden" name="uid" value="${sessionScope.user.id}">
		</form>


	</div>

</body>
</html>