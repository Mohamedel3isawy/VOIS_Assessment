import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;

public class Task_1_Automation {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// Launch and Navigate to URL and set window size (1024x768)
		// Used this URL to Amazon website instead of https://www.amazon.com/ to avoid human verification
		Navigate_to_URL(driver , "https://www.amazon.com/gp/goldbox?ref_=nav_cs_gb");
		
		// Enter "car accessories" in amazon search bar and click search
		Search_Website(driver);
		
		// Select first item from search results to open its page then click Add to Cart button
		Select_First_Item_and_Add_to_Cart(driver, wait);
		
		// Open cart and check if total items in cart = 1
		Open_Cart_and_validate_Item_is_Added(driver, wait);
		
		// Close browser
		driver.quit();
	}
	
	
	
	
	
	
	public static void Navigate_to_URL(WebDriver driver, String URL) {
		
		driver.get(URL);
		driver.manage().window().setSize(new Dimension(1024, 768));
	}
	
	public static void Search_Website(WebDriver driver) {
		
		WebElement Search_Box = driver.findElement(By.id("twotabsearchtextbox"));
		Search_Box.sendKeys("car accessories");
		
		WebElement Search_Button = driver.findElement(By.id("nav-search-submit-button"));
		Search_Button.click();
	}
	
	public static void Select_First_Item_and_Add_to_Cart(WebDriver driver, WebDriverWait wait) {
		
		List<WebElement> Search_Results = driver.findElements(By.xpath("//h2/a"));
		Search_Results.get(0).click();
		
		WebElement Add_to_Cart_Button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
		Add_to_Cart_Button.click();
	}
	
	public static void Open_Cart_and_validate_Item_is_Added(WebDriver driver, WebDriverWait wait) {
		
		WebElement Open_Cart_Button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart")));
		Open_Cart_Button.click();
		
		if(driver.findElement(By.id("sc-subtotal-label-activecart")).isDisplayed()) {
			WebElement Cart_Total = driver.findElement(By.id("sc-subtotal-label-activecart"));
			String Cart_Total_Text = Cart_Total.getText();
			
			if (Cart_Total_Text.equals("Subtotal (1 item):")) {
				System.out.println("TC Passed and item is added to Cart Successfully");
			}
			else {
				System.out.println("TC Failed");
			}
		}
		else {
			System.out.println("TC Failed");
		}
	}

}
