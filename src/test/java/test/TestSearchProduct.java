package test;

import org.testng.annotations.Test;

import base.BaseTest;
import pom.HomePage;
import pom.ProductsPage;
import pom.SignupLoginPage;

public class TestSearchProduct extends BaseTest {
	HomePage home;
	SignupLoginPage login;
	ProductsPage product;
	
	String searchItem =" dress";
	String userEmail="moochi2022@gmail.com";
	String passsword="moo1001";
	@Test
	public void testSearchBar() throws InterruptedException
	{
		home= new HomePage(driver,wait,actions,js);
		login= new SignupLoginPage(driver, wait, actions, js);
		product = new ProductsPage(driver,wait,actions,js);
		
		home.clickSignupLoginLink();
		Thread.sleep(3000);
		String loggedInUser= login.login(userEmail, passsword);
		home.clickProductsLink();
		product.verifySearchBar(searchItem);
		home.logout();
		
		
	}

}
