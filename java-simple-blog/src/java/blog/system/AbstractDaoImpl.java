/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system;

import blog.system.exception.PersistException;
import blog.system.intf.DaoGeneric;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author petroff
 */
public abstract class AbstractDaoImpl<T> implements DaoGeneric<T> {

    public abstract String queryFindByPk();

    public abstract T parseResultSet(ResultSet rs);

    public abstract T prepareQuery(PreparedStatement statement, int pid);

    private Connection connection;

    @Override
    public T findByPk(int id) throws PersistException {
        T obj;
        String sql = queryFindByPk();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareQuery(statement, id);
            ResultSet rs = statement.executeQuery();
            obj = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (obj == null) {
            return null;
        }
        return obj;
    }

}
