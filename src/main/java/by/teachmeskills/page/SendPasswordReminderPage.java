package by.teachmeskills.page;

import by.teachmeskills.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static by.teachmeskills.page.LoginPage.MAIN_PAIGE_LOGIN_BUTTON;

public class SendPasswordReminderPage extends BasePage {
    private static final By EMAIL = By.id("email");
    private static final By OK_BUTTON = By.name("commit");
    private static final By PAGE_IS_OPEN_TEXT = By.xpath("//h1[contains(text(), 'Send yourself a password reminder')]");
    private static final By MANDATORY_FIELD_HINT = By.xpath("//div[contains(text(), 'Mandatory field')]");
    private static final By PASSWORD_SENT_HINT = By.xpath("//p[contains(text(), 'If the email address you" +
            " entered is recognised, an email with a password hint will be sent to it.')]");
    private static final By SEND_PASSWORD_REMINDER_LINK = By.xpath("//a[contains(text(), 'Send password reminder')]");

    public static final String MANDATORY_FIELD_HINT_TEXT = "Mandatory field";
    public static final String PASSWORD_SENT_HINT_TEXT = "If the email address you entered is recognised, an email" +
            " with a password hint will be sent to it.";

    public SendPasswordReminderPage(WebDriver driver) {
        super(driver);
    }

    public SendPasswordReminderPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("passwordReminder.url"));
        return this;
    }

    public SendPasswordReminderPage openFromMainPage() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("mainPaige.url"));
        driver.findElement(MAIN_PAIGE_LOGIN_BUTTON).click();
        driver.findElement(SEND_PASSWORD_REMINDER_LINK).click();
        return this;
    }

    public boolean isOpened() {
        return driver.findElement(PAGE_IS_OPEN_TEXT).isDisplayed();
    }

    public void sendReminderToRegisteredEmail() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.findElement(EMAIL).sendKeys(properties.getProperty("user"));
        driver.findElement(OK_BUTTON).click();
    }

    public void sendReminderToEmptyEmail() {
        driver.findElement(EMAIL).clear();
        driver.findElement(OK_BUTTON).click();
    }

    public void sendReminderToUnregisteredEmail() {
        driver.findElement(EMAIL).sendKeys("unregisteredEmail@gmail.com");
        driver.findElement(OK_BUTTON).click();
    }

    public boolean ifPasswordSentHintDisplays() {
        return driver.findElement(PASSWORD_SENT_HINT).isDisplayed();
    }

    public boolean ifMandatoryHintDisplays() {
        return driver.findElement(MANDATORY_FIELD_HINT).isDisplayed();
    }

}
