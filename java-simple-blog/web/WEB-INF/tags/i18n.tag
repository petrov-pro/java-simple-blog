<%@tag description="I18n" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id="lang" onchange="change_lang(this.value);">
	<c:if test="${Load.session.get('lang') != null}">
		<option value="${Load.session.get('lang')}">${Load.session.get('lang')}</option>
	</c:if>
	<c:forEach items="${Load.config.langs}" var="item">
		<c:if test="${Load.session.get('lang') != item}">
			<option value="${item}">${item}</option>
		</c:if>	
	</c:forEach>

</select>
