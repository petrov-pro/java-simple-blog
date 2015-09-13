/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.dao;

import blog.system.exception.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author petroff
 */
public abstract class AbstractDaoImpl<T> implements DaoGeneric<T> {

	public Connection connection;

	@Override
	public T findByPk(int id) throws PersistException {
		List<T> list;
		String sql = queryFindByPk();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareQuery(statement, id);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() > 1) {
			throw new PersistException("Received more than one record.");
		}
		return list.iterator().next();
	}

	@Override
	public List<T> findAll() throws PersistException {
		List<T> list;
		String sql = queryFindAll();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {

			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean update(T entity) throws PersistException {
		String sql = queryUpdate();
		int rs;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareQuery(statement, entity);
			rs = statement.executeUpdate();
		} catch (Exception e) {
			throw new PersistException(e);
		}

		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int insert(T entity) throws PersistException {
		String sql = queryInsert();
		int insert_id;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareQuery(statement, entity);
			ResultSet rs = statement.executeQuery();
			insert_id = rs.getInt(1);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return insert_id;
	}

	@Override
	public boolean delete(int id) throws PersistException {
		String sql = queryDelete();
		int rs;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareQuery(statement, id);
			rs = statement.executeUpdate();
		} catch (Exception e) {
			throw new PersistException(e);
		}

		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}

}
