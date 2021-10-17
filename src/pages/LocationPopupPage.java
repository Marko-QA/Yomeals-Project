package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		this.actions = actions;

	}

	// Getters

	public WebElement getSelectLocation() {
		return driver.findElement(By.xpath("//*[contains(@class,'location-selector')]/a"));
	}

	public WebElement getClose() {
		return driver.findElement(By.xpath("//*[contains(@class,'close-btn close-btn-white')]"));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.id("locality_keyword"));

	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	// Methods
	// Opens location pop up box
	public void openLocationPopupBox() {
		this.getSelectLocation().click();
	}

	// Setting location by the given value
	public void setLocation(String locationName) {
		this.getKeyword().click();

		String dataValue = getLocationItem(locationName).getAttribute("data-value");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=arguments[1];", getLocationInput(), dataValue);

		js.executeScript("arguments[0].click();", getSubmit());

	}

	// Close location pop up box
	public void closeLocationPopupBox() throws InterruptedException {
		Thread.sleep(1000);

		this.getClose().click();
	}

}