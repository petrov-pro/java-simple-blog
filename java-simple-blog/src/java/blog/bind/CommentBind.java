/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.bind;

import blog.entity.Comment;
import blog.system.loader.Load;
import blog.system.tools.BindParams;
import java.util.Map;

/**
 *
 * @author petroff
 */
public class CommentBind {

    public static Comment bind(Comment comment) {
        return parseForm(comment);
    }

    public static Comment bind(Comment comment, String comment_id) {
        comment.setId(Integer.parseInt(comment_id));
        return parseForm(comment);
    }

    private static Comment parseForm(Comment comment) {
        if (!Load.auth.isAuth()) {
            comment.setEmail(Load.request.getParameter("email"));
        }
        BindParams.bind(comment);
        return comment;
    }

}
