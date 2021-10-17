package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCartTest() throws InterruptedException {
		// Open demo.yo-meals.com lobster shrimp chicken quesadilla combo meal page and close location pop-up box
		driver.get(lscqComboUrl);
		locationPopupPage.closeLocationPopupBox();
		// Add some quantity to the cart
		mealPage.addMealToTheCart("3");
		// Verify that message 'The Following Errors Occurred: Please Select Location'
		// is shown after adding meal to the cart without setting location
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("The Following Errors Occurred:"),
				"[ERROR] First error message 'The Following Errors Occurred:' is not shown");
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Please Select Location"),
				"[ERROR] Second error message ' Please Select Location' is not shown");
		notificationSistemPage.waitMessageToDispear();
		// Set location "City Center - Albany"
		String location = "City Center - Albany";
		locationPopupPage.openLocationPopupBox();
		locationPopupPage.setLocation(location);
		Thread.sleep(1000);
		// Add meal to the cart
		mealPage.addMealToTheCart("3");
		// Verify that message 'Meal Added To Cart' is shown after adding meal to the cart
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Meal Added To Cart"),
				"[ERROR] Message 'Meal Added To Cart' is not shown");
		notificationSistemPage.waitMessageToDispear();
	}

	@Test(priority = 1)
	public void addMealToFavoriteTest() throws InterruptedException {
		// Open demo.yo-meals.com lobster shrimp chicken quesadilla combo meal page
		driver.get(lscqComboUrl);
		locationPopupPage.closeLocationPopupBox();
		// Add meal to the favorites
		mealPage.addMealToTheFavorites();
		// Verify that message "Please login first!" is shown after adding meal to the
		// favorites without logging in to the site
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Please login first!"),
				"[ERROR] Message 'Please login first!' is not shown");
		notificationSistemPage.waitMessageToDispear();
		// Open demo.yo-meals.com login page
		driver.get(loginPageUrl);
		// Enter valid credentials and login
		Thread.sleep(2000);
		// Credentials
		String email = "customer@dummyid.com";
		String password = "12345678a";
		loginPage.logIn(email, password);
		notificationSistemPage.waitMessageToDispear();
		// Open demo.yo-meals.com lobster shrimp chicken quesadilla combo meal page
		driver.get(lscqComboUrl);
		Thread.sleep(500);
		// Add meal to the favorites
		mealPage.addMealToTheFavorites();
		Thread.sleep(1000);
		// Verify that message "Product has been added to your favorites." is shown
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Product has been added to your favorites."),
				"[ERROR] User added meal to favorites without login");
		notificationSistemPage.waitMessageToDispear();

	}

	@Test(priority = 2)
	public void clearCartTest() throws InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		// Open demo.yo-meals.com meals page and close location pop-up box
		driver.get(mealsPageUrl);
		locationPopupPage.closeLocationPopupBox();
		// Set location "City Center - Albany"
		String location = "City Center - Albany";
		locationPopupPage.openLocationPopupBox();
		locationPopupPage.setLocation(location);
		Thread.sleep(1000);

		// Open meal pages and add to the cart from links in data/Data.xlsx
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet meals = wb.getSheet("Meals");

		for (int i = 1; i <= meals.getLastRowNum(); i++) {

			String mealUrl = meals.getRow(i).getCell(0).getStringCellValue();

			driver.get(mealUrl);
			mealPage.addMealToTheCart("3");
			// Verify that message 'Meal Added To Cart' is shown after adding meal to the
			// cart
			softAssert.assertTrue(notificationSistemPage.returnMessage().contains("Meal Added To Cart"),
					"[ERROR] Message 'Meal Added To Cart' is not shown");
			notificationSistemPage.waitMessageToDispear();

		}
		softAssert.assertAll();
		Thread.sleep(1000);
		// Remove all from the cart
		cartSummaryPage.clearAllFromTheCart();
		// Verify that message'All meals removed from Cart successfully' is shown after
		// deleting all orders
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("All meals removed from Cart successfully"),
				"[ERROR] Message'All meals removed from Cart successfully' is not shown");
		notificationSistemPage.waitMessageToDispear();
		wb.close();
	}
}
