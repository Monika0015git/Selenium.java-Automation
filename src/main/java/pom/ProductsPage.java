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

public class ProductsPage {
	//-----------------------------Locators-----------------------------
	
		@FindBy(xpath="//a[@href='/products']  ")
		private WebElement productsLink;
		@FindBy(id="search_product")
	//-----------------------------Seach Bar-----------------------------
		private WebElement searchBox;
		@FindBy(id="submit_search")
		private WebElement submitSearchButton;
		@FindBy(xpath="//div[@class='productinfo text-center']")
		private List<WebElement> searchProducts;
	//-------------------CATEGORY PANEL-----------------------
		@FindBy(xpath="//h2[text()='Category']")
		private WebElement categoryLabel;
		@FindBy(id="accordian")
		private WebElement panelGroupSlider;
	
	//--------------------WOMEN PANEL--------------------	
		@FindBy(xpath="//a[@href='#Women']")
		private WebElement womenPanel;
		@FindBy(xpath="(//a[text()='Dress '])[1]")
		private WebElement womenDress;
		@FindBy(xpath="//a[@href='/product_details/3']")
		private WebElement womenProductViewButton;
	
		//--------------------MEN PANEL-------------------
		@FindBy(xpath="//a[@href='#Men']")
		private WebElement menPanel;
		@FindBy(xpath="//a[text()='Tshirts ']")
		private WebElement menTshirts;
		@FindBy(xpath="//a[@href='/product_details/2']")
		private WebElement menProductViewButton;
	
		//--------------------KIDS PANEL-------------------
		@FindBy(xpath="//a[@href='#Kids']")
		private WebElement kidsPanel;
		@FindBy(xpath="//a[text()='Tops & Shirts ']")
		private WebElement kidsTopsShirts;
		@FindBy(xpath="//a[@href='/product_details/11']")
		private WebElement kidsProductViewButton;
		
		//----------------------------ADD TO CART-------------------------
		@FindBy(xpath="//button[@class='btn btn-default cart']")
		private WebElement addToCartButton;
		@FindBy(xpath="//p[text()='Your product has been added to cart.']")
		private WebElement addedMessage;
		@FindBy(xpath="//u[text()='View Cart']")
		private WebElement viewCartLink ;
	
	//-----------------------------Constructor-----------------------------
	private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;


    public ProductsPage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        this.js = js;
  
        PageFactory.initElements(driver, this);
    }
    //-----------------------------Methods-----------------------------
    public void verifyProductsPage()
    {
    	try
    	{
    		TestLogger.info("Verifying Products page");
    		wait.until(ExpectedConditions.visibilityOf(searchBox));
    		Assert.assertTrue(searchBox.isDisplayed());
    		TestLogger.info("Products page is displayed and verified successfully");
    		
    	}catch(Exception e)
    	{
    		TestLogger.info("Error in executing verifyProductsPage() method");
    		e.printStackTrace();
    	}
    }
    public void verifySearchBar(String productName)
    {
      	try
    	{
      		TestLogger.info("Searching for the products : --- "+productName+" --- in the seach bar");
            js.executeScript("arguments[0].scrollIntoView(true);",searchBox);
            searchBox.sendKeys(productName);
            submitSearchButton.click();
            TestLogger.info("Printing searched products one by one ");
            Thread.sleep(3000);
            for(WebElement product: searchProducts)
            {
            	js.executeScript("arguments[0].scrollIntoView(true);", product);
            	TestLogger.info(product.getText());
            	Thread.sleep(2000);
            }  
            js.executeScript("argumenys[0].scrollIntoView(true);",searchBox); 
    	}catch(Exception e)
    	{
    		TestLogger.info("Error in executing verifySearchBar() method");
    		e.printStackTrace();
    	}
    }
    public void selectProductForWomen()
    {
    	TestLogger.info("Adding products into cart from women category");
    	try
    	{
	        js.executeScript("arguments[0].scrollIntoView(true);", panelGroupSlider);
	    	wait.until(ExpectedConditions.visibilityOf(panelGroupSlider));
	        womenPanel.click();
	        Thread.sleep(1000);
	        TestLogger.info("Women panel is clicked");
	        js.executeScript("arguments[0].click();", womenDress);
	        //womenDress.click();
	        Thread.sleep(7000);
	        js.executeScript("arguments[0].scrollIntoView(true);", womenProductViewButton);
	        Thread.sleep(3000);
	        womenProductViewButton.click();
	        TestLogger.info("Product for women added to the cart");
    		
    	}catch(Exception e) 
    	{
    		TestLogger.info("Error in executing selectProductForWomenn() method");
    		e.printStackTrace();
        }
    }
	public void addToCart() throws InterruptedException 
	{
		TestLogger.info("Clicking Add to Cart button");
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		addToCartButton.click();
        Thread.sleep(2000);
        TestLogger.info("Add to Cart button is clicked");
		wait.until(ExpectedConditions.visibilityOf(addedMessage));
		Assert.assertTrue(addedMessage.isDisplayed(), "Product added to the cart");
		TestLogger.info("Message displayed : "+ addedMessage.getText());
		Thread.sleep(1000);
		viewCartLink.click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/view_cart");
		TestLogger.info("View Cart Link is clicked and navigated to Cart page");
	}
  
}
