/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.bind.ContentBind;
import blog.dao.impl.ContentImpl;
import blog.entity.Content;
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

/**
 *
 * @author petroff
 */
public class ContentModel extends Model {

	private String errorMessage = "";

	private Content content;
	private List<Content> contents;

	public ContentModel() {
		content = new Content();
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@Override
	public String getView() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ContentModel getData() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Navigator getNavigator() {
		return this.navigator;
	}

	public boolean update(String contentId) {
		ContentBind.bind(content);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if (Content.validate(content, validator)) {
			ContentImpl i = (ContentImpl) DaoFactory.getDao("ContentImpl");
			boolean result;
			boolean resultTag = false;
			try {
				i.startTransaction();
				result = i.update(content);
			} catch (PersistException p) {
				Logger.write(p.toString());
				result = false;
			}
			if (!result) {
				errorMessage = Load.bundle.getString("content_cant_update");
				return false;
			}
			try {
				i.endTransaction();
			} catch (PersistException p) {
				Logger.write(p.toString());
				return false;
			}
			return true;

		} else {
			errorMessage = Content.getErrorMessage();
			return false;
		}
	}

	public void findAll() {
		ContentImpl i = (ContentImpl) DaoFactory.getDao("ContentImpl");
		try {
			contents = i.findAllForUser(Load.auth.getUserId());
		} catch (PersistException p) {
			Logger.write(p.toString());
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void findByPk(Integer contentId) {
		ContentImpl i = (ContentImpl) DaoFactory.getDao("ContentImpl");
		try {
			content = i.findByPk(contentId);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}

	}

}
