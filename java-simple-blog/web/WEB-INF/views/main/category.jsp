<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template title="${Load.bundle.main_category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
		<div id="category">
			<h1>${Load.bundle.category}: ${Data.categoryModel.category.translate.get(Load.lang.get())}</h1>
			<span>${Load.bundle.category_author}:</span><a href="/main/user/${Data.categoryModel.category.userName}/">${Data.categoryModel.category.userName}</a>
			<div>
				<c:forEach items="${Data.articleModel.articles}" var="article">
					<li>
						<a href="/main/article/${article.user_id}/${article.alias}/">
							${article.translate_title.get(Load.lang.get())}
						</a>
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
						<span>${article.userName}</span>
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
					</li>
				</c:forEach> 
			</div>
		</div>


    </jsp:attribute>

</t:template>






