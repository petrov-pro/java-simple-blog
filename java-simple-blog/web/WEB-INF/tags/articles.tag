<%@tag description="Articles" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag import="java.util.List" %>
<%@attribute name="articles" required="true" type="java.util.List"%>
<div class="container-fluid" id="article">
    <div class="panel-group">
        <c:forEach items="${articles}" var="article">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>
                        <a href="/main/article/${article.user_id}/${article.alias}/">
                            ${article.translate_title.get(Load.lang.get())}
                        </a>
                    </h3>
                </div>


                <div class="panel-body">
                    <c:set var="body" value="${article.translate_body.get(Load.lang.get())}"/>
                    <c:choose>
                        <c:when test="${body.length() > Load.config.separete}">
                            ${body.substring(0,Load.config.separete)}...
                        </c:when>
                        <c:otherwise>
                            ${body}
                        </c:otherwise>
                    </c:choose>

                    <div>
                        <span>
                            <a href="/main/user/${article.userName}/">
                                ${article.userName}
                            </a>
                        </span>
                        <span>${article.ut}</span>
                    </div>

                </div>

                <c:if test="${article.tags.size() > 0}">
                    <div class="panel-footer">
                        <div id="tags">
                            <span>Tags:</span>
                            <span>
                                <c:forEach items="${article.tags}" var="tag">
                                    <a href="/main/tag/${tag.name}/">
                                        ${tag.name}
                                    </a>
                                </c:forEach>
                            </span>
                        </div>
                    </div>
                </c:if>
            </div>
            <br/>
        </c:forEach> 
    </div>
</div>
