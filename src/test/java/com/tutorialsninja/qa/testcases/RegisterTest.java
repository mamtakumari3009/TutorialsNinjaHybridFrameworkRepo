package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utilities.Utilities;


public class RegisterTest extends Base {

	RegisterPage registerPage;
	public WebDriver driver;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest()
	{
		super();
	}




	@BeforeMethod
	public void setUp() {

		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		registerPage = homepage.navigateToRegisterPage();

	}
	@AfterMethod
	public void tearDown() {

		driver.quit();
	}



	@Test (priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));

		Assert.assertTrue(accountSuccessPage.retrieveAccountSuccessPageHeading().contains(dataProp.getProperty("accountCreatedSuccessfullyMessage")),
				"Account not created");
		System.out.println("Account has been created successfully...Register functionality got passed");

	}

	@Test (priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));

		Assert.assertTrue(accountSuccessPage.retrieveAccountSuccessPageHeading().contains(dataProp.getProperty("accountCreatedSuccessfullyMessage")),
				"Account not created");

		System.out.println("Account has been created successfully...Register functionality with providing all feild got passed");



	}

	@Test (priority=3)
	public void verifyRegisteringAccountWithAllreadyRegistredEmail() {
		
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));

		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("warningAlrDuplicateEmailMessage")),
				"Warning Message regarding duplicate email Address is not displayed");

		System.out.println("Coudn't created new account....Register functionality with providing duplicate email is got passed");



	}

	@Test (priority=4)
	public void verifyRegisteringAccountWithoutFilingAnyDetails() {

		accountSuccessPage = registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.displayStatusOPfWarningMessages(dataProp.getProperty("warningPrivacyPolicyMessage"),
				dataProp.getProperty("warningFirstNameMessage"),dataProp.getProperty("warningLastNameMessage"),
				dataProp.getProperty("warningEmailMessage"),dataProp.getProperty("warningTelephoneMessage"),
				dataProp.getProperty("warningPasswordMessage")));

	}

}

