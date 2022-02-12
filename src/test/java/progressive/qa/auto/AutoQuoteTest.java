package progressive.qa.auto;

import java.util.ArrayList;
import java.util.Iterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;
import progressive.qa.data.AutoDataClass;
import progressive.qa.data.ZipCodeData;
import progressive.qa.reporting.Logger;
import progressive.qa.utilities.Configurable;
import progressive.qa.utilities.ExcelReader;

public class AutoQuoteTest extends BaseClass{
	
	@DataProvider(name = "autoData")
	public Iterator<AutoDataClass> getAutoData(){
		ArrayList<AutoDataClass> list = new ArrayList<>();
		list.add(new AutoDataClass("Name & Birthdate", "John", "Doe", "SR", "10/10/2000","747 Taylor Ave", "2B", "Bronx"));
		list.add(new AutoDataClass("Name & Birthdate", "Rohim", "Abdullah", "JR", "02/05/1966","747 Taylor Ave", "3C", "Bronx"));
		AutoDataClass autoData1 = new AutoDataClass("Name & Birthdate", "Green", "Beret", "JR", "11/11/1953","747 Taylor Ave", "3C", "Bronx");
		list.add(autoData1);
		return list.iterator();
	}
	
	@DataProvider(name = "zipCodeData")
	public Iterator<ZipCodeData> getZipData(){
		ArrayList<ZipCodeData> list = new ArrayList<>();
		list.add(new ZipCodeData("10473", "Enter ZIP Code"));
		list.add(new ZipCodeData("10471", "Enter ZIP Code"));
		list.add(new ZipCodeData("10472", "Enter ZIP Code"));
		return list.iterator();
	}

	@DataProvider(name = "autoDataExcel")
	public Object[][] getAutoDataExcel(){
		String filePath = Configurable.getInstance().getExcelPath();
		int sheetNum = Configurable.getInstance().getSheetNum();
		ExcelReader reader = new ExcelReader(filePath, sheetNum);
		Object[][] data = reader.testData();
		return data;
	}
	
	//@Ignore
	//@Parameters("zipCode")
	@Test(enabled = true, priority = 1, groups = {"auto"})
	public void autoQuoteTesting() {
		productsPage.productPageAutoSteps();
		zipCodePage.zipCodePageSteps("10473", "Enter ZIP Code");
		personalDetails.personalDetailsSteps("Name & Birthdate", "John", "Doe", "SR", "10/10/2000","747 Taylor Ave", "2B", "Bronx");
		vehiclePage.vehiclePageSteps("Tell us about your vehicle(s)...");
	}
	
	@Test(dataProvider = "autoData", groups = {"auto"})
	public void autoQuoteTestingWithIteratorDataProbider(AutoDataClass autoData) {
		productsPage.productPageAutoSteps();
		zipCodePage.zipCodePageSteps("10473", "Enter ZIP Code");
		personalDetails.personalDetailsSteps(autoData);
		vehiclePage.vehiclePageSteps("Tell us about your vehicle(s)...");
	}
	
	@Test(dataProvider = "zipCodeData", groups = {"auto"})
	public void autoQuoteTestingWithIterator2DataProbider(ZipCodeData zipCodeData) {
		productsPage.productPageAutoSteps();
		zipCodePage.zipCodePageSteps(zipCodeData);
		personalDetails.personalDetailsSteps("Name & Birthdate", "John", "Doe", "SR", "10/10/2000","747 Taylor Ave", "2B", "Bronx");
		vehiclePage.vehiclePageSteps("Tell us about your vehicle(s)...");
	}
	
	@Test(dataProvider = "autoDataExcel", groups = {"auto"})
	public void autoQuoteTestingWithExcelDataProbider(String zipCode, String zipCodeTitle, String nameTitle, String firstName,
			String lastName, String suffix, String dob, String address, String apt, String city, String vehicleTtitle) {
		productsPage.productPageAutoSteps();
		zipCodePage.zipCodePageSteps(zipCode, zipCodeTitle);
		personalDetails.personalDetailsSteps(nameTitle, firstName, lastName, suffix, dob, address, apt, city);
		vehiclePage.vehiclePageSteps(vehicleTtitle);
	}
	
	@Test(dependsOnMethods = {"autoQuoteTesting"}, ignoreMissingDependencies = true)
	public void testMethodAuto() {
		Logger.log("this is an auto related test 0");
	}
	
	@Test(groups = {"auto"})
	public void testMethodAuto1() {
		Logger.log("this is an auto related test 1");
	}
}
