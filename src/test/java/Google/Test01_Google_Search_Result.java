package Google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import reuseableObject.ReusableMethods;

import java.util.concurrent.TimeUnit;

public class Test01_Google_Search_Result {
    // global or shared variables across methods need to be declared before calling annotations.
    WebDriver driver;


    @BeforeSuite
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized","incognito");

        // define webdriver

         driver = new ChromeDriver(options);
        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterSuite
    public void closeBroser (){

        //driver.quit();
    }

// execute the test
    @Test
    public void TestCase1 () {

        driver.navigate().to("https://www.google.com");
        // Enter a keyword in google search
        ReusableMethods.sendKeysMethod(driver, "//*[@name='q']", "Brooklyn", "search Field");
        //submit on google search

        ReusableMethods.submitMethod(driver,"//*[@value='Google Search']", "Google Search" );

    }
    @Test (dependsOnMethods = "TestCase1")

    public void TestCase2 (){

        try{
            String searchResult = driver.findElement(By.xpath("//*[@id='resultStats']")).getText();
            String[] searchNumber = searchResult.split(" ");
            System.out.println("My search number is " +searchNumber[1]);
        }catch(Exception Error){
            System.out.println("Unable to capture text for search Result");


        }// end of catch


    }

}// end of parent class


