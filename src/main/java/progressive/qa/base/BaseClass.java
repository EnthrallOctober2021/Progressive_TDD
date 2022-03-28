package progressive.qa.base;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import progressive.qa.common.CommonActions;
import progressive.qa.common.CommonWaits;
import progressive.qa.pages.PersonalDetails;
import progressive.qa.pages.ProductsPage;
import progressive.qa.pages.VehiclePage;
import progressive.qa.pages.ZipCodePage;
import progressive.qa.reporting.ExtentManager;
import progressive.qa.reporting.ExtentTestManager;
import progressive.qa.reporting.Logger;
import progressive.qa.utilities.Configurable;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor jsExecutor;
	private ExtentReports extent;
	
	public static CommonWaits waits;
	public static CommonActions commonActions;
	public Configurable configurable;
	
	public ProductsPage productsPage;
	public ZipCodePage zipCodePage;
	public PersonalDetails personalDetails;
	public VehiclePage vehiclePage;

	@BeforeSuite
	public void initiatinExtentReport() {
		extent = ExtentManager.getInstance();
	}
	
	@Parameters({"browser", "platform"})
	@BeforeMethod
	public void setUp(String browser, String platform) {
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
		}else if(browser.equalsIgnoreCase("cloud")) {
			driver = new BaseClass().remoteDriver(platform);
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
	
	@BeforeMethod
	public void beforeEachTest(Method method) {
		String className = method.getDeclaringClass().getSimpleName();
		ExtentTestManager.startTest(method.getName());
		ExtentTestManager.getTest().assignCategory(className);
	}
	
	@AfterMethod
	public void afterEachTest(ITestResult result) {
		for(String testName : result.getMethod().getGroups()) {
			ExtentTestManager.getTest().assignCategory(testName);
		}
		if(result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().addScreenCaptureFromPath(commonActions.addScreenShotToLocal(result.getName()));
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			//commonActions.addScreenShotToLocal(); // Will add to local only
		}else {
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}
		quttingBrowser();
	}
	
	private void quttingBrowser() {
		driver.quit();
	}
	
	@AfterSuite
	public void endReport() {
		extent.flush();
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
	
	public WebDriver remoteDriver(String browser) {
		if (browser.equals("safari")) {
			SafariOptions browserOptions = new SafariOptions();
			browserOptions.setPlatformName("macOS 10.15");
			browserOptions.setBrowserVersion("13");
			Map<String, Object> sauceOptions = new HashMap<>();
			browserOptions.setCapability("sauce:options", sauceOptions);
			Properties properties = new Properties();
			try {
				properties.load(new FileReader("./configuration/secrets.properties"));
				URL url = new URL("https://"+properties.getProperty("sauceLabUser")+":"
						+properties.getProperty("sauceLabKey")+"@"+properties.getProperty("sauceLabUrl"));
				driver = new RemoteWebDriver(url, browserOptions);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("Windows 10");
			browserOptions.setBrowserVersion("99");
			Map<String, Object> sauceOptions = new HashMap<>();
			browserOptions.setCapability("sauce:options", sauceOptions);
			Properties properties = new Properties();
			try {
				properties.load(new FileReader("./configuration/secrets.properties"));
				URL url = new URL("https://"+properties.getProperty("sauceLabUser")+":"
						+properties.getProperty("sauceLabKey")+"@"+properties.getProperty("sauceLabUrl"));
				driver = new RemoteWebDriver(url, browserOptions);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(browser.equalsIgnoreCase("ios")) {
			MutableCapabilities caps = new MutableCapabilities();
			caps.setCapability("platformName", "iOS");
			caps.setCapability("browserName", "Safari");
			caps.setCapability("appium:deviceName", "iPad Simulator");
			caps.setCapability("appium:platformVersion", "14.4");
			MutableCapabilities sauceOptions = new MutableCapabilities();
			sauceOptions.setCapability("appiumVersion", "1.22.2");
			caps.setCapability("sauce:options", sauceOptions);
			Properties properties = new Properties();
			try {
				properties.load(new FileReader("./configuration/secrets.properties"));
				URL url = new URL("https://"+properties.getProperty("sauceLabUser")+":"
						+properties.getProperty("sauceLabKey")+"@"+properties.getProperty("sauceLabUrl"));
				driver = new RemoteWebDriver(url, caps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(browser.equalsIgnoreCase("android")) {
			MutableCapabilities caps = new MutableCapabilities();
			caps.setCapability("platformName", "Android");
			caps.setCapability("browserName", "Chrome");
			caps.setCapability("appium:deviceName", "Google Pixel 3a GoogleAPI Emulator");
			caps.setCapability("appium:platformVersion", "10.0");
			MutableCapabilities sauceOptions = new MutableCapabilities();
			sauceOptions.setCapability("appiumVersion", "1.20.2");
			caps.setCapability("sauce:options", sauceOptions);
			Properties properties = new Properties();
			try {
				properties.load(new FileReader("./configuration/secrets.properties"));
				URL url = new URL("https://"+properties.getProperty("sauceLabUser")+":"
						+properties.getProperty("sauceLabKey")+"@"+properties.getProperty("sauceLabUrl"));
				driver = new RemoteWebDriver(url, caps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return driver;
	}
}
