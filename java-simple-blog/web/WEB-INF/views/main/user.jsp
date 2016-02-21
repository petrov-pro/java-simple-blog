<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.author}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>
            <strong>
                Context by  
            </strong>
            ${Data.userModel.user.user_name}</h1>
        <div id="category">
            <ul class="list-group">
                <c:forEach items="${Data.categoryModel.categories}" var="category">
                    <li class="list-group-item">
                        <a href="/main/category/${category.userId}/${category.alias}/">
                            ${category.translate.get(Load.lang.get())}
                        </a>
                    </li>
                </c:forEach> 
            </ul>
        </div>
        <t:articles articles="${Data.articleModel.articles}"/>
    </jsp:attribute>

</t:template>






