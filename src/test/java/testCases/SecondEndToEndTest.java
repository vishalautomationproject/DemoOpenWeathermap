package testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.HomepageActions;

public class SecondEndToEndTest extends TestBase {

	public SecondEndToEndTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		init();
		log.info("Starts on the https://openweathermap.org/ ");
	}
	
	@Test
	public void secondTest() throws IOException
	{
			HomepageActions homepage = new HomepageActions(driver);
			log.info("***************************Second test cases Execution started***************************");
			homepage.invalidCityName();
			
	}
	
	@AfterMethod
	public void tearDown()
	{
		log.info("Closing the browser for second test case");
		closeBrowser();
	}

}