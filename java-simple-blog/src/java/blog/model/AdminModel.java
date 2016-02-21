/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.dao.impl.UserImpl;
import blog.entity.User;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.model.Model;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
//test
public class AdminModel extends Model {

    private String errorMessage = "";

    private User user;

    private List<User> users;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Object getData() {
        return this;
    }

    @Override
    public String getView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getNavigator() {
        return super.navigator;
    }

    public AdminModel listUser() {
        UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");
        try {
            users = ui.findAll();
        } catch (PersistException p) {
            throw new RuntimeException();
        }
        return this;
    }

    public String userDel(int user_id) throws PersistException {
        Boolean message;
        JSONObject resultJson = new JSONObject();
        UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");

        message = ui.delete(user_id);
        resultJson.put("message", message);
        return resultJson.toString();
    }

}
