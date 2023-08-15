package com.vihara.webapiautomation.Web.testCases;

import com.vihara.webapiautomation.Web.PageObject.LUMAPage;
import com.vihara.webapiautomation.utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseUITest {
    public static WebDriver driver;
    public static Logger logger;
    public LUMAPage lumaPage;
    public static Actions action;
    private final ReadConfig readConfig = new ReadConfig();

    @BeforeClass
    public void setUp() {
        logger = Logger.getLogger(BaseUITest.class);
        PropertyConfigurator.configure("Log4j.properties");
        String browser = readConfig.getBrowser();

        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "ie" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(readConfig.getApplicationURL());

        lumaPage = new LUMAPage(driver);
        action = new Actions(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
