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
import progressive.qa.common.CommonActions;
import progressive.qa.common.CommonWaits;
import progressive.qa.pages.PersonalDetails;
import progressive.qa.pages.ProductsPage;
import progressive.qa.pages.ZipCodePage;
import progressive.qa.utilities.Configurable;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Robot robot;
	
	public static CommonWaits waits;
	public static CommonActions commonActions;
	public Configurable configurable;
	
	public ProductsPage productsPage;
	public ZipCodePage zipCodePage;
	public PersonalDetails personalDetails;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		initElements();
		driver.get(configurable.getUrl());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configurable.getPageLoadWait()));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configurable.getElementImplicitWait()));
		wait = new WebDriverWait(driver, Duration.ofSeconds(configurable.getExplicitWait()));
		try {
			robot = new Robot();
		}catch(AWTException e) {
			e.printStackTrace();
			Assert.fail();
		}
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
		commonActions = new CommonActions();
		configurable = Configurable.getInstance();
	}
}
