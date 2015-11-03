<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>

<body><%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div align="center">
		<c:choose>
			<c:when test="${sessionScope.name eq 'addTheme'}">
				<h3><fmt:message key="enter_theme_name"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset>
									<legend><fmt:message key="result_jsp.theme_name"/></legend>
									<input name="name"><br>
								</fieldset>
								<input type="hidden" name="command" value="refactor"><br>
								<div align="center">
									<input type="submit" value="<fmt:message key="create_theme"/>">
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="homepage">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form>
							</div>
						</td>
					</tr>
				</table>
			</c:when>
						<c:when test="${sessionScope.name eq 'addQuestion'}">
				<h3><fmt:message key="enter_question_name"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset>
									<legend><fmt:message key="question_name"/></legend>
									<input name="name"><br>
								</fieldset>
								<input type="hidden" name="command" value="refactor"><br>
								<div align="center">
									<input type="submit" value="<fmt:message key="create_question"/>">
								</div>
							</form> <br><div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="editTest">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
									<c:when test="${sessionScope.name eq 'addAnswer'}">
				<h3><fmt:message key="enter_answer_name"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset>
									<legend><fmt:message key="answer_name"/></legend>
									<input name="name"><br>
								</fieldset>
								<input type="hidden" name="command" value="refactor"><br>
								<div align="center">
									<input type="submit" value="<fmt:message key="create_answer"/>">
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="editQuestion">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
									<c:when test="${sessionScope.name eq 'testDifficulty'}">
				<h3><fmt:message key="enter_difficulty"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset><legend><fmt:message key="result_jsp.test_difficulty"/></legend>
									<input name="name"><br>
								</fieldset>
								<input type="hidden" name="command" value="refactor"><br>
								<div align="center">
									<input type="submit" value="<fmt:message key="change_difficulty"/>">
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="editTest">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
												<c:when test="${sessionScope.name eq 'testTime'}">
				<h3><fmt:message key="enter_time"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset><legend><fmt:message key="test_time_sec"/></legend>
									<input name="name"><br>
								</fieldset>
								<input type="hidden" name="command" value="refactor"><br>
								<div align="center">
									<input type="submit" value="<fmt:message key="change_time"/>">
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="editTest">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
							
						</td>
					</tr>
				</table>
			</c:when>
			<c:when test="${sessionScope.name eq 'addTest'}">
				<h3><fmt:message key="select_ndt"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset>
									<legend><fmt:message key="result_jsp.test_name"/></legend>
									<input name="name"><br>
								</fieldset>
								<fieldset>
									<legend><fmt:message key="result_jsp.test_difficulty"/></legend>
									<input name="difficulty"><br>
								</fieldset>
								<fieldset>
									<legend><fmt:message key="test_time_sec"/></legend>
									<input name="time"><br>
								</fieldset>
								<input type="hidden" name="command" value="refactor">
								<div align="center">
									<input type="submit" value="<fmt:message key="create_test"/>">
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="getTests">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
			<c:when test="${sessionScope.name eq 'deleteTheme'}">
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
							<h3><fmt:message key="sure_theme"/></h3><br> <input type="hidden"
									name="command" value="refactor">
								<div align="center">
									<input type="submit" value="<fmt:message key="admin_jsp.delete_theme"/>">
								<br>
								</div>
								
							</form> <br><div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="homepage">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
				<c:when test="${sessionScope.name eq 'deleteTest'}">
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
							<h3><fmt:message key="sure_test"/></h3><br> <input type="hidden"
									name="command" value="refactor">
								<div align="center">
									<input type="submit" value="<fmt:message key="delete_test"/>">
								<br>
								</div>
							</form> <br><div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="getTests">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
				<c:when test="${sessionScope.name eq 'deleteQuestion'}">
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
							<h3><fmt:message key="sure_question"/></h3><br> <input type="hidden"
									name="command" value="refactor">
								<div align="center">
									<input type="submit" value="<fmt:message key="delete_question"/>">
								<br>
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="editTest">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
			<c:when test="${sessionScope.name eq 'deleteAnswer'}">
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
							<h3><fmt:message key="sure_answer"/></h3><br> <input type="hidden"
									name="command" value="refactor">
								<div align="center">
									<input type="submit" value="<fmt:message key="delete_answer"/>">
								<br>
								</div>
							</form> <br>
							<div align="center">
							<form action="controller" method="post">
							<input type="hidden" name="command" value="editQuestion">
							<input type="submit" value ="<fmt:message key="users_jsp.homepage"/>">
							</form></div>
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<h3><fmt:message key="new_name"/></h3>
				<table>
					<tr>
						<td>
							<form action="controller" method="post">
								<fieldset>
									<legend><fmt:message key="users_jsp.name"/></legend>
									<input name="name"><br>
								</fieldset>
								<br> <input type="hidden" name="command" value="refactor">
								<div align="center">
									<input type="submit" value="<fmt:message key="change_name"/>">
								</div>
							</form> <br>
						</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>