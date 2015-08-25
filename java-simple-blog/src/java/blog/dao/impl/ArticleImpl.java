/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
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
	public String queryFindAll() throws PersistException {
		return "SELECT * FROM blogj.article;";
	}

	@Override
	public void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
		try {
			statement.setInt(1, pid);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void prepareQuery(PreparedStatement statement, Article a) throws PersistException {
		try {
			//statement.setInt(1, pid);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public List<Article> parseResultSet(ResultSet rs) throws PersistException {
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
	public String queryFindByPk() throws PersistException {
		return "SELECT * FROM blogj.article WHERE id = ?";
	}

	@Override
	public String queryUpdate() throws PersistException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String queryInsert() throws PersistException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String queryDelete() throws PersistException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
