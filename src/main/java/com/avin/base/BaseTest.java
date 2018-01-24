package com.avin.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

//import org.apache.bcel.classfile.Method;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.avin.pages.DashBoardPage;
import com.avin.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.TestListener;
import generics.Utility;
@Listeners(TestListener.class)
public abstract class BaseTest implements AutomationConstants {

	public Logger log;
	public WebDriver driver;
	public ExtentTest eTest;
	public static ExtentReports eReport;
	public static String un;
	public static String pw;
	public static String url;
	public static Long iTimeout;
	public static Long eTimeout;
	public boolean loginRequired=true;
	public boolean logoutRequired=true;
	
	public BaseTest()
	{
		log=Logger.getLogger(this.getClass());
	}
	
	@BeforeSuite
	public void initExtentReport()
	{
		log.info("Initializing ExtentReports");
		String now=Utility.getFormattedDateAndTime();
		String reportfile=REPORT_PATH+now+".html";
	    eReport=new ExtentReports(reportfile);
	
	}
	@AfterSuite
	public void publishExtentReport()
	{
		eReport.flush();
	}
	@BeforeTest
	
	
	public void initializingGlobalVariables()
	{
		log.info("Initializing Global Variables");
		un=Utility.getValue(CONFIG_PATH, "UN");
		pw=Utility.getValue(CONFIG_PATH, "PW");
		url=Utility.getValue(CONFIG_PATH, "URL");
		iTimeout=Long.parseLong(Utility.getValue(CONFIG_PATH, "IMPLICIT"));
		eTimeout=Long.parseLong(Utility.getValue(CONFIG_PATH, "EXPLICIT"));
	}
	@Parameters({"browser"})
	@BeforeClass
	public void LaunchingBrowser(String browser)
	{
		log.info("Opening browser : "+browser);
		if (browser.equals("chrome")){
			System.setProperty(CHROME_KEY, CHROME_VALUE);
		driver=new ChromeDriver();
		}
		else
		{
			System.setProperty(GECKO_KEY, GECKO_VALUE);
			driver=new FirefoxDriver();
		}
	}
		@AfterClass
		public void closingBrowser()
		{
			log.info("closing the browser");
			driver.quit();
		}
		@BeforeMethod
		public void loginApp(Method method)
		
		{
			driver.get(url);
			if(loginRequired)
			{
				
				
				log.info("Auto login");
				LoginPage lp=new LoginPage(driver);
//				lp.setUserName(un);
//				lp.setPassword(pw);
				lp.clickLoginBTN(un,pw);
			}
			else
			{
				log.warn("login required");
				loginRequired=true;
			}
			driver.manage().timeouts().implicitlyWait(iTimeout,TimeUnit.SECONDS);
			
			eTest=eReport.startTest(method.getName());
			log.info(eTest);
		}
		@AfterMethod
		public void loggingOut(ITestResult TestNGTestResult)
		{
			if(logoutRequired)
			{
				log.info("AutoLogout");
				DashBoardPage db=new DashBoardPage(driver);
				db.clickLogoutBtn();
				}
			else
			{
				log.warn("logout required");
				logoutRequired=true;
			}
			if(TestNGTestResult.getStatus()==ITestResult.FAILURE)
			{
				eTest.log(LogStatus.FAIL,"Check Log For Details");
			}
			else
			{
				eTest.log(LogStatus.PASS,"Scripts Executed Successfully");
			}
          eReport.endTest(eTest);
		}
		
	}
	



