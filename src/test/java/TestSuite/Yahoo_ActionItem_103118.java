package TestSuite;

import Utilities.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import reuseableObject.ReusableMethods_Loggers;

import java.io.IOException;
import java.util.List;

public class Yahoo_ActionItem_103118 extends Abstract_Class_Parallel {



    @Test()

    public void yahooActionItemTest () throws IOException, InterruptedException {

        //Navigate to https://www.yahoo.com
        ReusableMethods_Loggers.navigate(logger, driver, "https://www.yahoo.com");

        //Step 2. Assert that we are on the correct page by checking the title = 'Yahoo‘
        String pageTitle = driver.getTitle();
        if(pageTitle.equals("Yahoo")) {
            logger.log(LogStatus.PASS, "The page title matches ");
        }else {
            logger.log(LogStatus.FAIL, "The page title doesn't match" + pageTitle);
        }// end of conditional statement

        //Display the count of options on the left side panel ('Mail', 'News', 'Sports‘ & ‘More Yahoo Sites’)

        List<WebElement> linkCount = driver.findElements(By.xpath("//li[contains(@class,'D(ib) Mstart(21px) Mend(13px)(')]"));
        logger.log(LogStatus.INFO, "The link count is " + linkCount.size());

        //Enter 'Nutrition' on the search bar on the top

        ReusableMethods_Loggers.sendKeysMethod(logger, driver, "//*[@name='p']", "Nutrition", "Search field");

        // Click on ‘Search’ button

        ReusableMethods_Loggers.clickMethod(logger, driver, "//*[@id='uh-search-button']", "Search icon");

        //Scroll down to the page
        Thread.sleep(1500);
        jse.executeScript("scroll(0,5000)");

         //Display the search result Number
     //  ReusableMethods_Loggers.searchResultbySplit(logger, driver, "//*[@class='compPagination']", "Search Result");

        Thread.sleep(2500);

        String searchResult = ReusableMethods_Loggers.getText(logger, driver, "//*[@class='compPagination']", 0, "Search Result");
        String [] arraySearch = searchResult.split("Next");
        logger.log(LogStatus.INFO, "search count is " + arraySearch[1].trim());

       //  Click on Sign In button
        jse.executeScript("scroll(0,-5000)");

        Thread.sleep(1500);

        ReusableMethods_Loggers.clickMethod(logger, driver, "//*[@id='yucs-login_signIn']", "Sign in button");

        Thread.sleep(1500);

        //Verify the Boolean state of checkbox is selected as default //use boolean to check if check box is selected true/false

     Boolean checkBoxState = driver.findElement(By.xpath("//*[@id='persistent']")).isSelected();


        if (checkBoxState == true) {
            logger.log(LogStatus.PASS, "Checkbox is selected ");
        }else{
            logger.log(LogStatus.FAIL, "Checkbox is not selected");

            ReusableMethods_Loggers.getScreenshot(driver, logger, "check mark");
            }

      //Enter invalid user name
        ReusableMethods_Loggers.sendKeysMethod(logger, driver, "//*[@name='username']", "testabc@yahoo.com", "User Name Field");
        Thread.sleep(1500);

        //click on ‘Next’ button
        ReusableMethods_Loggers.clickMethod(logger, driver, "//*[@type='submit']", "Next Button");

        //Capture the error message and verify that if message matches the following string

       // String errMsg = "Sorry, we don't recognize this email.";

        ReusableMethods_Loggers.compareMessagesbyIndex(logger, driver, "//*[@class ='row error']", 0, "Sorry, we don't recognize this email.", "Error message");



        }// end of test annotation



}// end of parent class
