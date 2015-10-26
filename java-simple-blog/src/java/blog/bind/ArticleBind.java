/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.bind;

import blog.entity.Article;
import blog.system.loader.Load;
import blog.system.tools.BindParams;
import java.util.Map;

/**
 *
 * @author petroff
 */
public class ArticleBind {

    public static Article bind(Article article) {
        return parseForm(article);
    }

    public static Article bind(Article article, String article_id) {
        article.setId(Integer.parseInt(article_id));
        return parseForm(article);
    }

    private static Article parseForm(Article article) {
        Map<String, String> mp = BindParams.getParameterMap(Load.request, "article_title");
        for (Map.Entry<String, String> entry : mp.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            article.translate_title.put(key, value);
        }
        
        Map<String, String> mpb = BindParams.getParameterMap(Load.request, "article_body");
        for (Map.Entry<String, String> entry : mpb.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            article.translate_body.put(key, value);
        }
        BindParams.bind(article);
        return article;
    }

}
