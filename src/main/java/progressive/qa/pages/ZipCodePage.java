package progressive.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import progressive.qa.base.BaseClass;
import progressive.qa.data.ZipCodeData;

public class ZipCodePage {

	public ZipCodePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@for='zipCode_overlay' and text()='Enter ZIP Code']")
	private WebElement zipCodeText;
	@FindBy(xpath = "//label[@for='zipCode_overlay_subproducts' and text()='Enter ZIP Code']")
	private WebElement altZipCodeText;
	@FindBy(xpath = "//input[@id='zipCode_overlay' and @name='ZipCode']")
	private WebElement inputZipCode;
	@FindBy(xpath = "(//input[@name='ZipCode'])[2]")
	private WebElement altZipInput;
	@FindBy(id = "qsButton_overlay")
	private WebElement getQuoteBtn;
	@FindBy(id = "qsButton_overlay_subproducts")
	private WebElement altGetQuoteBtn;
	
	private void getZipCodeTextStep(WebElement element, String expected) {
		BaseClass.commonActions.getText(element, expected);
	}
	
	private void zipCodeInputStep(String zipCode, String expected) {
		if (BaseClass.driver.getCurrentUrl().contains("https://www.progressive.com/#s")) {
			getZipCodeTextStep(altZipCodeText, expected);
			BaseClass.commonActions.click(altZipInput);
			BaseClass.commonActions.writeText(altZipInput, zipCode);
			clickQuoteBtnStep(altGetQuoteBtn);
		}else if(BaseClass.driver.getCurrentUrl().contains("https://www.progressive.com/#l")
				|| BaseClass.driver.getCurrentUrl().contains("https://www.progressive.com/home/home/#l")) {
			getZipCodeTextStep(zipCodeText, expected);
			BaseClass.commonActions.writeText(inputZipCode, zipCode);
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
	
	public void zipCodePageSteps(ZipCodeData zipCodeData) {
		zipCodeInputStep(zipCodeData.getZipCode(), zipCodeData.getTitle());
	}
}
