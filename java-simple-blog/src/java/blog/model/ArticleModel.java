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
//test
public class ArticleModel extends Model {

    private Article article;

    public ArticleModel() {
        Navigator nav = new Navigator();
        super.setNavigator(nav);
    }

    public Article getArticle() {
        return article;
    }

    @Override
    public String getView() {
        return "/article/article.jsp";
    }

    @Override
    public ArticleModel getData() {
        ArticleImpl a = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        try {
            article = a.findByPk(1);
        } catch (Exception e) {
            System.out.print("Error");
        }

        return this;
    }

    @Override
    public Navigator getNavigator() {
        return this.navigator;
    }
}
