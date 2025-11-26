package V.TemplateSelFramewrk.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import V.TemplateSelFramewrk.AbstractComponent.abstractComponents;

public class RegistrationPage extends abstractComponents {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstNameInput;

    @FindBy(id = "lastName")
    WebElement lastNameInput;

    @FindBy(id = "userEmail")
    WebElement emailInput;

    @FindBy(id = "userMobile")
    WebElement mobileInput;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationSelect;

    @FindBy(css = "input[type='radio'][value='Male']")
    WebElement genderMaleRadio;

    @FindBy(css = "input[type='radio'][value='Female']")
    WebElement genderFemaleRadio;

    @FindBy(id = "userPassword")
    WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordInput;

    @FindBy(css = "input[type='checkbox'][formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerButton;

    public void enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        mobileInput.clear();
        mobileInput.sendKeys(mobile);
    }

    public void selectOccupation(String occupation) {
        Select select = new Select(occupationSelect);
        select.selectByVisibleText(occupation);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            genderMaleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            genderFemaleRadio.click();
        }
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void checkAgeCheckbox() {
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
    }

    public void clickRegister() {
        registerButton.click();
    }

    public void register(String firstName, String lastName, String email, String mobile, String occupation, String gender, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterMobile(mobile);
        selectOccupation(occupation);
        selectGender(gender);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        checkAgeCheckbox();
        clickRegister();
    }
}