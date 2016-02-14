<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.content_update}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <form method="POST" action="/content/update/${Data.content.id}" class="form-horizontal">


            <div class="control-group">

                <label class="control-label" for="content_name">${Load.bundle.content_text}</label>
                <div class="controls">
                    <textarea name="text">
                        ${Data.content.text}
                    </textarea>
                </div>
                <label class="control-label" for="content_name">${Load.bundle.content_lang}</label>
                <div class="controls">
                    <span>${Data.content.lang}</sapn>
                </div>

                <div class="controls">
                    <input type="hidden" name="id" value="${Data.content.id}"/>
                    <input type="submit" value="${Load.bundle.content_update2}"/>
                </div>
            </div>
        </form>

    </jsp:attribute>

</t:template>






