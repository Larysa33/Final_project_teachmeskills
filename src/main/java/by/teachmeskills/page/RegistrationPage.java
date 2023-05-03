package by.teachmeskills.page;

import by.teachmeskills.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

import static by.teachmeskills.page.LoginPage.MAIN_PAIGE_LOGIN_BUTTON;

public class RegistrationPage extends BasePage {
    private static final By REGISTRATION_EMAIL = By.id("registration_email");
    private static final By REGISTRATION_PASSWORD = By.id("registration_password");
    private static final By REGISTRATION_PASSWORD_CONFIRMATION = By.id("registration_password_confirmation");
    private static final By REGISTRATION_PASSWORD_HINT = By.id("registration_password_hint");
    private static final By REQUIRED_CHECKBOX_1 = By.id("registration_terms_of_use");
    private static final By REQUIRED_CHECKBOX_2 = By.id("registration_lost_password_warning_registered");
    private static final By OK_BUTTON = By.xpath("//div[text()=' OK ']");
    private static final By REGISTER_BUTTON = By.xpath("//a[text()=' Register ']");

    private static final By SIGN_UP_BUTTON = By.xpath("//a[text()=' Sign up – it’s free! ']");
    private static final By MANDATORY_FIELD_HINT = By.xpath("//div[text()=' Mandatory field ']");
    private static final By REGISTRATION_ERROR = By.xpath("//div[text()=' Registration not successful ']");
    private static final By CONFIRMATION_PASSWORD_ERROR = By.xpath("//div[text()='  Password confirmation " +
            "doesn't match ']");
    private static final By SUCCESSFUL_REGISTRATION = By.xpath("//h1[text()=' User registered ']");
    private static final By REGISTRATION_PAGE_IS_OPEN = By.xpath("//h1[text()=' Registration ']");
    private static final By SHORT_PASSWORD_HINT = By.xpath("//div[text()='Password is too short. Min. " +
            "8 characters']");
    private static final By INVALID_PASSWORD_HINT = By.xpath("//div[text()='Permitted: letters, digits," +
            " ~!@#$%^&amp;*_-+=|(){}[]:;&#39;&lt;&gt;,.?/']");
    public static final String REGISTRATION_ERROR_TEXT = "Registration not successful";
    public static final String CONFIRMATION_PASSWORD_ERROR_TEXT = "Password confirmation doesn't match";
    public static final String SHORT_PASSWORD_HINT_TEXT = "Password is too short. Min. 8 characters";

    public static final String INVALID_PASSWORD_HINT_TEXT = "Permitted: letters, digits, ~!@#$%^&amp;amp;*_-+=|(){}[]:;" +
            "&amp;#39;&amp;lt;&amp;gt;,.?/";
    public static final String SUCCESSFUL_REGISTRATION_TEXT = "User registered";
    public static final String MANDATORY_FIELD_TEXT = "Mandatory field";

