package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utilities.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base()   {

		// reading data from config properties file
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		

		// reading testdata from testdata properties file
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");


		try {

			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(propFile);

			prop.load(fis);
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICITLY_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT));

		driver.get(prop.getProperty("baseURL"));
		return driver;

	}
}
