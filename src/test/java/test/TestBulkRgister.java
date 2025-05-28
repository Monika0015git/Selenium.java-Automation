package test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import excel.ExcelUtils;
import pom.HomePage;
import pom.SignupLoginPage;

public class TestBulkRgister extends BaseTest {
	HomePage home;
	SignupLoginPage signup;
	
//	String username = " Sheetal";
//	String userEmail = "sheetal777@gmail.com";
	
	@DataProvider(name = "signupData")
	public Object[][] getSignupData() throws IOException {
	    ExcelUtils excel = new ExcelUtils(System.getProperty("user.dir")+"/excelData/userData.xlsx", "NewUsers");
	    return excel.getData();
	}
    
	  @Test(dataProvider = "signupData")
	    public void testSignup(String username,
	                           String userEmail,
	                           String title,
	                           String password,
	                           String day,
	                           String month,
	                           String year,
	                           String firstname,
	                           String lastname,
	                           String company,
	                           String address1,
	                           String address2,
	                           String country,
	                           String state,
	                           String city,
	                           String zipcode,
	                           String mobile) throws InterruptedException	{
	home = new HomePage(driver, wait, actions, js);
	signup = new SignupLoginPage(driver, wait, actions, js);
	
		home.clickSignupLoginLink();
		Thread.sleep(3000);	
		signup.createNewUser(username, userEmail);
		signup.enterAccountInformation(title, password, day, month, year);
		signup.enterAddressInformation(firstname, lastname, company, address1, address2, country, state, city, zipcode, mobile);
		signup.verifyAccountCreationMessage();
		home.logout();
		
	}


}
