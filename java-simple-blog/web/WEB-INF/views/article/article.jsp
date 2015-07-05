<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="My page">
    
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
        <div>
            <button onclick="hello();">${Data.getView()}</button>
        </div>
    </jsp:attribute>

</t:template>