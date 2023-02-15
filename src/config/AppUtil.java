package config;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver driver;
	public static Properties concrop;
	@BeforeSuite
	public static void setup() throws Throwable {
		concrop = new Properties();
		concrop.load(new FileInputStream("C:\\Qedge_selenium\\Hybrid_FrameWork\\PropertyFiles\\Environment.properties"));
		if (concrop.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(concrop.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
		else if(concrop.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.get(concrop.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else
		{
			System.out.println("browser value is not matching");
		}
		
	}
	@AfterSuite
	public static void tearDown()
	{
		driver.quit();
	}

}
