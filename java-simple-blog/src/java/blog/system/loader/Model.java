/*
 * blog.system.Modelblog.systemo change this license header, choose License Headers in Project Properties.
 * blog.system.Modelo change this template file, choose blog.system.Modelools | blog.system.Modelemplates
 * and open the template in the editor.
 */
package blog.system.loader;

import blog.system.exception.Exception404;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petroff
 */
public class Model<T> {

	private final String modelPrefix = "Model";
	private static final String path = "blog.model.";
	private List<blog.system.model.Model> models = new ArrayList<blog.system.model.Model>();

	public blog.system.model.Model name(String name) {
		return this.loadModel(name, null);
	}

	public blog.system.model.Model name(String name, Object[] params) {
		return this.loadModel(name, params);
	}

	private blog.system.model.Model loadModel(String name, Object[] params) {
		int pos = models.indexOf(name);
		if (pos != -1) {
			return models.get(pos);
		} else {
			String class_name_path = path + name + modelPrefix;
			Class c;
			Object obj;
			try {
				c = Class.forName(class_name_path);
				try {
					obj = c.newInstance();
					blog.system.model.Model model;
					if (params == null) {
						model = (blog.system.model.Model) obj;
					} else {
						model = (blog.system.model.Model) obj;
						model.init(params);
					}
					models.add(model);
					return model;
				} catch (InstantiationException | IllegalAccessException ie) {
					new Exception404();
				}
			} catch (ClassNotFoundException cN) {
				new Exception404();
			}
			return null;

		}
	}

}
