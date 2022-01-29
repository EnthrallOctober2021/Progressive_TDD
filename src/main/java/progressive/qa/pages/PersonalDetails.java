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
	
	private void getNameTextStep(String expected) {
		BaseClass.commonMethods.getText(nameText, expected);
	}
	
	private void firstNameInputStep(String value) {
		BaseClass.commonMethods.writeText(firstNameInput, value);
	}
	
	private void lastNameInputStep(String value) {
		BaseClass.commonMethods.writeText(lastNameInput, value);
	}
	
	public void personalDetailsSteps(String expected, String firstName, String lastName) {
		getNameTextStep(expected);
		firstNameInputStep(firstName);
		lastNameInputStep(lastName);
	}
}
