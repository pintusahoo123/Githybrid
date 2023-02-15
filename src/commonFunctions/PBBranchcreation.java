package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PBBranchcreation {
	WebDriver driver ;
	public PBBranchcreation (WebDriver driver)
	{
		this.driver = driver;
		
	}
	@FindBy (xpath = "(//input[@id='BtnNewBR'])[1]")
	WebElement ObjNewBranchBtn;
	@FindBy (name = "txtbName")
	WebElement ObjBranchName;
	@FindBy (name = "txtAdd1")
	WebElement ObjAdress1;
	@FindBy (name = "Txtadd2")
	WebElement ObjAdress2;
	@FindBy (name = "txtadd3")
	WebElement ObjAdress3;
	@FindBy (name = "txtArea")
	WebElement ObjArea;
	@FindBy (name = "txtZip")
	WebElement ObjZipcode;
	@FindBy (name = "lst_counrtyU")
	WebElement ObjCountry;
	@FindBy (name = "lst_stateI")
	WebElement ObjState;
	@FindBy (name = "lst_stateI")
	WebElement Objcity;
	@FindBy (name = "btn_insert")
	WebElement ObjSbumitbtn;
	public boolean verify_NewBranch(String BranchName, String Address1, String Address2,String Address3,
			String Area,String Zipcode,String Country,String State,String City) throws Throwable
	{
		this.ObjNewBranchBtn.click();
		this.ObjBranchName.sendKeys(BranchName);
		this.ObjAdress1.sendKeys(Address1);
		this.ObjAdress2.sendKeys(Address2);
		this.ObjAdress3.sendKeys(Address3);
		this.ObjArea.sendKeys(Area);
		this.ObjZipcode.sendKeys(Zipcode);
		new Select(this.ObjCountry).selectByVisibleText(Country);
		new Select(this.ObjState).selectByVisibleText(State);
		new Select(this.Objcity).selectByVisibleText(City);
		this.ObjSbumitbtn.click();
		Thread.sleep(3000);
		String expectedAlert = driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		String ActualAlert = "New Branch with";
		if(expectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))
		{
			Reporter.log(expectedAlert,true);
			return true;
			
		}
		else
		{
			Reporter.log("Fail to create new branch",true);
			return false;
			
		}
				
	}
	

}






