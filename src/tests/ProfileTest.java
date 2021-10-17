package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException {
		// Open demo.yo-meals.com login page and close location pop-up box
		driver.get(loginPageUrl);
		locationPopupPage.closeLocationPopupBox();
		// Enter valid credentials and login
		Thread.sleep(2000);
		// Credentials
		String email = "customer@dummyid.com";
		String password = "12345678a";
		loginPage.logIn(email, password);
		// Verify that message 'Login Successfull' is shown after successful login
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Login Successfull"),
				"[ERROR] Message 'Login Successfull' is now shown");
		notificationSistemPage.waitMessageToDispear();
		// Open user profile page
		driver.get(profilePageUrl);
		Thread.sleep(2000);
		// Change all user information
		profilePage.setUserInformation("John", "Smith", "12", "064123456789", "85087", "United States", "Arizona",
				"Phoenix");
		// Verify hat message 'Setup Successful' is shown after successful setup
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Setup Successful"),
				"[ERROR] Message 'Setup Successfull' is not shown");
		notificationSistemPage.waitMessageToDispear();
		// Logout from the page
		authPage.logOut();
		// Verify that message 'Logout Successfull!' is shown after logout
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Logout Successfull!"),
				"[ERROR] Message ''Logot Successfull! is not shown");
		notificationSistemPage.waitMessageToDispear();

	}

	@Test(priority = 1)
	public void changeProfileImageTest() throws InterruptedException, IOException, AWTException {
		// Open demo.yo-meals.com login page and close location pop-up box
		driver.get(loginPageUrl);
		locationPopupPage.closeLocationPopupBox();
		// Enter valid credentials and login
		Thread.sleep(2000);
		// Credentials
		String email = "customer@dummyid.com";
		String password = "12345678a";
		loginPage.logIn(email, password);
		// Verify that message 'Login Successfull' is shown after successful login
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Login Successfull"),
				"[ERROR] Message 'Login Successfull' is now shown");
		notificationSistemPage.waitMessageToDispear();
		// Open user profile page
		driver.get(profilePageUrl);
		Thread.sleep(2000);
		// Upload user photo
		profilePage.UploadPhoto();
		// Verify that message 'Profile Image Uploaded Successfully' is shown after
		// profile picture is uploaded successfully
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR] Message 'Profile Image Uploaded Successfully' is not shown");
		notificationSistemPage.waitMessageToDispear();
		// Remove profile picture from user profile
		profilePage.removePhoto();
		// Verify that message 'Profile Image Deleted Successfully' is shown after
		// profile picture is removed successfully
		Assert.assertTrue(notificationSistemPage.returnMessage().contains("Profile Image Deleted Successfully"),
				"[ERROR] Message 'Profile Image Deleted Successfully' is not shown");
		notificationSistemPage.waitMessageToDispear();
	}
}
