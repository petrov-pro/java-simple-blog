<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.nav_main}">

    <jsp:attribute name="head_area">
        <script type="text/javascript">
            $(document).ready(function () {
                $('.category_del').click(function (obj) {
                    var category_id = $(this).attr('category_id');
                    $.post("/category/del/" + category_id, function (data) {
                        if (data.message === true) {
                            $("#category_" + category_id).hide();
                            console.log($("category_" + category_id));
                        } else {
                            show_message("Error", "${Load.bundle.category_cant_delete_category}", true);
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
        <h1>Catgeory list</h1>
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <table class="table">
            <tr>
                <td>
                    ${Load.bundle.category_id}
                </td>
                <td>
                    ${Load.bundle.category_alias}
                </td>
                <td>
                    ${Load.bundle.category_enable}
                </td>
                <td>
                    ${Load.bundle.category_weight}
                </td>
                <td>
                    ${Load.bundle.category_lang}
                </td>
                <td>
                    ${Load.bundle.category_text}
                </td>
                <td>
                    ${Load.bundle.category_operation}
                </td>
            </tr>
            <c:forEach items="${Data.categories}" var="category">
                <tr id="category_${category.id}">
                    <td>
                        <c:out value="${category.id}"/>
                    </td>
                    <td>
                        <c:out value="${category.alias}"/>
                    </td>
                    <td>
                        <c:out value="${category.enable}"/>
                    </td>
                    <td>
                        <c:out value="${category.weight}"/>
                    </td>
                    <td>
                        <c:out value="${Load.lang.get()}"/>
                    </td>
                    <td>
                        <c:out value="${category.translate.get(Load.lang.get())}"/>
                    </td>
                    <td>
                        <a href="/category/update/${category.id}">${Load.bundle.category_edit}</a>
                        <span class="category_del" category_id="${category.id}">${Load.bundle.category_del}</span>
                    </td>
                </tr>

            </c:forEach>
        </table>

    </jsp:attribute>

</t:template>






