<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <c:forEach items="${CommentModel.comments}" var="comment">
        <div class="container-fluid">
            <div class="page-header">
                <h4> ${Load.bundle.comment_email}: ${comment.email}</h4>      
            </div>

            <span>
                ${comment.comment} 
            </span>

        </div>
    </c:forEach>
</div>

<ul class="pagination">
    <c:forEach begin="1" end="${CommentModel.count}" var="val">
        <li>
            <span class="link_comment" style="cursor:pointer;" ><c:out value="${val}"/></span>
        </li>
    </c:forEach>

</ul>

