package progressive.qa.common;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import progressive.qa.base.BaseClass;
import progressive.qa.reporting.Java_Logger;

public class CommonActions {

	public void click(WebElement element) {
		try {
			BaseClass.waits.waitUntilClickable(element);
			element.click();
			Java_Logger.getLog(element + " clicked");
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Java_Logger.getLog(element + " Element Not Found");
			Assert.fail();
		}
	}

	public String getText(WebElement element, String expected) {
		try {
			BaseClass.waits.waitUntilVisible(element);
			Java_Logger.getLog("Actual value : " + element.getText() +" >>><<< Expected value : "+ expected);
			Assert.assertEquals(element.getText(), expected);
			return element.getText();
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Java_Logger.getLog(element +" Element Not Found");
			return element + " : Element Not Found";
		}
	}
	
	public void writeText(WebElement element, String value) {
		try {
			BaseClass.waits.waitUntilClickable(element);
			element.sendKeys(value);
			Java_Logger.getLog(element + " Text Value entered : " + value);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Java_Logger.getLog(element +" Element Not Found");
			Assert.fail();
		}
	}
	
	public void selectDropDown(WebElement element, String value) {
		try {
			//BaseClass.waits.waitUntilSelectable(element);
			Select select = new Select(element);
			select.selectByValue(value);
			Java_Logger.getLog(value + " : Passed for the element, " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Java_Logger.getLog(element +" Element Not Found");
			Assert.fail();
		}
	}
	
	public void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
