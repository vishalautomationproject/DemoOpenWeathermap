package testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActions.HomepageActions;

public class ThirdEndToEndTest extends TestBase {

	public ThirdEndToEndTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		init();
		log.info("Starts on the https://openweathermap.org/ ");
	}
	
	@Test
	public void thirdTest() throws IOException
	{
	    	HomepageActions homepage = new HomepageActions(driver);
	    	log.info("***************************Third test cases Execution started***************************");
			homepage.validCityName();
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		log.info("Closing the browser for third test case");
		closeBrowser();
	}

}