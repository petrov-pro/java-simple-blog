/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

import blog.system.loader.Load;
import java.util.HashMap;

/**
 *
 * @author petroff
 */
public class Navigator<T> {

    private String errorMessage;
    private String view;
    private T obj;

    private HashMap<String, Link> profile_links;

    public Navigator() {
        profile_links = new HashMap();

        Link link = new Link();
        link.setLink("/main/");
        link.setTitle(Load.bundle.getString("main"));
        profile_links.put("main", link);

    }

    public String getView() {
        return Load.view.partial(view, this.obj);
    }

    public void setViewProfile(String view) {

        Link link = new Link();
        link.setLink("/category/list/");
        link.setTitle(Load.bundle.getString("category_list"));
        profile_links.put("category_list", link);

        link = new Link();
        link.setLink("/category/create/");
        link.setTitle(Load.bundle.getString("category_create"));
        profile_links.put("category_create", link);

        this.obj = (T) this;
        this.view = view;
    }

    public void setView(String view) {
        this.obj = null;
        this.view = view;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
