import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidateAboutUsPage {

    WebDriver driver;

    @BeforeTest
    public void setChromeDriver() {
        //Set the chrome Driver path , make sure to have the driver in the project root folder
        //Driver is compatible with 110 chrome version
        //Dependind on the OS choose the file name "chromedriver_exe" , in case of Window otherwise "chromedriver"
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
        //create the new chromedriver object
        driver = new ChromeDriver();
        //Set the implicit wait for page to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Maximize the window
        driver.manage().window().maximize();

    }

    @Test
    public void validateAboutUSPage() {

        try {
            //Invoke the URL
            driver.get("https://www.infijoy.com/");
            //Set the explicit wait condition for the shadow element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#usercentrics-root")));
            //find the shadowHost element which contains the dialog box DOM
            WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
            //get the root element from shadowHost(2 ways) , i.e using JS or getShadowRoot() Method
            //SearchContext shadowRoot= expandRootElement(root);
            SearchContext shadowRoot = shadowHost.getShadowRoot();
            //find and click the Deny button to close the dialog box
            shadowRoot.findElement(By.cssSelector("button[data-testid='uc-deny-all-button'][role='button']")).click();    //find and click the Deny button to close the dialog box
            //click on the About Us page
            driver.findElement(By.xpath("//footer//a[text()='About']")).click();
            //validate the title of the page
            driver.getTitle().equals("About Us | Infijoy Courses & Meditations");
            //Add other page level validations
            driver.getCurrentUrl().equals("https://www.infijoy.com/about-us");
            driver.findElement(By.xpath("//button[text()='Join Our Team']")).isDisplayed();
        } catch (Exception e) {
            //In case of errors catch any exception and print the same for debugging purpose
            e.printStackTrace();
        } finally {
            //Make sure to kill the driver object in any case.
            driver.quit();

        }

    }

    //Javascript way to find the root element from shadowHost
    public SearchContext expandRootElement(WebElement element) {
        SearchContext shadowRoot = (SearchContext) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].shadowRoot", element);
        return shadowRoot;
    }


}