    public List<WebElement> displayedMandatoryFieldHints = driver.findElements(MANDATORY_FIELD_HINT);

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("register.url"));
        return this;
    }

    public RegistrationPage openFromMainPageByLoginButton() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("mainPaige.url"));
        driver.findElement(MAIN_PAIGE_LOGIN_BUTTON).click();
        driver.findElement(REGISTER_BUTTON).click();
        return this;
    }

    public RegistrationPage openFromMainPageBySingUpButton() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("mainPaige.url"));
        driver.findElement(SIGN_UP_BUTTON).click();
        return this;
    }

    public boolean isOpened() {
        return driver.findElement(REGISTRATION_PAGE_IS_OPEN).isDisplayed();
    }

    public void registerWithAllFilledDataAndCheckboxesChecked(String email, String password, String passwordConfirmation, String passwordHint) {
        driver.findElement(REGISTRATION_EMAIL).sendKeys(email);
        driver.findElement(REGISTRATION_PASSWORD).sendKeys(password);
        driver.findElement(REGISTRATION_PASSWORD_CONFIRMATION).sendKeys(passwordConfirmation);
        driver.findElement(REGISTRATION_PASSWORD_HINT).sendKeys(passwordHint);
        driver.findElement(REQUIRED_CHECKBOX_1).click();
        driver.findElement(REQUIRED_CHECKBOX_2).click();
        driver.findElement(OK_BUTTON).click();
    }

    public void registerWithoutHintAndCheckboxesChecked(String email, String password, String passwordConfirmation) {
        driver.findElement(REGISTRATION_EMAIL).sendKeys(email);
        driver.findElement(REGISTRATION_PASSWORD).sendKeys(password);
        driver.findElement(REGISTRATION_PASSWORD_CONFIRMATION).sendKeys(passwordConfirmation);
        driver.findElement(REQUIRED_CHECKBOX_1).click();
        driver.findElement(REQUIRED_CHECKBOX_2).click();
        driver.findElement(OK_BUTTON).click();
    }

    public void registerWithoutEmailHintAndCheckboxesUnchecked () {
        driver.findElement(REGISTRATION_EMAIL).clear();
        driver.findElement(REGISTRATION_PASSWORD).sendKeys("12345678");
        driver.findElement(REGISTRATION_PASSWORD_CONFIRMATION).sendKeys("12345678");
        driver.findElement(REGISTRATION_PASSWORD_HINT).clear();
        driver.findElement(OK_BUTTON).click();
    }

    public void registerWithoutPasswordPConfirmationAndCheckboxesChecked () {
        driver.findElement(REGISTRATION_EMAIL).sendKeys("test@gmail.com");
        driver.findElement(REGISTRATION_PASSWORD).clear();
        driver.findElement(REGISTRATION_PASSWORD_CONFIRMATION).clear();
        driver.findElement(REGISTRATION_PASSWORD_HINT).sendKeys("1");
        driver.findElement(REQUIRED_CHECKBOX_1).click();
        driver.findElement(REQUIRED_CHECKBOX_2).click();
    }

    public void leaveRegistrationFormEmptyWithUncheckedCheckboxes() {
        driver.findElement(REGISTRATION_EMAIL).clear();
        driver.findElement(REGISTRATION_PASSWORD).clear();
        driver.findElement(REGISTRATION_PASSWORD_CONFIRMATION).clear();
        driver.findElement(REGISTRATION_PASSWORD_HINT).clear();
    }

    public void leaveRegistrationFormEmptyWithCheckedCheckboxes() {
        driver.findElement(REGISTRATION_EMAIL).clear();
        driver.findElement(REGISTRATION_PASSWORD).clear();
        driver.findElement(REGISTRATION_PASSWORD_CONFIRMATION).clear();
        driver.findElement(REGISTRATION_PASSWORD_HINT).clear();
        driver.findElement(REQUIRED_CHECKBOX_1).click();
        driver.findElement(REQUIRED_CHECKBOX_2).click();
    }

    public boolean ifRegistrationErrorTextDisplays() {
        return driver.findElement(REGISTRATION_ERROR).isDisplayed();
    }

    public boolean ifConfirmationPasswordErrorDisplays() {
        return driver.findElement(CONFIRMATION_PASSWORD_ERROR).isDisplayed();
    }


    public boolean isFirstMandatoryFieldDisplays() {
        return displayedMandatoryFieldHints.get(0).isDisplayed();
    }

    public boolean isSecondMandatoryFieldDisplays() {
        return displayedMandatoryFieldHints.get(1).isDisplayed();
    }

   public boolean isThirdMandatoryFieldDisplays() {
       return displayedMandatoryFieldHints.get(2).isDisplayed();
    }


    public boolean ifSuccessfulRegistrationTextDisplays()
    {
        return driver.findElement(SUCCESSFUL_REGISTRATION).isDisplayed();
    }

    public boolean okButtonState() {
        return driver.findElement(OK_BUTTON).isEnabled();
    }

    public boolean ifShortPasswordHintDisplays()
    {
        return driver.findElement(SHORT_PASSWORD_HINT).isDisplayed();
    }

    public boolean ifInvalidPasswordHintDisplays()
    {
        return driver.findElement(INVALID_PASSWORD_HINT).isDisplayed();
    }

    public void fillOutPasswordField(String password) {
        driver.findElement(REGISTRATION_PASSWORD).sendKeys(password);
    }

    public void clearPasswordField() {
        driver.findElement(REGISTRATION_PASSWORD).clear();
    }

}
