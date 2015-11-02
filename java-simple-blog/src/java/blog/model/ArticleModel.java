/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.bind.ArticleBind;
import blog.dao.impl.ArticleImpl;
import blog.entity.Article;
import blog.entity.Tag;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.tools.Navigator;
import blog.system.model.Model;
import blog.system.tools.Logger;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
//test
public class ArticleModel extends Model {

	private String errorMessage = "";
	private String url = "/article/create/";

	private Article article;
	private List<Article> articles;

	private Tag tag;

	public ArticleModel() {
		article = new Article();
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article getArticle() {
		return article;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getView() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArticleModel getData() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Navigator getNavigator() {
		return this.navigator;
	}

	public boolean update(String article_id) {
		url = "/article/update/" + article_id;
		ArticleBind.bind(article, article_id);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if (Article.validate(article, validator)) {
			ArticleImpl i = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
			boolean result;
			try {
				result = i.update(article);
			} catch (PersistException p) {
				Logger.write(p.toString());
				result = false;
			}
			if (!result) {
				errorMessage = Load.bundle.getString("article_cant_update");
				return false;
			} else {
				return true;
			}
		} else {
			errorMessage = Article.getErrorMessage();
			return false;
		}
	}

	public void findAll() {
		ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
		try {
			articles = ai.findAllForUser(Load.auth.getUserId());
		} catch (PersistException p) {
			Logger.write(p.toString());
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean create() {
		ArticleBind.bind(article);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if (Article.validate(article, validator)) {
			ArticleImpl ci = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
			Integer result;
			try {
				result = ci.insert(article);
			} catch (PersistException p) {
				Logger.write(p.toString());
				result = null;
			}
			if (result == null) {
				errorMessage = Load.bundle.getString("article_cant_insert");
				return false;
			} else {
				return true;
			}
		} else {
			errorMessage = Article.getErrorMessage();
			return false;
		}
	}

	public void findByPk(Integer article_id) {
		url = "/article/update/" + article_id;
		ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
		try {
			article = ai.findByPk(article_id);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}

	}

	@Override
	public boolean checkUnique(String name) {
		ArticleImpl i = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
		try {
			if (article != null) {
				int count = i.findByAlias(article.getAlias(), article.getId());
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

	public String del(int user_id) {
		Boolean message = false;
		JSONObject resultJson = new JSONObject();
		ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
		try {
			message = ai.delete(user_id);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}
		resultJson.put("message", message);
		return resultJson.toString();
	}
}
