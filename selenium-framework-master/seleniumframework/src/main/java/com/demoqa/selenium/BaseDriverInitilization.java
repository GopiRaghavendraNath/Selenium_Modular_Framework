package com.demoqa.selenium;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.demoqa.utils.BaseFrameWorkInitializer;
import com.demoqa.utils.DataUtils;
import com.demoqa.utils.GenericConstants;
import com.demoqa.utils.ReadPropertyData;
import com.demoqa.utils.ReadPropertyDataImpl;
import com.demoqa.utils.ScreenCasting;
import com.demoqa.webdriver.SeleniumDriverObj;
import com.demoqa.webdriver.SeleniumDriverObjImpl;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

@Listeners(value = { com.demoqa.listeners.MyTestngListener.class, com.demoqa.listeners.ExtentReporterNG.class })
public class BaseDriverInitilization {
	private static Logger log = LogManager.getLogger(BaseDriverInitilization.class);

	@BeforeMethod
	public void myBaseDriverInitilization() {
		ReadPropertyData readProp = new ReadPropertyDataImpl(GenericConstants.STORE_DEMO_PROP_FILE_NAME);
		createAndSetRequestsAndResponseChecks(readProp.isCodeChecksEnabled());
		createAndSetScreenCasting(readProp.isRunningDebug());
		SeleniumDriverObj selObj = new SeleniumDriverObjImpl();
		WebDriver driver = selObj.getDriver(readProp.readProperty("browser"), readProp.isCodeChecksEnabled());
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		BaseFrameWorkInitializer.getInstance().setDriver(driver);
		BaseFrameWorkInitializer.getInstance().setDriverWait(wait);
		BaseFrameWorkInitializer.getInstance().setReadProp(readProp);
		BaseFrameWorkInitializer.getInstance().setRunInDebugMode(readProp.isRunningDebug());
		BaseFrameWorkInitializer.getInstance().setCheckResponseCodes(readProp.isCodeChecksEnabled());
	}

	private void createAndSetRequestsAndResponseChecks(final Boolean checkCodes) {
		if (checkCodes) {
			BrowserMobProxy proxy = new BrowserMobProxyServer();
			proxy.start();
			HashSet<CaptureType> enable = new HashSet<CaptureType>();
			enable.add(CaptureType.REQUEST_HEADERS);
			enable.add(CaptureType.REQUEST_CONTENT);
			enable.add(CaptureType.RESPONSE_HEADERS);
			proxy.enableHarCaptureTypes(enable);
			HashSet<CaptureType> disable = new HashSet<CaptureType>();
			disable.add(CaptureType.REQUEST_COOKIES);
			disable.add(CaptureType.RESPONSE_COOKIES);
			proxy.disableHarCaptureTypes(disable);
			BaseFrameWorkInitializer.getInstance().setBrowserMobProxy(proxy);
			System.out.println("started browser mob proxy on port " + proxy.getPort());
		}
	}

	private void createAndSetScreenCasting(final Boolean checkCodes) {
		if (checkCodes) {
			ScreenCasting scrnCst = new ScreenCasting();
			BaseFrameWorkInitializer.getInstance().setScreenCasting(scrnCst);
		}
	}

	@AfterMethod
	public void takeScreenShot(final ITestResult result) {
		stopScreenCasting();
		stopBrowserMobProxy(result);
		WebDriver driver = BaseFrameWorkInitializer.getInstance().getDriver();
		takeScreenShotOnFailure(result, driver);
		driver.close();
		driver.quit();

		BaseFrameWorkInitializer.getInstance().removeThreadVairable();
	}

	private void stopBrowserMobProxy(final ITestResult result) {
		BrowserMobProxy proxy = BaseFrameWorkInitializer.getInstance().getBrowserMobProxy();
		Har har = proxy.getHar();
		try {
			OutputStream fos = new FileOutputStream(result.getMethod().getMethodName() + ".har");
			har.writeTo(fos);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		proxy.stop();
	}

	private void takeScreenShotOnFailure(final ITestResult result, final WebDriver driver) {
		if (ITestResult.FAILURE == result.getStatus()) {
			String screenshotName = DataUtils.getRandomCaptureFileName(result.getName());
			result.setAttribute(GenericConstants.SCREEN_SHOT_REPORTER_ATTRIBUTE, screenshotName);
			screenshotName = "./" + screenshotName;
			SeleniumActions.captureScreenshot(driver, screenshotName);
		}
	}

	private void stopScreenCasting() {
		ScreenCasting scrnCst = BaseFrameWorkInitializer.getInstance().getScreenCasting();
		if (BaseFrameWorkInitializer.getInstance().isRunInDebugMode()) {
			try {
				scrnCst.stopRecording();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void loadBaseUrl() {
		ReadPropertyData readProp = BaseFrameWorkInitializer.getInstance().getReadProp();
		Reporter.log("Loaded the URL is " + readProp.readProperty("baseurl"));
		BaseFrameWorkInitializer.getInstance().getDriver().get(readProp.readProperty("baseurl"));
	}
}
