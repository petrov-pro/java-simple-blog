<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.nav_main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <form method="POST" action="/category/create/">
			${Load.bundle.category_name}<input type="text" name="name" value="${Data.category.name}" />
			<br/>
			${Load.bundle.category_enable}<input type="checkbox" name="enable" value="${Data.category.enable}" />
			<br/>
			${Load.bundle.category_alias}<input type="text" name="alias" value="${Data.category.alias}" />
			<br/>
			${Load.bundle.category_weight}<input type="text" name="weight" value="${Data.category.weight}" />
			<br/>
            <input type="submit" value="${Load.bundle.user_registration_submit}"/><br/>
        </form>

    </jsp:attribute>

</t:template>






