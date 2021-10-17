package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		this.actions = actions;

	}

	// Getter for all results
	public List<WebElement> getResult() {
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	// Methods
	// Return all names from searched meal names
	public List<String> getNamesOfMeals() {
		List<String> stringArray = new ArrayList<>();
		for (int i = 0; i < getResult().size(); i++) {
			String name = getResult().get(i).getText();
			stringArray.add(name);

		}

		return stringArray;
	}

	// Return number of search results
	public String returnNumberOfTheResults() {

		int counter = getResult().size();
		String counterStr = Integer.toString(counter);

		return counterStr;
	}

}
