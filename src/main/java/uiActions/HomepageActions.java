package uiActions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.TestBase;

public class HomepageActions extends TestBase
{
	public static final Logger log = Logger.getLogger(HomepageActions.class.getName());	

	//Locators
	@FindBy(xpath="//label[contains(text(),'Search')]/../input")
	WebElement searchTextBox;
	
	@FindBy(xpath="//label[contains(text(),'Search')]/../../button")
	WebElement searchButton;
	
	@FindBy(xpath="//*[@id=\"forecast_list_ul\"]/div")
	WebElement notFoundValidationMessage;
		
	@FindBy(xpath="//*[@id='forecast_list_ul']/table/tbody/tr[1]/td[2]/b/a")
	WebElement weatherLocation;
	
	@FindBy(xpath="//img[@class='img-responsive'][@alt='openweathermap']")
	WebElement openWeatherMapLogo;
	
	@FindBy(xpath="//a[contains(text(),'Weather')][@href='/city']")
	WebElement weatherLink;
	
	@FindBy(xpath="//a[@href='/api'][contains(text(),'API')][@class='nav__link bg-hover-color']")
	WebElement apiLink;
	
	@FindBy(xpath="//a[@href='#'][@class='nav__link dropdown-toggle bg-hover-color']")
	WebElement mapDropdown;
	
	@FindBy(xpath="//a[@href='/price'][contains(text(),'Price')]")
	WebElement priceLink;
	
	@FindBy(xpath="//a[@class='pull-right'][@href='//home.openweathermap.org/users/sign_in']")
	WebElement signInLink;
	
	@FindBy(xpath="//*[@class='breadcrumb-title']")
	WebElement textonWeatherPage;
	
	
		
	public HomepageActions(WebDriver driver) throws IOException 
	{
		super();
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);  
	}
	
	public void invalidCityName()
	{		
		searchTextBox.clear();
		searchTextBox.sendKeys(properties.getProperty("invalidCityName"));
		log.info("Enters an invalid city name");
		searchButton.click();
		waitForLoad(driver);
		log.info("Searches for the weather");
		String validationMessage = notFoundValidationMessage.getAttribute("innerText").toString();
		Assert.assertEquals(validationMessage.substring(1),properties.getProperty("alertValidationMessage"));
		log.info("Verifying that website suggests city is \"Not found\"");		
	}
	
	public void validCityName()
	{		
		searchTextBox.clear();
		searchTextBox.sendKeys(properties.getProperty("validCityName"));
		log.info("2. Enters an invalid city name");
		searchButton.click();
		waitForLoad(driver);
		log.info("3. Searches for the weather");
		waitForElement(driver, 5, weatherLocation);		
		Assert.assertEquals(weatherLocation.getText(), properties.getProperty("location"));
		log.info("Verifying that website successfully returns weather details for the city.");
	}
	
	public void isWeatherLinkPresent()
	{
		log.info("Verifying that weather link is present on Home page");
	    Assert.assertEquals(weatherLink.getText(), "Weather");
		
	}
	
	public void ismapDropdownPresent()
	{
		log.info("Verifying that map dropdown is present on Home page");
	    Assert.assertEquals(mapDropdown.getText(), "Maps");
	}
	
	public void isApiLinkPresent()
	{
		log.info("Verifying that API link is present on Home page");
	    Assert.assertEquals(apiLink.getText(), "API");
	}
	
	public void isSignInLinkDisplayed() 
	{
		
		log.info("Verifying that Sign In link is displayed on Home page");
		signInLink.isDisplayed();
	}
	
	
	
}
