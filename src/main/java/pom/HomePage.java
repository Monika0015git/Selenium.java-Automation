package pom;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import logs.TestLogger;

public class HomePage {
	
	@FindBy(xpath="//img[@alt='Website for automation practice']")
	private WebElement logo;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']")
	private WebElement menuBar;
	@FindBy(xpath="//ul[@class='nav navbar-nav']//li/a")
	private List<WebElement> menuBarOptions;
	
	@FindBy(xpath="//a[@href='/login']")
	private WebElement signupLoginLink;
	@FindBy(xpath="//a[@href='/logout']")
	private WebElement logoutLink;
	@FindBy(xpath="//a[@href='/products']")
	private WebElement productsLink;

	
	private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;


    public HomePage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        this.js = js;
  
        PageFactory.initElements(driver, this);
    }
    public void verifyLaunchApplication()
    {
    	TestLogger.info("Launching application");
    	Assert.assertTrue(logo.isDisplayed());
    	TestLogger.info("Application is launched successfully");
    }

    public void verifyMenuBar()
    {
    	try
    	{
    	TestLogger.info("verifying Menu bar");
    	wait.until(ExpectedConditions.visibilityOf(menuBar));
    	Assert.assertTrue(menuBar.isDisplayed());
    	TestLogger.info("Menu bar is displayed");
    	TestLogger.info("printing Menu options one by one : ");
    	for(WebElement option : menuBarOptions)
    	{
    		TestLogger.info(option.getText());
    		Thread.sleep(1000);
    	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    public void clickSignupLoginLink()
    {
    	try 
    {
    	TestLogger.info("clicking Signup/Login ");
    	wait.until(ExpectedConditions.visibilityOf(signupLoginLink));
		js.executeScript("arguments[0].scrollIntoView(true);",signupLoginLink );
    	Assert.assertTrue(signupLoginLink.isDisplayed());
    	actions.moveToElement(signupLoginLink).click().perform();
    	Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/login");
    	TestLogger.info("Signup/Login is clicked and navigated to Login page");
    }catch(Exception e)
    	{
    	    TestLogger.error("Error in executing clickSignupLoginLink() method");
    	    e.printStackTrace();
    	}
    }
    public void clickProductsLink()
    {
    	try 
    {
    	TestLogger.info("clicking Products link ");
    	wait.until(ExpectedConditions.visibilityOf(productsLink));
		js.executeScript("arguments[0].scrollIntoView(true);",productsLink );
    	Assert.assertTrue(productsLink.isDisplayed());
    	actions.moveToElement(productsLink).click().perform();
    	Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/products");
    	TestLogger.info("Products link is clicked and navigated to Products page");
    }catch(Exception e)
    	{
    	    TestLogger.error("Error in executing clickProductsLink() method");
    	    e.printStackTrace();
    	}
    }
   
    public void logout()
    {
		try
		{
			TestLogger.info("Clicking Logout");
			wait.until(ExpectedConditions.visibilityOf(logoutLink));
			js.executeScript("arguments[0].scrollIntoView(true);",logoutLink );
     		Assert.assertTrue(logoutLink.isDisplayed());
			actions.moveToElement(logoutLink).click().perform();
			Thread.sleep(3000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/login");
			TestLogger.info("Logged out of the application");//https://www.automationexercise.com/login
		}catch(Exception e)
		{
			TestLogger.error("Error in executing logout() method");
			e.printStackTrace();
		}
    }

}
