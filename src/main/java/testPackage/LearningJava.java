package testPackage;

import java.awt.AWTException;
import org.testng.annotations.Test;

public class LearningJava {

	@Test (enabled = true)
	public void testObjectHashCode() throws AWTException {
		//productsPage.productPageSteps();
		//zipCodePage.zipCodePageSteps("10473");
		//System.out.println(zipCodePage.hashCode());
		//System.out.println(productsPage.hashCode());
		//ZipCodePage zipCodePage1 = new ZipCodePage(driver);
		//System.out.println(zipCodePage1.hashCode());
		String aString = new String("Name");
		String bString = "Name";
		System.out.println(aString.hashCode());
		System.out.println(bString.hashCode());
	}
	
	@Test
	public void throwException() throws MyException {
		throw new MyException();
	}
	
	public class MyException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyException() {
			super();
			System.out.println("Enthrall October Exception");
		}
	}
}
