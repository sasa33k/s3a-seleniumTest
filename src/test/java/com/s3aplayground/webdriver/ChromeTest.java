package com.s3aplayground.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by samantha on 30/12/2016.
 */
public class ChromeTest {



    @Test
    public void startChromeWebDriver(){

        // ======================= setting =====================
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/Users/samantha/Dev/selenium/webdrivers/chromedriver");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 40);
        //======================================================


    //The following code brings opened window to the front in mac
        //Store the current window handle
        String currentWindowHandle = driver.getWindowHandle();
        //run your javascript and alert code
        JavascriptExecutor jsex = ((JavascriptExecutor)driver);
        jsex.executeScript("alert('Test')");
        driver.switchTo().alert().accept();
        //Switch back to to the window using the handle saved earlier
        driver.switchTo().window(currentWindowHandle);

    //This bit allows resizing the window
        jsex.executeScript("window.open('','testwindow','width=400,height=200')");
        driver.close();
        driver.switchTo().window("testwindow");
        jsex.executeScript("window.moveTo(0,0);");
        jsex.executeScript("window.resizeTo(200,800);");


        // driver.manage().window().maximize();
        //  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.navigate().to("http://seleniumsimplified.com/");
        Assert.assertTrue("Title start with selenium simplified",
                driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
        driver.quit();
    }

}
