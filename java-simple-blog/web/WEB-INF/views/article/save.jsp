<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.article}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <form method="POST" action="${Data.url}" class="form-horizontal">
            <div class="control-group">
                <label class="control-label" for="article_name">${Load.bundle.article_title}</label>

                <c:forEach items="${Load.config.langs}" var="item"> 
                    <div class="controls">
                        ${item}    <input type="text" name="article_title[${item}]" value="${Data.article.translate_title.get(item)}" />
                    </div>
                </c:forEach>
            </div>
            <div class="control-group">
                <label class="control-label" for="article_name">${Load.bundle.article_body}</label>

                <c:forEach items="${Load.config.langs}" var="item"> 
                    <div class="controls">
                        ${item}   
                        <textarea name="article_body[${item}]">${Data.article.translate_body.get(item)}</textarea>
                    </div>
                </c:forEach>
            </div>
            <div class="control-group">
                <label class="control-label" for="artiv_name">${Load.bundle.article_enable}</label>
                <div class="controls">
                    <c:choose>
                        <c:when test="${Data.article.enable}">
                            <c:set var="checked" scope="page">checked</c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="checked" scope="page"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="checkbox" name="enable" value="true"  <c:out value="${checked}"/> />
                </div>
                <label class="control-label" for="article_name">${Load.bundle.article_alias}</label>
                <div class="controls">
                    <input type="text" name="alias" value="${Data.article.alias}" />
                </div>
                <label class="control-label" for="article_name">${Load.bundle.article_weight}</label>
                <div class="controls">
                    <input type="text" name="weight" value="${Data.article.weight}" />
                </div>
				<label class="control-label" for="article_name">${Load.bundle.article_tag}</label>
                <div class="controls">
                    <input type="text" name="tagsstr" value="${Data.article.tagsStr}" />
                </div>

                <div class="controls">
                    <input type="submit" value="${Load.bundle.user_registration_submit}"/>
                </div>
            </div>
        </form>

    </jsp:attribute>

</t:template>






