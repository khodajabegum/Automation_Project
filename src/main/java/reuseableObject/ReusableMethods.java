package reuseableObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReusableMethods {

    //method for clicking on an element

    public static void clickMethod(WebDriver driver, String locator, String elementName) {
        try {
            System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement clickbtn = driver.findElement(By.xpath(locator));
            clickbtn.click();


        } catch (Exception error) {
            System.out.println("Unable to click on element" + elementName);

        }// end of try and catch
    }// end of click method

    // create a clear method

    public static void clearMethod(WebDriver driver,String locator, String elementName){

        try{
            System.out.println("Clearing the values" +elementName);

            // Store the locator into webelement variable

            WebElement clearField = driver.findElement(By.xpath(locator));
            clearField.clear();

        }catch (Exception error){
            System.out.println("Unable to clear the field" +elementName+ " " +error);

        }// end of try and catch

    }// end of clear method.

// click method my index
    public static void clickMethodbyIndex(WebDriver driver, String locator, int indexNumber, String elementName) {
        try {
            System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            clickbtn.click();

        } catch (Exception error) {
            System.out.println("Unable to click on element" + elementName);

        }// end of try and catch
    }// end of click method
    // submit method
    public static void submitMethod(WebDriver driver, String locator, String elementName) {

        try {
            System.out.println("submitting on element" + elementName);

            //Store the locator into webelement variable

            WebElement submitbtn = driver.findElement(By.xpath(locator));
            submitbtn.submit();


        } catch (Exception error) {
            System.out.println("Unable to submit on element" + elementName);

        }// end of try and catch
    }



//method for entering on an element

    public static void sendKeysMethod(WebDriver driver, String locator, String userInput, String elementName){

        try{
            System.out.println("Entering " + userInput + "in element " + elementName );
            //Store the locator into webelement variable
            WebElement input = driver.findElement(By.xpath(locator));
            input.sendKeys(userInput);

        }catch(Exception Error){
            System.out.println("unable to send info on element " + elementName);

        }// end of try and catch
    }// end of the send key method

    // GetText method

    public  static String  getText(WebDriver driver, String Locator, int indexNum, String elementName){

        String Text = null;

        try{
            System.out.println( "Capturing text " + elementName);

            //store the locator into WebElement variable

             Text = driver.findElements(By.xpath(Locator)).get(indexNum).getText();

        }catch (Exception error){
            System.out.println("Unable to capture text from element" +elementName);

            }// end of try and catch
        return Text;
    }// end of get text method


   // Method for navigating to the site

    public  static void navigate(WebDriver driver,String url){

        try{
            System.out.println("Navigating to" +url );
            driver.navigate().to(url);

        }catch (Exception error){
            System.out.println("Unable to navigate tot he url" +error);

        }// end if try and catch


    }// end of navigate method

    // Create a reuseable methog for dropdown by visible text

    public static void selectByText (WebDriver driver, String locator, String value, String elementName){

        try{
            System.out.println("Selecting " +value+ " from dropdown " +elementName);
            // define the web element

            WebElement element = driver.findElement(By.xpath(locator));
            // define the select variable text
            Select select = new Select(element);
            // Select by visible text

            select.selectByVisibleText(value);

        }catch (Exception error){
            System.out.println("Unable to select a value from dropdown" +elementName + " " +error);
        }// end of try catch


    }// end of dropDown method




}// end of parent class

