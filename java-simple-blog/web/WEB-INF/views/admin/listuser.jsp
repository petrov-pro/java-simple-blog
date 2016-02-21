<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template_admin title="${Load.bundle.nav_main}">

    <jsp:attribute name="head_area">
        <script type="text/javascript">
            $(document).ready(function () {
                $('.user_del').click(function (obj) {
                    if (!confirm("${Load.bundle.del}")) {
                        return false;
                    }
                    var user_id = $(this).attr('user_id');
                    $.post("/admin/userdel/" + user_id, function (data) {
                        if (data.message === true) {
                            $("#user_" + user_id).hide();
                            console.log($("user_" + user_id));
                        } else {
                            show_message("Error", "${Load.bundle.admin_cant_delete_user}", true);
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
        <h1>Users</h1>
        <table class="table">
            <tr>
                <td>
                    <strong>
                        ${Load.bundle.admin_user_list_id}  
                    </strong>

                </td>
                <td>
                    <strong>
                        ${Load.bundle.admin_user_name}  
                    </strong>

                </td>
                <td>
                    <strong>
                        ${Load.bundle.admin_user_email} 
                    </strong>

                </td>
                <td>
                    <strong>
                        ${Load.bundle.admin_operations}
                    </strong>

                </td>
            </tr>
            <c:forEach items="${Data.users}" var="user">
                <tr id="user_${user.id}">
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.user_name}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <span user_id="${user.id}" style="cursor:pointer;" class="user_del">Del</span>
                    </td>
                </tr>

            </c:forEach>
        </table>

    </jsp:attribute>

</t:template_admin>







