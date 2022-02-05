package progressive.qa.reporting;

public class Logger {

	public static void log(String msg) {
		Java_Logger.getLog(msg);
		TestNGLogger.getLog(msg);
	}
}
