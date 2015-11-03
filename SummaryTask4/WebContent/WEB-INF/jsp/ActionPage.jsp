<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div align="center">
		<c:if test="${requestScope.status eq true}">
<fmt:message key="user"/> ${requestScope.login}  <fmt:message key="user_unblocked"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="getUsers"><br>  <input
						type="submit" value="<fmt:message key="u_page"/>">
				</div>
			</form>
		</c:if>
		<c:if test="${requestScope.status eq false}">
<fmt:message key="user"/> ${requestScope.login} <fmt:message key="user_blocked"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="getUsers"><br>  <input
						type="submit" value="<fmt:message key="u_page"/>">
				</div>
			</form>
		</c:if>
		<c:if test="${requestScope.register eq true}">
Registration is complete
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="anycommand"><br>  <input
						type="submit" value="Login page">
				</div>
			</form>
		</c:if>
		<c:if test="${requestScope.type eq 'theme'}">
<fmt:message key="theme_name_ref"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="homepage"><br>  <input
						type="submit" value="<fmt:message key="all_homepage"/>">
				</div>
			</form>
		</c:if>
		<c:if test="${requestScope.type eq 'test'}">
 <fmt:message key="test_name_ref"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editTest"><br>  <input
						type="submit" value="<fmt:message key="tests_page"/>">
				</div>
			</form>
		</c:if>
				<c:if test="${requestScope.type eq 'testDifficulty'}">
<fmt:message key="test_difficulty_ref"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editTest"><br>  <input
						type="submit" value="<fmt:message key="tests_page"/>">
				</div>
			</form>
		</c:if>
						<c:if test="${requestScope.type eq 'testTime'}">
<fmt:message key="test_time_ref"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editTest"><br>  <input
						type="submit" value="<fmt:message key="tests_page"/>">
				</div>
			</form>
		</c:if>
								<c:if test="${requestScope.type eq 'addAnswer'}">
<fmt:message key="created_answer"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editQuestion"><br>  <input
						type="submit" value="<fmt:message key="questions_page"/>">
				</div>
			</form>
		</c:if>
		<c:if test="${requestScope.type eq 'answer'}">
<fmt:message key="answer_name_ref"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editQuestion"><br>  <input
						type="submit" value="<fmt:message key="questions_page"/>">
				</div>
			</form>
		</c:if>
		<c:if test="${requestScope.type eq 'question'}">
<fmt:message key="question_name_ref"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editQuestion"><br>  <input
						type="submit" value="<fmt:message key="questions_page"/>">
				</div>
			</form>
		</c:if>
				<c:if test="${requestScope.type eq 'addTheme'}">
<fmt:message key="added_theme"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="homepage"><br> <input
						type="submit" value="<fmt:message key="all_homepage"/>">
				</div>
			</form>
		</c:if>
						<c:if test="${requestScope.type eq 'addTest'}">
<fmt:message key="added_test"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="getTests"><br> <input
						type="submit" value="<fmt:message key="tests_page"/>">
				</div>
			</form>
		</c:if>
						<c:if test="${requestScope.type eq 'deleteTheme'}">
<fmt:message key="deleted_theme"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="homepage"><br> <input
						type="submit" value="<fmt:message key="all_homepage"/>">
				</div>
			</form>
		</c:if>
								<c:if test="${requestScope.type eq 'deleteTest'}">
<fmt:message key="deleted_test"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="getTests"><br> <input
						type="submit" value="<fmt:message key="tests_page"/>">
				</div>
			</form>
		</c:if>
										<c:if test="${requestScope.type eq 'deleteQuestion'}">
<fmt:message key="deleted_question"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editTest"><br> <input
						type="submit" value="<fmt:message key="questions_page"/>">
				</div>
			</form>
		</c:if>
												<c:if test="${requestScope.type eq 'deleteAnswer'}">
<fmt:message key="deleted_answer"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editQuestion"><br> <input
						type="submit" value="<fmt:message key="answers_page"/>">
				</div>
			</form>
		</c:if>
														<c:if test="${requestScope.type eq 'addQuestion'}">
<fmt:message key="added_question"/>
<br>
			<form action="controller" method="post">
				<div align="center">
					<input type="hidden" name="command" value="editTest"><br> <input
						type="submit" value="<fmt:message key="tests_page"/>">
				</div>
			</form>
		</c:if>
		
		
	</div>


</body>


</html>