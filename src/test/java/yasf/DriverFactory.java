package yasf;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

class DriverFactory {
    private RemoteWebDriver webDriver;
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    RemoteWebDriver getDriver(final String BROWSER) {
        System.out.println("Current thread: " + Thread.currentThread().getId());
        if (null == webDriver) {
            System.out.println(" ");
            System.out.println("Current Operating System: " + operatingSystem);
            System.out.println("Current Architecture: " + systemArchitecture);
            System.out.println("Current Browser Selection: Firefox");
            System.out.println(" ");

            if (BROWSER.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "resources/geckodriver17.exe");
                DesiredCapabilities dc = new DesiredCapabilities();
                dc.setCapability("marionette", false);
                FirefoxOptions opt = new FirefoxOptions();
                opt.merge(dc);
                webDriver =  new FirefoxDriver(opt);
            } else {
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
                webDriver = new ChromeDriver();
            }
        }
        return webDriver;
    }

    void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }
}