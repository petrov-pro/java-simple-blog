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
            </tr>
            <c:forEach items="${Data.contents}" var="content">
                <tr id="content_${content.id}">
                    <td>
                        <c:out value="${content.id}"/>
                    </td>
                    
                    <td>
                        <a href="/content/update/${content.id}">${Load.bundle.content_edit}</a>
                    </td>
                </tr>

            </c:forEach>
        </table>

    </jsp:attribute>

</t:template>






