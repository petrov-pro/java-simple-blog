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
        <title>${title}</title>
        <meta charset="UTF-8">
        <meta http-equiv="Cache-Control" content="no-store" />
        <link rel="stylesheet" href="/static/css/jquery-ui.min.css">
        <link rel="stylesheet" href="/static/css/jquery-ui.theme.min.css">
        <link rel="stylesheet" href="/static/css/jquery-ui.structure.min.css">
        <script src="/static/js/jquery-2.1.4.min.js" type="text/javascript" encoding="UTF-8"></script>
        <script src="/static/js/jquery-ui.min.js" type="text/javascript" encoding="UTF-8"></script>
        <script src="/static/js/common.js" type="text/javascript" encoding="UTF-8"></script>
        <!-- Bootstrap -->
        <link href="/static/b/css/bootstrap.min.css" rel="stylesheet">
        <script src="/static/b/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="/static/a/asubtlegreen.css" />
        <jsp:invoke fragment="head_area"/>
    </head>
    <body>


        <div id="page"> 
            <div id="header">
                <div class="title">${Load.bundle.application_title}</div>
                <div class="subText">A design by Petroff</div>
                <div id="auth-box">
                    <a:auth auth="${Load}"/>
                    <a:i18n />
                </div>
            </div>
            <div id="bar">
                <jsp:invoke fragment="navigator_area"/>
            </div>
            <div id="pageContent">

                <div class="articleTitle">${title}</div>


                <div class="articleContent">
                    <jsp:invoke fragment="body_area"/> 
                </div>


            </div>

        </div>
        <div id="footer"><a href="http://www.aszx.net">web development</a> by <a href="http://www.bryantsmith.com">bryant smith</a></div>

    </body>

</html>