import com.sun.rowset.internal.Row;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import reuseableObject.ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Mortgage_Calculator_test {

    WebDriver driver;
    int Rows;
    Workbook readableFile;
    Sheet readableSheet;
    WritableWorkbook writeableFile;

    @BeforeSuite

    public void openBrowser() throws IOException, BiffException {

        //Line below locates the readable  file with data by the file path
        Workbook readableFile = Workbook.getWorkbook(new File("C:\\Users\\PC\\Desktop\\DatadrivenSheets\\MortgageCalculator.xls"));

        //Line below Locating the sheet where I stored the data in that readable file by index number
        Sheet readableSheet = readableFile.getSheet(0);

        //Write back to the file
        // Line below will create a writeabke workbook that takes the instance of readable workbook
        WritableWorkbook writableFile = Workbook.createWorkbook(new File("C:\\Users\\PC\\Desktop\\DatadrivenSheets\\MortgageCalculatorResult.xls"),readableFile);

        // Referencing the sheet to write back
        WritableSheet wSheet = writableFile.getSheet(0);
        //define the number of row
       int Rows = wSheet.getRows();

        System.out.println("Number of rows are "+Rows);
        // define chrome driver
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized","incognito");

        // define webdriver

        driver = new ChromeDriver(options);
        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    }// end of before Suite

    @AfterSuite
    public void closeBrowser (){
        //writableFile.write();
        //writableFile.close();
        //readableFile.close();


        //driver.quit();
    }// end of after suite annotations

    @Test
    public void testMortgageCalculator(){
        for(int i = 1; i<Rows; i++){

       // for (int i = 1; i<Rows; i++){


        driver.navigate().to("https://www.mlcalc.com");
        // Verify the title "Mortgage Calculator"

        String title = driver.getTitle();

        if(title.equals("Mortgage Calculator")){
            System.out.println("Title Matches");

        }else{
            System.out.println("Title doesn't match with expected. The actual title is " +title);

            }// end of get title conditional statement

        //enter purchase price

//            // Enter a new data
//            purchasePrice.sendKeys(myPurchasePrice[i]);

        ReusableMethods.clearMethod(driver,"//*[@name='ma']", "clearField" );

    // Enter a new data
        ReusableMethods.sendKeysMethod(driver,"//*[@name='ma']", "1000", "PurchasePrices" );

        //enter down payment
        ReusableMethods.clearMethod(driver,"//*[@name='dp']", "Celar downPayment" );
        ReusableMethods.sendKeysMethod(driver, "//*[@name='dp']", "10","Down Payment");

        ReusableMethods.clearMethod(driver,"//*[@name='ir']", "clear InterestRate" );
        ReusableMethods.sendKeysMethod(driver,"//*[@name='ir']", "2", "Interest Rate" );

        ReusableMethods.clearMethod(driver,"//*[@name='zipCode']", "clear zipcode" );
        ReusableMethods.sendKeysMethod(driver,"//*[@name='zipCode']", "11218", "zipcode" );

        //Select month from month drop down

          WebElement month = driver.findElement(By.xpath("//*[@name='sm']"));
          //define the select command and store the month dropdpown variable here
         Select monthDropdown = new Select(month);

        monthDropdown.selectByVisibleText("Nov");

          //Select year from year drop down

          WebElement year = driver.findElement(By.xpath("//*[@name='sy']"));
         Select YearDropdown = new Select(year);
        YearDropdown.selectByVisibleText("2018");

        // Click on calculate button

        ReusableMethods.submitMethod(driver,"//*[@type='submit']", "Calculate Button" );

        //	Capture the total monthly payment and print the monthly payment

        ReusableMethods.getText(driver,"//*[@class='big']",0, "Monthly Payment" );

        /*    try {
                String monthlyPayment = driver.findElement(By.xpath("//*[@class='big']")).getText();
                System.out.println("My search result is "+ monthlyPayment);
                Label label1 = new Label(1,i,monthlyPayment);
                wSheet.addCell(label1);
            }catch(java.lang.Exception err3) {
                System.out.println("Element wasn't found, Error code" +err3);

            }// end
*/
        }

    }// emd of at test












}// end of parent class




