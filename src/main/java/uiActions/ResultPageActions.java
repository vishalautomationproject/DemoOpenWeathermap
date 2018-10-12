package uiActions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class ResultPageActions extends TestBase
{
	public static final Logger log = Logger.getLogger(HomepageActions.class.getName());	

	//Locators
	@FindBy(xpath=".//*[@id='menu-item-40']/a")
	WebElement Shopbutton;

	
	public ResultPageActions(WebDriver driver) throws IOException 
	{
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);  
	}
	
	public void method()
	{		
		
	}
}