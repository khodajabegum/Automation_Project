package Yahoo;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import reuseableObject.ReusableMethods_Loggers;

import javax.naming.ldap.ExtendedRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Yahoo_Search_Result {

    /*
    1. navigate to yahoo
    2. verify the home page title as 'yahoo'
    3. verify the count of test link exist on home page
    4. enter a keyword om search field
    5. click on search
    6, scroll to bottom for search result
    7. capture search result
    8. sens it to extend report
    */

    // Declare all the global variable before annotation method
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;


    @BeforeSuite
     public void openBrowser (){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized","incognito");

        // define webdriver

        driver = new ChromeDriver(options);
        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // define the report path
        // write a title for the title for the title
        //report = new ExtentReports("src\\main\\java\\Report_folder\\ExtentReport" + UUID.randomUUID()+".html", true);
        report = new ExtentReports("src\\main\\java\\Report_folder\\ExtentReport.html", true);

    }// end of before suite

    @AfterSuite
    public void closeBrowser(){
        // end the test of the report
        report.endTest(logger);

        //flush the report

        report.flush();
        // close the report
        report.close();

       // driver.quit();

    }// end of Aftersuite

    @Test
    public void YahooSearchResult () throws InterruptedException, IOException {
    // Start the test
        logger = report.startTest("Yahoo search Result");
    // navigate to yahoo homepage

        ReusableMethods_Loggers.navigate(logger, driver, "https://www.yahoo.com");
    // Verify the homepage title

        String yahootitle = driver.getTitle();

        if (yahootitle.equals("Yahoo")){
            logger.log(LogStatus.PASS,"The yahoo title matches");

        }else{
            logger.log(LogStatus.FAIL,"The Yahoo title doesn't math " + yahootitle);

        }// end of conditional statement

        //  verify the list count for the link

        List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class, 'Mstart(')]"));

        logger.log(LogStatus.INFO, "The link count is " + linkCount.size());

        // Enter a keyword on a search field

        ReusableMethods_Loggers.sendKeysMethod(logger, driver, "//*[@name='p']", "Brooklyn", "Search Field");

        ReusableMethods_Loggers.clickMethod(logger, driver, "//*[@type='submit']","search icon");

        // define javascript executor

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //Scroll to the bottom of the page

        logger.log(LogStatus.INFO, "Scrolling to the bottom of the search result page");

        Thread.sleep(1500);

        jse.executeScript("scroll(0,10000)");



    }// end of test


}// end of parent class
