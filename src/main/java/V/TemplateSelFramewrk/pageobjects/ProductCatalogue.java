package V.TemplateSelFramewrk.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import V.TemplateSelFramewrk.AbstractComponent.abstractComponents;

public class ProductCatalogue extends abstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // List of product elements on page
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner1;

    @FindBy(css = ".ngx-spinner-overlay")
    WebElement spinner2;

    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream()
            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
            .findFirst()
            .orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        if (prod != null) {
            prod.findElement(addToCart).click();
            waitForElementToAppear(toastMessage);
            waitForNgAnimatingToDisappear(spinner1);
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }
}
