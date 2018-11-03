package Mlcalc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class scrolling {

    WebDriver driver;

    @Test

    public void scrpolling() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        //Define the chrome options

        ChromeOptions options = new ChromeOptions();

        // define the arguments for options

        options.addArguments("start-maximized","incognito");

        // define webdriver

        driver = new ChromeDriver(options);
        //using implicit wait statement
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // navigate to the site
        driver.navigate().to("https://mortgagecalculator.net/");
        // defining the javascriptexecutor

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        //scroll into the calculate element
        WebElement calculateBtn = driver.findElement(By.xpath("//*[@value='Calculate Now']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", calculateBtn);
        Thread.sleep(1500);

        // Scroll by number of time going down

        jse.executeScript("scroll(0,700)");
        //Scrolling to the up

        Thread.sleep(1500);
        jse.executeScript("scroll(0,-700)");


    }// end of scrolling test




}// end of parent class
