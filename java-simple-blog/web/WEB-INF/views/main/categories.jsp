<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.categories}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>
            <strong>
                ${Load.bundle.categories}:
            </strong>
        </h1>
        <form action="/main/categories/" method="post">
            <label class="control-label" for="content_name">${Load.bundle.search}</label>
            <div class="controls">
                <input type="text" name="search" value=""/>
            </div>

            <div class="controls">
                <input class="btn btn-default" type="submit" value="${Load.bundle.content_search}"/>
            </div>
        </form>
        <br/>
        <div id="category">
            <ul class="list-group">
                <c:forEach items="${Data.categoryModel.categories}" var="category">
                    <li class="list-group-item">
                        <span class="badge">
                            <a href="/main/user/${category.userName}/">
                                ${category.userName}
                            </a>

                        </span>
                        <a href="/main/category/${category.userId}/${category.alias}/">
                            ${category.translate.get(Load.lang.get())}
                        </a>
                    </li>
                </c:forEach> 
            </ul>
        </div>

        <div align="center" style="text-align: center;">
            <ul class="pagination">
                <c:forEach begin="1" end="${Data.categoryModel.count}" var="val">
                    <li><a href="/main/categories/${val}/${Data.categoryModel.search}"><c:out value="${val}"/></a></li>
                    </c:forEach>
            </ul>

        </div>

    </jsp:attribute>

</t:template>






