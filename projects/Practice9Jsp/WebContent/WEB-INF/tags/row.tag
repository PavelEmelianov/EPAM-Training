<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" type="ua.nure.your_last_name.Practice9.db.entity.User" %>
<%@ attribute name="field" %>
<%@ attribute name="label" %>
<%@ attribute name="value" %>
<%@ attribute name="type" %>
<%@ attribute name="readonly" type="java.lang.Boolean"%>
<jsp:doBody var="bodyText"/>
<tr>
	<td><c:out value="${label}">${field}</c:out></td>
	<td>
		<c:choose>
			<c:when test="${not empty bodyText}">
				${bodyText}
			</c:when>
			<c:otherwise>
				<c:set var="attributes" value=""/>
				<c:if test="${not empty type}">
					<c:set var="attributes" value="${attributes}${'type='}${type}${' '}"/>	
				</c:if>
				<c:if test="${readonly == true}">
					<c:set var="attributes" value="${attributes}${' readonly'}"/>	
				</c:if>			
				<input name="${field}" ${attributes} value="<c:out value='${value}'>${user[field]}</c:out>">
			</c:otherwise>
		</c:choose>
	</td>	
</tr>