package testCases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.TestBase;

public class FirstEndToEndTest extends TestBase {

	public FirstEndToEndTest() throws IOException {
		super();
	}
	
	@BeforeTest
	public void setup() throws IOException
	{
		init();
	}
	
	@Test
	public void firstTest()
	{
		System.out.println("Here");
	}
	
	@AfterTest
	public void tearDown()
	{
		closeBrowser();
	}

}
