package progressive.qa.home;

import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;
import progressive.qa.reporting.Logger;

public class HomeQuoteTest extends BaseClass{

	@Test(enabled = true, groups = {"home"})
	public void homeQuoteTesting() {
		productsPage.productPageHomeSteps();
		zipCodePage.zipCodePageSteps("11411","Enter ZIP Code");
		org.testng.Assert.fail();
	}
	
	@Test(enabled = true, groups = {"home"})
	public void homeTest() {
		Logger.log("This is a home test");
	}
}
