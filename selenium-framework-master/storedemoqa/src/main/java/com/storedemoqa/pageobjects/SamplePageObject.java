package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.selenium.MyExpectedConditions;

public class SamplePageObject extends PageFooter {
	@FindBy(xpath = ".//h1[text()='Sample Page']")
	WebElement samplePageHeader;

	public SamplePageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		wait.until(MyExpectedConditions.visibilityOf(samplePageHeader));

	}

}
