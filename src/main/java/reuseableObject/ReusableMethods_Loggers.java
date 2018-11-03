package reuseableObject;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ReusableMethods_Loggers {

    // Method for navigating to the site

    public static void navigate(ExtentTest logger, WebDriver driver, String url) throws IOException {

        try {
            // System.out.println("Navigating to" +url );
            logger.log(LogStatus.INFO, "Navigating to " + url);
            driver.navigate().to(url);

        } catch (Exception error) {
            //System.out.println("Unable to navigate tot he url" +error);
            logger.log(LogStatus.FAIL, "Unable to navigate tot he url" + error);
            getScreenshot(driver, logger, "URL Error");
        }// end if try and catch


    }// end of navigate method


    //method for clicking on an element

    public static void clickMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
        try {
            // System.out.println("Clicking on element" + elementName);
            logger.log(LogStatus.INFO, "Clicking on element " + elementName);
            //Store the locator into webelement variable

            WebElement clickbtn = driver.findElement(By.xpath(locator));
            clickbtn.click();
            //Optional
            //logger.log(LogStatus.PASS,"Successfully clicked on element"+ elementName );
        } catch (Exception error) {
            //System.out.println("Unable to click on element" + elementName);
            logger.log(LogStatus.FAIL, "Unable to click on element " + elementName + " " + error);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
    }// end of click method

    // create a clear method

    public static void clearMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {

        try {
            //System.out.println("Clearing the values" +elementName);
            logger.log(LogStatus.INFO, "Clearing the values " + elementName);

            // Store the locator into webelement variable

            WebElement clearField = driver.findElement(By.xpath(locator));
            clearField.clear();

        } catch (Exception error) {
            //System.out.println("Unable to clear the field" +elementName+ " " +error);
            logger.log(LogStatus.FAIL, "Unable to clear the field " + elementName + " " + error);
            getScreenshot(driver, logger, elementName);

        }// end of try and catch

    }// end of clear method.

    // click method my index
    public static void clickMethodbyIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
        try {
            logger.log(LogStatus.INFO, "Clicking on element " + elementName);
            // System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            clickbtn.click();

        } catch (Exception error) {
            //System.out.println("Unable to click on element" + elementName);
            logger.log(LogStatus.FAIL, "unable to click on element " + elementName + " " + error);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
    }// end of click method


    // submit method
    public static void submitMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {

        try {
            //System.out.println("submitting on element " + elementName);

            logger.log(LogStatus.INFO, " submitting on element " + elementName);

            //Store the locator into webelement variable

            WebElement submitbtn = driver.findElement(By.xpath(locator));
            submitbtn.submit();


        } catch (Exception error) {
            //System.out.println("Unable to submit on element" + elementName);
            logger.log(LogStatus.FAIL, "Unable to submit on element " + elementName);
            getScreenshot(driver, logger, elementName);

        }// end of try and catch
    }


//method for entering on an element

    public static void sendKeysMethod(ExtentTest logger, WebDriver driver, String locator, String userInput, String elementName) throws IOException {

        try {
            //System.out.println("Entering " + userInput + "in element " + elementName );
            logger.log(LogStatus.INFO, "Entering " + userInput + "in element " + elementName);
            //Store the locator into webelement variable
            WebElement input = driver.findElement(By.xpath(locator));
            input.sendKeys(userInput);

        } catch (Exception Error) {
            logger.log(LogStatus.FAIL, "unable to send info on element " + elementName);
            //System.out.println("unable to send info on element " + elementName);
            getScreenshot(driver, logger, elementName);

        }// end of try and catch
    }// end of the send key method

    // GetText method

    public static String getText(ExtentTest logger, WebDriver driver, String Locator, int indexNum, String elementName) throws IOException {

        String Text = null;

        try {
            // System.out.println( "Capturing text " + elementName);
            logger.log(LogStatus.INFO, "Capturing text " + elementName);

            //store the locator into WebElement variable

            Text = driver.findElements(By.xpath(Locator)).get(indexNum).getText();

        } catch (Exception error) {
            //System.out.println("Unable to capture text from element" +elementName);
            logger.log(LogStatus.FAIL, "Unable to capture text from element " + elementName);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
        return Text;
    }// end of get text method




    // Search result by split
    public static String searchResultbySplit(ExtentTest logger, WebDriver driver, String locator, String elementName) {

        String[] arraySearch = null;
        try {
            logger.log(LogStatus.INFO, "Capturing search result" + elementName);
            WebElement txtElement = driver.findElement(By.xpath(locator));
            String text = txtElement.getText();
           arraySearch = text.split("Next");
          logger.log(LogStatus.PASS, "Captured text is " + arraySearch[1] );


        } catch (Exception error) {
            logger.log(LogStatus.FAIL, "Unable to capture the search result " + elementName);

        }// end of try and catch

        return arraySearch[1];

    }// end of the method


    /*
    try{
            String searchResult = driver.findElement(By.xpath("//*[@id='resultStats']")).getText();
            String[] searchNumber = searchResult.split(" ");
            System.out.println("My search number is " +searchNumber[1]);
        }catch(Exception Error){
            System.out.println("Unable to capture text for search Result");


        }// end of catch
    */


    // Create a reuseable methog for dropdown by visible text

    public static void selectByText (ExtentTest logger, WebDriver driver, String locator, String value, String elementName) throws IOException {

        try{
            //System.out.println("Selecting " +value+ " from dropdown " +elementName);
            logger.log(LogStatus.INFO,"Selecting " +value+ " from dropdown " +elementName );
            // define the web element

            WebElement element = driver.findElement(By.xpath(locator));
            // define the select variable text
            Select select = new Select(element);
            // Select by visible text

            select.selectByVisibleText(value);

        }catch (Exception error){
            //System.out.println("Unable to select a value from dropdown" +elementName + " " +error);
            logger.log(LogStatus.FAIL, "Unable to select a value from dropdown " + elementName + " " +error);
            getScreenshot(driver, logger, elementName);
        }// end of try catch


    }// end of dropDown method

    // method for screen shot

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_folder\\ScreenShot\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);

    }

    // Method ofr Mouse over

    public static void mouseHover (ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {

        Actions mouseHover = new Actions(driver);

        try{
            logger.log(LogStatus.INFO, "using mouse hover on element " +elementName);
            WebElement element= driver.findElement(By.xpath(locator));
            mouseHover.moveToElement(element).perform();

        }catch(Exception error){
            logger.log(LogStatus.FAIL, "Unable to hover over the element "+elementName);
            getScreenshot(driver, logger, elementName);


        }// end try and catch

    }// end of MouseHover method

    // mouse over by index
    public static void mouseOverByIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
        Actions mouseHoverByIndex = new Actions(driver);
        try {
            logger.log(LogStatus.INFO, "Hovering on element" +elementName);
            // System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement hover = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseHoverByIndex.moveToElement(hover).perform();
        } catch (Exception error) {
            //System.out.println("Unable to hover on element" + elementName);
            logger.log(LogStatus.FAIL, "unable to hover on element "+ elementName+ " "+error);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
    }// end of click method

    // mouse movement method for hove by index


    // Mouseover click by index
    public static void mouseOverClickByIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
        Actions mouseHoverClickByIndex = new Actions(driver);
        try {
            logger.log(LogStatus.INFO, "Clicking on element " +elementName);
            // System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseHoverClickByIndex.moveToElement(clickbtn).click().perform();
        } catch (Exception error) {
            //System.out.println("Unable to click on element" + elementName);
            logger.log(LogStatus.FAIL, "unable to hover on element "+ elementName+ " "+error);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
    }// end of click method

    //  // Method for comparing two data

    public static String compareMessage(ExtentTest logger, WebDriver driver, String locator, String expectedMessage, String elementName ) throws IOException {
        Actions mouseMovement = new Actions(driver);
        String text = null;

        try {

            logger.log(LogStatus.INFO, "Capturing text is" + elementName);
            WebElement txtElement = driver.findElement(By.xpath(locator));
            text = txtElement.getText();
            if (text.equalsIgnoreCase(expectedMessage)) {
                logger.log(LogStatus.PASS, "Captured text Matched with " + expectedMessage);
            } else {
                logger.log(LogStatus.FAIL, "Captured text didn't match " + expectedMessage);
            }
        } catch (Exception error) {
            logger.log(LogStatus.FAIL, "Unable to find the specific text of " + expectedMessage);
            getScreenshot(driver, logger, elementName);
        }
        return text;

    }// end of textSelectionwithif

    // Method for comparing two data with index

    public static void compareMessagesbyIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String expectedMessage, String elementName) throws IOException {

        try{
            String actualMessage = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
            if(expectedMessage.equalsIgnoreCase(actualMessage)){
                logger.log(LogStatus.PASS, "Expected matches with actual for element "+elementName);
            }else{
                logger.log(LogStatus.FAIL, "Expected - " + expectedMessage + " doesn't match with actual - " + actualMessage );

                getScreenshot(driver, logger, elementName);
            }

        }catch(Exception error){
            logger.log(LogStatus.FAIL,"Unable to locate element " + elementName );

            getScreenshot(driver, logger, elementName);

        }// end of try and catch

    }// end of comparing data




}// end of parent class

