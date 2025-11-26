package V.TemplateSelFramewrk.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import V.TemplateSelFramewrk.AbstractComponent.abstractComponents;

public class checkoutPage extends abstractComponents {

    WebDriver driver;
    WebDriverWait wait;

    public checkoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(css = ".action__submit")  // double underscore, based on standard naming
    WebElement submit;


    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    public void selectCountry(String countryName) throws InterruptedException {
        Actions a = new Actions(driver);
        System.out.println(countryName + ":countryName");
        country.click();
        //country.sendKeys("india");
        a.sendKeys(country, countryName).build().perform();
        Thread.sleep(5000);


        waitForElementToAppear(By.cssSelector("section.ta-results"));
        System.out.println("Waiting for country options to appear...");

        wait.until(ExpectedConditions.elementToBeClickable(selectCountry));
        selectCountry.click();
        System.out.println("Selected country: " + countryName);
    }


    public void scrollToSubmitButton() {
        // waitForElementToAppear(By.cssSelector(".action_submit"));  // waits until submit button is visible
        //scrollToElement(submit);
        // scrolls the submit button into view

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 800);");

        System.out.println("Scrolled to submit button"); // allows
    }

    public ConfirmationPage submitOrder() throws InterruptedException {
        // Wait for overlays/spinners to disappear before clicking submit
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        // Optionally scroll to the button again
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        Thread.sleep(500); // Small pause to ensure UI is ready
        submit.click();
        return new ConfirmationPage(driver);
    }
}




