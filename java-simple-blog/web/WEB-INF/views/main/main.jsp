<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>





<t:template title="${Data.bundle.nav_main}">
     <jsp:attribute name="auth_area">
        <t:auth auth="${Load}"/> 
    </jsp:attribute>

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        ${Load.bundle.main}

    </jsp:attribute>

</t:template>






