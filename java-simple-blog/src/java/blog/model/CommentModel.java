/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

//import blog.bind.CommentBind;
import blog.dao.impl.CommentImpl;
import blog.entity.Comment;
import blog.system.dao.DaoFactory;
import blog.system.exception.Exception404;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.tools.Logger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
public class CommentModel extends blog.system.model.Model {

    private String errorMessage = "";

    private Comment comment;

    private String url;

    private List<Comment> comments;

    public CommentModel() {
        comment = new Comment();
    }

    @Override
    public void init(Object[] params) {
        super.init(params); //To change body of generated methods, choose Tools | Templates.       
        if (params.length > 0) {
            url = "/comment/update/" + params[0];
        } else {
            new Exception404("Miss id element");
        }
    }

    @Override
    public void init(HttpServletRequest r) {
        super.init(r); //To change body of generated methods, choose Tools | Templates.
        url = "/comment/create/";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Object getData() {
        return this;
    }

    @Override
    public String getView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getNavigator() {
        return null;
    }

    public CommentModel listComment() {
        CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
        try {
            comments = ci.findAll();
        } catch (PersistException p) {
            throw new RuntimeException(p);
        }
        return this;
    }

    public boolean create() {
        CommentBind.bind(comment);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        if (Comment.validate(comment, validator)) {
            CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
            Integer result;
            try {
                result = ci.insert(comment);
            } catch (PersistException p) {
                Logger.write(p.toString());
                result = null;
            }
            if (result == null) {
                errorMessage = Load.bundle.getString("comment_cant_insert");
                return false;
            } else {
                return true;
            }
        } else {
            errorMessage = Comment.getErrorMessage();
            return false;
        }
    }

    public boolean update(String comment_id) {
        CommentBind.bind(comment, comment_id);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        if (Comment.validate(comment, validator)) {
            CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
            boolean result;
            try {
                result = ci.update(comment);
            } catch (PersistException p) {
                Logger.write(p.toString());
                result = false;
            }
            if (!result) {
                errorMessage = Load.bundle.getString("comment_cant_update");
                return false;
            } else {
                return true;
            }
        } else {
            errorMessage = Comment.getErrorMessage();
            return false;
        }
    }

    public void findAll() {
        CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
        try {
            comments = ci.findAllForUser(Load.auth.getUserId());
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
    }

    public String del(int user_id) {
        Boolean message = false;
        JSONObject resultJson = new JSONObject();
        CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
        try {
            message = ci.delete(user_id);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
        resultJson.put("message", message);
        return resultJson.toString();
    }

    public void findAllForPk(Integer comment_id) {
        CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
        try {
            comment = ci.findAllForPk(comment_id);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

    }

    @Override
    public boolean checkUnique(String name) {
        CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
        try {
            if (comment != null) {
                int count = ci.findByAlias(comment.getAlias(), comment.getId());
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (PersistException p) {
            Logger.write(p.toString());
            return false;
        }

    }

}
