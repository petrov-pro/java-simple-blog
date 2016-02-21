/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

import blog.system.loader.Load;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author petroff
 */
public class Navigator<T> {

    private String errorMessage;
    private String view;
    private T obj;

    private LinkedHashMap<String, Link> profileLinks;

    public Navigator() {
        profileLinks = new LinkedHashMap();

        Link link = new Link();
        link.setLink("/main/");
        link.setTitle(Load.bundle.getString("main"));
        profileLinks.put("main", link);

    }

    public HashMap<String, Link> getProfileLinks() {
        return profileLinks;
    }

    public void setProfileLinks(LinkedHashMap<String, Link> profile_links) {
        this.profileLinks = profile_links;
    }

    public String getView() {
        return Load.view.partial(view, this);
    }

    public void setViewProfile(String view, String activate) {

        Link link = new Link();
        link.setLink("/category/list/");
        link.setTitle(Load.bundle.getString("category_list"));
        profileLinks.put("category_list", link);

        link = new Link();
        link.setLink("/category/create/");
        link.setTitle(Load.bundle.getString("category_create"));
        profileLinks.put("category_create", link);

        //articel
        link = new Link();
        link.setLink("/article/list/");
        link.setTitle(Load.bundle.getString("article_list"));
        profileLinks.put("article_list", link);

        link = new Link();
        link.setLink("/article/create/");
        link.setTitle(Load.bundle.getString("article_create"));
        profileLinks.put("article_create", link);

        link = new Link();
        link.setLink("/content/list/1/null/");
        link.setTitle(Load.bundle.getString("content_list"));
        profileLinks.put("content_list", link);

        if (Load.auth.isAdmin()) {
            link = new Link();
            link.setLink("/admin/listuser/");
            link.setTitle(Load.bundle.getString("admin"));
            profileLinks.put("admin", link);
        }

        Link lActivate = profileLinks.get(activate);
        if (lActivate != null) {
            lActivate.setActivate(true);
        }
        this.view = view;

    }

    public void setViewMain(String view, String activate) {
        Link link = new Link();
        link.setLink("/main/categories/1/null/");
        link.setTitle(Load.bundle.getString("categories"));
        profileLinks.put("categories", link);

        link = new Link();
        link.setLink("/main/articles/1/null/");
        link.setTitle(Load.bundle.getString("articles"));
        profileLinks.put("articles", link);

        link = new Link();
        link.setLink("/main/users/1/null/");
        link.setTitle(Load.bundle.getString("users"));
        profileLinks.put("users", link);

        Link lActivate = profileLinks.get(activate);
        if (lActivate != null) {
            lActivate.setActivate(true);
        }

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
