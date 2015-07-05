/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.tools.Navigator;
import blog.system.Model;

/**
 *
 * @author petroff
 */

public class Article extends Model {

    public Article() {
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
    public Article getData() {
        
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return this.navigator;
    }

}
