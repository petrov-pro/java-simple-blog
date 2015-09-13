<!DOCTYPE html>
<%@tag description="Template" pageEncoding="UTF-8"%>

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
        <jsp:invoke fragment="navigator_area"/>
        <jsp:invoke fragment="auth_area"/>

        <jsp:invoke fragment="body_area"/>

    </body>

</html>