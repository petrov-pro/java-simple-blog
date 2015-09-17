<!DOCTYPE html>
<%@tag description="Template" pageEncoding="UTF-8"%>
<%@taglib prefix="a" tagdir="/WEB-INF/tags/" %>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>
<%@attribute name="navigator_area" fragment="true" %>
<%@attribute name="auth_area" fragment="true" %>

<html>
    <head>
        <title>${title}</title>
        <jsp:invoke fragment="head_area"/>
    </head>
    <body>
        <a:auth auth="${Load}"/>
        <jsp:invoke fragment="navigator_area"/>
        <jsp:invoke fragment="body_area"/>

    </body>

</html>