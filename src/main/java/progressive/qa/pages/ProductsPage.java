package progressive.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import progressive.qa.base.BaseClass;

public class ProductsPage {
	
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "(//a[contains(.,'Auto')])[1]")
	public WebElement autoButton;

	private void clickAutoBtnStep() {
		BaseClass.commonActions.click(autoButton);
	}
	
	public void productPageSteps() {
		clickAutoBtnStep();
	}
}
