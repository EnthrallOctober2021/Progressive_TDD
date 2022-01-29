package progressive.qa.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import progressive.qa.common.CommonMethods;
import progressive.qa.common.CommonWaits;
import progressive.qa.pages.PersonalDetails;
import progressive.qa.pages.ProductsPage;
import progressive.qa.pages.ZipCodePage;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Robot robot;
	
	public ProductsPage productsPage;
	public ZipCodePage zipCodePage;
	public PersonalDetails personalDetails;
	public static CommonWaits waits;
	public static CommonMethods commonMethods;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.progressive.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			robot = new Robot();
		}catch(AWTException e) {
			e.printStackTrace();
			Assert.fail();
		}
		initElements();
	}
	
	@AfterMethod
	public void quttingBrowser() {
		//driver.quit();
	}
	
	private void initElements() {
		productsPage = new ProductsPage(driver);
		zipCodePage = new ZipCodePage(driver);
		personalDetails = new PersonalDetails(driver);
		waits = new CommonWaits();
		commonMethods = new CommonMethods();
	}
}
