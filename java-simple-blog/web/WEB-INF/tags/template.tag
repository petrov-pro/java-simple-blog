<!DOCTYPE html>
<%@tag description="Template" pageEncoding="UTF-8"%>
<%@taglib prefix="a" tagdir="/WEB-INF/tags/" %>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>
<%@attribute name="navigator_area" fragment="true" %>
<%@attribute name="auth_area" fragment="true" %>
<%@attribute name="i18n_area" fragment="true" %>

<html>
    <head>
		<script src="/static/js/jquery-2.1.4.min.js" type="text/javascript" encoding="UTF-8"></script>
        <script src="/static/js/common.js" type="text/javascript" encoding="UTF-8"></script>
        <title>${title}</title>
        <jsp:invoke fragment="head_area"/>
    </head>
    <body>
        <a:auth auth="${Load}"/>
		<a:i18n />

        <jsp:invoke fragment="navigator_area"/>
        <jsp:invoke fragment="body_area"/>

    </body>

</html>