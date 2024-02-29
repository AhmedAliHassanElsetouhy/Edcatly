package base;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Helper;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeTest
    public void setup() {
        WebDriver driver = new DriverFactory().initializeDriver();
        setDriver(driver);
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenshot(getDriver(), result.getName());
        }
    }

    @AfterTest
    public void tearDown() {
        getDriver().quit();
    }

    private Select select;
    private static WebDriverWait wait ;

    //To fins element where have a selector
    protected static WebElement findElement(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected static boolean isElementDisplayed(WebDriver driver, By locator){
        return findElement(driver, locator).isDisplayed();
    }
    public boolean assertThatElementAppear(WebDriver driver, By locator){
        Assert.assertTrue(findElement(driver, locator).isDisplayed());
        return true;
    }
    protected static void waitForElementToBeVisible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected static void waitUntilElementToBeVisibleThenSendKeys(WebDriver driver, By locator, String input) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(input);
    }

    protected static void waitUntilElementToBeClickableThenClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    public void pressEnterKey(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    //If i want to count number of element with same selector
    protected static int getCountForSameElement(WebDriver driver, By locator) {
        List<WebElement> elements = driver.findElements(locator);
        int count = 0;

        for (WebElement element : elements) {
            if (element.isEnabled()) {
                count++;
            }
        }
        return count;
    }

    //When i have lots of elements and i want to select first enable item
    protected static WebElement selectFirstEnabledItem(WebDriver driver, By locator) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            if (element.isEnabled()) {
                return element;
            }
        }
        return null; // or throw an exception if no enabled element is found
    }

//If i want to click on specific index when lots of element have same selector
    public void clickElementByIndex(WebDriver driver, By locator, int index) {
        List<WebElement> elements = driver.findElements(locator);
        if (index >= 0 && index < elements.size()) {
            elements.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    //To move into element where passing the locator
    protected void moveToElement(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions generateReportHoverAction = new Actions(driver);
        generateReportHoverAction.moveToElement(findElement(driver, locator)).perform();
    }
}
