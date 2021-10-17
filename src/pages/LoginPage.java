package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		this.actions = actions;

	}

	// Getters
	public WebElement getLogin() {
		return driver.findElement(By.xpath("//li[contains(@class,'filled')]/a"));
	}

	public WebElement getUsernameInputField() {
		return driver.findElement(By.name("username"));
	}

	public WebElement getPasswordInputField() {
		return driver.findElement(By.name("password"));
	}

	public WebElement getLoginButton() {
		return driver.findElement(By.name("btn_submit"));
	}

	// Methods
	// Login method
	public void logIn(String email, String password) {

		this.getUsernameInputField().clear();
		this.getUsernameInputField().sendKeys(email);

		this.getPasswordInputField().clear();
		this.getPasswordInputField().sendKeys(password);

		this.getLoginButton().click();
	}

}
