/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

/**
 *
 * @author petroff
 */
public class Logger {

	private static String level = "Info";

	public static boolean write(String msg) {
		return writeToLog(level, msg);
	}

	public static boolean write(String level, String msg) {
		return writeToLog(level, msg);
	}

	private static boolean writeToLog(String level, String msg) {
		System.err.println(level + " " + msg);
		return true;
	}

}
