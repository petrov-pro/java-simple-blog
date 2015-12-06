<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.main_category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div id="category">
            <h1>${Load.bundle.article}: ${Data.articleModel.article.translate_title.get(Load.lang.get())}</h1>
            <span>${Data.categoryModel.category.translate.get(Load.lang.get())}</span>
            \<span>${Load.bundle.article_author}:</span>${Data.articleModel.article.userName}
            <div>

                <div>
                    ${Data.articleModel.article.translate_body.get(Load.lang.get())}
                    <div>${Data.articleModel.article.ut}</div>
                </div>
                <c:if test="${article.tags.size() > 0}">
                    <div id="tags">
                        <span>Tags:</span>
                        <span>
                            <c:forEach items="${article.tags}" var="tag">
                                <a href="/main/tag/${tag.name}/">
                                    ${tag.name}
                                </a>
                            </c:forEach>
                        </span>
                    </div>
                </c:if>

            </div>
        </div>

        <t:comment user_name="${Load.auth.getUserName()}" article_id="${Data.articleModel.article.id}" lang="${Load.lang.get()}"/>
    </jsp:attribute>

</t:template>






