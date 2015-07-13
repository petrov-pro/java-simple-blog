/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.system.exception.PersistException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author petroff
 */
public interface DaoGeneric<T> {

    public T findByPk(int id) throws PersistException;

    public List<T> findAll() throws PersistException;

    public void setConnection(Connection connection);
}
