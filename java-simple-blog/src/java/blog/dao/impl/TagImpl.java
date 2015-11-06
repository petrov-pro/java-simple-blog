/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
import blog.entity.Tag;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petroff
 */
public class TagImpl extends AbstractDaoImpl<Tag> {
	
	@Override
	public String queryFindAll() throws PersistException {
		return "SELECT * FROM blogj.tag;";
	}
	
	@Override
	public void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
		try {
			statement.setInt(1, pid);
			statement.setInt(2, Load.auth.getUserId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}
	
	@Override
	public void prepareQuery(PreparedStatement statement, Tag t) throws PersistException {
		try {
			statement.setString(1, t.getName());
			statement.setInt(2, Load.auth.getUserId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}
	
	@Override
	public List<Tag> parseResultSet(ResultSet rs) throws PersistException {
		List<Tag> listTags = new ArrayList();
		Tag tag = new Tag();
		try {
			rs.next();
			
			listTags.add(tag);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return listTags;
	}
	
	@Override
	public String queryFindByPk() throws PersistException {
		return "SELECT * FROM blogj.tag WHERE id = ?";
	}
	
	@Override
	public String queryUpdate() throws PersistException {
		return "UPDATE blogj.tag SET enable = ?, alias = ?, weight = ? WHERE id = ? AND user_id = ?";
	}
	
	@Override
	public String queryInsert() throws PersistException {
		return "INSERT blogj.tag (name, user_id) VALUE(?, ?);";
	}
	
	@Override
	public String queryDelete() throws PersistException {
		return "DELETE c, con FROM blogj.category c inner join blogj.content con ON c.id = con.object_id and (con.`type` = 'tag_t' or con.`type` = 'tag_b')  WHERE c.id = ? and c.user_id = ?;";
	}
	
	@Override
	public void prepareQueryUpdate(PreparedStatement statement, Tag entity) throws PersistException {
		try {
			statement.setInt(4, entity.getId());
			statement.setInt(5, Load.auth.getUserId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}
	
	@Override
	public Integer insert(Tag tag) throws PersistException {
		Integer res = 0;
		res = super.insert(tag);
		if (res != null) {
			tag.setId(res);
		}
		return res;
	}
	
	@Override
	public boolean update(Tag tag) throws PersistException {
		boolean res = false;
		res = super.update(tag);
		return res;
	}
	
	public Tag findByName(String tagName) throws PersistException {
		Tag tag = new Tag();
		String sql = "select a.*, co.* from blogj.tag a  where  a.id = ? and a.user_id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, tagName);
			statement.setInt(2, Load.auth.getUserId());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				tag.setId(rs.getInt("id"));
				String text = rs.getString("text");
				tag.setName(text);
			}
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return tag;
	}
	
	public Integer insert_link(Tag tag, int articleId) throws PersistException {
		String sql = "INSERT blogj.article_tag_link (article_id, tag_id) VALUE(?, ?);";
		int affectedRows;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, articleId);
			statement.setInt(2, tag.getId());
			affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				statement.close();
				throw new PersistException("Insert failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					Integer last_id = generatedKeys.getInt(1);
					statement.close();
					return last_id;
				} else {
					statement.close();
					throw new PersistException("Get result failed, no ID obtained.");
				}
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}
	
	public boolean dellByArticleUser(int articleId, int userId) throws PersistException {
		String sql = "DELETE FROM blogj.article_tag_link WHERE article_id = (SELECT id FROM article WHERE id = ? AND user_id = ?);";
		int rs;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, articleId);
			statement.setInt(2, userId);
			rs = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		
		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Tag> findByPkForUser(int userId, int articleId) throws PersistException {
		List<Tag> tags = new ArrayList();
		String sql = "select a.* from blogj.tag a  where  a.id = ? and a.user_id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, articleId);
			statement.setInt(2, userId);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag();
				tag.setId(rs.getInt("id"));
				tag.setName(rs.getString("name"));
				tag.setUser_id(rs.getInt("user_id"));
				tags.add(tag);
			}
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return tags;
	}
	
}
