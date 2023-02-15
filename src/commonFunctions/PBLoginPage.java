package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBLoginPage {
	WebDriver driver;
	public PBLoginPage (WebDriver driver)
	{
		this.driver = driver;
		
	}
	@FindBy(name ="txtuId")
	WebElement Objuser ;
	@FindBy (name ="txtPword")
	WebElement Objpass;
	@FindBy (name = "login")
	WebElement Objloginbtn;
	public boolean verify_login(String Objuser ,String password)
	{
		this.Objuser.sendKeys(Objuser);
		this.Objpass.sendKeys(password);
		this.Objloginbtn.click();
		String Expected = "adminflow";
		String Actual = driver.getCurrentUrl();
		if (Actual.toLowerCase().contains(Expected.toLowerCase()))
		{
			Reporter.log("Admin login succes ::"+Expected+"      "+Actual);
			return true;
			
		}
		else
		{
			Reporter.log("Admin login failed ::"+Expected+"      "+Actual);
			return false;
		}
		
	}
	

}
