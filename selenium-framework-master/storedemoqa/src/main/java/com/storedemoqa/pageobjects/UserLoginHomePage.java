package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.selenium.MyExpectedConditions;

public class UserLoginHomePage extends PageFooter {
	@FindBy(xpath = ".//a[text()='Log out']")
	public WebElement logOut;
	@FindBy(xpath = ".//a[text()='Purchase History']")
	WebElement purchaseHistory;
	@FindBy(xpath = ".//a[text()='Your Details']")
	WebElement yourDetails;
	@FindBy(xpath = ".//a[text()='Your Downloads']")
	WebElement yourDownloads;
	@FindBy(xpath = ".//a[contains(text(),'Howdy,')]")
	WebElement testingUserName;
	@FindBy(xpath = ".//input[@id='wpsc_checkout_form_2']")
	WebElement firstName;
	@FindBy(xpath = ".//input[@id='wpsc_checkout_form_3']")
	WebElement lastName;
	@FindBy(xpath = ".//li[@id='wp-admin-bar-my-account']/a[contains(text(),'Howdy,')]")
	WebElement howdyLink;
	@FindBy(xpath = ".//li[@id='wp-admin-bar-logout']/a[contains(text(),'Log Out')]")
	WebElement logoutFromPopUp;

	public UserLoginHomePage() {
		PageFactory.initElements(driver, this);

	}

	public void verifyPageLoaded() {
		wait.until(MyExpectedConditions.visibilityOf(logOut));
		wait.until(MyExpectedConditions.visibilityOf(purchaseHistory));

	}

	public LoggedOutPageObject logout() {
		LoggedOutPageObject loggedOutPageObject = new LoggedOutPageObject();
		wait.until(MyExpectedConditions.visibilityOf(logOut));
		logOut.click();
		return loggedOutPageObject;
	}

	public void verifyPurchaseHistory() {
		wait.until(MyExpectedConditions.visibilityOf(purchaseHistory));
		purchaseHistory.click();
	}

	public void verifyYourDetailsTab() {
		wait.until(MyExpectedConditions.visibilityOf(yourDetails));
		yourDetails.click();
		wait.until(MyExpectedConditions.visibilityOf(firstName));
		wait.until(MyExpectedConditions.visibilityOf(lastName));
	}

	public void verifyYourDownloadsTab() {
		wait.until(MyExpectedConditions.visibilityOf(yourDownloads));
		yourDownloads.click();
	}

	public LoggedOutPageObject verifyLogoutFromPopUp() {
		LoggedOutPageObject loggedOutPageObject = new LoggedOutPageObject();
		wait.until(MyExpectedConditions.visibilityOf(howdyLink));
		Actions action = new Actions(driver);
		action.moveToElement(howdyLink).perform();
		wait.until(MyExpectedConditions.visibilityOf(logoutFromPopUp));
		logoutFromPopUp.click();
		return loggedOutPageObject;
	}
}
