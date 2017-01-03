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
        import org.openqa.selenium.*;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.annotations.AfterTest;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.Test;

        import static org.junit.Assert.assertEquals;

public class CaptureScreenShot {
    AndroidDriver driver;
    Dimension size;
    String destDir;
    DateFormat dateFormat;

    @BeforeTest
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

    @Test
    public void ScrollToTab() {
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
        assertEquals(errorMessage.getText(),"你的登入帳號與密碼不符");


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

    @AfterTest
    public void End() {
        driver.quit();
    }
}