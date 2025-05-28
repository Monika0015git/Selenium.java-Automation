package pom;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;
import logs.TestLogger;

public class PaymentPage extends BaseTest {
	//--------------------------PAYMENT CARD------------------------------
	@FindBy(xpath="//li[@class='active']")
	private WebElement paymentText;
	@FindBy(name="name_on_card")
	private WebElement cardNameInputBox;
	@FindBy(name="card_number")
	private WebElement cardNumberInputBox;
	@FindBy(name="cvc")
	private WebElement cvcInputBox;
	@FindBy(name="expiry_month")
	private WebElement expMonthInputBox;
	@FindBy(name="expiry_year")
	private WebElement expYearInputBox;
	@FindBy(xpath="//button[text()='Pay and Confirm Order']")
	private WebElement payConfirmOrderButton;
	
	@FindBy(xpath="//p[text()='Congratulations! Your order has been confirmed!']")
	private WebElement congratulationsOrderMessage;
	@FindBy(xpath="//a[text()='Download Invoice']")
	private WebElement downloadInvoiceButton;
	@FindBy(xpath="//a[text()='Continue']")
	private WebElement continueButton;
	
	
	//-----------------------------Constructor-----------------------------
	
	// private String downloadPath = System.getProperty("user.dir") + "/downloads";
	
	     String downloadPath;
		private WebDriver driver;
	    private WebDriverWait wait;
	    private Actions actions;
	    private JavascriptExecutor js;
	    
	 	    public PaymentPage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js, String downloadPath) {
	        this.driver = driver;
	        this.wait = wait;
	        this.actions = actions;
	        this.js = js;
	        this.downloadPath = downloadPath;
	  
	        PageFactory.initElements(driver, this);
	    }
	//-----------------------------Methods-----------------------------
	    public void verifyPaymentPage()
	    {
	    	try
	    	{
	    		TestLogger.info("Verifying Payment page");
	    	    js.executeScript("arguments[0].scrollIntoView(true);",paymentText);
	    	    wait.until(ExpectedConditions.visibilityOf(paymentText));
	    		Assert.assertTrue(paymentText.isDisplayed());
	    		TestLogger.info("Cart page is displayed and verified successfully with label : "+paymentText.getText());
	    		
	    	}catch(Exception e)
	    	{
	    		TestLogger.error("Error in executing verifyCartPage() method");
	    		e.printStackTrace();
	    	}
	    }
	    
	    public void doPayment(String cardName, String cardNumber, String cvc, String expMonth, String expYear) throws InterruptedException
	       { 
	    	TestLogger.info("Entering card details to proceed payment");
		    	try 
		        	{
			    	   cardNameInputBox.sendKeys(cardName);
			    	   Thread.sleep(2000);
			    	   cardNumberInputBox.sendKeys(cardNumber);
			    	   Thread.sleep(2000);
			    	   cvcInputBox.sendKeys(cvc);
			    	   Thread.sleep(2000);
			    	   expMonthInputBox.sendKeys(expMonth);
			    	   Thread.sleep(2000);
			    	   expYearInputBox.sendKeys(expYear);
			    	   Thread.sleep(2000);
			    	   
			    	   TestLogger.info("clicking Pay and Confirm button");
			    	 ;
			    	   payConfirmOrderButton.click();
			    	   Thread.sleep(2000);
			    	   if(driver.getCurrentUrl().contains("https://www.automationexercise.com/payment_done/"))
			    	   {
			    		   TestLogger.info("Payment is done and order is confirmed");
			    	   }
		    	}catch(Exception e)
				    	{
				    		TestLogger.info("Error in executing doPayment() method");
				    		e.printStackTrace();		    		
				    	}
				    	
	       }
	       public void verfifyConfirmedOrder() throws InterruptedException
	       {
		   try {
			   wait.until(ExpectedConditions.visibilityOf(congratulationsOrderMessage));
				Assert.assertTrue(congratulationsOrderMessage.isDisplayed());
				TestLogger.info("Order is placed ; " + congratulationsOrderMessage.getText());	    		   
	       	   }catch(Exception e)
		       {
		    		TestLogger.info("Error in executing verifyConfirmOrder() method");
		    		e.printStackTrace();		           		   
		       }
          }
	       public void clickContinueButton() 
	       {
	    	   try
	    	   {
	    		   TestLogger.info("clicking Continue button");
		    	    js.executeScript("arguments[0].scrollIntoView(true);",continueButton);
		    	    wait.until(ExpectedConditions.visibilityOf(continueButton));
			    	continueButton.click();
			    	Thread.sleep(2000);
			    	Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/");
			    	TestLogger.info("Continue button is clicked and navigated back to Home page");
	    	   }catch(Exception e)
	    	   {
		    		TestLogger.info("Error in executing clickContinueButton() method");
		    		e.printStackTrace();	
	    	   }
          }
	       public void downloadInvoice(String expectedFileName) throws InterruptedException {
	    	   
	      	    js.executeScript("arguments[0].scrollIntoView(true);",downloadInvoiceButton);	    	   
	           downloadInvoiceButton.click();
	           Thread.sleep(7000); // wait for download

	           File downloadedFile = new File(downloadPath + File.separator + expectedFileName);
	           boolean exists = downloadedFile.exists();

	           Assert.assertTrue(exists, "Invoice file not downloaded: " + expectedFileName);
	           TestLogger.info("Invoice downloaded successfully: " + expectedFileName);
	       }
	       /*
	        * @Test
       public void testInvoiceDownload() throws InterruptedException {
    DownloadPage downloadPage = new DownloadPage(driver, wait, actions, js);
    downloadPage.downloadInvoice("invoice.txt");
}
	        */
	    
}
