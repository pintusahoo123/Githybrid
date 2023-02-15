package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBBranchUpdation {
	WebDriver driver;
	public PBBranchUpdation (WebDriver driver)
	{
		this.driver = driver;
		
	}
	@FindBy (xpath =  "(//img)[10]")
	WebElement ObjEditbtn;
	@FindBy (name = "txtbnameU")
	WebElement ObjBranch;
	@FindBy (name = "txtadd1u")
	WebElement ObjAdress;
	@FindBy (name = "txtareaU")
	WebElement ObjArea;
	@FindBy (name = "txtzipu")
	WebElement Objzip;
	@FindBy (name = "btnupdate")
	WebElement ObjUpdation;
	public boolean Verify_BranchUpadte(String BranchName,String Address1,String Area, String Zipcode) throws Throwable
	{
		this.ObjEditbtn.click();
		this.ObjBranch.clear();
		this.ObjBranch.sendKeys(BranchName);
		this.ObjAdress.clear();
		this.ObjAdress.sendKeys(Address1);
		this.ObjArea.clear();
		this.ObjArea.sendKeys(Area);
		this.Objzip.clear();
		this.Objzip.sendKeys(Zipcode);
		String ExpectedAlert = driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		String ActualAlert ="Branch updated";
		if (ExpectedAlert.toLowerCase().contains(ExpectedAlert.toLowerCase()))
		{
			Reporter.log(ExpectedAlert,true);
			return true;
			
		}else
		{
			Reporter.log("unable to Branch Update");
			return false;
			
		}
	}
	
	
	

}
