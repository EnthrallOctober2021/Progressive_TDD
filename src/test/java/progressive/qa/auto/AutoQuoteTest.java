package progressive.qa.auto;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;
import progressive.qa.reporting.Logger;

public class AutoQuoteTest extends BaseClass{
	
	@Ignore
	@Test(enabled = true, priority = 1)
	public void autoQuoteTesting() {
		productsPage.productPageAutoSteps();
		zipCodePage.zipCodePageSteps("10473", "Enter ZIP Code");
		personalDetails.personalDetailsSteps("Name & Birthdate", "John", "Doe", "SR", "10/10/2000","747 Taylor Ave", "2B", "Bronx");
		vehiclePage.vehiclePageSteps("Tell us about your vehicle(s)...");
	}
	
	@Test(dependsOnMethods = {"autoQuoteTesting"}, ignoreMissingDependencies = true, alwaysRun = true)
	public void testMethodAuto() {
		Logger.log("this is an auto related test");
	}
}
