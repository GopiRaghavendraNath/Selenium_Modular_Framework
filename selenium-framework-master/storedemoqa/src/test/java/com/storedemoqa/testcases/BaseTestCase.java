package com.storedemoqa.testcases;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoqa.selenium.BaseDriverInitilization;
import com.demoqa.utils.FilePathUtils;
import com.storedemoqa.pageobjects.CheckOutPageObject;
import com.storedemoqa.pageobjects.HomePageObject;
import com.storedemoqa.pageobjects.IPadsCaterogyPageObject;
import com.storedemoqa.pageobjects.IPhonesCaterogyPageObject;
import com.storedemoqa.pageobjects.IPodsCaterogyPageObject;
import com.storedemoqa.pageobjects.ImacsCaterogyPageObject;
import com.storedemoqa.pageobjects.ProductCaterogyPageObject;
import com.storedemoqa.pageobjects.RegisterationPageObject;
import com.storedemoqa.pageobjects.SamplePageObject;
import com.storedemoqa.pageobjects.YourAccountPageObject;

public class BaseTestCase extends BaseDriverInitilization {
  HomePageObject homePageObject;

  @BeforeMethod
  public void SetUp() {
    homePageObject = new HomePageObject();
  }
  // @Test(invocationCount = 10)

  @Test
  public void loadUrl() {
    Reporter.log("started Load Url Test Case");
    loadBaseUrl();
    YourAccountPageObject yourAccountPageObject = homePageObject.clickMyAccount();
    yourAccountPageObject.verifyPageLoaded();
    RegisterationPageObject registrationPageObj = yourAccountPageObject.clickRegisterLink();
    registrationPageObj.verifyPageLoaded();
  }

  @Test
  public void loadUrlAnother() {
    Reporter.log("started Load Url Test Case");
    System.out.println("env" + System.getenv("allure.version"));
    System.out.println("env" + System.getProperty("allure.version"));
    loadBaseUrl();
    YourAccountPageObject yourAccountPageObject = homePageObject.clickMyAccount();
    yourAccountPageObject.verifyPageLoaded();
    RegisterationPageObject registrationPageObj = yourAccountPageObject.clickRegisterLink();
    registrationPageObj.verifyPageLoaded();
  }

  // Test case 9
  @Test(enabled = false)
  public void checkCategories() {
    loadBaseUrl();
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    loadBaseUrl();
    IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject.goToIPadsCategory();
    ipCaterogyPageObject.verifyPageLoaded();
    loadBaseUrl();
    IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject.goToIPodsCategory();
    ipodsCaterogyPageObject.verifyPageLoaded();
    loadBaseUrl();
    IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject.goToIPhonesCategory();
    iphonesCaterogyPageObject.verifyPageLoaded();
  }

  // Test case 8
  @Test(enabled = false)
  public void goToCheckOutPage() {
    loadBaseUrl();
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    CheckOutPageObject checkoutPage = imacsCaterogyPageObject.goToCheckOutPage();
    // checkoutPage.verifyMagicMouseAddedToCart();
    checkoutPage.verifyIphoneAddedToCart();
  }

  // Test case 10
  @Test(enabled = false)
  public void goToProductDetailsCheckoutPage() {
    loadBaseUrl();
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    CheckOutPageObject checkoutPage = productCategoryObj.goToCheckOutPage();
    checkoutPage.verifyIphoneAddedToCart();
  }

  // Test case 11
  @Test(enabled = false)
  public void verifyItemsAddedToCartPage() {
    loadBaseUrl();
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    CheckOutPageObject checkoutPage = productCategoryObj.goToCheckOutPage();
    checkoutPage.verifyIphoneAddedToCart();
    loadBaseUrl();
    checkoutPage = homePageObject.goToCheckOutPage();
    checkoutPage.verifyIphoneAddedToCart();
  }

  // Test case 12 -- Part A verify SPHOME links
  @Test(enabled = false)
  public void verifyPageFooterLinks() {
    loadBaseUrl();
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    productCategoryObj.clickSpHomeLink();
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    imacsCaterogyPageObject.clickSpHomeLink();
    IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject.goToIPadsCategory();
    ipCaterogyPageObject.verifyPageLoaded();
    ipCaterogyPageObject.clickSpHomeLink();
    IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject.goToIPodsCategory();
    ipodsCaterogyPageObject.verifyPageLoaded();
    ipodsCaterogyPageObject.clickSpHomeLink();
    IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject.goToIPhonesCategory();
    iphonesCaterogyPageObject.verifyPageLoaded();
    iphonesCaterogyPageObject.clickSpHomeLink();
  }

  // Test case 12 -- Part B verify Sample Page links
  @Test(enabled = false)
  public void verifySamplePageLinks() {
    loadBaseUrl();
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    SamplePageObject samplePage = productCategoryObj.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    samplePage = imacsCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject.goToIPadsCategory();
    ipCaterogyPageObject.verifyPageLoaded();
    samplePage = ipCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject.goToIPodsCategory();
    ipodsCaterogyPageObject.verifyPageLoaded();
    samplePage = ipodsCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject.goToIPhonesCategory();
    iphonesCaterogyPageObject.verifyPageLoaded();
    samplePage = iphonesCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
  }

  public static void main(final String[] args) {
    System.out.println(FilePathUtils.getFullPathOfFileFromClassPath("chromedriver.exe"));
  }
}
