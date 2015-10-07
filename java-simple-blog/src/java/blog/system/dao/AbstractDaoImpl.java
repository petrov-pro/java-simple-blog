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
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author petroff
 */
public abstract class AbstractDaoImpl<T> implements DaoGeneric<T> {

    public Connection connection;

    public ResultSet res;

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
            prepareQueryUpdate(statement, entity);
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
    public Long insert(T entity) throws PersistException {
        String sql = queryInsert();
        int affectedRows;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareQuery(statement, entity);
            affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                statement.close();
                throw new PersistException("Creating user failed, no rows affected.");
            }

            try {
                res = statement.getResultSet();
            } catch (Exception e) {
                throw new PersistException(e);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    statement.close();
                    return generatedKeys.getLong(1);
                } else {
                    statement.close();
                    throw new PersistException("Creating user failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
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

    @Override
    public void startTransaction() throws PersistException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException s) {
            throw new PersistException(s);
        }
    }

    @Override
    public void endTransaction() throws PersistException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException s) {
            throw new PersistException(s);
        }

    }

    @Override
    public void rollbackTransaction() throws PersistException {
        try {
            connection.rollback();
        } catch (SQLException s) {
            throw new PersistException(s);
        }
    }

}
