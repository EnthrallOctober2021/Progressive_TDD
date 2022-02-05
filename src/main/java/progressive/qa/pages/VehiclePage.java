package progressive.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import progressive.qa.base.BaseClass;

public class VehiclePage {

	public VehiclePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName = "h1")
	private WebElement vehicleTitle;
	
	private void getVehicleHeader(String expected) {
		BaseClass.commonActions.scrollIntoViewTheElement(vehicleTitle);
		BaseClass.commonActions.getText(vehicleTitle, expected);
	}
	
	public void vehiclePageSteps(String expected) {
		getVehicleHeader(expected);
	}
}
