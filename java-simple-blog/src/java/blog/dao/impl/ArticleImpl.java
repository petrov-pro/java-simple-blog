/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.dao.AbstractDaoImpl;
import blog.entity.Article;
import blog.system.exception.PersistException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petroff
 */
public class ArticleImpl extends AbstractDaoImpl<Article> {

	@Override
	protected String queryFindAll() throws PersistException {
		return "SELECT * FROM blogj.article;";
	}

	@Override
	protected void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
		try {
			statement.setInt(1, pid);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected List<Article> parseResultSet(ResultSet rs) throws PersistException {
		List<Article> listArticles = new ArrayList();
		Article article = new Article();
		try {
			rs.next();
			article.setId(rs.getInt("id"));
			article.setTitle(rs.getString("title"));
			article.setBody(rs.getString("body"));
			article.setLang(rs.getString("lang"));
			listArticles.add(article);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return listArticles;
	}

	@Override
	protected String queryFindByPk() throws PersistException {
		return "SELECT * FROM blogj.article WHERE id = ?";
	}
}
