package V.TemplateSelFramewrk.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import V.TemplateSelFramewrk.TestComponents.BaseTest;
import V.TemplateSelFramewrk.pageobjects.CartPage;
import V.TemplateSelFramewrk.pageobjects.ConfirmationPage;
import V.TemplateSelFramewrk.pageobjects.LandingPage;
import V.TemplateSelFramewrk.pageobjects.ProductCatalogue;
import V.TemplateSelFramewrk.pageobjects.checkoutPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public  ConfirmationPage confirmationPage;
	public checkoutPage cp;
 @Given("I landed on Ecommerce Page")
 public void I_landed_on_Ecommerce_Page() throws IOException
 {

	 landingPage=launchApplication();
}	
 
 @Given("^Logged in with username (.+) and password (.+)$")
 public void logged_in_username_and_password(String username,String password) {
	 productCatalogue = landingPage.loginApplication(username,password);
	 }
 @When("^I add Product (.+) to cart$")
public void i_add_product_to_cart(String productName) {
	 List<WebElement> products = productCatalogue.getProductList();
     productCatalogue.addProductToCart(productName);
 }

 @When("^Checkout (.+) and submit the order$")
 public void checkout_submit_order(String productName) throws InterruptedException {
	 CartPage cartPage = productCatalogue.goToCartPage();
     Boolean match = cartPage.VerifyProductDisplay(productName);
     Assert.assertTrue(match);
     checkoutPage cp = cartPage.goToCheckout();
     cp.selectCountry("india");
     confirmationPage = cp.submitOrder();
 } 

 @Then("{string} message is displayed on ConfirmationPage")
 public void message_displayed_confirmationPage(String string) {
	 String confirmMessage = confirmationPage.getConfirmationMessage();

     Assert.assertTrue(confirmMessage.equalsIgnoreCase(string)); 
	 driver.close();
 }

 @Then("^\"([^\"]*)\" message is displayed$")
 public void something_message_is_displayed(String strArg1) throws Throwable{
	  Assert.assertEquals(strArg1,landingPage.getErrorMessage());
	  driver.close();
 }
 
 
}




