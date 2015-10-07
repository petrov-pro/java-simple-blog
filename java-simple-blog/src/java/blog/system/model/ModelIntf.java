/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.model;

import blog.model.AuthModel;
import blog.system.tools.Navigator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author petroff
 */
public interface ModelIntf<T> {

    public String getView();

    public T getData();

    public void setNavigator(Navigator navigator);

    public T getNavigator();

    public void init(HttpServletRequest r);

    public void init(Object[] params);

    public boolean checkUnique(String name);

}
