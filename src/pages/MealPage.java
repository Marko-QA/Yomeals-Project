package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		this.actions = actions;

	}

	// Getters

	public WebElement getProductQty() {
		return driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddToCartBtn() {
		return driver
				.findElement(By.xpath("//*[contains(@class,'btn btn--primary btn--large js-proceedtoAddInCart ')]"));
	}

	public WebElement getFavorite() {
		return driver.findElement(By.xpath("//*[@title='Favorite']"));// Poslednje Menjao
	}

	// Methods
	// Adding given quantity to the cart
	public void addMealToTheCart(String mealQty) throws InterruptedException {
		getProductQty().sendKeys(Keys.CONTROL, "a", Keys.DELETE);
		Thread.sleep(500);

		getProductQty().sendKeys(mealQty);

		getAddToCartBtn().click();
	}

	// Adding meal to the favorites
	public void addMealToTheFavorites() {
		getFavorite().click();
	}
}
