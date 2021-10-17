package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		this.actions = actions;

	}

	// getters
	public WebElement getAut() {
		return driver.findElement(By.xpath("//*[contains(@class,'after-arrow user-trigger-js')]"));
	}

	public WebElement getMyAccount() {
		return driver.findElement(By.xpath("//*[text()='My Account']"));
	}

	public WebElement getLogout() {
		return driver.findElement(By.xpath("//*[text()='Logout']"));
	}

	// Methods
	// Logout from the site
	public void logOut() {
		this.getAut().click();
		this.getLogout().click();
	}

}
