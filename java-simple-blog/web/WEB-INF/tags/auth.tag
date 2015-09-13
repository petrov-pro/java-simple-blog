<!DOCTYPE html>
<%@tag description="Auth" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@tag import="blog.system.loader.Load" %>
<%@attribute name="auth" required="true" type="blog.system.loader.Load"%>
  <c:choose>
    <c:when test="${Load.auth.isAuth()}">
       ${Load.bundle.auth_login_user}: ${Load.auth.getUserName()}<a href="/auth/logout/">Logout</a>
    </c:when>
    <c:otherwise>
        <a href="/auth/login/">Login</a> <a href="">Registration</a>
    </c:otherwise>
</c:choose>
