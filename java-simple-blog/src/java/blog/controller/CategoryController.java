/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import blog.system.tools.Http;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class CategoryController extends ControllerImpl<CategoryController> {

    @Override
    public void index() throws ServletException, IOException {
        Http.redirect();
    }
    
    public void create(){
        Load.view.name("/category/create.jsp");
        
    }

}
