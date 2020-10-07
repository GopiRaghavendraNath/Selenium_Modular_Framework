package com.storedemoqa.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demoqa.selenium.MyExpectedConditions;

import ru.yandex.qatools.allure.annotations.Step;

public class YourAccountPageObject extends PageFooter {
	@FindBy(xpath = ".//*[@id='meta']/ul/li[1]/a")
	public WebElement registerLink;
	@FindBy(name = "")
	WebElement registerNameLink;
	@FindBy(xpath = ".//input[@id='wp-submit']")
	WebElement linkTest;
	@FindBy(partialLinkText = "")
	WebElement partialLinkTextRegister;
	@FindBy(xpath = ".//input[@id='log']")
	WebElement loginputText;
	@FindBy(xpath = ".//input[@id='pwd']")
	WebElement pwdinputText;
	@FindBy(xpath = ".//input[@id='login'][@type='submit']")
	WebElement loginButton;
	@FindBy(xpath = ".//p[contains(text(),'Invalid login credentials')]")
	WebElement invalidCredentials;

	public YourAccountPageObject() {
		PageFactory.initElements(driver, this);
	}

	@Step("Verify My account page is loaded")
	public void verifyPageLoaded() {
		wait.until(MyExpectedConditions.visibilityOf(registerLink));
		wait.until(MyExpectedConditions.visibilityOf(loginputText));
		wait.until(MyExpectedConditions.visibilityOf(pwdinputText));
		wait.until(MyExpectedConditions.visibilityOf(loginButton));
	}

	public RegisterationPageObject clickRegisterLink() {
		Reporter.log("Click on Register Link");
		RegisterationPageObject registerPageObject = new RegisterationPageObject();
		registerLink.click();
		return registerPageObject;
	}

	public UserLoginHomePage login(final String userName, final String password, final boolean isValidUser) {
		System.out.println("user login ");
		UserLoginHomePage userLoginHomePage = new UserLoginHomePage();
		loginputText.sendKeys(userName);
		pwdinputText.sendKeys(password);
		pwdinputText.sendKeys(Keys.TAB);
		wait.until(MyExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		System.out.println("user login after click ");

		if (!(isValidUser)) {
			wait.until(MyExpectedConditions.visibilityOf(invalidCredentials));
		}
		return userLoginHomePage;
	}
}
