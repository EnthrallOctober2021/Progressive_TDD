package progressive.qa.base;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import progressive.qa.common.CommonActions;
import progressive.qa.common.CommonWaits;
import progressive.qa.pages.PersonalDetails;
import progressive.qa.pages.ProductsPage;
import progressive.qa.pages.VehiclePage;
import progressive.qa.pages.ZipCodePage;
import progressive.qa.reporting.Logger;
import progressive.qa.utilities.Configurable;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor jsExecutor;
	
	public static CommonWaits waits;
	public static CommonActions commonActions;
	public Configurable configurable;
	
	public ProductsPage productsPage;
	public ZipCodePage zipCodePage;
	public PersonalDetails personalDetails;
	public VehiclePage vehiclePage;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		String os = System.getProperty("os.name");
		Logger.log("My OS version is " + os); 
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("ei")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("safari") && os.contains("Mac")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		initElements();
		driver.get(configurable.getUrl());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configurable.getPageLoadWait()));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configurable.getElementImplicitWait()));
		wait = new WebDriverWait(driver, Duration.ofSeconds(configurable.getExplicitWait()));
	}
	
	@AfterMethod
	public void quttingBrowser() {
		driver.quit();
	}
	
	private void initElements() {
		productsPage = new ProductsPage(driver);
		zipCodePage = new ZipCodePage(driver);
		personalDetails = new PersonalDetails(driver);
		vehiclePage = new VehiclePage(driver);
		waits = new CommonWaits();
		commonActions = new CommonActions();
		configurable = Configurable.getInstance();
		jsExecutor = (JavascriptExecutor)driver;
	}
}
