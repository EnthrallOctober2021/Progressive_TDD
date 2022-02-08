package progressive.qa.home;

import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;

public class HomeQuoteTest extends BaseClass{

	@Test(enabled = false)
	public void homeQuoteTexting() {
		productsPage.productPageHomeSteps();
		zipCodePage.zipCodePageSteps("11411","Enter ZIP Code");
	}
}
