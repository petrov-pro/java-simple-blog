<%@tag description="Navigator" pageEncoding="UTF-8"%>

<%@tag import="blog.system.tools.Navigator" %>
<%@attribute name="navigator" required="true" type="blog.system.tools.Navigator"%>
<div id="navigator">
    ${navigator.getView()}    
</div>
