package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		this.actions = actions;

	}

	// getters
	public WebElement getFirstNameInputField() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastNameInputField() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddressInputField() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneInputField() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipInputField() {
		return driver.findElement(By.name("user_zip"));
	}

	public Select selectCountry() {
		Select selectCountry = new Select(driver.findElement(By.name("user_country_id")));
		return selectCountry;
	}

	public Select selectState() {
		Select selectState = new Select(driver.findElement(By.name("user_state_id")));
		return selectState;
	}

	public Select selectCity() {
		Select selectCity = new Select(driver.findElement(By.name("user_city")));
		return selectCity;
	}

	public WebElement getSaveButton() {
		return driver.findElement(By.name("btn_submit"));
	}

	public WebElement getUploadPhotoBtn() {
		return driver.findElement(By.className("upload"));
	}

	public WebElement getRemovePhotoBtn() {
		return driver.findElement(By.className("remove"));
	}

	public WebElement getAvatarArea() {
		return driver.findElement(By.className("avatar"));
	}

	public WebElement getUploadInput() {
		return driver.findElement(By.xpath("//*[@id ='form-upload']/input"));
	}

	// Methods
	// Uploads photo to the user profile
	public void UploadPhoto() throws IOException, AWTException {
		actions.moveToElement(getAvatarArea()).perform();
		getUploadPhotoBtn().click();

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

		File image = new File("img/slika.png");
		String photo = image.getAbsolutePath();
		getUploadInput().sendKeys(photo);
	}

	// Removes photo from the user profile
	public void removePhoto() throws AWTException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", getRemovePhotoBtn());
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	// Setting all user information
	public void setUserInformation(String firstName, String lastName, String address, String phoneNumber,
			String ZipCode, String country, String state, String city) throws InterruptedException {

		getFirstNameInputField().clear();
		getFirstNameInputField().sendKeys(firstName);

		getLastNameInputField().clear();
		getLastNameInputField().sendKeys(lastName);

		getAddressInputField().clear();
		getAddressInputField().sendKeys(address);

		getPhoneInputField().clear();
		getPhoneInputField().sendKeys(phoneNumber);

		getZipInputField().clear();
		getZipInputField().sendKeys(ZipCode);

		Thread.sleep(500);
		selectCountry().selectByVisibleText(country);

		Thread.sleep(500);
		selectState().selectByVisibleText(state);

		Thread.sleep(500);
		selectCity().selectByVisibleText(city);

		getSaveButton().click();

	}

}
