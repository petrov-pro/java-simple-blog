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
import org.apache.commons.lang3.StringUtils;

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

	public boolean update(String tags_str, int articleId, int userId) {
		List<String> items = Arrays.asList(tags_str.split(","));
		TagImpl i = (TagImpl) DaoFactory.getDao("TagImpl");
		try {
			i.dellByArticleUser(articleId, userId);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}
		for (String item : items) {
			if (!item.isEmpty()) {
				tag.setName(item);
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				if (Tag.validate(tag, validator)) {

					Tag find = null;
					try {
						find = i.findByName(tag.getName(), userId);
					} catch (PersistException p) {
						Logger.write(p.toString());
					}

					if (find == null) {
						int result = 0;
						try {
							result = i.insert(tag);
						} catch (PersistException p) {
							Logger.write(p.toString());
						}
						if (result == 0) {
							errorMessage = Load.bundle.getString("tag_cant_insert");
							return false;
						}
					} else {
						tag.setId(find.getId());
						tag.setUser_id(find.getUser_id());
					}

					int result_map = 0;
					try {
						result_map = i.insert_link(tag, articleId);
					} catch (PersistException p) {
						Logger.write(p.toString());
					}

				} else {
					errorMessage = Tag.getErrorMessage();
					return false;
				}
			}

		}

		return true;
	}

	public String getTagsStr(int userId, int articleId) {
		StringBuilder res = new StringBuilder();
		TagImpl i = (TagImpl) DaoFactory.getDao("TagImpl");
		List<Tag> tags = new ArrayList();
		try {
			tags = i.findByPkForUser(userId, articleId);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}

		for (Tag tag : tags) {
			res.append(tag.getName());
			res.append(",");
		}
		String resFinal = res.toString();
		if (resFinal.endsWith(",")) {
			resFinal = resFinal.substring(0, resFinal.length() - 1);
		}
		return resFinal;
	}

	public boolean dellByArticleUser(int articleId, int userId) {
		TagImpl i = (TagImpl) DaoFactory.getDao("TagImpl");
		try {
			return i.dellByArticleUser(articleId, userId);
		} catch (PersistException p) {
			Logger.write(p.toString());
			return false;
		}
	}

	public List<Tag> findByPkForUser(int userId, int articleId) {
		TagImpl i = (TagImpl) DaoFactory.getDao("TagImpl");
		List<Tag> tags = new ArrayList();
		try {
			tags = i.findByPkForUser(userId, articleId);
		} catch (PersistException p) {
			Logger.write(p.toString());
		}

		return tags;
	}

}
