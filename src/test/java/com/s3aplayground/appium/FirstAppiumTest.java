package com.s3aplayground.appium;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.DependsOn;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 Reference: https://www.youtube.com/watch?v=2VKyW1lkbq0

 http://www.seleniumhq.org/download/

 Download:
 Appium: http://appium.io/
 Android SDK: https://developer.android.com/studio/index.html#Other

 Appium Setting:
 Android icon:
 - App Path - find an apk for launching
 - Device Name - must make up one, also used in the following prog
 - Platform version - follow the device connected
 Advanced > Android sdk path e.g ~~~/Library/Android/sdk

 Launch -> Inspect: see if app can be launched
 **/

// OBJECTIVES
//** capabilities.setCapability("noReset", true) - run test on installed app
// sendKeys: may have issue on sending capital letters (note2 default keyboard)
// - install Google Keyboard to solve)
//** takeScreenShots
//** Before / After group of tests
//** @FixMethodOrder(MethodSorters.DEFAULT) - Fixed test ordering

@FixMethodOrder(MethodSorters.DEFAULT)
public class FirstAppiumTest{


    String destDir;
    DateFormat dateFormat;
    private static AndroidDriver driver;

    //@Before
    @BeforeClass
    public static void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        //capabilities.setCapability("browserName", "Android");
        //capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("noReset", true); // load installed app, set false to reinstall the app
        //capabilities.setCapability("appPackage", "io.appium.android.apis");
        //capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    //@After
    @AfterClass
    public static void End() {
        driver.quit();
    }

    @Test
    public void acceptBtn() {

        WebElement acceptBtn = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button3")));
        acceptBtn.click();
    }

    @Test
    public void loginError() {
        // Scroll till element which contains "Views" text If It Is not visible on screen.
        //driver.scrollTo("Views");
        // Click on Views.
        //driver.findElement(By.name("Views")).click();
        // Scroll till element which contains "Tabs" text If It Is not visible on screen.
        //driver.scrollTo("Tabs");
        //Call takeScreenShot() function to capture screenshot of android screen.

        WebElement loginBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[4]")));
        loginBtn.click();
        takeScreenShot();
        WebElement errorMessage = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));
        takeScreenShot();
        assertEquals(errorMessage.getText(),"ID dan password anda tidak sesuai.");


        WebElement errorMsgBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1")));
        errorMsgBtn.click();

    }


    @Test
    public void loginSuccess() {


        WebElement loginBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[4]")));

        String password = "Done123!"; password = password.substring(0, 1).toUpperCase() + password.substring(1);

        //driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[1]")).click();
        WebElement useName = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[1]"));
        useName.sendKeys("10000003333"); //cannot submit capital letter yet

        //driver.longPressKeyCode(59);
        //useName.sendKeys(password);



        //driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[2]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[2]"))
                .sendKeys("Abcd5678");
        /*
        driver.findElement(By.name("Agent ID")).sendKeys("10000003123");
        driver.findElement(By.name("Password")).sendKeys("Arba1234");
        */
        takeScreenShot();
        loginBtn.click();
        WebElement errorMessage = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[1]")));
        takeScreenShot();
        assertEquals(errorMessage.getAttribute("name"),"Sekarang");
        //errorMessage.getCssValue()



    }

    public void takeScreenShot() {
        // Set folder name to store screenshots.
        destDir = "screenshots";
        // Capture screenshot.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Set date format to set It as screenshot file name.
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        // Set file name using current date time.
        String destFile = dateFormat.format(new Date()) + ".png";

        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
