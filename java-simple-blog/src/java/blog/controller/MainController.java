/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.annotation.Get;
import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
public class MainController extends ControllerImpl<MainController> {

	@Override
	public void index() {
		String lang = Load.session.get("lang");
		Load.view.name("/main/main.jsp");
	}

	@Get
	public void setlang(blog.system.environment.Get get, String lang) throws ServletException, IOException {
		String status;
		if (Arrays.asList(Load.config.langs).contains(lang)) {
			status = "ok";
			Load.request.getSession().setAttribute("lang", lang);

		} else {
			status = "error";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("status", status);
		String str = resultJson.toString();
		Load.view.out(str);
	}

}
