/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.ArticleModel;
import blog.model.CategoryModel;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import blog.system.tools.Http;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class ArticleController extends ControllerImpl<ArticleController> {

    @Override
    public void index() {
        ArticleModel AModel = new ArticleModel();
        AModel.init(request);
        super.request.setAttribute("Data", AModel.getData());
        super.getView("/article/article.jsp");
    }

    @Get
    public void create(blog.system.environment.Get get) throws ServletException, IOException {
        ArticleModel articleModel = (ArticleModel) Load.model.name("Article");
		articleModel.navigator.setViewProfile("/navigator/user.jsp", "article_create");
        CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");
        articleModel.setTree(categoryModel);
        Load.view.name("/article/save.jsp", articleModel);
    }

    @Post
    public void create(blog.system.environment.Post post) throws ServletException, IOException {
        ArticleModel articleModel = (ArticleModel) Load.model.name("Article");
		articleModel.navigator.setViewProfile("/navigator/user.jsp", "article_create");
        CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");
        articleModel.setTree(categoryModel);
        if (articleModel.create()) {
            Http.redirect("/main/done");
        } else {
            super.request.setAttribute("Data", articleModel);
            Load.view.name("/article/save.jsp");
        }

    }

    @Get
    public void update(blog.system.environment.Get get, String article_id) throws ServletException, IOException {
        Object[] arg = new Object[]{article_id};
        ArticleModel articleModel = (ArticleModel) Load.model.name("Article", arg);
        articleModel.findByPk(Integer.parseInt(article_id));
        CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");
        articleModel.setTree(categoryModel);
		articleModel.navigator.setViewProfile("/navigator/user.jsp", "");
        Load.view.name("/article/save.jsp", articleModel);
    }

    @Post
    public void update(blog.system.environment.Post post, String article_id) throws ServletException, IOException {
        Object[] arg = new Object[]{article_id};
        ArticleModel articleModel = (ArticleModel) Load.model.name("Article", arg);
        CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");
        articleModel.setTree(categoryModel);
		articleModel.navigator.setViewProfile("/navigator/user.jsp", "");
        if (articleModel.update(article_id)) {
            Http.redirect("/main/done");
        } else {
            super.request.setAttribute("Data", articleModel);
            Load.view.name("/article/save.jsp");
        }

    }

    public void list() {
        ArticleModel articleModel = (ArticleModel) Load.model.name("Article");
        articleModel.findAll();
		articleModel.navigator.setViewProfile("/navigator/user.jsp", "article_list");
        Load.view.name("/article/list.jsp", articleModel);
    }

    public void del(String article_id) throws IOException {
        ArticleModel articleModel = (ArticleModel) Load.model.name("Article");
        String str = articleModel.del(Integer.parseInt(article_id));
        Load.view.out(str);
    }
}
