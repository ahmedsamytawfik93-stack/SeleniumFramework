package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import data.LoadProperties;
import utilities.Helper;

public class TestBaseSauceLabs {

	// SauceLabs Configurations
	public static final String USERNAME = LoadProperties.sauceLabsData.getProperty("username");
	public static final String ACCESS_KEY = LoadProperties.sauceLabsData.getProperty("accesskey");
	public static final String SAUCE_URL = "http://"+ USERNAME+ ":"+ ACCESS_KEY+ ":"+ LoadProperties.sauceLabsData.getProperty("seleniumURL");
	
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static String BaseURL = "https://demo.nopcommerce.com/";

    @SuppressWarnings("deprecation")
    @BeforeClass
	@Parameters(value={"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {

        RemoteWebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            webDriver = new RemoteWebDriver(new URL(SAUCE_URL), options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            webDriver = new RemoteWebDriver(new URL(SAUCE_URL), options);

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        System.out.println("Starting browser on Grid: " + browser);

        driver.set(webDriver);
        driver.get().get(BaseURL);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed - taking screenshot");
            Helper.captureScreenshot(getDriver(), result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}