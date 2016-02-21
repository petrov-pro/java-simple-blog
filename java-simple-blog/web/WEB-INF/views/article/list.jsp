<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.article}">

    <jsp:attribute name="head_area">
        <script type="text/javascript">
            $(document).ready(function () {
                $('.article_del').click(function (obj) {

                    if (!confirm("${Load.bundle.del}")) {
                        return false;
                    }

                    var article_id = $(this).attr('article_id');
                    $.post("/article/del/" + article_id, function (data) {
                        if (data.message === true) {
                            $("#article_" + article_id).hide();
                            console.log($("article_" + article_id));
                        } else {
                            show_message("Error", "${Load.bundle.article_cant_delete_article}", true);
                        }

                    });
                });
            });
        </script>
    </jsp:attribute>

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>${Load.bundle.article} list</h1>
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <table class="table">
            <tr>
                <td>
                    ${Load.bundle.article_id}
                </td>
                <td>
                    ${Load.bundle.article_alias}
                </td>
                <td>
                    ${Load.bundle.article_enable}
                </td>
                <td>
                    ${Load.bundle.article_weight}
                </td>
                <td>
                    ${Load.bundle.article_lang}
                </td>
                <td>
                    ${Load.bundle.article_text}
                </td>
                <td>
                    ${Load.bundle.article_operation}
                </td>
            </tr>
            <c:forEach items="${Data.articles}" var="article">
                <tr id="article_${article.id}">
                    <td>
                        <c:out value="${article.id}"/>
                    </td>
                    <td>
                        <c:out value="${article.alias}"/>
                    </td>
                    <td>
                        <c:out value="${article.enable}"/>
                    </td>
                    <td>
                        <c:out value="${article.weight}"/>
                    </td>
                    <td>
                        <c:out value="${Load.lang.get()}"/>
                    </td>
                    <td>
                        <c:out value="${article.translate_title.get(Load.lang.get())}"/>
                    </td>
                    <td>
                        <a class="btn btn-info" href="/comment/get/${article.id}/1">${Load.bundle.comment_view}</a>
                        <a class="btn btn-info" href="/article/update/${article.id}">${Load.bundle.article_edit}</a>
                        <span  class="article_del btn btn-warning"  article_id="${article.id}">
                            ${Load.bundle.article_del}</span>
                    </td>
                </tr>

            </c:forEach>
        </table>

    </jsp:attribute>

</t:template>






