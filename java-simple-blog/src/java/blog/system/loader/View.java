/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

import java.io.IOException;

/**
 *
 * @author petroff
 */
public class View<T> {

    public static final String basePath = "/WEB-INF/views/";

    public void name(String path) {
        try {
            Load.response.setContentType("text/html;charset=UTF-8");
            Load.request.getRequestDispatcher(basePath + path).forward(Load.request, Load.response);
        } catch (Exception e) {
            Load.errorPage.show404(e);
        }
    }

    public void name(String path, T obj) {
        try {
            Load.request.setAttribute("Data", obj);
            Load.response.setContentType("text/html;charset=UTF-8");
            Load.request.getRequestDispatcher(basePath + path).forward(Load.request, Load.response);
        } catch (Exception e) {
            Load.errorPage.show404(e);
        }
    }

    public void out(String str) throws IOException {
        Load.response.setContentType("application/json");
        Load.response.getWriter().write(str);
    }

}
