package BrowserTesting;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;



import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.apache.commons.io.FileUtils.copyFile;


public class TestDifferentBrowser {
    WebDriver driver;
    DriverType driverType = DriverType.CHROME;

    @BeforeMethod
    public void setUP(){

        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(driverType);
        driver.manage().window().maximize();

    }

    @Test
    public void test1(){
        driver.get("http://www.google.com/");

        WebElement searchElement = driver.findElement(By.xpath("//input[@spellcheck='false']"));

        searchElement.sendKeys("SoftServe");
        searchElement.submit();

        String expectedResult = "SoftServe | IT-компанія з консалтингу та розробки ПЗ";
        String actualResult = "SoftServe | IT-компанія з консалтингу та розробки ПЗ";

        Assert.assertEquals(expectedResult, actualResult);

        String softserve = "//a[@href='https://www.softserveinc.com/uk-ua']";

        WebElement goToSoftServe = driver.findElement(By.xpath(softserve));
        goToSoftServe.click();

        String verifyUrl = "https://www.softserveinc.com/uk-ua";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, verifyUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                copyFile(scrFile, new File(result.getName() +"["+ LocalDate.now()+ "][" + System.currentTimeMillis() + "].png"));

            } catch (
                    IOException e) {
                e.printStackTrace();

            }
        }
        driver.quit();

    }

}
