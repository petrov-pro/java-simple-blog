<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="My page">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

    <jsp:attribute name="head_area">
    </jsp:attribute>

    <jsp:attribute name="body_area">
        <form name="login" action="/auth/login" method="POST">
			<input name="login" value=""/>
			<input name="password" value="" type="password"/>
			<input name="send" value="Log In" type="submit" />
		</form>
    </jsp:attribute>

</t:template>






