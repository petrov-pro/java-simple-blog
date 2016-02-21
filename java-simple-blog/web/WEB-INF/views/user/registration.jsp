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

        <form role="form" method="POST" action="/user/registration/">
            <div class="form-group">
                <label for="user_name">${Load.bundle.user_registration_user_name}:</label>
                <input type="input" class="form-control" id="user_name" name="user_name" value="${Data.user.user_name}"/>
            </div>

            <div class="form-group">
                <label for="email">${Load.bundle.user_registration_email}:</label>
                <input type="email" class="form-control" id="email" name="email" value="${Data.user.email}"/>
            </div>
            <div class="form-group">
                <label for="pwd">${Load.bundle.user_registration_password}:</label>
                <input type="password" name="password" class="form-control" id="pwd" value=""/>
            </div>

            <div class="form-group">
                <label for="pwd">${Load.bundle.user_registration_confirm}:</label>
                <input type="password" name="confirm" class="form-control" id="pwd" value=""/>
            </div>

            <button type="submit" class="btn btn-default">${Load.bundle.submit}</button>
        </form>

    </jsp:attribute>

</t:template>






