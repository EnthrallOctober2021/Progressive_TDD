package progressive.qa.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import progressive.qa.base.BaseClass;
import progressive.qa.reporting.Logger;

public class CommonActions {

	public void click(WebElement element) {
		try {
			BaseClass.waits.waitUntilClickable(element);
			element.click();
			Logger.log(element + " clicked");
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Logger.log(element + " Element Not Found");
			Assert.fail();
		}
	}

	public String getText(WebElement element, String expected) {
		try {
			BaseClass.waits.waitUntilVisible(element);
			Logger.log("Actual value : " + element.getText() +" >>><<< Expected value : "+ expected);
			Assert.assertEquals(element.getText(), expected);
			return element.getText();
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Logger.log(element +" Element Not Found");
			return element + " : Element Not Found";
		}
	}
	
	public void writeText(WebElement element, String value) {
		try {
			BaseClass.waits.waitUntilClickable(element);
			element.sendKeys(value);
			Logger.log(element + " Text Value entered : " + value);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Logger.log(element +" Element Not Found");
			Assert.fail();
		}
	}
	
	public void selectDropDown(WebElement element, String value) {
		try {
			//BaseClass.waits.waitUntilSelectable(element);
			Select select = new Select(element);
			select.selectByValue(value);
			Logger.log(value + " : has been selected for element, " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Logger.log(element +" Element Not Found");
			Assert.fail();
		}
	}
	
	public boolean isPresent(By locator) {
		boolean flag = false;
		try {
			List<WebElement> list = BaseClass.driver.findElements(locator);
			if(list.size() > 0) {
				flag = true;
				Logger.log(locator +" : Element is present");
			}
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Logger.log(locator +" : Element not present");
		}
		return flag;
	}
	
	public void scrollDown() {
		try {
			BaseClass.jsExecutor.executeScript("scroll(0,250);");
			Logger.log("Scrolling down to 0 to 250 pixels");
		} catch (JavascriptException e) {
			Logger.log("Exception while scrolling down");
		}
	}
	
	public void scrollUp() {
		try {
			BaseClass.jsExecutor.executeScript("scroll(0, -250);");
			Logger.log("Scrolling up to 250 to 0 pixels");
		} catch (JavascriptException e) {
			Logger.log("Exception while scrolling up");
		}
	}
	
	public void scrollIntoViewTheElement(WebElement element) {
		try {
			BaseClass.jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			Logger.log("Scrolling into element : " + element + ", Succeed");
		} catch (JavascriptException e) {
			e.printStackTrace();
			Logger.log("Scrolling into element : "+ element+ ", Failed");
			Assert.fail();
		}
	}
	
	public void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
			Logger.log("Thread is sleeping zZz...");
		} catch (InterruptedException e) {
			e.printStackTrace();
			Logger.log("Sleeping interuppted...");
		}
	}
}
