<!DOCTYPE html>
<%@tag description="Comment" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="user_name" required="true" type="String"%>
<%@attribute name="lang" required="true" type="String"%>
<%@attribute name="article_id" required="true" type="Integer"%>
<script>
    var article_id = ${article_id};
    $(document).ready(function () {
        get_comment(article_id, 1);
        $("#submit").click(function () {
            var data_send = $("#comment_form").serialize();
            $.post('/comment/create/', data_send, function (data) {

                try {
                    if (data.status) {
                        show_m(data.message, 'done');
                        $("#sticky_img").attr('src', $("#sticky_img").attr('src') + '?' + Math.random());//fix bug
                        $("#sticky_img").attr('src', $("#sticky_img").attr('src') + '?' + Math.random());
                        var form = $("#comment_form");
                        $("input[type=text]", form).val("");
                        $("textarea", form).val("");
                        reload();
                        get_comment(article_id, 1);
                    } else {
                        show_m(data.message, 'error');
                    }
                } catch (e) {
                    show_m(e.toString(), 'error');

                }
            });

        });
        $("#reload").click(function () {
            reload();
        });

        $("#message_container").on('click', ".link_comment", function () {
            get_comment(article_id, $(this).text());
        });
    });
    function get_comment(article_id, page) {
        $.post('/comment/list/', "&article_id=" + article_id + "&page=" + page, function (data) {

            try {
                if (data.status) {
                    $("#message_container").html(data.message);
                } else {
                    show_m(data.message, 'error');
                }
            } catch (e) {
                show_m(e.toString(), 'error');
            }
        });
    }

    function show_m(m, title) {
        if(title == 'error'){
            $("#m_b").attr('class', 'alert alert-warning');
        }else{
            $("#m_b").attr('class', 'alert alert-success');
        }
        $("#m_b").html(m);
        $("#m_b").show();
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

    <form role="form" method="post" action="" id="comment_form">

        <div class="form-group">
            <label for="comment">${Load.bundle.messages}:</label>
            <textarea class="form-control"  name="comment"></textarea>
        </div>

        <div class="form-group">
            <c:choose>
                <c:when test="${user_name != null && !user_name.isEmpty()}">
                    <label for="user_name">${Load.bundle.user_name}:</label>
                    ${user_name}  
                </c:when>
                <c:otherwise>

                    <label for="email">${Load.bundle.email}:</label>
                    <input name="email" type="email" class="form-control" id="email">
                </c:otherwise>
            </c:choose>

        </div>
        <div class="form-group">
            <label for="captcha">${Load.bundle.captcha}:</label>
            <img src="/stickyImg" id="sticky_img"/>
            <input class="form-control input-sm" type="captcha" id="captcha" name="captcha">
            <div class="btn btn-default btn-sm" id="reload" >${Load.bundle.reload}</div>

        </div>
        <div id="m_b" class="alert alert-info" style="display: none;">
        </div>
        <input type="hidden" name="article_id" value="${article_id}" id="article_id"/>
        <input  class="btn btn-default"  type="button" value="send" id="submit"/>


    </form>
</div>
