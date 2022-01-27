package progressive.qa.reporting;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Java_Logger {

	public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void getLog(String msg) {
		logger.log(Level.INFO, msg);
	}
}
