package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emaiAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//label[normalize-space()='Yes']")
	private WebElement yesNewsLetterOption;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emaiAddressWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;



	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterFirstNameField(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastNameField(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmaiAddressField(String emailAddressText) {
		emaiAddressField.sendKeys(emailAddressText);
	}

	public void enterTelephoneField(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPasswordField(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPasswordField(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}

	public void selectPrivacyPolicyField() {
		privacyPolicyCheckbox.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	public String retrieveDuplicateEmailAddressWarning() {

		String duplicateEmailWarningMessage = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningMessage;

	}
	public String retrievePrivacyPolicyWarning() {

		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;

	}
	public String retrieveFirstNameWarning() {

		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;

	}
	public String retrieveLastNameWarning() {

		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveEmailAddressWarning() {

		String emaiAddressWarningText = emaiAddressWarning.getText();
		return emaiAddressWarningText;
	}
	public String retrieveTelephoneWarning() {

		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	public String retrievePasswordWarning() {

		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}

	public AccountSuccessPage 
	registerWithMandatoryFields(String firstNameText,String lastNameText,String emailAddressText,String telephoneText,String passwordText,String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emaiAddressField.sendKeys(emailAddressText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		privacyPolicyCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage 
	registerWithAllFields(String firstNameText,String lastNameText,String emailAddressText,String telephoneText,String passwordText,String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emaiAddressField.sendKeys(emailAddressText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		yesNewsLetterOption.click();
		privacyPolicyCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public boolean displayStatusOPfWarningMessages(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,
			String expectedEmailAddresssWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
		
		boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
		System.out.println("Warning: You must agree to the Privacy Policy!.... is displayed succesfully");
		
		boolean firstNameWarningStatus = firstNameWarning.getText().equals(expectedFirstNameWarning);
		System.out.println("First Name Warning Message.... is displayed succesfully");
		
		boolean lastNameWarningStatus = lastNameWarning.getText().equals(expectedLastNameWarning);
		System.out.println("Last Name Warning Message.... is displayed succesfully");
		
		boolean emailAddressWarningStatus = emaiAddressWarning.getText().equals(expectedEmailAddresssWarning);
		System.out.println("Email Address Warning Message.... is displayed succesfully");
		
		boolean telephoneWarningStatus = telephoneWarning.getText().equals(expectedTelephoneWarning);
		System.out.println("Telephone Warning Message.... is displayed succesfully");
		
		boolean passwordWarningStatus = passwordWarning.getText().equals(expectedPasswordWarning);
		System.out.println("Password Warning Message.... is displayed succesfully");
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailAddressWarningStatus 
				&& telephoneWarningStatus && passwordWarningStatus;
	}
}