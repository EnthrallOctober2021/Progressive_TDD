package progressive.qa.auto;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.annotations.Test;
import progressive.qa.base.BaseClass;

public class AutoQuoteTest extends BaseClass{
	
	@Test
	public void autoQuoteTesting() throws AWTException {
		
		commonMethods.click(productsPage.autoButton);
		commonMethods.getText(zipCodePage.zipCodeText);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_4);
		robot.keyPress(KeyEvent.VK_7);
		robot.keyPress(KeyEvent.VK_3);
		//commonMethods.writeText(zipCodePage.inputZipCode, "10473");
		commonMethods.click(zipCodePage.getQuoteBtn);
	}
}
