import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;


public class Task_2_Automation {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// Launch and Navigate to URL and set window size (1024x768)
		Navigate_to_URL(driver, "https://ksrtc.in/oprs-web/guest/home.do?h=1");
		
		// Select "CHIKKAMAGALURU" to "BENGALURU" from Popular Routes
		Select_Route(driver);
		
		// Select Date of Departure (Mandatory to Search) 
		Select_Departure_date(driver, js);
		
		// Select Date of Return (Mandatory to Search) 
		Select_Return_date(driver, js);
		
		// Click Search and Select Trip
		Search_and_Select_Trip(driver, wait);
	  	
		// Select Available Seat, Boarding Point and Dropping Point
		Select_Seat_and_Points(driver, wait);
	  	
		// Enter Customer Details and Passenger Data
		Enter_Passenger_Data(driver);
	  	
		// Can't Proceed to Payment without booking return trip so test ends here
	}
	
	
	
	
	
	
	
	public static void Navigate_to_URL(WebDriver driver, String URL) {
		
		driver.get(URL);
		driver.manage().window().setSize(new Dimension(1024, 768));
	}
	
	public static void Select_Route(WebDriver driver) {
		
		WebElement Next_Button = driver.findElement(By.cssSelector("#routeSlider a.carousel-control-next"));
		WebElement Popular_Route = driver.findElement(By.cssSelector("#routeSlider div:nth-child(2) li:nth-child(1)"));
		while(!Popular_Route.isDisplayed()) {
			Next_Button.click();
		}
		Popular_Route.click();
	}
	
	public static void Select_Departure_date(WebDriver driver, JavascriptExecutor js) {
		
		List<WebElement> Departure_Month_Days = driver.findElements(By.cssSelector(".ui-datepicker-calendar tbody tr td a"));
		js.executeScript("arguments[0].scrollIntoView();", Departure_Month_Days.get(0));
		Departure_Month_Days.get(0).click();
	}
	
	public static void Select_Return_date(WebDriver driver, JavascriptExecutor js) {
		
		WebElement ReturnDate = driver.findElement(By.id("txtReturnJourneyDate"));
		ReturnDate.click();
		
		List<WebElement> Return_Month_Days = driver.findElements(By.cssSelector(".ui-datepicker-calendar tbody tr td a"));
		js.executeScript("arguments[0].scrollIntoView();", Return_Month_Days.get(0));
	  	Return_Month_Days.get(0).click();
	}
	
	public static void Search_and_Select_Trip(WebDriver driver, WebDriverWait wait) {
		
		WebElement Search_Button = driver.findElement(By.cssSelector("#bookingsForm div.main-booking button"));
	  	Search_Button.click();
	  	
	  	List<WebElement> Select_Seats = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".select-service input[type=button]")));
	  	Select_Seats.get(0).click();
	}
	
	public static void Select_Seat_and_Points(WebDriver driver, WebDriverWait wait) {
		
		List<WebElement> Available_Seats = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".availSeatClassS")));
	  	Available_Seats.get(0).click();
	  	
	  	List<WebElement> Points = driver.findElements(By.cssSelector("#myTabContent ul li"));
	  	for (WebElement Point : Points) {
	  		Point.click();
	  	}
	}
	
	public static void Enter_Passenger_Data(WebDriver driver) {
		
		WebElement Mobile_Number = driver.findElement(By.name("mobileNo"));
	  	Mobile_Number.sendKeys("6789125987");
	  	
	  	WebElement Email = driver.findElement(By.name("email"));
	  	Email.sendKeys("Test@gmail.com");
	  	
	  	WebElement Passenger_Name = driver.findElement(By.name("passengerName"));
	  	Passenger_Name.sendKeys("Passenger Name");
	  	
	  	Select Passenger_Gender = new Select(driver.findElement(By.name("genderCodeId")));
	  	Passenger_Gender.selectByVisibleText("MALE");
	  	
	  	WebElement Passenger_Age = driver.findElement(By.name("passengerAge"));
	  	Passenger_Age.sendKeys("20");
	  	
	  	Select Concession_IDs = new Select(driver.findElement(By.name("concessionIds")));
	  	Concession_IDs.selectByVisibleText("GENERAL PUBLIC");
	}

}
