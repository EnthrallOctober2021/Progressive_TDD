package progressive.qa.auto;

import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;

public class AutoQuoteTest extends BaseClass{
	
	@Test
	public void autoQuoteTesting() {
		productsPage.productPageSteps();
		zipCodePage.zipCodePageSteps("10473", "Enter ZIP Code");
		personalDetails.personalDetailsSteps("Name & Birthdate", "John", "Doe", "SR", "10/10/2000","747 Taylor Ave", "2B", "Bronx");
		vehiclePage.vehiclePageSteps("Tell us about your vehicle(s)...");
	}
}
