/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.model;

import blog.system.loader.Load;
import blog.system.tools.Navigator;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author petroff
 */
public abstract class Model implements ModelIntf {

    public Navigator navigator;

    public HttpServletRequest request;

    public ResourceBundle bundle;

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void init(HttpServletRequest r) {
        this.request = r;
        this.bundle = Load.bundle;
    }

    @Override
    public void init(Object[] params) {

    }

    public Model() {
    }

    @Override
    public boolean checkUnique(String name) {
        return false;
    }

}
