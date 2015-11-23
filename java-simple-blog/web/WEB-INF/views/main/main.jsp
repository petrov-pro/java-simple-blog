<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div class="category">
            <ul>
                <c:forEach items="${Data.categoryModel.categories}" var="category">
                    <li>
                        <a href="/category/get/${category.alias}/">
                            ${category.translate.get(Load.lang.get())}
                        </a>
                    </li>
                </c:forEach> 
            </ul>
        </div>
        <div>
            
        </div>
    </jsp:attribute>

</t:template>






