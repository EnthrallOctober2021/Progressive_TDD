package progressive.qa.common;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import progressive.qa.base.BaseClass;
import progressive.qa.reporting.Java_Logger;

public class CommonMethods {

	public void click(WebElement element) {
		try {
			BaseClass.waits.waitUntilClickable(element);
			element.click();
			Java_Logger.getLog(element + " clicked");
		} catch (NullPointerException e) {
			e.printStackTrace();
			Java_Logger.getLog(element + " Element Not Found");
			Assert.fail();
		}
	}

	public String getText(WebElement element) {
		try {
			BaseClass.waits.waitUntilVisible(element);
			Java_Logger.getLog(element + " Text Value : " + element.getText());
			return element.getText();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Java_Logger.getLog(element +" Element Not Found");
			return element + " : Element Not Found";
		}
	}
	
	public void writeText(WebElement element, String value) {
		try {
			BaseClass.waits.waitUntilClickable(element);
			element.sendKeys(value);
			Java_Logger.getLog(element + " Text Value entered : " + element.getText());
		} catch (NullPointerException e) {
			e.printStackTrace();
			Java_Logger.getLog(element +" Element Not Found");
			Assert.fail();
		}
	}
}
