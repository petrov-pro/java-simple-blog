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
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
public class TagModel extends Model {

	private String errorMessage = "";
	private String url = "";

	private Tag tag;
	private List<Tag> tags;

	public TagModel() {
		tag = new Tag();
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getView() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public TagModel getData() {
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

	public boolean update(String tags, int articleId) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if (Tag.validate(tag, validator)) {
			TagImpl i = (TagImpl) DaoFactory.getDao("TagImpl");
			boolean result;
			try {
				result = i.update(tag);
			} catch (PersistException p) {
				Logger.write(p.toString());
				result = false;
			}
			if (!result) {
				errorMessage = Load.bundle.getString("tag_cant_update");
				return false;
			} else {
				return true;
			}
		} else {
			errorMessage = Tag.getErrorMessage();
			return false;
		}
	}

}
