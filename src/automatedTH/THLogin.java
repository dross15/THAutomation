package automatedTH;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class THLogin {
	
	public static void main(String[] args) throws InterruptedException {
		/*sets up chrome driver - specifically to drive google chrome*/
		String exePath = "/Users/danielross/Downloads/chromedriver";
		System.setProperty("webdriver.chrome.driver", exePath);
		
		/* launches web browser - in this case google chrome*/
		WebDriver driver = new ChromeDriver();
		
		/*defines first url to go to*/
		String url1 = "https://chgainz.trainheroic.com/app/signup/ch#/welcome";
		
		/*fills out form with information*/
		driver.get(url1);

		/*define strings to pass into fields when get to signup page*/
		String name = "Test" + " " + "Test";
		String phoneNumber = "111-111-1111";
		
		/*calls method generateRandomChars to generate the fields that need to be unique - random username, email, and organization name*/
		String uniqueName = (generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 15));
		
		/*defines all web elements within signup form*/
		WebElement inputFullName = driver.findElement(By.name("fullName"));
		WebElement inputUserName = driver.findElement(By.name("username"));
		WebElement inputEmail = driver.findElement(By.name("email"));
		WebElement inputPassword = driver.findElement(By.name("password"));
		WebElement inputPhone = driver.findElement(By.name("phone"));
		WebElement inputOrg = driver.findElement(By.name("newOrgName"));
		
		/*fills out all the inputs in signup form*/
		inputFullName.sendKeys(name);
		inputUserName.sendKeys(uniqueName);
		inputEmail.sendKeys(uniqueName + "@gmail.com");
		inputPassword.sendKeys(uniqueName);
		inputPhone.sendKeys(phoneNumber);
		inputOrg.sendKeys(uniqueName+"company");
		
		/*waits for submit button to become visible after all fields have been filled correctly and defines the button*/
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".form-submit-button")));
		
		/*clicks submit button*/
		submitButton.click();
		
		/*gives time for page loading*/
		Thread.sleep(1000);
		
		/*while loop- while the submit is still displayed i.e. you were not successful in registering, generate new credentials for the fields that need 
		 * to be unique - username, email, organization, and then resubmit
		 */
		while(driver.findElement(By.cssSelector(".form-submit-button")).isDisplayed()==true){
			String uniqueName2 = (generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 15));
			inputUserName.clear();
			inputUserName.sendKeys(uniqueName2);
			inputEmail.clear();
			inputEmail.sendKeys(uniqueName2 + "@gmail.com");
			inputOrg.clear();
			inputOrg.sendKeys(uniqueName+"company");
			submitButton.click();
			/*gives time for page loading*/
			Thread.sleep(1000);
		}
		
		/*waits until the new url is the team page and then selects the add team button*/
		Boolean url2 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.urlToBe("https://chgainz.trainheroic.com/admin/coach#/team"));
		if(url2){
			WebElement addTeamButton = driver.findElement(By.className("blue-action"));
			addTeamButton.sendKeys(Keys.RETURN);
		}
		
		/*gives time for the modal to pop up*/
		Thread.sleep(1000);
		
		/*defines team title and access code fields and then randomly generates and enters them*/
		WebElement teamTitle = driver.findElement(By.id("input_1"));
		WebElement teamAccessCode = driver.findElement(By.id("input_2"));
		String uniqueName3 = (generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 15));
		teamTitle.sendKeys(uniqueName3);
		teamAccessCode.sendKeys(uniqueName3);
		
		/*defines createTeam button and gives time after the information is filled out to find the click button*/
		WebElement createTeam = driver.findElement(By.cssSelector("md-dialog-actions.layout-row > button:nth-child(3)"));
		Thread.sleep(1000);
		createTeam.click();
		
		/*prints to console Test Successful as have made it to the calendar*/
		System.out.println("Test Successful");
		}

	/*method that generates a random string based on the inputs of character and length above in the method call*/
	public static String generateRandomChars(String candidateChars, int length) {
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
	    }

	    return sb.toString();
	}

}



