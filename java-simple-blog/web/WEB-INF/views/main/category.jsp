<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>
            <strong>
                ${Load.bundle.category}:
            </strong>
            ${Data.categoryModel.category.translate.get(Load.lang.get())} 
        </h1>

        <t:articles articles="${Data.articleModel.articles}"/>
    </jsp:attribute>

</t:template>






