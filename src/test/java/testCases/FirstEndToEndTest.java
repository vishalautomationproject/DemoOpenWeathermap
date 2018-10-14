package testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.HomepageActions;

public class FirstEndToEndTest extends TestBase {
	
	public FirstEndToEndTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		init();
		log.info("Starts on the https://openweathermap.org/ ");
	}
	
	@Test
	public void firstTest() throws IOException
	{
			HomepageActions homepage = new HomepageActions(driver);
			log.info("***************************First test cases Execution started***************************");
			homepage.isWeatherLinkPresent();
			homepage.ismapDropdownPresent();
			homepage.isApiLinkPresent();
			homepage.isSignInLinkDisplayed();
	}
	
	@AfterMethod
	public void tearDown()
	{
		log.info("Closing the browser for first test case");
		closeBrowser();
	}

}
