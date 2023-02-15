package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBLogoutPage {
	WebDriver driver;
	public PBLogoutPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	@FindBy (xpath = "(//img)[4]")
	WebElement objLogout;
	@FindBy (name = "login")
	WebElement ObjLoginbtn;
	public boolean Verify_logout()
	{
		this.objLogout.click();
		if(this.ObjLoginbtn.isDisplayed())
		{
			Reporter.log("Admin Logout succes",true);
			return true;
			
		}
		else
		{
			Reporter.log("Admin Login Failed ",true);
			return false;
			
		}
	}
	

}
