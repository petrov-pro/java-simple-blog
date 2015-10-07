/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.exception;

import blog.system.tools.Logger;

/**
 *
 * @author petroff
 */
public class Exception404 extends Exception {

    public Exception404() {
        Logger.write("404");
    }

    public Exception404(String message) {
        super(message);
        Logger.write(message);
    }

    public Exception404(String message, Throwable cause) {
        super(message, cause);
        Logger.write(message + " " + cause.toString());
    }

    public Exception404(Throwable cause) {
        super(cause);
        Logger.write(cause.toString());
    }

    public Exception404(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        Logger.write(message + " " + cause.toString());
    }

}
