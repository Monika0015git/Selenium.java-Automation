package pom;

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

public class SignupLoginPage extends BaseTest {
	//---------------------LOGIN------------------------------
		@FindBy(xpath="//h2[text()='Login to your account']")
		private WebElement loginToYourAccountSection;
		@FindBy(xpath="(//input[@name='email'])[1]")
		private WebElement loginUserEmailInputBox;
		@FindBy(xpath="//input[@placeholder='Password']")
		private WebElement loginpasswordInputBox;
		@FindBy(xpath="//button[text()='Login']")
		private WebElement loginButton;
		@FindBy(xpath="//i[@class='fa fa-user']")
		private WebElement loggedInUserLabel;
		//---------------------SIGN-UP----------------------------
		@FindBy(xpath="//h2[text()='New User Signup!']")
		private WebElement newUserSignupSection;
		@FindBy(name="name")
		private WebElement usernameInputBox;
		@FindBy(xpath="(//input[@name='email'])[2]")
		private WebElement emailAddressInputBox;
		@FindBy(xpath="//button[text()='Signup']")
		private WebElement signupButton;
		
		//-------------------- ACCOUNT INFORMATION-------------------------------
		@FindBy(xpath="//b[text()='Enter Account Information']")
		private WebElement enterAccountInformationLabel;
		@FindBy(xpath="//input[@value='Mr']")
		private WebElement mrRadioButton; 
		@FindBy(xpath="//input[@value='Mrs']")
		private WebElement mrsRadioButton; 
		@FindBy(id="name")
		private WebElement nameInputBox;
		@FindBy(id="email")
		private WebElement emailInputBox;
		@FindBy(id="password")
		private WebElement passwordInputBox;
		@FindBy(xpath="//label[text()='Date of Birth']")
		private WebElement dateOfBirthLabel;
		@FindBy(id="days")
		private WebElement daysDropdown;
		@FindBy(id="months")
		private WebElement monthsDropdown;
		@FindBy(id="years")
		private WebElement yearsDropdown;
		@FindBy(id="newsletter")
		private WebElement newsLetterCheckbox;
		@FindBy(id="optin")
		private WebElement specialOfferCheckbox;
		
		//-------------------- ADDRESS INFORMATION-------------------------------
		@FindBy(xpath="//b[text()='Address Information']")
		private WebElement addressInformationLabel;
		
		@FindBy(id="first_name")
		private WebElement firstNameInputBox; 
		@FindBy(id="last_name")
		private WebElement lastNameInputBox; 
		@FindBy(id="address1")
		private WebElement addressOneInputBox; 
		@FindBy(id="address2")
		private WebElement addressTwoInputBox; 
		@FindBy(id="company")
		private WebElement companyInputBox;
		@FindBy(id="country")
		private WebElement countryDropdown; 
		@FindBy(id="state")
		private WebElement stateInputBox; 
		@FindBy(id="city")
		private WebElement cityInputBox;
		@FindBy(id="zipcode")
		private WebElement zipcodeInputBox;
		@FindBy(id="mobile_number")
		private WebElement mobileNumberInputBox;
		@FindBy(xpath="//button[text()='Create Account']")
		private WebElement createAccountButton;
		
	   //---------------ACCOUNT CREATED SUCCESS MESSAGE---------------------
		@FindBy(xpath="//b[text()='Account Created!']")
		private WebElement accountCreatedText;
		@FindBy(xpath="//p[text()='Congratulations! Your new account has been successfully created!']")
		private WebElement accountCreatedSuccessText;
		@FindBy(xpath="//div[@class='pull-right']")
		private WebElement continueButton;
		
		@FindBy(xpath="//a[@href='/logout']")
		private WebElement logoutButton;
		//--------------------------Constructor--------------------------
		WebDriver driver;
		Actions action;
		JavascriptExecutor js;
		WebDriverWait wait;
		
