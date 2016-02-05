/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

//import blog.bind.CommentBind;
import blog.bind.CommentBind;
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

/**
 *
 * @author petroff
 */
public class CommentModel extends blog.system.model.Model {
	
	private String errorMessage = "";
	
	private Comment comment;
	
	private String url;
	
	private List<Comment> comments;
	
	private int count;
	
	private int articleId;
	
	public CommentModel() {
		comment = new Comment();
	}
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public void setArticleId(String articleId) {
		int t = Integer.parseInt(articleId);
		this.articleId = t;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
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
		return super.navigator;
	}
	
	public boolean create() {
		CommentBind.bind(comment);
		comment.setEnable(Load.config.isEnableComment);
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
				errorMessage = Load.bundle.getString("comment_done");
				return true;
			}
		} else {
			errorMessage = Comment.getErrorMessage();
			return false;
		}
	}
	
	public boolean update() {
		Comment comment_form = new Comment();
		CommentBind.bind(comment_form);
		CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
		try {
			comment = ci.findByPk(comment_form.getId());
			comment.setEnable(comment_form.isEnable());
		} catch (Exception p) {
			errorMessage = Load.bundle.getString("comment_cant_find_comment");
			return false;
		}
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if (Comment.validate2(comment, validator)) {
			
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
	
	public boolean findAll(String articleIdS, String pageS, boolean enable) {
		CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
		int article_id = Integer.parseInt(articleIdS);
		int page = Integer.parseInt(pageS);
		try {
			comments = ci.findAllByParams(article_id, page, enable);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}
		return true;
	}
	
	public int count(String articleIdS) {
		CommentImpl ci = (CommentImpl) DaoFactory.getDao("CommentImpl");
		int article_id = Integer.parseInt(articleIdS);
		try {
			count = ci.count(article_id);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}
		float count_t;
		count_t = (float) count / Load.config.limit;
		count = (int) Math.round(count_t);
		return count;
	}
	
}
