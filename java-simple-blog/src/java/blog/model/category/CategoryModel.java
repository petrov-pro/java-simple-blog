/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model.category;

import blog.dao.impl.CategoryImpl;
import blog.dao.impl.UserImpl;
import blog.entity.Category;
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
public class CategoryModel extends Model {

	private String errorMessage = "";

	private Category сategory;

	private List<Category> сategories;

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
		return null;
	}

	public CategoryModel listCategory() {
		CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
		try {
			сategories = ci.findAll();
		} catch (PersistException p) {
			throw new RuntimeException();
		}
		return this;
	}

}
