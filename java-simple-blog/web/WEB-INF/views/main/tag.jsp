<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.main_category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div id="tag">
            <h1>
                <strong>
                    ${Load.bundle.tag}:  
                </strong>
                ${Data.tagModel.tag.name}</h1>
        </div>
        <t:articles articles="${Data.tagModel.tag.articles}"/>
    </jsp:attribute>

</t:template>






