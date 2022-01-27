package progressive.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZipCodePage {

	public ZipCodePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//label[@for='zipCode_overlay' and text()='Enter ZIP Code']")
	public WebElement zipCodeText;
	@FindBy(xpath = "//input[@name='ZipCode']/preceding-sibling::label[@for='zipCode_overlay']")
	public WebElement inputZipCode;
	@FindBy(id="qsButton_overlay")
	public WebElement getQuoteBtn;
}
