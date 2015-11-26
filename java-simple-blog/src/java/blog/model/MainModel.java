/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.system.annotation.Get;
import blog.system.loader.Load;
import blog.system.tools.Navigator;
import blog.system.model.Model;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class MainModel extends Model {

	private String errorMessage = "";
	private CategoryModel categoryModel;
	private ArticleModel articleModel;

	public CategoryModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public ArticleModel getArticleModel() {
		return articleModel;
	}

	public void setArticleModel(ArticleModel articleModel) {
		this.articleModel = articleModel;
	}

	@Override
	public String getView() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public MainModel getData() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Navigator getNavigator() {
		return this.navigator;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void getCategoryArticle() {
		articleModel = (ArticleModel) Load.model.name("Article");
		categoryModel = (CategoryModel) Load.model.name("Category");
		categoryModel.findAllForMain();
		articleModel.findAllForMain();
	}

	public void getCategoryArticle(String user_id, String article_alias) {
		articleModel = (ArticleModel) Load.model.name("Article");
		categoryModel = (CategoryModel) Load.model.name("Category");
		articleModel.findByAliasUser(user_id, article_alias);
		categoryModel.findByPkFree(articleModel.getArticle().getCategory_id());
	}

	public void getArticleForTag(String user_id, String tag) {

	}

}
