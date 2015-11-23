/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.dao.impl.TagImpl;
import blog.entity.Tag;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.tools.Navigator;
import blog.system.model.Model;
import blog.system.tools.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author petroff
 */
public class MainModel extends Model {

	private String errorMessage = "";

	@Override
	public String getView() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public MainModel getData() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Navigator getNavigator() {
		return this.navigator;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
