package com.demoqa.selenium;

import java.util.function.Function;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyExpectedConditions {

	public static Function<? super WebDriver, WebElement> visibilityOf(WebElement element) {
		return new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return elementIfVisible(element);
			}
		};
	}

	private static WebElement elementIfVisible(WebElement element) {
		return element.isDisplayed() ? element : null;
	}

	public static Function<? super WebDriver, WebElement> elementToBeClickable(WebElement locator) {
		return new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement element = visibilityOf(locator).apply(driver);
				try {
					if (element != null && element.isEnabled()) {
						return element;
					}
					return null;
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}
		};
	}
}
