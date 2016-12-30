package com.s3aplayground.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by samantha on 30/12/2016.
 */
public class FirefoxTest {

    @Test
    public void startFirefoxWebDriver(){
        // ======================= setting =====================
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "/Users/samantha/Dev/selenium/webdrivers/geckodriver011");
        //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        //capabilities.setCapability("marionette", false);
        /*capabilities.setCapability("firefox_binary",
                new File(System.getProperty("user.dir"),
                        "/tools/FirefoxPortable/FirefoxPortable.exe").
                        getAbsolutePath());*/
        //driver = new FirefoxDriver(capabilities);
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 40);
        //======================================================


        //Launching the browser application
        driver.get("http://www.uftHelp.com");
        //Adding wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Maximize window
        driver.manage().window().maximize();
        driver.switchTo().window(driver.getWindowHandle());
        System.out.println("driver.getWindowHandle() = "+driver.getWindowHandle());

        //Creating the Javascriptexecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Fetching the Domain Name
        String sDomain = js.executeScript("return document.domain;").toString();
        System.out.println("Domain = "+sDomain);
        //Fetching the URL
        String sURL = js.executeScript("return document.URL;").toString();
        System.out.println("URL = "+sURL);
        //Fetching the Title
        String sTitle = js.executeScript("return document.title;").toString();
        System.out.println("Title = "+sTitle);
        //Vertical scroll - down by 200  pixels
        js.executeScript("window.scrollBy(0,200)");
        //Adding wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println("Successfully did the vertical scroll by 200px");



/*
    //The following code brings opened window to the front in mac
        //Store the current window handle
        String currentWindowHandle = driver.getWindowHandle();
        //run your javascript and alert code
        //JavascriptExecutor jsex = ((JavascriptExecutor)driver);
        driver.get("http://seleniumsimplified.com/");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("alert('Test')");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.switchTo().alert().accept();

        Alert simpleAlert = driver.switchTo().alert();
        String alertText = simpleAlert.getText();
        System.out.println("Alert text is " + alertText);
        simpleAlert.accept();

/*
        Alert promptAlert  = driver.switchTo().alert();
        String alertText2 = promptAlert .getText();
        System.out.println("Alert text is " + alertText);
        //Send some text to the alert
        promptAlert .sendKeys("Accepting the alert");
        Thread.sleep(4000); //This sleep is not necessary, just for demonstration
        promptAlert .accept();



        simpleAlert.sendKeys("Accepting the alert");
        simpleAlert.accept();

*/
        //jsex.executeScript("alert('Test')");
        /*
        new WebDriverWait(driver, 60)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());

        Alert al = driver.switchTo().alert();
        al.accept();

        //driver.switchTo().alert().accept();
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
                driver.getTitle().startsWith("Selenium Simplified"));*/
        Assert.assertTrue(true);
        driver.close();
        driver.quit();
    }

}
