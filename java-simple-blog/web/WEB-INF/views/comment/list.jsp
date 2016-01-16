<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
    <c:forEach items="${CommentModel.comments}" var="comment">
        <li>
            <div>
                ${Load.bundle.comment_email}: ${comment.email}
            </div>
            ${comment.comment}
        </li>
    </c:forEach>
</ul>

<div>
	<c:forEach begin="1" end="${CommentModel.count}" var="val">
		<span class="link_comment" style="cursor:pointer;" ><c:out value="${val}"/></span>
	</c:forEach>

</div>

