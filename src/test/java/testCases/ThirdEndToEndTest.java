package testCases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.HomepageActions;

public class ThirdEndToEndTest extends TestBase {

	public ThirdEndToEndTest() throws IOException {
		super();
	}
	
	@BeforeTest
	public void setup() throws IOException
	{
		init();
		log.info("1. Starts on the https://openweathermap.org/ ");
	}
	
	@Test
	public void thirdTest()
	{
		try {
			HomepageActions homepage = new HomepageActions(driver);
			homepage.validCityName();
			//getScreenShot("TC01_Success");
		} catch (Exception e) {
			e.printStackTrace();
			//getScreenShot("TC01_Success");
		}
	}
	
	@AfterTest
	public void tearDown()
	{
		closeBrowser();
	}

}