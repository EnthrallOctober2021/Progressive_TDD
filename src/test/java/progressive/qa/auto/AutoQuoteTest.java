package progressive.qa.auto;
import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;

public class AutoQuoteTest extends BaseClass{
	
	@Test
	public void autoQuoteTesting() {
		
		waits.waitUntilClickable(productsPage.autoButton);
		productsPage.autoButton.click();
		//commonMethods.click(productsPage.autoButton);
		
		waits.waitUntilVisible(productsPage.zipCodeText);
		String zipCodeText = commonMethods.getText(productsPage.zipCodeText);
		
		System.out.println(">>>>>>>>>>>>>>>>>> "+zipCodeText);	
	}
	
	@Test
	public void test1() {
		System.out.println("Test 1");
	}
	
	@Test
	public void test2() {
		System.out.println("Test 2");
	}
	
	@Test
	public void test3() {
		System.out.println("Test 3");
	}
}
