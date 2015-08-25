/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.entity.Article;
import blog.model.article.ArticleModel;
import blog.system.controller.ControllerImpl;

/**
 *
 * @author petroff
 */
public class ArticleController extends ControllerImpl<ArticleController> {

	public void test() {
		super.getView("/article/article.jsp");
	}

	@Override
	public ArticleController index() {
		ArticleModel AModel = new ArticleModel();
		super.request.setAttribute("Data", AModel.getData());
		super.getView("/article/article.jsp");
		return this;
	}
}
