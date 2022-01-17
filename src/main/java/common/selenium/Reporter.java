package common.selenium;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reporter {

    public static void info(String detail){
        MyBaseTest.extentTest.get().info(detail);
    }

    public static void fail(Throwable throwable){
        MyBaseTest.extentTest.get().fail(throwable);
    }

    public static void fail(String detail){
        if(MyBaseTest.driver.get()!=null){
            String base64 = takeScreenShot(MyBaseTest.driver.get());
            MyBaseTest.extentTest.get().createNode(detail).
                    fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        }else{
            MyBaseTest.extentTest.get().fail(detail);
        }

    }

    private static String takeScreenShot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

}
