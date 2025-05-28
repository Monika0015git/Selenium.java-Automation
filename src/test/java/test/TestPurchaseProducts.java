package test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import excel.ExcelUtils;
import pom.CartPage;
import pom.HomePage;
import pom.PaymentPage;
import pom.ProductsPage;
import pom.SignupLoginPage;

public class TestPurchaseProducts extends BaseTest {
	HomePage home;
	SignupLoginPage login;
	ProductsPage product;
	CartPage cart;
	PaymentPage pay;
	
    private static final String EXCEL_PATH = System.getProperty("user.dir")+"/excelData/userData.xlsx";
    private static final String SHEET_NAME = "ExistingUserCardInfo";
    String userEmail="malini404@gmail.com";
    String password="malini0505";
    
    @Test(dataProvider="cardDetails")
    public void purchaseWomenProduct(String cardName,
                              String cardNumber,
                              String cvc,
                              String expMonth,
                              String expYear) throws InterruptedException {
    	home = new HomePage(driver,wait,actions,js);
    	login= new SignupLoginPage(driver, wait, actions, js);
    	product= new ProductsPage(driver, wait, actions, js);
    	cart= new CartPage(driver, wait, actions, js);
    	pay=new PaymentPage(driver, wait, actions, js, downloadDir);
    	
    	home.clickSignupLoginLink();
    	login.login(userEmail, password);
    	home.clickProductsLink();
    	product.verifyProductsPage();
    	product.selectProductForWomen();
    	product.addToCart();
    	cart.verifyCartPage();
    	cart.verifyCartProducts();
    	cart.clickCheckoutButton();
    	cart.clickPlaceOrderButton();
    	pay.verifyPaymentPage();
    	pay.doPayment(cardName, cardNumber, cvc, expMonth, expYear);
    	pay.verfifyConfirmedOrder();
    	//pay.downloadInvoice("invoice.txt");
    	pay.clickContinueButton();
    	home.logout();
    	
    	
    }
	
    @DataProvider(name = "cardDetails")
    public Object[][] cardDetailsProvider() throws IOException {
        // you could also parameterize this so you pass the email in, but kept simple here:
        String loggedInEmail = userEmail;
        return ExcelUtils.getCardDetailsByEmail(EXCEL_PATH, SHEET_NAME, loggedInEmail);
    }

}
