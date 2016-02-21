<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.articles}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>
            <strong>
                ${Load.bundle.articles}:
            </strong>
        </h1>
        <form action="/main/articles/" method="post">
            <label class="control-label" for="content_name">${Load.bundle.search}</label>
            <div class="controls">
                <input type="text" name="search" value=""/>
            </div>

            <div class="controls">
                <input class="btn btn-default" type="submit" value="${Load.bundle.content_search}"/>
            </div>
        </form>
        <br/>

        <t:articles articles="${Data.articleModel.articles}"/>

        <div align="center" style="text-align: center;">
            <ul class="pagination">
                <c:forEach begin="1" end="${Data.articleModel.count}" var="val">
                    <li><a href="/main/articles/${val}/${Data.articleModel.search}"><c:out value="${val}"/></a></li>
                    </c:forEach>
            </ul>

        </div>

    </jsp:attribute>

</t:template>






