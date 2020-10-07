package com.storedemoqa.testcases;

import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demoqa.exceldtos.EmployeeLogin;
import com.demoqa.selenium.BaseDriverInitilization;
import com.demoqa.testngdataprovider.ExcelReadDataProvider;
import com.storedemoqa.pageobjects.HomePageObject;
import com.storedemoqa.pageobjects.LoggedOutPageObject;
import com.storedemoqa.pageobjects.UserLoginHomePage;
import com.storedemoqa.pageobjects.YourAccountPageObject;

public class HomePageTestCase extends BaseDriverInitilization {
  HomePageObject homePageObject;

  @BeforeMethod
  public void SetUp() {
    homePageObject = new HomePageObject();
  }



  // Test case 4
  @Test(dataProvider = "data-source", enabled = true)
  public void purchaseHistory(final EmployeeLogin emp) {
    System.out.println("in side the test case");
    System.out.println(emp.getEmployeedPassword());
    System.out.println(emp.getEmployeeLogin());
    System.out.println("***********************");
    // Ctrl+shift+C
    loadBaseUrl();
    YourAccountPageObject yourAccountPage = homePageObject.clickMyAccount();
    yourAccountPage.verifyPageLoaded();
    UserLoginHomePage userLoginHome = yourAccountPage.login(emp.getEmployeeLogin(),
        emp.getEmployeedPassword(), emp.getIsValidUser());
    userLoginHome.verifyPageLoaded();
    userLoginHome.verifyPurchaseHistory();
    LoggedOutPageObject loggedOutPageObject = userLoginHome.logout();
    loggedOutPageObject.verifyPageLoaded();

  }

  // Test case 6
  @Test(dataProvider = "data-source", enabled = false)
  public void yourDetails(final EmployeeLogin emp) {
    System.out.println("in side the test case");
    System.out.println(emp.getEmployeedPassword());
    System.out.println(emp.getEmployeeLogin());
    System.out.println("***********************");
    // Ctrl+shift+C
    loadBaseUrl();
    YourAccountPageObject yourAccountPage = homePageObject.clickMyAccount();
    yourAccountPage.verifyPageLoaded();
    UserLoginHomePage userLoginHome = yourAccountPage.login(emp.getEmployeeLogin(),
        emp.getEmployeedPassword(), emp.getIsValidUser());
    userLoginHome.verifyPageLoaded();
    userLoginHome.verifyYourDetailsTab();
    LoggedOutPageObject loggedOutPageObject = userLoginHome.logout();
    loggedOutPageObject.verifyPageLoaded();

  }

  // Test case 5
  @Test(dataProvider = "data-source", enabled = false)
  public void yourDownloadsTab(final EmployeeLogin emp) {
    System.out.println("in side the test case");
    System.out.println(emp.getEmployeedPassword());
    System.out.println(emp.getEmployeeLogin());
    System.out.println("***********************");
    // Ctrl+shift+C
    loadBaseUrl();
    YourAccountPageObject yourAccountPage = homePageObject.clickMyAccount();
    yourAccountPage.verifyPageLoaded();
    UserLoginHomePage userLoginHome = yourAccountPage.login(emp.getEmployeeLogin(),
        emp.getEmployeedPassword(), emp.getIsValidUser());
    userLoginHome.verifyPageLoaded();
    userLoginHome.verifyYourDownloadsTab();
    LoggedOutPageObject loggedOutPageObject = userLoginHome.logout();
    loggedOutPageObject.verifyPageLoaded();

  }

  // Lazy initialization of the data provide of excel data
  // Normal initialization --> not used
  @DataProvider(name = "data-source")
  public Iterator<Object[]> dataOneByOne() {
    System.out.println("in data One by One");
    return new ExcelReadDataProvider("home_login.xlsx", "Sheet1");
  }
}
