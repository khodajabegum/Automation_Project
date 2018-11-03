package TestSuite;

import Utilities.Abstract_Class;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import reuseableObject.ReusableMethods;
import reuseableObject.ReusableMethods_Loggers;

import java.io.IOException;
import java.util.List;

public class Yahoo_Search extends Abstract_Class {

    @Test
    public void YahooSearchResult () throws InterruptedException, IOException {
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

        ReusableMethods_Loggers.clickMethod(logger, driver, "//*[@id='uhaa-search-button']","search icon");

        // define javascript executor

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //Scroll to the bottom of the page

        logger.log(LogStatus.INFO, "Scrolling to the bottom of the search result page");

        Thread.sleep(1500);

        jse.executeScript("scroll(0,10000)");



    }// end of test



}//end of parent class

