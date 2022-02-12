package progressive.qa.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;
	
	public synchronized static ExtentReports getInstance() {
		if(extent == null) {
			try {
				extent = new ExtentReports();
				ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./test-output/ExtentReport.html");
				sparkReporter.config().setReportName("QA Automation for Progressive");
				extent.attachReporter(sparkReporter);
				Java_Logger.getLog("Reporting is starting ...");
				extent.setSystemInfo("QA Team", "Avengers_Progressive");
				extent.setSystemInfo("Environment", "QA");
				extent.setSystemInfo("Assigned Tester", System.getProperty("user.name"));
			}catch(Exception e) {
				e.printStackTrace();
				Java_Logger.getLog("Reporting cannot started. \n" + e.getLocalizedMessage());
			}
		}
		return extent;
	}
}
