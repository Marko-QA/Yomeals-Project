package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;

	// Pages
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSistemPage notificationSistemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;

	// URLs
	protected String baseUrl = "https://demo.yo-meals.com/";
	protected String loginPageUrl = baseUrl + "guest-user/login-form";
	protected String profilePageUrl = baseUrl + "member/profile";
	protected String lscqComboUrl = baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo";
	protected String mealsPageUrl = baseUrl + "meals";

	@BeforeMethod
	public void beforeMethod() {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		Actions actions = new Actions(driver);

		locationPopupPage = new LocationPopupPage(driver, wait, js, actions);
		loginPage = new LoginPage(driver, wait, js, actions);
		profilePage = new ProfilePage(driver, wait, js, actions);
		authPage = new AuthPage(driver, wait, js, actions);
		mealPage = new MealPage(driver, wait, js, actions);
		notificationSistemPage = new NotificationSistemPage(driver, wait, js, actions);
		cartSummaryPage = new CartSummaryPage(driver, wait, js, actions);
		searchResultPage = new SearchResultPage(driver, wait, js, actions);

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException {

		// using ITestResult.FAILURE is equals to result.getStatus then it enter into if
		// condition
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				// To create reference of TakesScreenshot
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				// Call method to capture screenshot
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				// Copy files to specific location
				// result.getName() will return name of test case so that screenshot name will
				// be same as test case name and SimpleDateFormat() will add date and time to
				// the name
				String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
				FileUtils.copyFile(src, new File("screenshots\\" + result.getName() + " - " + timestamp + ".png"));
				// print message if screenshot is captured
				System.out.println("Successfully captured a screenshot");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		Thread.sleep(4000);
		driver.quit();

	}

}