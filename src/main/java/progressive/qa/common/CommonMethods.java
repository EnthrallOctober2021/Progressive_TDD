package progressive.qa.common;

import org.openqa.selenium.WebElement;

public class CommonMethods {

	public void click(WebElement element) {
		try {
			element.click();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public String getText(WebElement element) {
		try {
			return element.getText();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return element + " : Not Found";
	}
}
