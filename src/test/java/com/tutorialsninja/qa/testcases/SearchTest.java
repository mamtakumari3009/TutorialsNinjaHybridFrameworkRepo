package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public SearchTest()
	{
		super();
	}

	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		 homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product is not displayed");
		System.out.println("Valid Product.... is displayed succesfully");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductMatchInSearchMessage"),"No Product Message in Search Result is not displayed");
		System.out.println("There is no product that matches the search criteria. .... is displayed succesfully");
	}
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductMatchInSearchMessage"),"No Product Message in Search Result is not displayed");
		System.out.println("There is no product that matches the search criteria. .... is displayed succesfully");
	}	
}