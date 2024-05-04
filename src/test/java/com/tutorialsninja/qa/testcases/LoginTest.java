package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utilities.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;
	public WebDriver driver;
	AccountPage accountPage;

	public LoginTest() {
		super();
	}

	

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

		HomePage homepage = new HomePage(driver);
	    loginPage = homepage.navigateToLoginPage();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")

	public void verifyLoginWithValidCredentials(String email,String password) {
		
		accountPage = loginPage.login(email,password);

		accountPage.getDisplayStatusOfAcountInformationOption();

		System.out.println(" succesfuly Logined to the website after giving valid email & password");

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;

	}

	@Test(priority = 2)

	public void verifyLoginWithInvalidCredentials() throws InterruptedException {
		
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("inValidPassword"));

		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("warningNoMatchEmailMessage")),
				"Expected Warning massage not displayed");

		System.out.println("Error message showed succesfuly after giving invalid email & password");

	}

	@Test(priority = 3)

	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException {
		
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));

		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("warningNoMatchEmailMessage")),
				"Expected Warning massage not displayed");

		System.out.println("Error message showed succesfuly after giving invalid email & valid password");

	}

	@Test(priority = 4)

	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		accountPage = loginPage.login(prop.getProperty("validEmail"),prop.getProperty("inValidPassword"));

		try {
			Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("warningNoMatchEmailMessage")),
					"Expected Warning massage not displayed");
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		System.out.println("Error message showed succesfuly after giving valid email & invalid password");

	}

	@Test(priority = 5)

	public void verifyLoginWithoutCredentials() throws InterruptedException {
		
		loginPage.clickOnLoginButton();

		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("warningNoMatchEmailMessage")),
				"Expected Warning massage not displayed");

		System.out.println("Error message showed succesfuly without giving email & password");

	}

}
