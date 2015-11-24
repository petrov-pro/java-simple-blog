<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <div id="category">
            <ul>
                <c:forEach items="${Data.categoryModel.categories}" var="category">
                    <li>
                        <a href="/main/category/${category.userId}/${category.alias}/">
                            ${category.translate.get(Load.lang.get())}
                        </a>
                    </li>
                </c:forEach> 
            </ul>
        </div>
        <div id="article">
			<ul>
				<c:forEach items="${Data.articleModel.articles}" var="article">
					<li>
						<a href="/article/get/${article.user_id}/${article.alias}/">
							${article.translate_title.get(Load.lang.get())}
						</a>
						<div>
							<c:set var="body" value="${article.translate_body.get(Load.lang.get())}"/>
							<c:choose>
								<c:when test="${body.length() > 50}">
									${body.substring(0,10)}...
								</c:when>
								<c:otherwise>
									${body}
								</c:otherwise>
							</c:choose>

						</div>
						<span>${article.userName}</span>
						<span>${article.ut}</span>
						<c:if test="${article.tags.size() > 0}">
							<div id="tags">
								<span>Tags:</span>
								<span>
									<c:forEach items="${article.tags}" var="tag">
										<a href="/tag/get/${tag.user_id}/${tag.name}/">
											${tag.name}
										</a>
									</c:forEach>
								</span>
							</div>
						</c:if>
					</li>
				</c:forEach> 
			</ul>
        </div>
    </jsp:attribute>

</t:template>






