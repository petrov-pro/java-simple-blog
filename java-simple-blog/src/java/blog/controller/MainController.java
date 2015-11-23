/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.MainModel;
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
        MainModel mainModel = (MainModel) Load.model.name("Main");
        mainModel.getCategoryArticle();
        Load.view.name("/main/main.jsp", mainModel);
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

    public void done() {
        Load.view.name("/main/done.jsp");
    }

}
