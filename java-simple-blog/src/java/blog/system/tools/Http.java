/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

import blog.system.loader.Load;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class Http {

    public static void redirect() throws ServletException, IOException {
        String url = Load.request.getHeader("referer");
        if (url == null) {
            url = Load.config.urlDefault;
        }

        String urlWithSessionID = Load.response.encodeRedirectURL(url);
        Load.response.sendRedirect(urlWithSessionID);
    }

    public static void redirect(String url) throws ServletException, IOException {
        if (url == null) {
            url = Load.config.urlDefault;
        }

        String urlWithSessionID = Load.response.encodeRedirectURL(url);
        Load.response.sendRedirect(urlWithSessionID);
    }

}
