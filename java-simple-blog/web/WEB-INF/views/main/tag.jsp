<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.main_category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div id="tag">
            <h1>${Load.bundle.tag}: ${Data.tagModel.tag.name}</h1>
        </div>
        <div id="article">
            <ul>
                <c:forEach items="${Data.tagModel.tag.articles}" var="article">
                    <li>
                        <div id="category_name">
                            <sapn>
                                ${Load.bundle.category}:
                            </sapn>
                            <a href="/main/category/${article.user_id}/${article.category.alias}/">
                                ${article.category.translate.get(Load.lang.get())}
                            </a>
                        </div>
                        <div>
                            <span>
                                <a href="/main/article/${article.user_id}/${article.alias}/">
                                    ${article.translate_title.get(Load.lang.get())}
                                </a>
                            </span>
                            <div>
                                <c:set var="body" value="${article.translate_body.get(Load.lang.get())}"/>
                                <c:choose>
                                    <c:when test="${body.length() > 200}">
                                        ${body.substring(0,10)}...
                                    </c:when>
                                    <c:otherwise>
                                        ${body}
                                    </c:otherwise>
                                </c:choose>

                            </div>
							<span><a href="/main/user/${article.userName}/">${article.userName}</a></span>
                            <span>${article.ut}</span>
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

                    </li>

                </c:forEach>
            </ul>
        </div>

    </jsp:attribute>

</t:template>






