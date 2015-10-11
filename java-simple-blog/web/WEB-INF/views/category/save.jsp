<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="${Load.bundle.nav_main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>


    <jsp:attribute name="body_area">
        <c:if test="${!Data.errorMessage.isEmpty()}">
            <p>${Data.errorMessage}<p>
            </c:if>
        <form method="POST" action="/category/create/" class="form-horizontal">
            <div class="control-group">
                <label class="control-label" for="category_name">${Load.bundle.category_name}</label>

                <c:forEach items="${Load.config.langs}" var="item"> 
                    <div class="controls">
                        ${item}    <input type="text" name="category_name[${item}]" value="${Data.category.translate.get(item)}" />
                    </div>
                </c:forEach>
            </div>
            <div class="control-group">
                <label class="control-label" for="category_name">${Load.bundle.category_enable}</label>
                <div class="controls">
                    <c:choose>
                        <c:when test="${Data.category.enable}">
                            <c:set var="checked" scope="page">checked</c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="checked" scope="page"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="checkbox" name="enable" value="true"  <c:out value="${checked}"/> />
                </div>
                <label class="control-label" for="category_name">${Load.bundle.category_alias}</label>
                <div class="controls">
                    <input type="text" name="alias" value="${Data.category.alias}" />
                </div>
                <label class="control-label" for="category_name">${Load.bundle.category_weight}</label>
                <div class="controls">
                    <input type="text" name="weight" value="${Data.category.weight}" />
                </div>
                <div class="controls">
                    <input type="submit" value="${Load.bundle.user_registration_submit}"/>
                </div>
            </div>
        </form>

    </jsp:attribute>

</t:template>






