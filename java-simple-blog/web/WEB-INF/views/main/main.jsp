<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div class="rightLinks">
            <div class="linkTitle">${Load.bundle.categories}:</div>
            <c:forEach items="${Data.categoryModel.categories}" var="category">
                <p class="links"><a href="/main/category/${category.userId}/${category.alias}/">
                        ${category.translate.get(Load.lang.get())}
                    </a></p>
                </c:forEach> 
        </div>
        <t:articles articles="${Data.articleModel.articles}"/>
    </jsp:attribute>

</t:template>






