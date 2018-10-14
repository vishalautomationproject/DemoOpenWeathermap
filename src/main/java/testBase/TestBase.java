package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.TestUtil;

public class TestBase 
{
public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriver driver;
	public Properties properties;
		
	public TestBase() throws IOException
	{
		try {
			properties = new Properties();
//			File file = new File("E:\\PROJECT\\OpenWeatherMap\\src\\resource\\configFiles\\Property.properties");
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/resource/configFiles/Property.properties");
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
}
	}
	
	public void Selectbrowser() throws IOException
	{
		String browser = properties.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "E:\\PROJECT\\OpenWeatherMap\\src\\resource\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Open the browser : " +browser);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "E:\\PROJECT\\OpenWeatherMap\\src\\resource\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Open the browser : " +browser);
			
		}
			
	}
	
	public void getURL()
	{
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("url"));
		waitForLoad(driver);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}
	
    public void init() throws IOException
    {    
    	String log4jconfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfPath);
    	Selectbrowser();
    	getURL();
    }
        
       
    public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {  
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {   
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public void waitForLoad(WebDriver driver) 
	{
	        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver)
	                    {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(pageLoadCondition);
	 }	
	

	
    public void closeBrowser() 
    {   
		driver.close();
	}
}
