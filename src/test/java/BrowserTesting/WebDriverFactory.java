package BrowserTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
    public WebDriver createWebDriver(DriverType driverType){
        WebDriver webDriver;
        switch (driverType){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            case IE:
                System.setProperty("webdriver.ie.driver", "browserDrivers/IEDriverServer.exe");
                webDriver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("Not supported driver" + driverType);

        }
        return webDriver;
    }
}
