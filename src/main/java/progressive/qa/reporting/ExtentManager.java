package progressive.qa.reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;
	
	public synchronized static ExtentReports getInstance() {
		if(extent == null) {
			try {
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy_HH.mm");
				String dateString = dateFormat.format(date);
				
				extent = new ExtentReports();
				ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./test-output/ExtentReports/ExtentReport_"+dateString+".html");
				sparkReporter.config().setReportName("QA Automation for Progressive");
				extent.attachReporter(sparkReporter);
				extent.setSystemInfo("QA Team", "Avengers_Progressive");
				extent.setSystemInfo("Environment", "QA");
				extent.setSystemInfo("Assigned Tester", System.getProperty("user.name"));
				Java_Logger.getLog("Reporting is starting ...");
			}catch(Exception e) {
				e.printStackTrace();
				Java_Logger.getLog("Reporting cannot started. \n" + e.getLocalizedMessage());
			}
		}
		return extent;
	}
}
