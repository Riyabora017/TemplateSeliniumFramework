
package V.TemplateSelFramewrk.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import V.TemplateSelFramewrk.TestComponents.Retry;

import V.TemplateSelFramewrk.TestComponents.BaseTest;
import V.TemplateSelFramewrk.pageobjects.CartPage;
import V.TemplateSelFramewrk.pageobjects.LandingPage;
import V.TemplateSelFramewrk.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        //String productName = "ZARA COAT 3";
        
       landingPage.loginApplication("ruhi@123gmail.com", "Ruhi@98375");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
    
    
    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
       

        ProductCatalogue productCatalogue = landingPage.loginApplication("ruhi@123gmail.com", "Ruhi@98375");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match, "Product '" + productName + "' not found in cart");

       
    
    }
}


