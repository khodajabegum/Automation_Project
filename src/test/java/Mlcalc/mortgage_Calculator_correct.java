package Mlcalc;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reuseableObject.ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class mortgage_Calculator_correct {

    //Declare webdriver here since it's a global variable
    WebDriver driver;
    //Declare all readable & writnle excel workbook and worksheet here since its global
    Workbook readable;
    Sheet readableSheet;
    WritableWorkbook writable;
    WritableSheet writableSheet;
    int rows;
    SoftAssert softAssert = new SoftAssert();

    @BeforeSuite

    public void openBrowser() throws IOException, BiffException {
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized","incognito");

        // define webdriver

        driver = new ChromeDriver(options);
        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Line below locates the readable  file with data by the file path
        readable = Workbook.getWorkbook(new File("C:\\Users\\PC\\Desktop\\DatadrivenSheets\\MortgageCalculator.xls"));

        // defining readbale file
        readableSheet= readable.getSheet(0);

        // definining writable excel path

        writable = Workbook.createWorkbook(new File("C:\\Users\\PC\\Desktop\\DatadrivenSheets\\MortgageCalculatorResult.xls"),readable);
        // defining the variable excel sheet
        writableSheet = writable.getSheet(0);

        // define the rows
        rows = readableSheet.getRows();

    }// end of beforesuite

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writable.write();
        writable.close();
        readable.close();
        driver.quit();


    }

    @Test
    public void TestScenario() throws WriteException {

        for(int i = 1; i<rows; i++){
            String purchasePrice = readableSheet.getCell(0,i).getContents();
            String downPayment = readableSheet.getCell(1,i).getContents();
            String interestRate = readableSheet.getCell(2,i).getContents();
            String zipCode = readableSheet.getCell(3,i).getContents();
            String payMonth = readableSheet.getCell(4,i).getContents();
            String payYear = readableSheet.getCell(5,i).getContents();

            ReusableMethods.navigate(driver, "https://www.mlcalc.com");

            // verify the expected title using hard assert
           // Assert.assertEquals("Mortgage calc", driver.getTitle());

            // Verify the expected using softAssert
            softAssert.assertEquals("Mortgage Loan Calculator", driver.getTitle());


             //Clear purchase price
            ReusableMethods.clearMethod(driver,"//*[@name='ma']", "Purchase Price" );
             // Enter information to purchase price
             ReusableMethods.sendKeysMethod(driver,"//*[@name='ma']", purchasePrice, "PurchasePrices" );

            //Clear down Payment and enter value
            ReusableMethods.clearMethod(driver,"//*[@name='dp']", "Clear downPayment" );
            ReusableMethods.sendKeysMethod(driver, "//*[@name='dp']", downPayment,"Down Payment");

            // Clear interest rate and enter value

            ReusableMethods.clearMethod(driver,"//*[@name='ir']", "clear InterestRate" );
            ReusableMethods.sendKeysMethod(driver,"//*[@name='ir']", interestRate, "Interest Rate" );

            // Clear zipcode and enter value
            ReusableMethods.clearMethod(driver,"//*[@name='zipCode']", "clear zipcode" );
            ReusableMethods.sendKeysMethod(driver,"//*[@name='zipCode']", zipCode, "zipcode" );

            // Select pay month

            ReusableMethods.selectByText(driver, "//*[@name='sm']",payMonth, "month");

            //Select pay year
            ReusableMethods.selectByText(driver, "//*[@name='sy']",payYear, "Year");

            // Click on Calculate

            ReusableMethods.clickMethod(driver,"//*[@alt='Calculate']","calculate");

            // capture the monthly payment



            String mntPayment = ReusableMethods.getText(driver, "//*[@class='big']",0,"month");
            //capture the payoff date
            String payOffDate = ReusableMethods.getText(driver, "//*[@class='big']",2,"PayoffDate");



            // add label for monthly payment

            Label label1 = new Label(6,i,mntPayment);
            writableSheet.addCell(label1);

            // capture the payoff date

            String payoffTitle = ReusableMethods.getText(driver, "//*[@nowrap='nowrap']",2, "Payoff title");
            if (payoffTitle.equalsIgnoreCase("Mortgage payoff date")){
                // add label for monthly payment
                String payOffDt = ReusableMethods.getText(driver, "//*[@class='big']",2,"Payoff Date");

                // add label for payoff date

                Label label2 = new Label(7,i,payOffDt);
                writableSheet.addCell(label2);
            }else{
                String PayoffDate = ReusableMethods.getText(driver, "//*[@class='big']",3, "Payoff Date");
                Label label2 = new Label(7,i,PayoffDate);
                writableSheet.addCell(label2);
            }// end of if condition




        }// end of for loop

        // asserAll using soft assert will handle and catch your exception and show it on your log
        softAssert.assertAll();

    }// end of test execution





}// end of the parent class
