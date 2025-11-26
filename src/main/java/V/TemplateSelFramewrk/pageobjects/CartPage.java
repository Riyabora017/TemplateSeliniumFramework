package V.TemplateSelFramewrk.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import V.TemplateSelFramewrk.AbstractComponent.abstractComponents;

public class CartPage extends abstractComponents {

    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> productTitles;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean VerifyProductDisplay(String productName) {
        boolean match = productTitles.stream()
                .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public checkoutPage goToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutEle));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutEle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutEle);

        checkoutPage checkoutPage = new checkoutPage(driver);
        System.out.println("We are here first");
        checkoutPage.scrollToSubmitButton();
        System.out.println("We are here");
        return checkoutPage;
    }
} 
  