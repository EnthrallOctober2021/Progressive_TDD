package progressive.qa.pages;

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
	public WebElement nameText;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_FirstName")
	public WebElement firstNameInput;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_LastName")
	public WebElement lastNameInput;
	@FindBy(id = "NameAndAddressEdit_embedded_questions_list_Suffix")
	public WebElement suffix;
	
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
	
	public void personalDetailsSteps(String expected, String firstName, String lastName, String suffixValue) {
		getNameTextStep(expected);
		firstNameInputStep(firstName);
		lastNameInputStep(lastName);
		selectSuffix(suffixValue);
	}
}
