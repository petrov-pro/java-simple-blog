<%-- 
    Document   : template
    Created on : Jun 12, 2015, 2:12:34 PM
    Author     : petroff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Template</h1>
		<c:import url="${Data.getView()}" />
	
    </body>
</html>
