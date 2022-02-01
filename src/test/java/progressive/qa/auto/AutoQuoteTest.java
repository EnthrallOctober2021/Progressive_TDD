package progressive.qa.auto;

import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;

public class AutoQuoteTest extends BaseClass{
	
	@Test
	public void autoQuoteTesting() {
		productsPage.productPageSteps();
		zipCodePage.zipCodePageSteps("10473", "Enter ZIP Code");
		personalDetails.personalDetailsSteps("Name & Birthdate", "John", "Doe", "SR");
	}
}
