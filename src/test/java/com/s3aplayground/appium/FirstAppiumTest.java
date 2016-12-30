package com.s3aplayground.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by samantha on 30/12/2016.
 */
public class FirstAppiumTest{
    AppiumDriver driver;

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","AndroidTestDevice");
/*
        DesiredCapabilities capabilities = new DesiredCapabilities().android();
       capabilities.setCapability("no",true);
        capabilities.setCapability("newCommandTimeout", 100000);
        capabilities.setCapability("noReset", true);
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("version", "5.0");
        //capabilities.setCapability("app", application.getAbsolutePath());
        //capabilities.setCapability("automationName", "selendroid");
        capabilities.setCapability("noRest", true);
*/
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

    }

    @After
    public void quit() throws Exception {
        driver.quit();
    }


    @Test
    public void loginWithEmail(){
        //WebElement emailLoginBtn = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[4]"));
        WebElement loginBtn = (new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[4]")));
        loginBtn.click();

        WebElement errorMessage = (new WebDriverWait(driver,60))
                            .until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));

        assertEquals(errorMessage.getText(),"你的登入帳號與密碼不符");



    }
}
