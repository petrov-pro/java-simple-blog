/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.dao.DaoFactory;
import blog.dao.impl.ArticleImpl;
import blog.entity.Article;
import blog.tools.Navigator;
import blog.system.Model;

/**
 *
 * @author petroff
 */
public class ArticleModel extends Model {

	public ArticleModel() {
		Navigator nav = new Navigator();
		super.setNavigator(nav);
	}

	public String getArticle() {
		return "Article";
	}

	@Override
	public String getView() {
		return "/article/article";
	}

	@Override
	public ArticleModel getData() {
		ArticleImpl a = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
		try {
			Article article = a.findByPk(1);
		} catch (Exception e) {
		}
		

		return this;
	}

	@Override
	public Navigator getNavigator() {
		return this.navigator;
	}
}
