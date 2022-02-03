package progressive.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import progressive.qa.reporting.Java_Logger;

public class Configurable {

	static Configurable configurable;
	private String path = "./configuration/config.properties";
	private Properties properties;

	private String url;
	private int explicitWait;
	private int pageLoadWait;
	private int elementImplicitWait;

	private Configurable() {
	}

	public static Configurable getInstance() {
		if (configurable == null) {
			configurable = new Configurable();
			configurable.loadProperties();
		}
		return configurable;
	}

	private void loadProperties() {
		// BufferedReader bufferedReader;
		// FileReader fileReader;
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(path);
			properties = new Properties();
			properties.load(fileInputStream);
			url = properties.getProperty("url");
			try {
				explicitWait = Integer.parseInt(properties.getProperty("explicitWait"));
				pageLoadWait = Integer.parseInt(properties.getProperty("pageLoadWait"));
				elementImplicitWait = Integer.parseInt(properties.getProperty("elementImplicitWait"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				Java_Logger.getLog("Check your config file @ " + new File(path).getAbsolutePath());
				Assert.fail();
			}
		} catch (IOException e) {
			e.printStackTrace();
			Java_Logger.getLog("File Not Found");
		}
	}

	public String getUrl() {
		return url;
	}

	public int getExplicitWait() {
		return explicitWait;
	}

	public int getPageLoadWait() {
		return pageLoadWait;
	}

	public int getElementImplicitWait() {
		return elementImplicitWait;
	}
}
