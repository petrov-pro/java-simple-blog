<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:template title="${Load.bundle.main_category}">

    <jsp:attribute name="navigator_area">
        <t:navigator navigator="${Data.getNavigator()}"/> 
    </jsp:attribute>

	<jsp:attribute name="head_area">
		<script>
			$(document).ready(function () {

				$('.enable').change(function () {
					var comment_id = $(this).attr('comment_id');
					var check = $(this).is(":checked");
					$.post('/comment/update/', "&id=" + comment_id + "&enable=" + check, function (data) {

						try {
							if (data.status) {
								$("#message_container").html(data.message);
							} else {
								$("#dialog").html(data.message);
								$("#dialog").dialog('open');
							}
						} catch (e) {
							$("#dialog").html(e.toString());
							$("#dialog").dialog('open');
						}
					});
				});
			});
		</script>
	</jsp:attribute>


    <jsp:attribute name="body_area">
		<ul>
			<c:forEach items="${Data.comments}" var="comment">
				<li>
					<div>
						${Load.bundle.comment_email}: ${comment.email}
						<span>
							<c:choose>
								<c:when test="${comment.enable}">
									<c:set var="checked" scope="page">checked</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="checked" scope="page"></c:set>
								</c:otherwise>
							</c:choose>
							Enable: <input class="enable" name="visible" comment_id="${comment.id}" type="checkbox" <c:out value="${checked}"/> value="true"/>
						</span>
					</div>
					${comment.comment}
				</li>
			</c:forEach>
		</ul>

		<div>
			<c:forEach begin="1" end="${Data.count}" var="val">
				<a href="/comment/get/${Data.articleId}/${val}"><c:out value="${val}"/></a>
			</c:forEach>

		</div>

    </jsp:attribute>

</t:template>






