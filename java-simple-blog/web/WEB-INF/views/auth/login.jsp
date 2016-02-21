<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/bundle" prefix="Load" %>
<%@page import="java.sql.Connection" %>

<t:template title="Login">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

    <jsp:attribute name="head_area">
    </jsp:attribute>

    <jsp:attribute name="body_area">
        <form role="form" action="j_security_check" method="post">
            <div class="form-group">
                <label for="j_username">${Load:getBundleStatic().getString("auth_login_i")}:</label>
                <input type="input" class="form-control" id="j_username" name="j_username" value=""/>
            </div>
            <div class="form-group">
                <label for="pwd">${Load:getBundleStatic().getString("auth_password_i")}:</label>
                <input type="password" name="j_password" class="form-control" id="pwd" value=""/>
            </div>
            <button type="submit" class="btn btn-default">${Load:getBundleStatic().getString("auth_auth")}</button>
        </form>
    </jsp:attribute>

</t:template>






