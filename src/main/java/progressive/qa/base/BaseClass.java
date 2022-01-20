package progressive.qa.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import progressive.qa.pages.ProductsPage;

public class BaseClass {
	
	public static WebDriver driver;
	public ProductsPage productsPage;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.progressive.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		initElements();
	}
	
	@AfterMethod
	public void quttingBrowser() {
		//driver.quit();
	}
	
	private void initElements() {
		productsPage = new ProductsPage(driver);
	}
}
