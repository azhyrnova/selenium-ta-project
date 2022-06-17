package util;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener  {
    private Logger log = LogManager.getRootLogger();

    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest)testClass).getDriver();

        if(driver instanceof  WebDriver) {
            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
            Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        }

        String text = saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        Allure.addAttachment("screenshot", text);
    }

    private String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getMethodName();
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
