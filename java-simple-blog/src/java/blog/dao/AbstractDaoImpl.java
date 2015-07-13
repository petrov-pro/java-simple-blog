/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

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

	private Connection connection;

	protected abstract String queryFindByPk() throws PersistException;

	protected abstract String queryFindAll() throws PersistException;

	protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

	protected abstract void prepareQuery(PreparedStatement statement, int pid) throws PersistException;

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
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}
}
