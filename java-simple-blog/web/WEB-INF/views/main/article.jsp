<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.article}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div class="container-fluid" id="category">
            <div class="panel-group">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3>${Data.articleModel.article.translate_title.get(Load.lang.get())}</h3>
                    </div>

                    <div class="panel-body">
                        ${Data.articleModel.article.translate_body.get(Load.lang.get())}
                        <div class="well well-sm">
                            <span>
                                <strong>
                                    ${Load.bundle.category}:
                                </strong>
                                <a href="/main/category/${Data.categoryModel.category.userId}/${Data.categoryModel.category.alias}">
                                    ${Data.categoryModel.category.translate.get(Load.lang.get())}
                                </a>
                            </span>
                            <span>
                                <strong>
                                    ${Load.bundle.article_author}:
                                </strong>
                                <a href="/main/user/${Data.articleModel.article.userName}/">
                                    ${Data.articleModel.article.userName}
                                </a>

                            </span>

                            <span>
                                <strong>
                                    Dt: 
                                </strong>
                                ${Data.articleModel.article.ut}
                            </span>

                        </div>
                    </div>
                    <c:if test="${article.tags.size() > 0}">
                        <div id="tags" class="panel-footer">
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
        </div>

        <t:comment user_name="${Load.auth.getUserName()}" article_id="${Data.articleModel.article.id}" lang="${Load.lang.get()}"/>
    </jsp:attribute>

</t:template>






