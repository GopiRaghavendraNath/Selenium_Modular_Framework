package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demoqa.selenium.MyExpectedConditions;

import ru.yandex.qatools.allure.annotations.Step;

public class HomePageObject extends PageFooter {
	@FindBy(xpath = ".//a[text()='My Account']")
	public WebElement myAccount;
	@FindBy(xpath = ".//a[text()='Product Category']")
	WebElement productCatergoy;
	@FindBy(xpath = ".//a[text()='All Product']")
	WebElement allProductsPage;
	@FindBy(xpath = ".//ul[@id='slide_menu']")
	WebElement slideMenu;
	@FindBy(xpath = ".//a[text()='iMacs']")
	WebElement imacsCategory;
	@FindBy(xpath = ".//a[text()='iPads']")
	WebElement iPadsCategory;
	@FindBy(xpath = ".//a[text()='iPhones']")
	WebElement iPhonesCategory;
	@FindBy(xpath = ".//a[text()='iPods']")
	WebElement iPodsCategory;
	@FindBy(xpath = ".//a[text()='Checkout']")
	WebElement checkoutFromHome;

	public HomePageObject() {
		PageFactory.initElements(driver, this);
	}

	@Step("Click on my Account")
	public YourAccountPageObject clickMyAccount() {
		Reporter.log("Click on my Account");
		YourAccountPageObject yourAccountPageObject = new YourAccountPageObject();
		wait.until(MyExpectedConditions.elementToBeClickable(myAccount));
		myAccount.click();
		return yourAccountPageObject;
	}

	public ProductCaterogyPageObject gotToProductCategory() {
		ProductCaterogyPageObject productCaterogyPageObject = new ProductCaterogyPageObject();
		wait.until(MyExpectedConditions.visibilityOf(productCatergoy));
		productCatergoy.click();
		return productCaterogyPageObject;
	}

	public ImacsCaterogyPageObject goToImacsCategory() {
		ImacsCaterogyPageObject imacsCatergoryPageObject = new ImacsCaterogyPageObject();
		wait.until(MyExpectedConditions.visibilityOf(productCatergoy));
		Actions action = new Actions(driver);
		action.moveToElement(productCatergoy).perform();
		wait.until(MyExpectedConditions.visibilityOf(imacsCategory));
		imacsCategory.click();
		return imacsCatergoryPageObject;
	}

	public IPadsCaterogyPageObject goToIPadsCategory() {
		IPadsCaterogyPageObject iPadsCatergoryPageObject = new IPadsCaterogyPageObject();
		Actions action = new Actions(driver);
		wait.until(MyExpectedConditions.visibilityOf(productCatergoy));
		action.moveToElement(productCatergoy).perform();
		wait.until(MyExpectedConditions.visibilityOf(iPadsCategory));
		iPadsCategory.click();
		return iPadsCatergoryPageObject;
	}

	public IPodsCaterogyPageObject goToIPodsCategory() {
		IPodsCaterogyPageObject iPodsCatergoryPageObject = new IPodsCaterogyPageObject();
		Actions action = new Actions(driver);
		wait.until(MyExpectedConditions.visibilityOf(productCatergoy));
		action.moveToElement(productCatergoy).perform();
		wait.until(MyExpectedConditions.visibilityOf(iPodsCategory));
		iPodsCategory.click();
		return iPodsCatergoryPageObject;
	}

	public IPhonesCaterogyPageObject goToIPhonesCategory() {
		IPhonesCaterogyPageObject iPhonesCatergoryPageObject = new IPhonesCaterogyPageObject();
		Actions action = new Actions(driver);
		wait.until(MyExpectedConditions.visibilityOf(productCatergoy));
		action.moveToElement(productCatergoy).perform();
		wait.until(MyExpectedConditions.visibilityOf(iPhonesCategory));
		iPhonesCategory.click();
		return iPhonesCatergoryPageObject;
	}

	public CheckOutPageObject goToCheckOutPage() {
		CheckOutPageObject checkOutPageObject = new CheckOutPageObject();
		wait.until(MyExpectedConditions.visibilityOf(checkoutFromHome));
		checkoutFromHome.click();
		return checkOutPageObject;
	}

	public void verifyPageLoaded() {
		// wait.until(MyExpectedConditions.visibilityOf(productCatergoy));
		wait.until(MyExpectedConditions.visibilityOf(allProductsPage));
		wait.until(MyExpectedConditions.visibilityOf(slideMenu));

	}
}
