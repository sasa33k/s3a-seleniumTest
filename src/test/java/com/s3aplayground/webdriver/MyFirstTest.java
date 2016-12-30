package com.s3aplayground.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by samantha on 30/12/2016.
 */
public class MyFirstTest {

    @Test
    public void startWebDriver(){
        WebDriver driver;
        //System.setProperty("webdriver.gecko.driver", "/Users/samantha/Dev/selenium/webdrivers/chromedriver");
        System.setProperty("webdriver.gecko.driver", "/Users/samantha/Dev/selenium/webdrivers/geckodriver011");
        //driver =new FirefoxDriver();
        //File pathToWindowsGecko = new File("/Users/samantha/Dev/selenium/webdrivers/geckodriver");
        //System.setProperty("webdriver.gecko.driver", pathToWindowsGecko.getPath());
        //driver = new FirefoxDriver();
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        //capabilities.setCapability("marionette", false);
        /*capabilities.setCapability("firefox_binary",
                new File(System.getProperty("user.dir"),
                        "/tools/FirefoxPortable/FirefoxPortable.exe").
                        getAbsolutePath());*/
        driver = new FirefoxDriver(capabilities);
/*
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


*/
       // driver.manage().window().maximize();
      //  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.navigate().to("http://seleniumsimplified.com/");
        Assert.assertTrue("Title start with selenium simplified",
                driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
        driver.quit();
    }

}
