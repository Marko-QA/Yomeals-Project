package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public BasicPage(WebDriver driver, WebDriverWait wait) {
	
		this.driver = driver;
		this.wait = wait;
	}
	
	
}
