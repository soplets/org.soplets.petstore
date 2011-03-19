package org.petstore.util;

import java.util.logging.Logger;

import org.petstore.soplets.SopExceptions;

public class PizzaLogger {

	private static Logger logger = Logger.getAnonymousLogger();
	
	public static void log(SopExceptions se, Exception ex) {
		
		ex.printStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append("Problem:  " + se.problem() + "\n");
		sb.append("Solution: " + se.solution() + "\n");
		sb.append(ex.getStackTrace().toString());
		logger.severe(sb.toString());
		
	}

}
