<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.article}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

    <jsp:attribute name="head_area">
        <script src="/static/js/tinymce/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: 'textarea'
            });
        </script>
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <form method="POST" action="${Data.url}" class="form-horizontal">
            <div class="control-group">
                <label class="control-label" for="article_name">${Load.bundle.article_category}</label>

                <select name="category_id" class="form-control">
                    <c:forEach items="${Data.tree.getBranches()}" var="item"> 
                        <c:choose>
                            <c:when test="${item.id == Data.article.category_id}">
                                <option selected="selected" value="${item.id}">
                                    ${item.translate.get(Load.lang.get())}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option  value="${item.id}">
                                    ${item.alias}
                                </option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </div>

            <div class="control-group">
                <label class="control-label" for="article_name">${Load.bundle.article_title}</label>

                <c:forEach items="${Load.config.langs}" var="item"> 
                    <div class="controls">
                        ${item}    <input class="form-control" type="text" name="article_title[${item}]" value="${Data.article.translate_title.get(item)}" />
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
                <div class="checkbox" >
                    <c:choose>
                        <c:when test="${Data.article.enable}">
                            <c:set var="checked" scope="page">checked</c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="checked" scope="page"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <label class="control-label" for="artiv_name">
                        <input type="checkbox" name="enable" value="true"  <c:out value="${checked}"/> />
                        ${Load.bundle.article_enable}
                    </label>

                </div>
                <label class="control-label" for="article_name">${Load.bundle.article_alias}</label>
                <div class="controls">
                    <input class="form-control" type="text" name="alias" value="${Data.article.alias}" />
                </div>
                <label class="control-label" for="article_name">${Load.bundle.article_weight}</label>
                <div class="controls">
                    <input class="form-control" type="text" name="weight" value="${Data.article.weight}" />
                </div>
                <label class="control-label" for="article_name">${Load.bundle.article_tag}</label>
                <div class="controls">
                    <input class="form-control" type="text" name="tagsstr" value="${Data.article.tagsStr}" />
                </div>
                <br/>
                <div class="controls">
                    <input class="btn btn-default" type="submit" value="${Load.bundle.user_registration_submit}"/>
                </div>
            </div>
        </form>

    </jsp:attribute>

</t:template>






