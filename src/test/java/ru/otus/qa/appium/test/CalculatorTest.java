package ru.otus.qa.appium.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private AndroidDriver<MobileElement> driver;

    @BeforeEach
    void before() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File apkFile = new File("C:\\Users\\thisa\\Downloads\\calculator.apk");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.167.101:5555");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @AfterEach
    void after() {
        driver.quit();
//        service.stop();
    }

    @Test
    void testPlus() {
        WebElement five = driver.findElementById("digit_5");
        five.click();
        WebElement plus = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"+\")");
        plus.click();
        WebElement seven = driver.findElementByXPath(".//*[@text='7']");
        seven.click();
        WebElement equal = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").description(\"equals\")");
        equal.click();
        WebElement result = driver.findElementById("result_final");
        String text = result.getText();
        assertEquals("12", text);
    }
}
