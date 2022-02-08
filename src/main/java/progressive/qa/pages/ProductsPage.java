package progressive.qa.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import progressive.qa.base.BaseClass;
import progressive.qa.reporting.Logger;

public class ProductsPage {

	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//a[contains(.,'Auto')])[1]")
	private WebElement autoButton;
	@FindBy(xpath = "(//a[contains(.,'Home')])[2]")
	private WebElement homeButton;
	@FindBy(xpath = "(//a[contains(.,'Home')])[2]/span/img")
	private WebElement homeBtnImg;
	@FindBy(xpath = "(//a[contains(.,'Property')])[1]")
	private WebElement propertyBtn;
	@FindBy(xpath = "//a[contains(.,'No, thanks')]")
	private List<WebElement> noThanksBtn;

	private void clickAutoBtnStep() {
		BaseClass.commonActions.click(autoButton);
	}

	private void clickHomeBtnStep() {
		if (BaseClass.commonActions.chekAttribute(homeBtnImg, "src")) {
			BaseClass.commonActions.click(homeButton);
		}else {
			BaseClass.commonActions.click(propertyBtn);
		}
	}

	private void checkOverlay() {
		if (BaseClass.commonActions.isPresent(noThanksBtn)) {
			BaseClass.commonActions.click(noThanksBtn.get(0));
		} else {
			Logger.log("Overlay Not Found");
		}
	}

	public void productPageAutoSteps() {
		checkOverlay();
		clickAutoBtnStep();
	}

	public void productPageHomeSteps() {
		checkOverlay();
		clickHomeBtnStep();
	}
}
