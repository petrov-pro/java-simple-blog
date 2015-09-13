/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;

/**
 *
 * @author petroff
 */
public class MainController extends ControllerImpl<MainController> {

    @Override
    public void index() {
        Load.view.name("/main/main.jsp");
    }

}
