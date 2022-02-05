package progressive.qa.reporting;

import org.testng.Reporter;

public class TestNGLogger {

	public static void getLog(String msg) {
		Reporter.log(msg + "<br>");
	}
}
