/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.entity.Comment;
import blog.entity.Content;
import blog.system.dao.AbstractDaoImpl;
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
public class CommentImpl extends AbstractDaoImpl<Comment> {

    private String sql_insert;

    @Override
    public String queryFindAll() throws PersistException {
        return "SELECT * FROM blogj.comment cat INNER JOIN blogj.content con"
                + " ON cat.id = con.object_id AND con.`type` = '" + Comment.getType() + "' AND lang = '"
                + Load.config.getDefaultLang() + "'";
    }

    @Override
    public void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
        try {
            statement.setString(1, Comment.getType());
            statement.setInt(2, pid);
            statement.setInt(3, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQuery(PreparedStatement statement, Comment c) throws PersistException {
        try {
            if (Load.auth.isAuth()) {
                statement.setBoolean(1, c.isEnable());
                statement.setInt(2, Load.auth.getUserId());
                statement.setInt(3, c.getArticleId());
            } else {
                statement.setBoolean(1, c.isEnable());
                statement.setString(2, c.getEmail());
                statement.setInt(3, c.getArticleId());

            }

        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQueryUpdate(PreparedStatement statement, Comment c) throws PersistException {
        try {
            statement.setBoolean(1, c.isEnable());
            statement.setInt(4, c.getId());
            statement.setInt(5, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<Comment> parseResultSet(ResultSet rs) throws PersistException {
        List<Comment> listCategories = new ArrayList();
        Comment comment;
        try {
            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setEnable(rs.getBoolean("enable"));

                listCategories.add(comment);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return listCategories;
    }

    @Override
    public String queryFindByPk() throws PersistException {
        return "SELECT * FROM blogj.comment cat INNER JOIN blogj.content con"
                + " ON cat.id = con.object_id AND con.`type` = ? AND cat.id = ? AND cat.user_id = ?;";
    }

    @Override
    public String queryUpdate() throws PersistException {
        return "UPDATE blogj.comment SET enable = ?, alias = ?, weight = ? WHERE id = ? AND user_id = ?";
    }

    @Override
    public String queryInsert() throws PersistException {
        return sql_insert;
    }

    @Override
    public String queryDelete() throws PersistException {
        return "DELETE c, con FROM blogj.comment c inner join blogj.content con ON c.id = con.object_id and con.`type` = ?  WHERE c.id = ? and c.user_id = ?;";
    }

    @Override
    public Integer insert(Comment comment) throws PersistException {
        Integer res;
        super.startTransaction();
        if (Load.auth.isAuth()) {
            this.sql_insert = "INSERT blogj.comment (enable, user_id, article_id, ut) VALUE(?, ?, ?, NOW());";
        } else {
            this.sql_insert = "INSERT blogj.comment (enable, email, article_id, ut) VALUE(?, ?, ?, NOW());";
        }
        res = super.insert(comment);
        try {
            if (res != null) {
                comment.setId(res);
                if (!insertContent(comment)) {
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

    public boolean insertContent(Comment comment) throws PersistException {
        ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");
        Content content = new Content();
        content.setType(Comment.getType());
        content.setObject_id(comment.getId());
        if (Load.auth.getUserId() == null) {
            content.setUser_id(0);
        } else {
            content.setUser_id(Load.auth.getUserId());

        }
        content.setLang(Load.lang.get());
        content.setText(comment.getComment());
        Integer res = contentImpl.insert(content);
        if (res == null) {
            throw new PersistException("Can't insert content");
        }
        return true;
    }

    public List<Comment> findAllByParams(int article_id, int page) throws PersistException {

        List<Comment> comments = new ArrayList();
        String sql = "SELECT com.*, con.*, u.email as email_u FROM blogj.comment com INNER JOIN blogj.content con "
                + " ON com.id = con.object_id  AND com.enable = true AND con.`type` = ? AND lang = ? AND article_id = ? "
                + " LEFT JOIN blogj.users u ON u.id = com.user_id "
                + "ORDER BY com.ut DESC LIMIT ? OFFSET ? ;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Comment.getType());
            statement.setString(2, Load.lang.get());
            statement.setInt(3, article_id);
            statement.setInt(4, Load.config.limit);
            statement.setInt(5, page);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setEnable(rs.getBoolean("enable"));
                comment.setArticleId(rs.getInt("article_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setComment(rs.getString("text"));
                comment.setUt(rs.getString("ut"));
                String email = rs.getString("email");
                String email_u = rs.getString("email_u");
                if (comment.getUserId() > 0) {
                    comment.setEmail(email_u);
                } else {
                    comment.setEmail(email);
                }
                comments.add(comment);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return comments;
    }

    @Override
    public boolean update(Comment comment) throws PersistException {
        boolean res;
        super.startTransaction();
        res = super.update(comment);
        try {
            if (res) {
                if (!updateContent(comment)) {
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

    public boolean updateContent(Comment comment) throws PersistException {
        ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

        Content content = new Content();
        content.setType(comment.getType());
        content.setObject_id(comment.getId());
        content.setUser_id(Load.auth.getUserId());
        boolean res = contentImpl.update(content);
        if (!res) {
            throw new PersistException("Can't update content");
        }

        return true;
    }

}
