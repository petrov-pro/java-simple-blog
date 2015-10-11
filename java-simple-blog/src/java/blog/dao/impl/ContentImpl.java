/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
import blog.entity.Content;
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
public class ContentImpl extends AbstractDaoImpl<Content> {

    @Override
    public String queryFindAll() throws PersistException {
        return "SELECT * FROM blogj.content;";
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
    public void prepareQuery(PreparedStatement statement, Content c) throws PersistException {
        try {
            statement.setString(1, c.getText());
            statement.setString(2, c.getLang());
            statement.setLong(3, c.getObject_id());
            statement.setString(4, c.getType());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQueryUpdate(PreparedStatement statement, Content c) throws PersistException {
        try {
            statement.setString(1, c.getText());
            statement.setString(2, c.getLang());
            statement.setInt(3, c.getObject_id());
            statement.setString(4, c.getType());
            statement.setInt(5, c.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<Content> parseResultSet(ResultSet rs) throws PersistException {
        List<Content> listContents = new ArrayList();
        Content content;
        try {
            while (rs.next()) {
                content = new Content();
//                content.setId(rs.getInt("id"));
//                content.setContent_name(rs.getString("content_name"));
//                content.setEmail(rs.getString("email"));
                listContents.add(content);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return listContents;
    }

    @Override
    public String queryFindByPk() throws PersistException {
        return "SELECT * FROM blogj.content WHERE id = ?";
    }

    @Override
    public String queryUpdate() throws PersistException {
        return "UPDATE blogj.content SET text = ?, lang = ?, object_id = ?, `type` = ? WHERE id = ?;";
    }

    @Override
    public String queryInsert() throws PersistException {
        return "INSERT blogj.content (text, lang, object_id, type) VALUE(?, ?, ?, ?);";
    }

    @Override
    public String queryDelete() throws PersistException {
        return "DELETE FROM blogj.content WHERE id = ?;";
    }

    public String queryFindByName() throws PersistException {
        return "";
    }
}
