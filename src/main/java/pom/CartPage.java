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

public class CartPage {
	//----------------------------CART/PLACE ORDER-------------------------
	@FindBy(xpath="//li[@class='active']")
	private WebElement shoppingCartText;
	@FindBy(xpath="//a[text()='Proceed To Checkout']")
	private WebElement proceedCheckoutButton ;
	@FindBy(xpath="//td[@class='cart_description']")
	private List<WebElement> cartProducts; 
	
	@FindBy(xpath="//li[@class='active']")
	private WebElement checkoutText;
	@FindBy(xpath="//a[text()='Place Order']")
	private WebElement placeOrderButton ;
	
	
	//----------------------Constructor----------------------
	private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;


    public CartPage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        this.js = js;
  
        PageFactory.initElements(driver, this);
    }
	//----------------------Methods----------------------
    public void verifyCartPage()
    {
    	try
    	{
    		TestLogger.info("Verifying Cart page");
    	    js.executeScript("arguments[0].scrollIntoView(true);",shoppingCartText);
    	    wait.until(ExpectedConditions.visibilityOf(shoppingCartText));
    		Assert.assertTrue(shoppingCartText.isDisplayed());
    		TestLogger.info("Cart page is displayed and verified successfully with label :"+shoppingCartText.getText());
    		
    	}catch(Exception e)
    	{
    		TestLogger.error("Error in executing verifyCartPage() method");
    		e.printStackTrace();
    	}
    }
    public void verifyCartProducts()
    {
    	try
	    	{
	    		TestLogger.info("Printing all the products added to the cart one by one");
	    		for(WebElement cart:cartProducts)
	    		{
	    			 js.executeScript("arguments[0].scrollIntoView(true);",cart);
	    			 wait.until(ExpectedConditions.visibilityOf(cart));
	    			 TestLogger.info(cart.getText());
	    			 Thread.sleep(2000);
	    		}
	    		TestLogger.info("Completed printing");	    		
	    	}catch(Exception e)
		    	{
		    		TestLogger.error("Error in executing verifyCartProducts() method");
		    		e.printStackTrace();    	
		       }
    }
    public void clickCheckoutButton()
    {
       	try
    	{
    		TestLogger.info("Clicking Proceed_To_Checkout button");
			 js.executeScript("arguments[0].scrollIntoView(true);",proceedCheckoutButton);
			 wait.until(ExpectedConditions.visibilityOf(proceedCheckoutButton));
			 proceedCheckoutButton.click();
			 Thread.sleep(2000);
            Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/checkout");
    		TestLogger.info("Proceed To Checkout button is clicked and navigated to Checkout page");	    		
    	}catch(Exception e)
	    	{
	    		TestLogger.error("Error in executing clickCheckoutButton() method");
	    		e.printStackTrace();    	
	       }
    }
    public void clickPlaceOrderButton()
    {
       	try
    	{
       	    wait.until(ExpectedConditions.visibilityOf(checkoutText));
       	    Assert.assertTrue(checkoutText.isDisplayed());
       	    TestLogger.info("Checkout page is displayed with label : "+ checkoutText.getText());
    		TestLogger.info("Clicking Place Order button");
			 js.executeScript("arguments[0].scrollIntoView(true);",placeOrderButton);
			 wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
			 placeOrderButton.click();
			 Thread.sleep(2000);
            Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/payment");
    		TestLogger.info("Place Order button is clicked and navigated to Payment page");	    		
    	}catch(Exception e)
	    	{
	    		TestLogger.error("Error in executing clickPlaceOrderButton() method");
	    		e.printStackTrace();    	
	       }
    }
}
