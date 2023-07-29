import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;

public class Automation {

	public static void main(String[] args) {
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe");
		//ChromeDriver driver = new ChromeDriver();
		

		
		WebDriver driver = new ChromeDriver();
		driver.get("https://ksrtc.in/oprs-web/guest/home.do?h=1");
		//driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement Next_Button = driver.findElement(By.cssSelector("#routeSlider a.carousel-control-next"));
		WebElement Popular_Route = driver.findElement(By.cssSelector("#routeSlider div:nth-child(2) li:nth-child(1)"));
		while(!Popular_Route.isDisplayed()) {
			Next_Button.click();
		}
		Popular_Route.click();
				
		List<WebElement> Departure_Month_Days = driver.findElements(By.cssSelector(".ui-datepicker-calendar tbody tr td a"));
		js.executeScript("arguments[0].scrollIntoView();", Departure_Month_Days.get(0));
		Departure_Month_Days.get(0).click();
		
		WebElement ReturnDate = driver.findElement(By.id("txtReturnJourneyDate"));
		ReturnDate.click();
		
		List<WebElement> Return_Month_Days = driver.findElements(By.cssSelector(".ui-datepicker-calendar tbody tr td a"));
		js.executeScript("arguments[0].scrollIntoView();", Return_Month_Days.get(0));
	  	Return_Month_Days.get(0).click();
		
	  	WebElement Search_Button = driver.findElement(By.cssSelector("#bookingsForm div.main-booking button"));
	  	Search_Button.click();
	  	
	  	List<WebElement> Select_Seats = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".select-service input[type=button]")));
	  	Select_Seats.get(0).click();
	  	
	  	
	  	List<WebElement> Available_Seats = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".availSeatClassS")));
	  	Available_Seats.get(0).click();
	  	
	  	List<WebElement> Points = driver.findElements(By.cssSelector("#myTabContent ul li"));
	  	for (WebElement Point : Points) {
	  		Point.click();
	  	}
	  	
	  	WebElement Mobile_Number = driver.findElement(By.name("mobileNo"));
	  	Mobile_Number.sendKeys("6789125987");
	  	
	  	WebElement Email = driver.findElement(By.name("email"));
	  	Email.sendKeys("Test@gmail.com");
	  	
	  	WebElement Passsenger_Name = driver.findElement(By.name("passengerName"));
	  	Passsenger_Name.sendKeys("Passenger Name");
	  	
	  	Select Passsenger_Gender = new Select(driver.findElement(By.name("genderCodeId")));
	  	Passsenger_Gender.selectByVisibleText("MALE");
	  	
	  	WebElement Passsenger_Age = driver.findElement(By.name("passengerAge"));
	  	Passsenger_Age.sendKeys("20");
	  	
	  	Select Concession_IDs = new Select(driver.findElement(By.name("concessionIds")));
	  	Concession_IDs.selectByVisibleText("GENERAL PUBLIC");
	  	
	  	
		//driver.quit();
		
		 

		

	}
}
