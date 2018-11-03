package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class_Parallel {
    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;


    @BeforeSuite
    public void openBrowser() {


        // define the report path
        // write a title for the title for the title
        //report = new ExtentReports("src\\main\\java\\Report_folder\\ExtentReport" + UUID.randomUUID()+".html", true);
        report = new ExtentReports("src\\main\\java\\Report_folder\\ExtentReport.html", true);




    } // end of BeforeSuite

    @Parameters("browser")

    @BeforeMethod()
    public void loggerSession(String browser, Method methodName) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
        //System.setProperty("webdriver.ie.driver", "src\\main\\resources\\chromedriver.exe"); for IE
        //System.setProperty("webdriver.safari.driver", "src\\main\\resources\\chromedriver.exe"); for Safari

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("start-maximized", "incognito", "disable-infobars");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            //System.setProperty("WebDriver.gecko.driver" , "src\\main\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.navigate().to("https://www.google.com"); // navigate to a default site
            Thread.sleep(800);
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("ie")) {
            //this is where your ie driver would go.. install ie driver
        } else if (browser.equalsIgnoreCase("safari")) {
            //this is where safari dru=iver would go install the safari driver

        }// end of conditional statement

        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // defining javascript
        jse = (JavascriptExecutor) driver;
        logger = report.startTest(browser.toUpperCase() + "-" + methodName.getName());
        logger.log(LogStatus.INFO, "Automation test started ...");

    }// end of before method

    @AfterMethod()
    public void endLogger() {
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation test scenario ended...");

    }// end of AafterMethod


    @AfterSuite()

    public void closeBrowser() {

        report.flush();
        report.close();
        driver.quit();
        logger.log(LogStatus.INFO, "Automation  test suite ended...");


    }// End of after suite


}
