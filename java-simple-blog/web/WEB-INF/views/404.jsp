<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="My page">

    <jsp:attribute name="navigator_area">
    </jsp:attribute>

    <jsp:attribute name="head_area">
    </jsp:attribute>

    <jsp:attribute name="body_area">
        <h1>404</h1>
		${Error}
    </jsp:attribute>

</t:template>






