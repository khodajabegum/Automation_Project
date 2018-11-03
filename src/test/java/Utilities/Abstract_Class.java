package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class {
    public static WebDriver driver= null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;

    @BeforeSuite
    public void openBrowser (){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized","incognito", "disable-infobars");

        // define webdriver

        driver = new ChromeDriver(options);
        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // define the report path
        // write a title for the title for the title
        //report = new ExtentReports("src\\main\\java\\Report_folder\\ExtentReport" + UUID.randomUUID()+".html", true);
        report = new ExtentReports("src\\main\\java\\Report_folder\\ExtentReport.html", true);

        // defining javascript
        jse = (JavascriptExecutor) driver;




    } // end of BeforeSuite

    @BeforeMethod()
    public void loggerSession(Method methodName){
        logger = report.startTest(methodName.getName());
       logger.log(LogStatus.INFO,"Automation test started ...");

    }// end of before method

    @AfterMethod()
    public void endLogger(){
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation test scenario ended...");

    }// end of AafterMethod


    @AfterSuite()

    public void closeBrowser(){

        report.flush();
        report.close();
        //driver.quit();
        logger.log(LogStatus.INFO, "Automation  test suite ended...");


    }// End of after suite


}