		public SignupLoginPage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js)
		{
	        this.driver = driver;
	        this.wait = wait;
	        this.action = actions;
	        this.js = js;
	    
	        PageFactory.initElements(driver, this);
		}
		//--------------------------Methods--------------------------
		public String login(String userEmail, String password)
		{
			try {
			js.executeScript("arguments[0].scrollIntoView(true);", loginToYourAccountSection);
			Assert.assertTrue(loginToYourAccountSection.isDisplayed());
			loginUserEmailInputBox.sendKeys(userEmail);
			Thread.sleep(1000);
			loginpasswordInputBox.sendKeys(password);
			Thread.sleep(1000);
			loginButton.click();
			wait.until(ExpectedConditions.visibilityOf(loggedInUserLabel));
			Assert.assertTrue(loggedInUserLabel.isDisplayed());
			TestLogger.info("User logged in successfully with label displayed:  "+loggedInUserLabel.getText());		
			}catch(Exception e)
			{
				TestLogger.info("Error in executing login() method");
				e.printStackTrace();
			}
			return userEmail;
		}
		public void createNewUser(String name, String email)
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOf(newUserSignupSection));
				Assert.assertTrue(newUserSignupSection.isDisplayed());
				TestLogger.info("New_User_Signup section is displayed");
				usernameInputBox.sendKeys(name);
				Thread.sleep(1000);
				emailAddressInputBox.sendKeys(email);
				Thread.sleep(1000);
				signupButton.click();
				Thread.sleep(3000);
				Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/signup");
				TestLogger.info("Signup button is clicked");//https://www.automationexercise.com/signup
			}catch(Exception e)
			{
				TestLogger.error("Error on executing createNewUser() method ");
				e.printStackTrace();
			}				
	    }
		public void enterAccountInformation(String title, String password, String day, String month, String year)
		{
				try 
				{
					wait.until(ExpectedConditions.visibilityOf(enterAccountInformationLabel));
					Assert.assertTrue(enterAccountInformationLabel.isDisplayed(), "Enter Account Information section is displayed");
					TestLogger.info("Account_Information section is displayed");
					
					boolean checkTitle= title.equalsIgnoreCase("Mr");
					if (checkTitle) {
						mrRadioButton.click();
					} else {
						mrsRadioButton.click();
					}
					Thread.sleep(2000);
					
					passwordInputBox.sendKeys(password);
					Thread.sleep(2000);
					
					js.executeScript("arguments[0].scrollIntoView(true);", dateOfBirthLabel);
					
					selectByVisibleText(daysDropdown, day);
					Thread.sleep(2000);
					
					selectByVisibleText(monthsDropdown, month);
					Thread.sleep(2000);
					
					selectByVisibleText(yearsDropdown, year);
					Thread.sleep(4000);
					
//					newsLetterCheckbox.click();
//					Assert.assertTrue(newsLetterCheckbox.isSelected());
//					specialOfferCheckbox.click();
//					Assert.assertTrue(specialOfferCheckbox.isSelected());			
				}catch(Exception e)
				{
					TestLogger.error("Error on executing enterAccountInformation() method");
					e.printStackTrace();
				}
		}
		public void enterAddressInformation(String firstname, String lastname, String company, String address1,String address2 , String country, String state, String city, String zipcode, String mobile)
		{
			try
			{
				js.executeScript("arguments[0].scrollIntoView(true);", addressInformationLabel);
				wait.until(ExpectedConditions.visibilityOf(addressInformationLabel));
				Assert.assertTrue(addressInformationLabel.isDisplayed(), "Addresst Information section is displayed");
				TestLogger.info("Address_Information section is displayed");
				TestLogger.info("Entering all user details");
				Thread.sleep(2000);
				
				firstNameInputBox.sendKeys(firstname);
				Thread.sleep(2000);
				
				lastNameInputBox.sendKeys(lastname);
				Thread.sleep(2000);
				
				companyInputBox.sendKeys(company);
				Thread.sleep(2000);
				
				addressOneInputBox.sendKeys(address1);
				Thread.sleep(2000);
				
				addressTwoInputBox.sendKeys(address2);
				Thread.sleep(2000);
				
				selectByVisibleText(countryDropdown, country);
				Thread.sleep(2000);
				
				js.executeScript("arguments[0].scrollIntoView(true);", countryDropdown);
				wait.until(ExpectedConditions.visibilityOf(countryDropdown));
				
				stateInputBox.sendKeys(state);
				Thread.sleep(2000);
				
				cityInputBox.sendKeys(city);
				Thread.sleep(2000);
				
				zipcodeInputBox.sendKeys(zipcode);
				Thread.sleep(2000);
				
				mobileNumberInputBox.sendKeys(mobile);
				Thread.sleep(2000);
				
				createAccountButton.click();
				Thread.sleep(2000);
				Assert.assertEquals(driver.getCurrentUrl(),"https://www.automationexercise.com/account_created");
				TestLogger.info("Create_Account_Button is clicked");
				
			}catch(Exception e)
			{
				TestLogger.error("Error on executing enterAddressInformation() method");
				e.printStackTrace();
			}
		}
		public void verifyAccountCreationMessage()
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOf(accountCreatedText));
				Assert.assertTrue(accountCreatedText.isDisplayed(), "Account is successfully created");
				TestLogger.info("Account is successfully created");
				TestLogger.info("Account creation message displayed : "+accountCreatedSuccessText.getText() );
				continueButton.click();
				Thread.sleep(2000);			
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/");
				TestLogger.info("Continue button is clicked and navigated to Home page");
			}catch(Exception e)
			{
				TestLogger.error("Error on executing verifyAccountCreation() method");
				e.printStackTrace();
			}
		}
		
		
		

}
