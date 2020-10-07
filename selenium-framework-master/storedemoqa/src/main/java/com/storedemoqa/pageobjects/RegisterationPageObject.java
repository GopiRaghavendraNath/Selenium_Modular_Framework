package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demoqa.selenium.MyExpectedConditions;

public class RegisterationPageObject extends PageFooter {

	@FindBy(xpath = ".//*[@id='user_login']")
	public WebElement userLogin;
	@FindBy(xpath = ".//*[@id='user_email']")
	WebElement userEmail;
	@FindBy(xpath = ".//input[@id='wp-submit']")
	WebElement linkTest;
	@FindBy(partialLinkText = "Log")
	WebElement partialLinkTextRegister;

	public RegisterationPageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		Reporter.log("Navigated to registration page");
		wait.until(MyExpectedConditions.visibilityOf(userLogin));
		wait.until(MyExpectedConditions.visibilityOf(userEmail));
		wait.until(MyExpectedConditions.visibilityOf(linkTest));
		wait.until(MyExpectedConditions.visibilityOf(partialLinkTextRegister));
	}
}
