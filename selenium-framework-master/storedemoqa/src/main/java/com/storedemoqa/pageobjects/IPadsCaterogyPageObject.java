package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.selenium.MyExpectedConditions;

public class IPadsCaterogyPageObject extends PageFooter {

	public IPadsCaterogyPageObject() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[text()='Magic Mouse']")
	WebElement magicMouse;

	@FindBy(xpath = ".//input[@value='Add To Cart'][@type='submit']")
	WebElement addToCard;

	@FindBy(xpath = ".//h1[text()='iPads']")
	WebElement iPads;

	public void verifyPageLoaded() {
		wait.until(MyExpectedConditions.visibilityOf(iPads));
		wait.until(MyExpectedConditions.visibilityOf(addToCard));

	}

}
