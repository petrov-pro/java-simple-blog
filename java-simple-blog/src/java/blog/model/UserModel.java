/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.dao.impl.UserImpl;
import blog.entity.User;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.model.Model;
import blog.system.tools.BindParams;
import blog.system.tools.Logger;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author petroff
 */
//test
public class UserModel extends Model {

	private String errorMessage = "";

	private User user;

	public UserModel() {
		user = new User();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return null;
	}

	public boolean create() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		BindParams.bind(user);
		if (User.validate(user, validator)) {
			UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");
			Integer result;
			try {
				result = ui.insertAdv(user);
			} catch (PersistException p) {
				Logger.write(p.toString());
				result = null;
			}
			if (result == null) {
				errorMessage = Load.bundle.getString("user_cant_insert");
				return false;
			} else {
				return true;
			}
		} else {
			errorMessage = User.getErrorMessage();
			return false;
		}
	}

	public boolean update(int user_id) {
		this.getUserInfo(user_id);
		user.setEmail(Load.request.getParameter("email"));
		user.setPassword(Load.request.getParameter("password"));
		UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");
		Boolean result;
		try {
			if (user.getPassword().isEmpty()) {
				ui.setStrUpdateWithoutPass();
			} else {
				ui.setStrUpdateWithPass();
			}
			result = ui.update(user);
		} catch (PersistException p) {
			result = false;
		}
		if (result == false) {
			errorMessage = Load.bundle.getString("user_cant_update");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkUnique(String name) {
		UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");
		User u = ui.findByUserName(name);
		if (u == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getUserInfo(int user_id) {
		UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");
		try {
			user = ui.findByPk(user_id);
		} catch (PersistException p) {
			throw new RuntimeException();
		}
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public int getUserByName(String name) {
		UserImpl ui = (UserImpl) DaoFactory.getDao("UserImpl");

		user = ui.findByUserName(name);
		if (user != null) {
			return user.getId();
		} else {
			return 0;
		}

	}

}
