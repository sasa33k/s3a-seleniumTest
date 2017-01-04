package com.s3aplayground.appium;

/**
 * Created by samantha on 3/1/2017.
 */

        import io.appium.java_client.android.AndroidDriver;

        import java.io.File;
        import java.io.IOException;
        import java.net.URL;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.concurrent.TimeUnit;
        import org.apache.commons.io.FileUtils;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.*;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import static org.junit.Assert.assertEquals;

public class CaptureScreenShot {
    AndroidDriver driver;
    Dimension size;
    String destDir;
    DateFormat dateFormat;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        //capabilities.setCapability("browserName", "Android");
        //capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("appPackage", "io.appium.android.apis");
        //capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @After
    public void End() {
        driver.quit();
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
        WebElement acceptBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.Button[2]")));
        acceptBtn.click();

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

        WebElement acceptBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.Button[2]")));
        acceptBtn.click();


        WebElement loginBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[4]")));


        //driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[1]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[1]"))
                .sendKeys("A10000"); //cannot submit capital letter yet
        //driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[2]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[3]/android.widget.EditText[2]"))
                .sendKeys("1234");
        /*
        driver.findElement(By.name("Agent ID")).sendKeys("10000003123");
        driver.findElement(By.name("Password")).sendKeys("Arba1234");
        */
        takeScreenShot();
        loginBtn.click();
        WebElement errorMessage = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[1]")));
        takeScreenShot();
        assertEquals(errorMessage.getText(),"Sekarang");



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