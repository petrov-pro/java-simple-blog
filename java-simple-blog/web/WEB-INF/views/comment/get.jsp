<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="${Load.bundle.main_category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

    <jsp:attribute name="head_area">
        <script>
            $(document).ready(function () {

                $('.enable').change(function () {
                    var comment_id = $(this).attr('comment_id');
                    var check = $(this).is(":checked");
                    $.post('/comment/update/', "&id=" + comment_id + "&enable=" + check, function (data) {

                        try {
                            if (data.status) {
                                $("#message_container").html(data.message);
                            } else {
                                $("#dialog").html(data.message);
                                $("#dialog").dialog('open');
                            }
                        } catch (e) {
                            $("#dialog").html(e.toString());
                            $("#dialog").dialog('open');
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

        <c:forEach items="${Data.comments}" var="comment">
            <div class="container-fluid">
                <div class="page-header">
                    <h4> ${Load.bundle.comment_email}: ${comment.email}</h4>      
                </div>
                <div>
                    <c:choose>
                        <c:when test="${comment.enable}">
                            <c:set var="checked" scope="page">checked</c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="checked" scope="page"></c:set>
                        </c:otherwise>
                    </c:choose>
                    Enable: <input class="enable" name="visible" comment_id="${comment.id}" type="checkbox" <c:out value="${checked}"/> value="true"/>
                </div>
                <span>
                    ${comment.comment} 
                </span>

            </div>
        </c:forEach>

        <ul class="pagination">
            <c:forEach begin="1" end="${Data.count}" var="val">
                <li>
                    <a href="/comment/get/${Data.articleId}/${val}"><c:out value="${val}"/></a>
                </li>
            </c:forEach>

        </ul>

    </jsp:attribute>

</t:template>






