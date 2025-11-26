package V.TemplateSelFramewrk.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standAlone {
	
public static void main(String[] args) {
	
	        
	        String productName = "ZARA COAT 3";
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.get("https://rahulshettyacademy.com/client");
	      //  LandingPage landingPage = new LandingPage(driver);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();

	        driver.findElement(By.id("userEmail")).sendKeys("ruhi@123gmail.com");
	        driver.findElement(By.id("userPassword")).sendKeys("Ruhi@98375");
	        driver.findElement(By.id("login")).click();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

	        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	        WebElement prod = products.stream()
	                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
	                .findFirst()
	                .orElse(null);

	        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); 

	        
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
	        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	        boolean match = cartProducts.stream()
	                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	        Assert.assertTrue(match);

	        WebElement placeOrderBtn = driver.findElement(By.cssSelector(".totalRow button"));

	       
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0, 800);");

	        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
	        placeOrderBtn.click();

	        Actions a = new Actions(driver);
	        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
	        driver.findElement(By.cssSelector(".action__submit")).click();

	       
	        js.executeScript("window.scrollBy(0, 900);");

	        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order"));

	        driver.close();
	   
}
}
