<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>





<t:template title="${Data.bundle.nav_main}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

    <jsp:attribute name="head_area">

        <script>
			function hello() {
				alert("Hello World");
			}
        </script>
    </jsp:attribute>

    <jsp:attribute name="body_area">
${Load.bundle.getString("auth_password_i")}

        <div>
            <button onclick="hello();">${Data.article.getBody()}</button>
        </div>   
		<img src="/static/test.jpg"

		</jsp:attribute>

	</t:template>






