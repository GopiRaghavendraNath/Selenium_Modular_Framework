package com.demoqa.webdriver;

import org.openqa.selenium.WebDriver;

public interface SeleniumDriverObj {
  public WebDriver getDriver(String browerName, Boolean checkCodes);
}
