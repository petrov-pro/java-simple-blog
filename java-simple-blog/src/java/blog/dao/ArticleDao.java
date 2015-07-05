/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.entity.Article;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author petroff
 */
public interface ArticleDao {

    public Article create();

    public Article read(int key);

    public void update(Article article);

    public void delete(Article article);

    public List<Article> getAll() throws SQLException;

}
