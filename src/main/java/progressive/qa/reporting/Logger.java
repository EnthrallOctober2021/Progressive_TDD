package progressive.qa.reporting;

import com.aventstack.extentreports.Status;

public class Logger {

	public static void log(final String msg) {
		Java_Logger.getLog(msg);
		TestNGLogger.getLog(msg);
		ExtentTestManager.getTest().log(Status.INFO, msg);
	}
}
