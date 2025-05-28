package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import config.ConfigReader;
import extent.ExtentManager;
import logs.TestLogger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
public class BaseTest {
	public String downloadDir = System.getProperty("user.dir") + "/downloads";
	protected ChromeOptions options;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    
    protected ConfigReader config;
    public ExtentTest test;
    
//   public String downloadDir = System.getProperty("user.dir") + "/downloads";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
    	config= new ConfigReader();
    	
    //    String downloadDir = System.getProperty("user.dir") + "/downloads";
        initializeDriverWithDownloadDir(downloadDir);
    	
        /*options= new ChromeOptions();
    	options.addArguments("--incognito");
        driver = new ChromeDriver(options);*/
        
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        String url = config.getUrl();
        driver.get(url);
    }
    public void initializeDriverWithDownloadDir(String downloadDir) {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("download.directory_upgrade", true);

        options = new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
   

    // take screenshot and return path
    public String takeScreenshot(String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dst = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
        try {
            org.openqa.selenium.io.FileHandler.copy(src, new File(dst));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dst;
    }

    // dropdown helper
    public void selectByVisibleText(WebElement dropdown, String optionText) 
    {
        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
    }
}




