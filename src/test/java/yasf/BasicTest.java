package yasf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicTest {

    private static final String BROWSER = "firefox";
    private ExpectedCondition<Boolean> pageTitleStartsWith(final
                                                           String searchString) {
        return driver -> driver.getTitle().toLowerCase().
                startsWith(searchString.toLowerCase());
    }

    private void googleExampleThatSearchesFor(final
                                              String searchString) {

        WebDriver driver = null;
        if (BROWSER.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver17.exe");
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("marionette", false);
            FirefoxOptions opt = new FirefoxOptions();
            opt.merge(dc);
            driver =  new FirefoxDriver(opt);
        } else {
             System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
             driver = new ChromeDriver();
        }

        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith(searchString));

        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }

    @Test
    public void googleCheeseExample() {
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() {
        googleExampleThatSearchesFor("Milk!");
    }
}