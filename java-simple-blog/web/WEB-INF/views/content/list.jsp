<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.content}">

    <jsp:attribute name="head_area">
        <script type="text/javascript">
            $(document).ready(function () {
            });
        </script>
    </jsp:attribute>

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <h1>${Load.bundle.content} list</h1>
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
            <form action="/content/list/" method="post">
            <label class="control-label" for="content_name">${Load.bundle.content_lang}</label>
            <div class="controls">
                <input type="text" name="search" value=""/>
            </div>

            <div class="controls">
                <input type="submit" value="${Load.bundle.content_search}"/>
            </div>
        </form>
        <br/>
        <table class="table">
            <tr>
                <td>
                    ${Load.bundle.content_id}
                </td>
                <td>
                    ${Load.bundle.content_lang}
                </td>

                <td>
                    ${Load.bundle.content_type}
                </td>

                <td>
                    ${Load.bundle.content_text}
                </td>

                <td>
                    ${Load.bundle.content_action}
                </td>
            </tr>
            <c:forEach items="${Data.contents}" var="content">
                <tr id="content_${content.id}">
                    <td>
                        <c:out value="${content.id}"/>
                    </td>

                    <td>
                        <c:out value="${content.lang}"/>
                    </td>

                    <td>
                        <c:out value="${content.type}"/>
                    </td>

                    <td>
                        <c:set var="body" value="${content.text}"/>
                        <c:choose>
                            <c:when test="${body.length() > 200}">
                                ${body.substring(0,200)}...
                            </c:when>
                            <c:otherwise>
                                ${body}
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <a href="/content/update/${content.id}">${Load.bundle.content_edit}</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <div>
            <c:forEach begin="1" end="${Data.count}" var="val">
                <a href="/content/list/${val}"><c:out value="${val}"/></a>
            </c:forEach>

        </div>

    </jsp:attribute>

</t:template>






