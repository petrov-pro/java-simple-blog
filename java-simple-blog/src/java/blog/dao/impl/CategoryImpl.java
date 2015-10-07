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
        return "SELECT * FROM blogj.category;";
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
        return "SELECT * FROM blogj.category WHERE id = ?";
    }

    @Override
    public String queryUpdate() throws PersistException {
        return "";
    }

    @Override
    public String queryInsert() throws PersistException {
        return "INSERT blogj.category (enable, alias, weight, user_id) VALUE(?, ?, ?, ?) RETURNING title_id;";
    }

    @Override
    public String queryDelete() throws PersistException {
        return "DELETE FROM blogj.category WHERE id = ?;";
    }

    @Override
    public Long insert(Category category) throws PersistException {
        Long res;
        super.startTransaction();
        res = super.insert(category);
        try {
            if (res != null) {
                if (!insertContent(category.getTitle_id())) {
                    throw new PersistException();
                }
            }
            super.endTransaction();
        } catch (PersistException p) {
            super.rollbackTransaction();
            return null;
        }
        return null;
    }

    public boolean insertContent(int id) {
        return false;
    }

}
