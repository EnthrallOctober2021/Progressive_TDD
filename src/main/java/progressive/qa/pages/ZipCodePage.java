package progressive.qa.pages;

import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import progressive.qa.base.BaseClass;

public class ZipCodePage {

	public ZipCodePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@for='zipCode_overlay' and text()='Enter ZIP Code']")
	public WebElement zipCodeText;
	@FindBy(xpath = "//label[@for='zipCode_overlay_subproducts' and text()='Enter ZIP Code']")
	public WebElement altZipCodeText;
	@FindBy(xpath = "(//input[@name='ZipCode'])[1]")
	public WebElement inputZipCode;
	@FindBy(xpath = "(//input[@name='ZipCode'])[2]")
	public WebElement altZipInput;
	@FindBy(id = "qsButton_overlay")
	public WebElement getQuoteBtn;
	@FindBy(id = "qsButton_overlay_subproducts")
	public WebElement altGetQuoteBtn;
	
	private void getZipCodeTextStep(WebElement element, String expected) {
		BaseClass.commonActions.getText(element, expected);
	}
	
	private void zipCodeInputStep(String zipCode, String expected) {
		if (BaseClass.driver.getCurrentUrl().contains("https://www.progressive.com/#s")) {
			getZipCodeTextStep(altZipCodeText, expected);
			BaseClass.commonActions.click(altZipInput);
			BaseClass.commonActions.writeText(altZipInput, zipCode);
			clickQuoteBtnStep(altGetQuoteBtn);
		}else if(BaseClass.driver.getCurrentUrl().contains("https://www.progressive.com/#l")) {
			getZipCodeTextStep(zipCodeText, expected);
			BaseClass.commonActions.sleep(5);
			BaseClass.robot.keyPress(KeyEvent.VK_1);
			BaseClass.robot.keyPress(KeyEvent.VK_0);
			BaseClass.robot.keyPress(KeyEvent.VK_4);
			BaseClass.robot.keyPress(KeyEvent.VK_7);
			BaseClass.robot.keyPress(KeyEvent.VK_3);
			//commonMethods.writeText(zipCodePage.inputZipCode, "10473");
			clickQuoteBtnStep(getQuoteBtn);
		}else {
			Assert.fail();
		}
	}
	
	private void clickQuoteBtnStep(WebElement element) {
		BaseClass.commonActions.click(element);
	}
	
	public void zipCodePageSteps(String zipCode, String expected) {
		zipCodeInputStep(zipCode, expected);
	}
}
