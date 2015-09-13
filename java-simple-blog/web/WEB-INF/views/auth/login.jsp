<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/bundle" prefix="Load" %>
<%@page import="java.sql.Connection" %>

<t:template title="My page">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

    <jsp:attribute name="head_area">
    </jsp:attribute>

    <jsp:attribute name="body_area">

        <form action="j_security_check" method="post">
			${Load:getBundleStatic().getString("auth_login_i")}:<br>
			<input name="j_username" type="text"><br>
			${Load:getBundleStatic().getString("auth_password_i")}:<br>
			<input name="j_password" type="password"><br>
			<input type="submit" value="${Load:getBundleStatic().getString("auth_auth")}">
		</form>
    </jsp:attribute>

</t:template>






