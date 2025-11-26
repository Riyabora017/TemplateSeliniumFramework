package V.TemplateSelFramewrk.AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import V.TemplateSelFramewrk.pageobjects.CartPage;
import V.TemplateSelFramewrk.pageobjects.OrderPage;

public class abstractComponents implements SearchContext {

    WebDriver driver;

    public abstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;
    
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    
    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }
    
    public OrderPage goToOrdersPage() {
        orderHeader.click();
       OrderPage orderPage=new  OrderPage(driver);
       return orderPage;
    }
    
    

    public void waitForNgAnimatingToDisappear(WebElement spinner) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }

    public void waitForSpinnerToDisappear(By spinnerLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Override
    public List<WebElement> findElements(By by) {
        // Implement as needed or leave null if unused
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        // Implement as needed or leave null if unused
        return null;
    }
}

