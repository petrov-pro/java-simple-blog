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
public interface DaoGeneric<T> {

    public T findByPk(int id) throws PersistException;

    public List<T> findAll() throws PersistException;

    public void setConnection(Connection connection);

    public boolean update(T entity) throws PersistException;

    public Integer insert(T entity) throws PersistException;

    public boolean delete(int Id) throws PersistException;

    public String queryFindByPk() throws PersistException;

    public String queryFindAll() throws PersistException;

    public String queryUpdate() throws PersistException;

    public String queryInsert() throws PersistException;

    public String queryDelete() throws PersistException;

    public List<T> parseResultSet(ResultSet rs) throws PersistException;

    public void prepareQuery(PreparedStatement statement, int pid) throws PersistException;

    public void prepareQuery(PreparedStatement statement, T entity) throws PersistException;

    public void prepareQueryUpdate(PreparedStatement statement, T entity) throws PersistException;

    public void startTransaction() throws PersistException;

    public void endTransaction() throws PersistException;

    public void rollbackTransaction() throws PersistException;
}
