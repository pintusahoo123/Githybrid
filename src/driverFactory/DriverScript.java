package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.PBBranchUpdation;
import commonFunctions.PBBranchcreation;
import commonFunctions.PBLoginPage;
import commonFunctions.PBLogoutPage;
import commonFunctions.PBbranches;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript  extends AppUtil{
	String inputpath = "C:\\Qedge_selenium\\Hybrid_FrameWork\\FileInput\\DataEngine.xlsx";
	String outputpath = "C:\\Qedge_selenium\\Hybrid_FrameWork\\FileOutput\\HybridResults.xlsx";
	String TCSheet = "TestCases";
	String TSSheet = "TestSteps";
	@Test
	public void startTest() throws Throwable
	{
		boolean res = false ;
		String tcres = "";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		// count no rows in both sheets
		int TCCount = xl.rowCount(TCSheet);
		int TSCount = xl.rowCount(TSSheet);
		Reporter.log("no of rows in TCSheet ::"+TCCount+"      "+"        "+"no of rows in TCSheet ::"+TSCount,true);
		//iterate all rows in TCSheet
		for(int i=1; i<TCCount; i++)
		{
			// read module status cell
			String ModuleStatus = xl.getCellData(TCSheet, i, 2);
			if (ModuleStatus.equalsIgnoreCase("Y"))
			{
				// read tcid from TCSheet
				String tcid = xl.getCellData(TCSheet, i, 0);
				// iterate all rows in TSSheet
				for (int j=1; j<=TSCount; j++)
				{
					// read tcid from TSSheet
					String tsid = xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						// read keyword cell
						String Keyword = xl.getCellData(TSSheet, j, 3);
						if(Keyword.equalsIgnoreCase("AdminLogin"))
						{
							PBLoginPage login = PageFactory.initElements(driver, PBLoginPage.class);
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							res = login.verify_login(para1, para2);

						}
						else if (Keyword.equalsIgnoreCase("BranchCreation"))
						{
							PBbranches branches = PageFactory.initElements(driver, PBbranches.class);
							PBBranchcreation newBranch = PageFactory.initElements(driver, PBBranchcreation.class);
							String para1 = xl.getCellData(TSSheet, j, 5);  //branch name
							String para2 = xl.getCellData(TSSheet, j, 6);  // adress1
							String para3 = xl.getCellData(TSSheet, j, 7);  // adress 2
							String para4 = xl.getCellData(TSSheet, j, 8);  // adress3
							String para5 = xl.getCellData(TSSheet, j, 9);  //area
							String para6 = xl.getCellData(TSSheet, j, 10); // zipcode
							String para7 = xl.getCellData(TSSheet, j, 11); //country
							String para8 = xl.getCellData(TSSheet, j, 12); //state
							String para9 = xl.getCellData(TSSheet, j, 13); // city
							branches.ClickBranches();
							res = newBranch.verify_NewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);


						}
						else if(Keyword.equalsIgnoreCase("BranchUpdate"))
						{
							PBbranches branches = PageFactory.initElements(driver, PBbranches.class);
							PBBranchUpdation branchu = PageFactory.initElements(driver, PBBranchUpdation.class);
							String para1 = xl.getCellData(TSSheet, j, 5); //branchname
							String para2 = xl.getCellData(TSSheet, j, 6); //adress1
							String para3 = xl.getCellData(TSSheet, j, 9); // area
							String para4 = xl.getCellData(TSSheet, j, 10); //zipcode
							branches.ClickBranches();
							res = branchu.Verify_BranchUpadte(para1, para2, para3, para4);

						}
						else if (Keyword.equalsIgnoreCase("AdminLogout"))
						{
							PBLogoutPage logout = PageFactory.initElements(driver, PBLogoutPage.class);
							res = logout.Verify_logout();
						}
						String tsres ="";
						if(res)
						{
							// write as pass if res is true
							tsres ="pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						else
						{
							//write as fail if res is false
							tsres = "Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						// if not tcres equal to null then write as pass or fail into tcsheet
						if(!tsres.equalsIgnoreCase("Fail"))
						{
							//assign teststep results to testcase results
							tcres = tsres;
						}

					}

				}
				// write tcres into TCsheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				// write as blocked which tc are flaged to N
				xl.setCellData(TCSheet, i, 3, "Blocked"	,outputpath );

			}
		}
	}


}


