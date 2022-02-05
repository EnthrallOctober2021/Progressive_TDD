package progressive.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import progressive.qa.base.BaseClass;

public class PersonalDetails {

	public PersonalDetails(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//legend[@class='section-sub-header'])[1]")
	private WebElement nameText;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_FirstName")
	private WebElement firstNameInput;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_LastName")
	private WebElement lastNameInput;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_Suffix")
	private WebElement suffix;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_DateOfBirth")
	private WebElement dobInput;
	@FindBy(xpath = "//div[contains(.,'Street number')]/following-sibling::input")
	private WebElement addressElement;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_ApartmentUnit")
	private WebElement aptElement;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_City")
	private WebElement cityElement;
	@FindBy(xpath = "//button[contains(.,'Okay, start my quote')]")
	private WebElement startQuoteBtn;
	private By errorMsg = By.xpath("//span[contains(.,'Oops')]");
	
	private void getNameTextStep(String expected) {
		BaseClass.commonActions.getText(nameText, expected);
	}
	
	private void firstNameInputStep(String value) {
		BaseClass.commonActions.writeText(firstNameInput, value);
	}
	
	private void lastNameInputStep(String value) {
		BaseClass.commonActions.writeText(lastNameInput, value);
	}
	
	private void selectSuffix(String suffixValue) {
		BaseClass.commonActions.selectDropDown(suffix, suffixValue);
	}
	
	private void inputDOB(String input) {
		BaseClass.commonActions.writeText(dobInput, input);
	}
	
	private void inputAddress(String addressValue) {
		BaseClass.commonActions.sleep(1);
		BaseClass.commonActions.writeText(addressElement, addressValue);
	}
	
	private void inputApt(String aptNo) {
		BaseClass.commonActions.writeText(aptElement, aptNo);
	}
	
	private void inputCity(String city) {
		BaseClass.commonActions.writeText(cityElement, city);
	}
	
	private void startQuoteBtn() {
		BaseClass.commonActions.click(startQuoteBtn);
	}
	
	public void checkAddressError(String address) {
		if (BaseClass.commonActions.isPresent(errorMsg)) {
			inputAddress(address);
			startQuoteBtn();
		}
	}
	
	public void personalDetailsSteps(String expected, String firstName, String lastName, String suffixValue, String dob, String address, String aptNo, String city) {
		getNameTextStep(expected);
		firstNameInputStep(firstName);
		lastNameInputStep(lastName);
		selectSuffix(suffixValue);
		inputDOB(dob);
		inputAddress(address);
		inputApt(aptNo);
		inputCity(city);
		startQuoteBtn();
		checkAddressError(address);
	}
}
