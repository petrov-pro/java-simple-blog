/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.exception;

/**
 *
 * @author petroff
 */
public class Exception404 extends Exception {

    public Exception404() {
		System.out.println("er");
    }

    public Exception404(String message) {
        super(message);
    }

    public Exception404(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception404(Throwable cause) {
        super(cause);
    }

    public Exception404(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
