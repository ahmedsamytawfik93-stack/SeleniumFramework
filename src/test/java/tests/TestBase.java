package tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests{
	public static WebDriver driver;
	public static String downloadsPath = System.getProperty("user.dir") + "/Downloads";
	
	public static ChromeOptions chromeOption() {
		ChromeOptions option = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadsPath);
		option.setExperimentalOption("prefs", chromePrefs);
		option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		return option;
	}
	
	public static FirefoxOptions firefoxOption()
	{
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadsPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdf.js.disabled", true);
		return option;
	}
	
	public static EdgeOptions edgeOption() {
	    EdgeOptions options = new EdgeOptions();
	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("download.default_directory", downloadsPath); // custom download path
	    prefs.put("download.prompt_for_download", false);       // disable download prompt
	    prefs.put("plugins.always_open_pdf_externally", true);  // open PDFs externally instead of in Edge
	    options.setExperimentalOption("prefs", prefs);
	    options.addArguments("--disable-pdfjs");
	    return options;
	}
	
	public static InternetExplorerOptions ieOption() {
	    InternetExplorerOptions options = new InternetExplorerOptions();
	    // Ensure clean session (no cached data, cookies, etc.)
	    options.destructivelyEnsureCleanSession();
	    // Ignore zoom level (useful if system zoom is not 100%)
	    options.ignoreZoomSettings();
	    // Enable native events for better interaction
	    options.enablePersistentHovering();
	    // Optional: run IE in private mode
	    options.addCommandSwitches("-private");
	    return options;
	}
	
	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			// Set path to your ChromeDriver
	        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeOption());
		} else if(browserName.equalsIgnoreCase("firefox")) {
			// Set path to your FirefoxDriver
	        //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver(firefoxOption());
		} else if(browserName.equalsIgnoreCase("edge")) {
			// Set path to your EdgeDriver
	        //System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +"/Drivers/msedgedriver.exe");
			driver = new EdgeDriver(edgeOption());
		} else if(browserName.equalsIgnoreCase("ie")) {
			// Set path to your IEDriver
	        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(ieOption());
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.navigate().to("https://demo.nopcommerce.com/");
	}
	
	// Take screenshot when test case fail and add it in the Screenshots folder
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed \n Taking a screenshot");
			Helper.captureScreenshot(driver, result.getName());
		} else {
			// TODO pass 
		}
	}
	
	@AfterSuite
	public void stopDriver() {
		driver.quit();
	}
}
