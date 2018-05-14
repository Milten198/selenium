package pages.executors;

import org.openqa.selenium.support.PageFactory;
import pages.locators.PopUpLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class PopUpWindow {

    private PopUpLocators locators;

    public PopUpWindow() {
        this.locators = new PopUpLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void switchToFrame() {
        SeleniumExtender.SeleniumFrame.switchToFrame(locators.iFrame);
    }

    public boolean checkPopUpFormIsDisplayed() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayed(locators.saveBtn);
    }

    public void setName(String name) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.candidateName, name);
    }

    public void setEmail(String email) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.candidateEmail, email);
    }

    public void setPhoneNumber(String phoneNumber) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.candidatePhoneNumber, phoneNumber);
    }

    public void clickOnSaveButton() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.saveBtn);
    }

    public boolean checkEmailErrorMessage() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.emailErrorMessage, "Nieprawidłowy email");
    }

    public boolean checkPhoneNumberErrorMessage() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.phoneNumberErrorMessage, "Zły format telefonu - prawidłowy: 600-100-200");
    }

    public boolean checkConfirmationMessage() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.confirmationMessage, "Wiadomość została wysłana");
    }
}
