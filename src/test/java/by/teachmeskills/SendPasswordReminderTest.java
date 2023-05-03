package by.teachmeskills;

import by.teachmeskills.page.SendPasswordReminderPage;
import org.testng.annotations.Test;

import static by.teachmeskills.page.LoginPage.LOGIN_FAILED_TEXT;
import static by.teachmeskills.page.SendPasswordReminderPage.MANDATORY_FIELD_HINT_TEXT;
import static by.teachmeskills.page.SendPasswordReminderPage.PASSWORD_SENT_HINT_TEXT;
import static org.assertj.core.api.Assertions.assertThat;

public class SendPasswordReminderTest extends BaseTest {

    @Test(priority = 0)
    public void sendReminderToRegisteredEmailTest() {
        SendPasswordReminderPage sendPasswordReminderPage = new SendPasswordReminderPage(driver).open();
        assertThat(sendPasswordReminderPage.isOpened())
                .isEqualTo(true)
                .as("Send password reminder page page must be opened by direct link.");
        sendPasswordReminderPage.sendReminderToRegisteredEmail();
        assertThat(sendPasswordReminderPage.ifPasswordSentHintDisplays())
                .isEqualTo(true)
                .as("The message \"" + PASSWORD_SENT_HINT_TEXT + "\" must display after filling out the " +
                        "registered email.");
    }

    @Test(priority = 1)
    public void sendReminderToEmptyEmailTest() {
        SendPasswordReminderPage sendPasswordReminderPage = new SendPasswordReminderPage(driver).openFromMainPage();
        assertThat(sendPasswordReminderPage.isOpened())
                .isEqualTo(true)
                .as("Send password reminder page page must be opened by the button from main page.");
        sendPasswordReminderPage.sendReminderToEmptyEmail();
        assertThat(sendPasswordReminderPage.ifMandatoryHintDisplays())
                .isEqualTo(true)
                .as("The hint \"" + MANDATORY_FIELD_HINT_TEXT + "\" must display when sending reminder to " +
                        "an empty email.");
    }

    @Test(priority = 3)
    public void sendReminderToUnregisteredEmailTest() {
        SendPasswordReminderPage sendPasswordReminderPage = new SendPasswordReminderPage(driver).open();
        assertThat(sendPasswordReminderPage.isOpened())
                .isEqualTo(true)
                .as("Send password reminder page page must be opened by direct link.");
        sendPasswordReminderPage.sendReminderToUnregisteredEmail();
        assertThat(sendPasswordReminderPage.ifPasswordSentHintDisplays())
                .isEqualTo(true)
                .as("The message \"" + PASSWORD_SENT_HINT_TEXT + "\" must display after filling out the " +
                        "unregistered email.");
    }
}
