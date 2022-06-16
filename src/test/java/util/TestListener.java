package util;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest)testClass).getDriver();

        if(driver instanceof  WebDriver) {
            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
    @Attachment(value = "{0}", type = "html")
    public static String attachHtml(String html){
        return html;
    }
}
