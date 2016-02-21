<!DOCTYPE html>
<%@tag description="Template_admin" pageEncoding="UTF-8"%>
<%@taglib prefix="a" tagdir="/WEB-INF/tags/" %>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>
<%@attribute name="navigator_area" fragment="true" %>
<%@attribute name="auth_area" fragment="true" %>

<html>
    <head>
        <script src="/static/js/jquery-2.1.4.min.js" type="text/javascript" encoding="UTF-8"></script>
        <script src="/static/js/common.js" type="text/javascript" encoding="UTF-8"></script>
        <script>
            $(document).ready(function () {
                $("#a_close").click(function () {
                    window.location.href = '/main/';
                });
            })
        </script>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="/static/notepad/style.css" />
        <jsp:invoke fragment="head_area"/>
    </head>
    <body>

        <div id="page">

            <div id="titlebar">

                <h1>${Load.bundle.admin} - Notepad</h1>
                <div id="a_close">
                </div>
            </div>
            <div id="bar">
                <jsp:invoke fragment="navigator_area"/>
            </div>
            <div id="main">
                <jsp:invoke fragment="body_area"/>
            </div>
            <!-- please leave my link somewhere in your page, that is all I ask -->
            <div id="footer"><a href="http://www.bryantsmith.com">web page designer </a> <a href="http://www.bryantsmith.com">bryant smith</a></div>
        </div>

    </body>

</html>