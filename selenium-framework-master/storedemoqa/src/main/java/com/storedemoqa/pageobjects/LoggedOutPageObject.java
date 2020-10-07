package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.selenium.MyExpectedConditions;

public class LoggedOutPageObject extends PageFooter {

	@FindBy(xpath = ".//p[contains(text(),'You are now logged out')]")
	WebElement loggedOutText;

	public LoggedOutPageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		wait.until(MyExpectedConditions.visibilityOf(loggedOutText));

	}
}
