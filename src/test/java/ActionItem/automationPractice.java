package ActionItem;

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
import reuseableObject.ReusableMethods;
import reuseableObject.ReusableMethods_Loggers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class automationPractice {

    // Declaring the global variable

    WebDriver driver;
    ExtentReports report;
    ExtentTest logger1;
    ExtentTest logger2;
    JavascriptExecutor jse;

    @BeforeSuite
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized", "incognito", "disable-infobars");

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

    }// End of beofore suite annotation

    @AfterSuite
    public void clodeBrowser() {
        report.endTest(logger1);
        report.endTest(logger2);
        report.flush();
        report.close();
        //driver.quit();

    } // end of after suite

    @Test
    public void testcase1() throws IOException, InterruptedException {

        logger1 = report.startTest("Proceed to Check out for Tshirts");

        // Navigate to http://automationpractice.com/index.php
        ReusableMethods_Loggers.navigate(logger1, driver, "http://automationpractice.com/index.php");

        //Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL

        String pageTitle = driver.getTitle();
        if (pageTitle.equals("My-Store")) {
            logger1.log(LogStatus.PASS, "The Page title matches ");

        } else {
            logger1.log(LogStatus.FAIL, "The Page title Doesn't match" + pageTitle);

        }// end of conditional statement

        //using mouseHover method hover over Women tab

        ReusableMethods_Loggers.mouseHover(logger1, driver, "//*[@title='Women']", "Woman tab");
        Thread.sleep(1500);

        //click on T-shirts link from there
        ReusableMethods_Loggers.clickMethod(logger1, driver, "//*[@title='T-shirts']", "T-shirt link");
        // scroll down about 350 times on next page


        //Scroll to the bottom of the page

        // logger1.log(LogStatus.INFO, "Scrolling down ");

        Thread.sleep(1500);

        jse.executeScript("scroll(0,350)");

        //now hover over the picture with women in it

        ReusableMethods_Loggers.mouseHover(logger1, driver, "//*[@alt ='Faded Short Sleeve T-shirts']", "women image");

        //click on add to cart button
        ReusableMethods_Loggers.clickMethod(logger1, driver, "//*[@title='Add to cart']", "Add to Cart");

        //on the pop up using if else verify the message appears
        Thread.sleep(2500);
        //ReusableMethods_Loggers.selectByText(logger1, driver, "//*[@class='icon-ok']", "Product successfully added to your shopping cart","text");

        ReusableMethods_Loggers.compareMessagesbyIndex(logger1, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2", 0, "Product successfully added to your shopping cart", "Check Point");

        // ReusableMethods_Loggers.compareMessage(logger1, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2",
        // "Product successfully added to your shopping cart", "text message");

        //click on proceed to checkout button

        Thread.sleep(3000);
        ReusableMethods_Loggers.clickMethod(logger1, driver, "//*[@title='Proceed to checkout']", "Checkout button");

        //change the quantity to 3 items

        Thread.sleep(2000);

        ReusableMethods_Loggers.clearMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']", "quantity field");

        ReusableMethods_Loggers.sendKeysMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']", "3", "Increase quantity field");

        //click on proceed to check out

        Thread.sleep(3000);

        ReusableMethods_Loggers.clickMethod(logger1, driver, "//*[@class='button btn btn-default standard-checkout button-medium']", "Proceed to checkout");
        Thread.sleep(3000);


    } // end of test 1

    @Test(dependsOnMethods = "testcase1")

    public void testcase2() throws IOException, InterruptedException {
        logger2 = report.startTest("Procedd to Checkout for Summer Dresses");

        //Hover over Dresses tab

        ReusableMethods_Loggers.mouseOverByIndex(logger2, driver, "//*[@class='sf-with-ul']", 3, "Dresses");
        Thread.sleep(3000);

        //click on Summer Dresses
        ReusableMethods_Loggers.clickMethodbyIndex(logger2, driver, "//*[@title='Summer Dresses']", 1, "summer dresses");
        Thread.sleep(3000);

        //Scroll to the bottom of the page

        logger2.log(LogStatus.INFO, "Scrolling down ");

        // Thread.sleep(1500);

        jse.executeScript("scroll(0,300)");

        //hover over first picture of the dress

        Thread.sleep(1500);
        ReusableMethods_Loggers.mouseOverByIndex(logger2, driver, "//*[@title='Printed Summer Dress']", 2, "First dress");

        Thread.sleep(2000);
        //click on More tab
        ReusableMethods_Loggers.clickMethodbyIndex(logger2, driver, "//*[@title='View']", 0, "more Button");

        Thread.sleep(1500);

        //change the quantity to 4
        ReusableMethods_Loggers.clearMethod(logger2, driver, "//*[@name='qty']", "quantity filed");

        ReusableMethods_Loggers.sendKeysMethod(logger2, driver, "//*[@name='qty']", "4", "quantity field");

        //select a size from dropdown(S,M or L)
        ReusableMethods_Loggers.selectByText(logger2, driver, "//*[@name='group_1']", "M", "Size");

        //click on 'Add to Car' button
        ReusableMethods_Loggers.clickMethod(logger2, driver, "//*[@class='exclusive']", "Add to Cart");

        Thread.sleep(2000);
        //on pop up verify checkpoint says Product successfully added to your shopping cart using if else condition with logger.pass and fail

        ReusableMethods_Loggers.compareMessagesbyIndex(logger2, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2", 0, "Product successfully added to your shopping cart", "Check point");
        //ReusableMethods_Loggers.compareMessage(logger2, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2",
        //"Product successfully added to your shopping cart", "text message");
        Thread.sleep(2000);

        //click on proceed to checkout

        ReusableMethods_Loggers.clickMethodbyIndex(logger2, driver, "//*[@title='Proceed to checkout']", 0, "Proceed to checkout");

        Thread.sleep(2500);
        // next page click on delete icon to delete the item

        ReusableMethods_Loggers.clickMethod(logger2, driver, "//*[@class='cart_quantity_delete']", "delete icon");

        Thread.sleep(3000);
        ReusableMethods_Loggers.clickMethod(logger2, driver, "//*[@class='cart_quantity_delete']", "Second delete icon");

        Thread.sleep(1500);
        //on next page verify following message appears using if else
        ReusableMethods_Loggers.compareMessagesbyIndex(logger2, driver, "//*[@class='alert alert-warning']", 0, "Your shopping cart is empty.", "check point");

        //ReusableMethods_Loggers.compareMessage(logger2, driver, "//*[@class='alert alert-warning']", "Your shopping cart is empty.","Message");
        Thread.sleep(2000);


    }// End of test case


}// end of the parent class
