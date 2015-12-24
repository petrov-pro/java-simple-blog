/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.system.loader.Load;
import blog.system.tools.Navigator;
import blog.system.model.Model;

/**
 *
 * @author petroff
 */
public class MainModel extends Model {

	private String errorMessage = "";
	private CategoryModel categoryModel;
	private ArticleModel articleModel;
	private TagModel tagModel;
	private CommentModel commentModel;

	public CommentModel getCommentModel() {
		return commentModel;
	}

	public void setCommentModel(CommentModel commentModel) {
		this.commentModel = commentModel;
	}

	public TagModel getTagModel() {
		return tagModel;
	}

	public void setTagModel(TagModel tagModel) {
		this.tagModel = tagModel;
	}

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
		commentModel = (CommentModel) Load.model.name("Comment");
		articleModel.findByAliasUser(user_id, article_alias);
		categoryModel.findByPkFree(articleModel.getArticle().getCategory_id());
	}

	public void getArticleForTag(String tag) {
		tagModel = (TagModel) Load.model.name("Tag");
		tagModel.findByName(tag);
	}

	public void getCategory(String user_id, String category_alias) {
		articleModel = (ArticleModel) Load.model.name("Article");
		categoryModel = (CategoryModel) Load.model.name("Category");
		commentModel = (CommentModel) Load.model.name("Comment");
		categoryModel.findByAlias(user_id, category_alias);
		articleModel.findAllForCategory(categoryModel.getCategory().getId());
	}

}
