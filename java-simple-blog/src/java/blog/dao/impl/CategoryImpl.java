/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.entity.Category;
import blog.system.dao.AbstractDaoImpl;
import blog.system.exception.PersistException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petroff
 */
public class CategoryImpl extends AbstractDaoImpl<Category> {

	@Override
	public String queryFindAll() throws PersistException {
		return "SELECT * FROM blogj.users;";
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
	public void prepareQuery(PreparedStatement statement, Category c) throws PersistException {
		try {
//			statement.setString(1, c.getUser_name());
//			statement.setString(2, c.getEmail());
//
//			statement.setString(3, u.getPasswordHash());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void prepareQueryUpdate(PreparedStatement statement, Category c) throws PersistException {
		try {

		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public List<Category> parseResultSet(ResultSet rs) throws PersistException {
		List<Category> listCategories = new ArrayList();
		Category category;
		try {
			while (rs.next()) {
				category = new Category();

				listCategories.add(category);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return listCategories;
	}

	@Override
	public String queryFindByPk() throws PersistException {
		return "SELECT * FROM blogj.users WHERE id = ?";
	}

	@Override
	public String queryUpdate() throws PersistException {
		return "";
	}

	@Override
	public String queryInsert() throws PersistException {
		return "INSERT blogj.users (user_name, email, password) VALUE(?, ?, ?);";
	}

	@Override
	public String queryDelete() throws PersistException {
		return "DELETE FROM blogj.users WHERE id = ?;";
	}

}
