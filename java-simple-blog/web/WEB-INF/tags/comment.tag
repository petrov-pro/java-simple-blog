<!DOCTYPE html>
<%@tag description="Comment" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="user_name" required="true" type="String"%>
<%@attribute name="lang" required="true" type="String"%>
<%@attribute name="article_id" required="true" type="Integer"%>
<script>
	var article_id = ${article_id};
	$(document).ready(function () {
		$("#dialog").dialog({autoOpen: false});
		get_comment(article_id, 1);
		$("#submit").click(function () {
			var data_send = $("#comment_form").serialize();
			$.post('/comment/create/', data_send, function (data) {

				try {
					if (data.status) {
						$("#dialog").html(data.message);
						$("#dialog").dialog('open');
						$("#sticky_img").attr('src', $("#sticky_img").attr('src') + '?' + Math.random());//fix bug
						$("#sticky_img").attr('src', $("#sticky_img").attr('src') + '?' + Math.random());
						var form = $("#comment_form");
						$("input[type=text]", form).val("");
						$("textarea", form).val("");
						reload();
						get_comment(article_id, 1);
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
		$("#reload").click(function () {
			reload();
		});

		$("div").on('click', ".link_comment", function () {
			get_comment(article_id, $(this).text());
		});
	});
	function get_comment(article_id, page) {
		$.post('/comment/list/', "&article_id=" + article_id + "&page=" + page, function (data) {

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
	}

	function reload() {
		$.post('/comment/reload/', '', function (data) {
			$("#sticky_img").attr('src', $("#sticky_img").attr('src') + '?' + Math.random());//fix bug
			$("#sticky_img").attr('src', $("#sticky_img").attr('src') + '?' + Math.random());
		});
	}
</script>
<div id="comment_container">
	<div id="message_container"></div>
	<div id="dialog" title="Basic dialog" style="display: none;"></div>
	<form method="post" action="" id="comment_form">
		<ul>
			<li>
				<label>Message</label>
				<textarea name="comment"></textarea>
			</li>
			<c:choose>
				<c:when test="${user_name != null && !user_name.isEmpty()}">
					<li>
						<label>User name</label>
						${user_name}  
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<label>Email</label>
						<input type="text" name="email" />
					</li>
				</c:otherwise>
			</c:choose>

			<li>
				<label>Captcha</label>
				<img src="/stickyImg" id="sticky_img"/>
				<input type="text" name="captcha" />
				<div id="reload" style="cursor: pointer;">Reload</div>
			</li>
			<li>
				<input type="hidden" name="article_id" value="${article_id}" id="article_id"/>
				<input  type="button" value="send" id="submit"/>
			</li>
		</ul>

	</form>
</div>
