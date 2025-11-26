package V.TemplateSelFramewrk.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import V.TemplateSelFramewrk.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import V.TemplateSelFramewrk.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData", groups= {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        registrationPage.register(
//                input.get("firstName"),
//                input.get("lastName"),
//                input.get("email"),
//                input.get("mobile"),
//                input.get("occupation"),
//                input.get("gender"),
//                input.get("password"),
//                input.get("confirmPassword")
//        );
//
//        // If registration does not redirect to login, navigate to login page here
//        // driver.get("URL_OF_LOGIN_PAGE");

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        checkoutPage cp = cartPage.goToCheckout();
        cp.selectCountry("india");

        ConfirmationPage confirmationPage = cp.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
        // Remove driver.quit() from here
    }

    @Test(dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("ruhi@123gmail.com", "Ruhi@98375");
        OrderPage orderspage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
    }

  
    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file); 
        return file.getAbsolutePath();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\V\\TemplateSelFramewrk\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
   // HashMap<String, String> map = new HashMap<String, String>();
//  map.put("email", "ruhi@123gmail.com");
//  map.put("password", "Ruhi@98375");
//  map.put("product", "ZARA COAT 3");
//
//  HashMap<String, String> map1 = new HashMap<String, String>();
//  map1.put("email", "Savi@123gmail.com");
//  map1.put("password", "Savi@74173");
//  map1.put("product", "ADIDAS ORIGINAL");
	
//    @DataProvider
//    public Object[][] getData() {
//    return new Object[][] {{"ruhi@123gmail.com","Ruhi@98375","ZARA COAT 3"}, {"savi@123gmail.com","Savi@74173","ADIDAS ORIGINAL"}};
//    }

    
    
    
    
    