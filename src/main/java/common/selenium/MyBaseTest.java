package common.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import common.utils.CapabilityFactory;
import common.utils.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

public class MyBaseTest {

    ExtentReports extent = new ExtentReports();

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();




    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    private final Properties prop = PropertiesUtil.read("config.properties");



    @BeforeSuite
    public void setupSuite () {
        //report part
        String reporterPath = prop.getProperty("reporterPath");
        new File(reporterPath).mkdir();
        ExtentSparkReporter spark = new ExtentSparkReporter(reporterPath +System.currentTimeMillis()+".html");
        extent.attachReporter(spark);


    }

    @AfterSuite
    public void afterSuite(){
        extent.flush();
    }


    @BeforeMethod
    public void setup (Method method) {
        String testName = method.getName();
        extentTest.set(extent.createTest(testName));
        String browser = prop.getProperty("browser");
        String host = prop.getProperty("host");

        //Set Browser to ThreadLocalMap
        //        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));

        //
        //        if (browser.equals("firefox")) {
        //            driver = ThreadLocal.withInitial(() -> new FirefoxDriver(OptionsManager.getFirefoxOptions()));
        //        } else {
        //            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //            driver = ThreadLocal.withInitial(() -> new ChromeDriver(OptionsManager.getChromeOptions()));
        //        }
    }
    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }


    @AfterMethod
    public void tearDown() {
        //        getDriver().quit();
    }
    @AfterClass
    public void terminate () {
        //Remove the ThreadLocalMap element
        driver.remove();
    }
}
