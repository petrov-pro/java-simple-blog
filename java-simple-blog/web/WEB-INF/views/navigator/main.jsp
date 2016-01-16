<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach  var="l" items="${Navigator.profileLinks}">
	<c:choose>
		<c:when test="${l.value.activate}">
			<span>
				${l.value.title}
			</span>
		</c:when>    
		<c:otherwise>
			<a href="${l.value.link}">
				${l.value.title}
			</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
