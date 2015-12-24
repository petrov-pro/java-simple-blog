/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

/**
 *
 * @author petroff
 */
public class Config {

    public final String urlDefault = "/main/";
    public final String userGroup = "USERS";
    public final String userDecriptor = "default";
    public final String defaultLang = "en";
    public final int limit = 2;

    public String getDefaultLang() {
        return defaultLang;
    }

    public String[] langs = {"en", "ru"};

    public String[] getLangs() {
        return langs;
    }

    public void setLangs(String[] langs) {
        this.langs = langs;
    }

}
