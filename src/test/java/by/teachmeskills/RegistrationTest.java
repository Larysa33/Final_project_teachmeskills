package by.teachmeskills;

import static by.teachmeskills.page.RegistrationPage.*;
import by.teachmeskills.page.RegistrationPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest extends BaseTest {

    @Test(priority = 0)
    public void successfulRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.registerWithAllFilledDataAndCheckboxesChecked("test@mail.ru", "12345678",
                "12345678", "1");
        assertThat(registrationPage.ifSuccessfulRegistrationTextDisplays())
                .isEqualTo(true)
                .as("Successful registration text \"" + SUCCESSFUL_REGISTRATION_TEXT + "\" must display " +
                        "after successful registration");
    }

    @Test(priority = 1)
    public void registerWithRegisteredEmailTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.registerWithAllFilledDataAndCheckboxesChecked("test@mail.ru", "12345678",
                "12345678", "1");
        assertThat(registrationPage.ifRegistrationErrorTextDisplays())
                .isEqualTo(true)
                .as("It's possible to register with already registered email time.");
    }

    @Test(priority = 2)
    public void registerWithEmptyFieldsAndUncheckedCheckboxesTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).openFromMainPageBySingUpButton();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by the SingUp button from the main page.");
        registrationPage.leaveRegistrationFormEmptyWithUncheckedCheckboxes();
        assertThat(registrationPage.okButtonState())
                .isEqualTo(true)
                .as("'OK' button must be disabled while registering with empty fields and unchecked checkboxes.");
    }

    @Test(priority = 3)
    public void registerWithEmptyFieldsAndCheckedCheckboxesTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.leaveRegistrationFormEmptyWithCheckedCheckboxes();
        assertThat(registrationPage.okButtonState())
                .isEqualTo(true)
                .as("'OK' button must be disabled while registering with empty fields and unchecked checkboxes.");
    }

    @Test(priority = 4)
    public void withoutEmailHintAndCheckboxesUncheckedTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.registerWithoutEmailHintAndCheckboxesUnchecked();
        assertThat(registrationPage.ifRegistrationErrorTextDisplays()).isEqualTo(true)
                .as("The error \"" + REGISTRATION_ERROR_TEXT + "\" must display while registering with all " +
                        "valid data and unchecked checkboxes.");


        if (registrationPage.displayedMandatoryFieldHints.size() == 3) {
            assertThat(registrationPage.isFirstMandatoryFieldDisplays())
                    .isEqualTo(true)
                    .as("The error \"" + MANDATORY_FIELD_TEXT + "\" must display below email field.");

            assertThat(registrationPage.isSecondMandatoryFieldDisplays())
                    .isEqualTo(true)
                    .as("The error \"" + MANDATORY_FIELD_TEXT + "\" must display below first checkbox.");

            assertThat(registrationPage.isThirdMandatoryFieldDisplays())
                    .isEqualTo(true)
                    .as("The error \"" + MANDATORY_FIELD_TEXT + "\" must display below second checkbox.");
        } else {
            System.out.println("The number of 'Mandatory field' hints doesn't equal to 3.");
        }
    }

    @Test(priority = 5)
    public void emailNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.registerWithoutHintAndCheckboxesChecked("test", "12345678",
                "12345678");
        registrationPage.registerWithoutHintAndCheckboxesChecked(" test1@gmail.com", "12345678",
                "12345678");
        registrationPage.registerWithoutHintAndCheckboxesChecked("test2@gmail.com ", "12345678",
                "12345678");
        registrationPage.registerWithoutHintAndCheckboxesChecked("test3 @gmail.com", "12345678",
                "12345678");
        registrationPage.registerWithoutHintAndCheckboxesChecked(" ", "12345678",
                "12345678");
        assertThat(registrationPage.ifSuccessfulRegistrationTextDisplays())
                .isEqualTo(false)
                .as("Registration is possible with invalid email.");
    }

    @Test(priority = 6)
    public void registerWithoutPasswordPConfirmationAndCheckboxesCheckedTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.registerWithoutPasswordPConfirmationAndCheckboxesChecked();
        assertThat(registrationPage.okButtonState()).isEqualTo(false)
                .as("The button 'OK' is enabled with empty password/password confirmation, filled out email, " +
                        "hint and checked checkboxes");
    }

    @Test(priority = 7)
    public void passwordMinNumberOfCharactersTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).openFromMainPageByLoginButton();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by the login button from the main page.");
        registrationPage.fillOutPasswordField("1234567");
        assertThat(registrationPage.ifShortPasswordHintDisplays())
                .isEqualTo(true)
                .as("The hint \"" + SHORT_PASSWORD_HINT_TEXT + "\" must display when password is less than " +
                        "8 characters.");
    }

    @Test(priority = 8)
    public void passwordInvalidValueTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.fillOutPasswordField(" ");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(true)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" must display when space is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("кир");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(true)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" must display when cyrillic text is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
    }

    @Test(priority = 9)
    public void passwordValidValueTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.fillOutPasswordField("latynitsa");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when latin symbols is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("123_4567");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when _ is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567@");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when @ is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567!");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when ! is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567*");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when * is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567$");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when $ is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567%");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when % is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567^");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when ^ is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567&");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when & is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567#");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when # is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567;");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when ; is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567-");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when - is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567+");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when + is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567=");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when = is filled" +
                        " out in password field. ");
        registrationPage.clearPasswordField();
        registrationPage.fillOutPasswordField("1234567|");
        assertThat(registrationPage.ifInvalidPasswordHintDisplays())
                .isEqualTo(false)
                .as("The hint \"" + INVALID_PASSWORD_HINT_TEXT + "\" mustn't display when | is filled" +
                        " out in password field.");
        registrationPage.clearPasswordField();
    }

    @Test(priority = 10)
    public void passwordConfirmationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver).open();
        assertThat(registrationPage.isOpened())
                .isEqualTo(true)
                .as("Registration page must be opened by direct link.");
        registrationPage.registerWithoutHintAndCheckboxesChecked("test@gmail.com","12345678","1");
        assertThat(registrationPage.ifConfirmationPasswordErrorDisplays())
                .isEqualTo(true)
                .as("The error \"" + CONFIRMATION_PASSWORD_ERROR_TEXT + "\" must display when password is not" +
                        " same confirmation password.");
    }
}
