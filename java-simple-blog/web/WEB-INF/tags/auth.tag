<%@tag description="Auth" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@tag import="blog.system.loader.Load" %>
<%@attribute name="auth" required="true" type="blog.system.loader.Load"%>
<c:choose>
    <c:when test="${Load.auth.isAuth()}">
        ${Load.bundle.auth_login_user}: ${Load.auth.getUserName()}
        <a href="/user/privat">${Load.bundle.profile}</a>
        <a href="/auth/logout/">${Load.bundle.logout}</a>
    </c:when>
    <c:otherwise>
        <a href="/auth/login/">${Load.bundle.login}</a> <a href="/user/registration/">${Load.bundle.registration}</a>
    </c:otherwise>
</c:choose>
