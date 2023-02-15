package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBbranches {
	@FindBy (xpath = "(//img)[5]")
	WebElement Objbranchesbtn;
	public void ClickBranches()
	{
		Objbranchesbtn.click();
	}
	

}
