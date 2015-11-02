/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
import blog.entity.Content;
import blog.entity.Tag;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public void prepareQuery(PreparedStatement statement, Tag a) throws PersistException {
		try {

			statement.setInt(4, Load.auth.getUserId());
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
		return "INSERT blogj.tag (enable, alias, weight, user_id, ut) VALUE(?, ?, ?, ?, NOW());";
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
		Integer res;
		super.startTransaction();
		res = super.insert(tag);
		try {
			if (res != null) {
				tag.setId(res);
				if (!insertContent(tag)) {
					throw new PersistException("Can't insert content");
				}
			}
			super.endTransaction();
		} catch (PersistException p) {
			super.rollbackTransaction();
			throw p;
		}
		return res;
	}

	public boolean insertContent(Tag tag) throws PersistException {
		ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

		for (Map.Entry<String, String> entry : tag.getTranslate().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			Content content = new Content();
			content.setType(tag.getType() + "_t");
			content.setObject_id(tag.getId());
			content.setUser_id(Load.auth.getUserId());
			content.setLang(key);
			content.setText(value);
			Integer res = contentImpl.insert(content);
			if (res == null) {
				throw new PersistException("Can't insert content");
			}
		}

		return true;
	}

	@Override
	public Tag findByPk(int tag_id) throws PersistException {
		Tag tag = new Tag();
		String sql = "select a.*, co.* from blogj.tag a inner join blogj.content co "
				+ "ON a.id = co.object_id and (co.`type` = 'tag_t' or co.`type` = 'tag_b') and a.id = ? and a.user_id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, tag_id);
			statement.setInt(2, Load.auth.getUserId());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				tag.setId(rs.getInt("id"));
				String lang = rs.getString("lang");
				String text = rs.getString("text");
				tag.getTranslate().put(lang, text);

			}
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return tag;
	}

	@Override
	public boolean update(Tag tag) throws PersistException {
		boolean res;
		super.startTransaction();
		res = super.update(tag);
		try {
			if (res) {
				if (!updateContent(tag)) {
					throw new PersistException("Can't update content");
				}
			}
			super.endTransaction();
		} catch (PersistException p) {
			super.rollbackTransaction();
			throw p;
		}
		return res;
	}

	public boolean updateContent(Tag tag) throws PersistException {
		ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

		for (Map.Entry<String, String> entry : tag.getTranslate().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			Content content = new Content();
			content.setType(tag.getType() + "_t");
			content.setObject_id(tag.getId());
			content.setUser_id(Load.auth.getUserId());
			content.setLang(key);
			content.setText(value);
			boolean res = contentImpl.update(content);
			if (!res) {
				throw new PersistException("Can't update content");
			}
		}
		return true;
	}

	public int findByAlias(String alias, int tag_id) throws PersistException {
		int count = 0;
		String sql = "SELECT * FROM blogj.tag t  WHERE t.alias = ? AND t.user_id = ? and t.id != ?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, alias);
			statement.setInt(2, Load.auth.getUserId());
			statement.setInt(3, tag_id);
			ResultSet rs = statement.executeQuery();
			rs.last();
			count = rs.getRow();
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return count;
	}

	public List<Tag> findAllForUser(int userId) throws PersistException {

		List<Tag> tags = new ArrayList();
		String sql = "SELECT t.*, title.text as title, body.text as body, title.lang as lang FROM blogj.tag t  "
				+ "INNER JOIN blogj.content title "
				+ "ON t.id = title.object_id AND title.`type` = 'tag_t' and title.lang = ? "
				+ "INNER JOIN blogj.content body "
				+ "ON t.id = body.object_id AND body.`type` = 'tag_b' and body.lang = ? "
				+ "WHERE  user_id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, Load.lang.get());
			statement.setString(2, Load.lang.get());
			statement.setInt(3, userId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Tag tag = new Tag();
				tag.setId(rs.getInt("id"));
				String lang = rs.getString("lang");
				String text = rs.getString("title");
				tag.getTranslate().put(lang, text);
				tags.add(tag);
			}
			rs.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return tags;
	}

}
