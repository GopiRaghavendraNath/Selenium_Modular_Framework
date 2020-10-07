package com.demoqa.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.demoqa.utils.BaseFrameWorkInitializer;
import com.demoqa.utils.FilePathUtils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;

public enum DriverType implements DriverSetup {
	FIREFOX {

		@Override
		public WebDriver getWebDriverObject(final Proxy proxy) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (proxy != null) {
				firefoxOptions.setCapability("proxy", proxy);
			}
			System.setProperty("webdriver.gecko.driver", FilePathUtils.getDriverFullPath("geckodriver.exe"));
			return new FirefoxDriver(firefoxOptions);
		}

		@Override
		public Proxy addResponseCodeChecks(final boolean isCheckRequestcode) {
			return DriverType.setBrowserProxy(isCheckRequestcode);
		}

	},
	CHROME {

		@Override
		public WebDriver getWebDriverObject(final Proxy proxy) {
			ChromeOptions options = new ChromeOptions();
			options.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, String> chromePreferences = new HashMap<String, String>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			options.setCapability("chrome.prefs", chromePreferences);
			System.setProperty("webdriver.chrome.driver", FilePathUtils.getDriverFullPath("chromedriver"));
			options.addArguments("test-type");
			options.addArguments("--no-sandbox");
			options.setCapability("chrome.binary", FilePathUtils.getDriverFullPath("chromedriver"));
			if (proxy != null) {
				options.setCapability("proxy", proxy);
			}
			return new ChromeDriver(options);
		}

		@Override
		public Proxy addResponseCodeChecks(final boolean isCheckRequestcode) {
			return DriverType.setBrowserProxy(isCheckRequestcode);

		}

	},
	IE {

		@Override
		public WebDriver getWebDriverObject(final Proxy proxy) {
			InternetExplorerOptions ieOption = new InternetExplorerOptions();
			ieOption.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			ieOption.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			ieOption.setCapability("requireWindowFocus", true);
			ieOption.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			if (proxy != null) {
				ieOption.setCapability("proxy", proxy);
			}
			System.setProperty("webdriver.ie.driver", FilePathUtils.getDriverFullPath("IEDriverServer.exe"));
			return new InternetExplorerDriver(ieOption);
		}

		@Override
		public Proxy addResponseCodeChecks(final boolean isCheckRequestcode) {
			return DriverType.setBrowserProxy(isCheckRequestcode);

		}

	},
	SAFARI {

		@Override
		public WebDriver getWebDriverObject(final Proxy proxy) {
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.setCapability("safari.cleanSession", true);
			if (proxy != null) {
				safariOptions.setCapability("proxy", proxy);
			}
			return new SafariDriver(safariOptions);
		}

		@Override
		public Proxy addResponseCodeChecks(final boolean isCheckRequestcode) {
			return DriverType.setBrowserProxy(isCheckRequestcode);
		}

	};

	private static Proxy setBrowserProxy(final Boolean checkCodes) {
		if (checkCodes) {
			Logger logger = LogManager.getLogger(DriverSetup.class);
			logger.info("setting the proxy");
			BrowserMobProxy proxy = BaseFrameWorkInitializer.getInstance().getBrowserMobProxy();
			return ClientUtil.createSeleniumProxy(proxy);
		}
		return null;
	}

	public static DriverType getRandomBrowser() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}
