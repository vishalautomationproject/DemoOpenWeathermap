package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TestBase 
{
public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public WebDriver driver;
	public Properties properties;
	
	public TestBase() throws IOException
	{
		try {
			properties = new Properties();
			File file = new File("C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\OpenWeatherMap\\src\\resource\\configFiles\\Property.properties");
			FileInputStream fis = new FileInputStream(file); 
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	public void Selectbrowser() throws IOException
	{
		String browser = properties.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\OpenWeatherMap\\src\\resource\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Open the browser : " +browser);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver", "C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\OpenWeatherMap\\src\\resource\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Open the browser : " +browser);
	    }
	}
	
	public void getURL()
	{
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
		waitForLoad(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	}
	
    public void init() throws IOException
    {    
    	String log4jconfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfPath);
    	Selectbrowser();
    	getURL();
    }
        
    public void getScreenShot(String Name)
	{
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\src\\main\\java\\ScreenShot\\";
			File destFile = new File((String) reportDirectory +"_"+  Name + "_" + formater.format(calender.getTime()) + ".png");
			FileUtils.copyFile(src, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/> </a>");
		} catch (IOException e) {
				e.printStackTrace();
		}     
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
		driver.quit();	
	}
}
