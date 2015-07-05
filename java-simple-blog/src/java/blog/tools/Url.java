/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.tools;

import blog.system.Model;

/**
 *
 * @author petroff
 */
public class Url {

    static public String normalizeUrl(String input) {
        if (!input.equals("")) {
            input = input.substring(0, 0) + input.substring(1);
            String output = "";//все слова с заглавной буквы.
            String[] words = input.split(" ");//разделяем на массив из слов
            for (String word : words) {
                String first = word.substring(0, 1).toUpperCase();
                String all = word.substring(1);
                output += first + all;

            }
            return output;
        } else {
            return "";
        }
    }
}
