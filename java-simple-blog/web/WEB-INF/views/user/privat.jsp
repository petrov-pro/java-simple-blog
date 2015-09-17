<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.nav_main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>${Load.bundle.user_update}</h1>
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <form method="POST" action="/user/privat/">
            ${Load.bundle.user_registration_email}: <input type="input" name="email" value="${Data.user.email}"/><br/>
            ${Load.bundle.user_registration_password}: <input type="password" name="password" value=""/><br/>
            ${Load.bundle.user_registration_confirm}: <input type="password" name="confirm" value=""/><br/>
            <input type="submit" value="${Load.bundle.user_registration_submit}"/><br/>
        </form>

    </jsp:attribute>

</t:template>






