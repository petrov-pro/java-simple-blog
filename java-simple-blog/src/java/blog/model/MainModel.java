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
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

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

    public void getCategoryArticleForUser(String userName) {
        articleModel = (ArticleModel) Load.model.name("Article");
        categoryModel = (CategoryModel) Load.model.name("Category");
        userModel = (UserModel) Load.model.name("User");
        int userId = userModel.getUserByName(userName);
        categoryModel.getCategoriesForUser(userId);
        articleModel.findAllForUser(userId);

    }

    public void getCategories(String pageS, String search) {
        int page;
        try {
            page = Integer.parseInt(pageS);
        } catch (NumberFormatException nfe) {
            page = 1;
        }
        if (search == null || (search != null && search.equals("null"))) {
            search = "";
        }
        categoryModel = (CategoryModel) Load.model.name("Category");
        categoryModel.findAllCustom(page, search);
        if (search.isEmpty()) {
            categoryModel.setSearch("null");
        } else {
            categoryModel.setSearch(search);
        }
    }

    public void getArticles(String pageS, String search) {
        int page;
        try {
            page = Integer.parseInt(pageS);
        } catch (NumberFormatException nfe) {
            page = 1;
        }
        if (search == null || (search != null && search.equals("null"))) {
            search = "";
        }
        articleModel = (ArticleModel) Load.model.name("Article");
        articleModel.findAllCustom(page, search);
        if (search.isEmpty()) {
            articleModel.setSearch("null");
        } else {
            articleModel.setSearch(search);
        }
    }

    public void getUsers(String pageS, String search) {
        int page;
        try {
            page = Integer.parseInt(pageS);
        } catch (NumberFormatException nfe) {
            page = 1;
        }
        if (search == null || (search != null && search.equals("null"))) {
            search = "";
        }
        userModel = (UserModel) Load.model.name("User");
        userModel.findAllCustom(page, search);
        if (search.isEmpty()) {
            userModel.setSearch("null");
        } else {
            userModel.setSearch(search);
        }
    }

}
