package com.demoqa.webdriver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public interface DriverSetup {
	public WebDriver getWebDriverObject(Proxy proxy);

	public Proxy addResponseCodeChecks(boolean isCheckRequestcode);
}
