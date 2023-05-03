package by.teachmeskills.page;
import by.teachmeskills.util.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;
public class LoginPage extends BasePage {

    private static final By USER = By.id("login");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_PAGE_IS_OPEN_TEXT = By.xpath("//h1[text()=' Login ']");
    private static final By LOGIN_FAILED = By.xpath("//div[text()=' Login failed ']");
    private static final By MANDATORY_FIELD_HINT = By.xpath("//div[text()=' Mandatory field ']");
    public static final By MAIN_PAIGE_LOGIN_BUTTON = By.xpath("//a[text()='Login']");
    private static final By LOGIN_BUTTON = By.xpath("//div[text()=' Login ']");
    public static final String LOGIN_FAILED_TEXT = "Login failed";
    public static final String MANDATORY_FIELD_TEXT = "Mandatory field";

    public List<WebElement> displayedMandatoryFieldHints = driver.findElements(MANDATORY_FIELD_HINT);
    public WebElement firstHint = displayedMandatoryFieldHints.get(0);
    public WebElement secondHint = displayedMandatoryFieldHints.get(1);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("login.url"));
        return this;
    }

    public LoginPage openFromMainPage() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("mainPaige.url"));
        driver.findElement(MAIN_PAIGE_LOGIN_BUTTON).click();
        return this;
    }

    public boolean isOpened() {
        return driver.findElement(LOGIN_PAGE_IS_OPEN_TEXT).isDisplayed();
    }

    public void registeredUserLogin() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.findElement(USER).sendKeys(properties.getProperty("user"));
        driver.findElement(PASSWORD).sendKeys(properties.getProperty("password"));
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void loginWithFilledUserAndPassword(String user, String password) {
        driver.findElement(USER).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void loginWithEmptyFields() {
        driver.findElement(USER).clear();
        driver.findElement(PASSWORD).clear();
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getFirstMandatoryFieldText() {
        return firstHint.getText();
    }

    public String getSecondMandatoryFieldText() {
        return secondHint.getText();
    }

    public boolean ifLoginFailedTextDisplays() {
        return driver.findElement(LOGIN_FAILED).isDisplayed();
    }
}
