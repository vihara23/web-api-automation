package com.vihara.webapiautomation.utilities;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.vihara.webapiautomation.Web.testCases.BaseUITest.driver;

public class ScreenshotUtil {

    @Step("Tack screenshot of {screenshotContext}")
    @Description("Take Screenshots of web pages")
    public static void captureScreenshot(String screenshotContext) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenshotContext, new ByteArrayInputStream(screenshot));
    }
}
