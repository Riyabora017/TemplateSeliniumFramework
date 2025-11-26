package V.TemplateSelFramewrk.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import V.TemplateSelFramewrk.AbstractComponent.abstractComponents;

public class LandingPage extends abstractComponents {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Correct order: driver first, then this
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;
    
    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        return new ProductCatalogue(driver);
    }
 
		

    public String getErrorMessage() {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
	}

   

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
	
	
}
