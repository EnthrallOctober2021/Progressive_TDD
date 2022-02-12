package progressive.qa.auto;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import progressive.qa.base.BaseClass;
import progressive.qa.utilities.Configurable;
import progressive.qa.utilities.ExcelReader;

public class AutoQuoteWithExtentReport extends BaseClass {

	ExtentReports report;
	
	@BeforeClass
	public void extentSetup() {
		report = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		report.attachReporter(spark);
	}
	
	@DataProvider(name = "autoDataExcel")
	public Object[][] getAutoDataExcel(){
		String filePath = Configurable.getInstance().getExcelPath();
		int sheetNum = Configurable.getInstance().getSheetNum();
		ExcelReader reader = new ExcelReader(filePath, sheetNum);
		Object[][] data = reader.testData();
		return data;
	}
	
	@Test(dataProvider = "autoDataExcel", groups = "auto")
	public void autoQuoteTestingWithExcelDataProbider(String zipCode, String zipCodeTitle, String nameTitle, String firstName,
			String lastName, String suffix, String dob, String address, String apt, String city, String vehicleTtitle) {
		productsPage.productPageAutoSteps();
		zipCodePage.zipCodePageSteps(zipCode, zipCodeTitle);
		personalDetails.personalDetailsSteps(nameTitle, firstName, lastName, suffix, dob, address, apt, city);
		report.createTest("autoQuoteTestingWithExcelDataProbider").log(Status.PASS, "This Test case has been passed");
	}
	
	@Test
	public void test1() {
		report.createTest("test1").log(Status.FAIL, "This Test case has been Failed");
	}
	
	@Test
	public void test2() {
		report.createTest("test2").log(Status.PASS, "This Test case has been PASS");
	}
	
	@AfterClass
	public void finishReport() {
		report.flush();
	}
}
