package util;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.ByteArrayInputStream;

public class InvokedMethodTestListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        System.out.println("This method is invoked after every config method - " + method.getTestMethod().getMethodName());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest)testClass).getDriver();

        if(driver instanceof  WebDriver && !(result.isSuccess())) {
            System.out.println("Screenshot captured for test case: " + getTestMethodName(result));
            Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
            String text = saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
        }
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    private String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getMethodName();
    }
}
