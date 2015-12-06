<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
    <c:forEach items="${Data.comments}" var="comment">
        <li>
            <div>
                ${Load.bundle.comment_email}: ${comment.email}
            </div>
            ${comment.comment}
        </li>
    </c:forEach>
</ul>

