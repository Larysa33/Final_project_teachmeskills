package by.teachmeskills;

import by.teachmeskills.page.LoginPage;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static by.teachmeskills.page.LoginPage.LOGIN_FAILED_TEXT;
import static by.teachmeskills.page.LoginPage.MANDATORY_FIELD_TEXT;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test(priority = 0)
    public void registeredUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver).open();
        assertThat(loginPage.isOpened())
                .isEqualTo(true)
                .as("Login page must be opened by direct link.");
        loginPage.registeredUserLogin();
        assertThat(loginPage.ifLoginFailedTextDisplays())
                .isEqualTo(false)
                .as("It's impossible to login with registered user credentials.");
    }

    @Test(priority = 1)
    public void loginWithWrongPasswordTest() {
        LoginPage loginPage = new LoginPage(driver).openFromMainPage();
        assertThat(loginPage.isOpened())
                .isEqualTo(true)
                .as("Login page must be opened by Login button from the main page.");
        loginPage.loginWithFilledUserAndPassword("test@gmail.com", "1");
        assertThat(loginPage.ifLoginFailedTextDisplays())
                .isEqualTo(true)
                .as("Login error text \"" + LOGIN_FAILED_TEXT + "\" must display when logining in with " +
                        "wrong password.");
    }

    @Test(priority = 2)
    public void unregisteredUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver).open();
        assertThat(loginPage.isOpened())
                .isEqualTo(true)
                .as("Login page must be opened by direct link.");
        loginPage.loginWithFilledUserAndPassword("notregistered@gmail.com", "1");
        assertThat(loginPage.ifLoginFailedTextDisplays())
                .isEqualTo(true)
                .as("Login error text \"" + LOGIN_FAILED_TEXT + "\" must display when logining in with " +
                        "unregistered email.");
    }

    @Test(priority = 3)
    public void mandatoryFieldsTest() {
        LoginPage loginPage = new LoginPage(driver).open();
        assertThat(loginPage.isOpened())
                .isEqualTo(true)
                .as("Login page must be opened by direct link.");
        loginPage.loginWithEmptyFields();
        if(loginPage.displayedMandatoryFieldHints.size() == 2) {
            assertThat(loginPage.getFirstMandatoryFieldText())
                    .isEqualTo(MANDATORY_FIELD_TEXT)
                    .as("The hint \"" + MANDATORY_FIELD_TEXT + "\" must display below the empty 'user' field.");

            assertThat(loginPage.getSecondMandatoryFieldText())
                    .isEqualTo(MANDATORY_FIELD_TEXT)
                    .as("The hint \"" + MANDATORY_FIELD_TEXT + "\" must display below the empty 'password' field.");
        }else{
            System.out.println("The number of 'Mandatory field' hints doesn't equal to 2.");
        }
    }
}
