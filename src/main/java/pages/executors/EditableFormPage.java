package pages.executors;

import org.openqa.selenium.support.PageFactory;
import pages.locators.EditableFormLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class EditableFormPage {

    private EditableFormLocators locators;

    public EditableFormPage() {
        locators = new EditableFormLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void enableEditionMode() {
        SeleniumExtender.SeleniumPerform.performMouseoverSubMenu(locators.menuDropDown, locators.subMenuForm);
        SeleniumExtender.SeleniumClick.clickWithWait(locators.subMenuFormEdit);
    }

    public void setName(String name) {
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeysWithWait(locators.name, name);
    }

    public String getName() {
        return SeleniumExtender.SeleniumGetText.getValueWithWait(locators.name);
    }

    public void setSurname(String surname) {
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeysWithWait(locators.surname, surname);
    }

    public String getSurname() {
        return SeleniumExtender.SeleniumGetText.getValueWithWait(locators.surname);
    }

    public void setNote(String note) {
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeysWithWait(locators.note, note);
    }

    public String getNote() {
        return SeleniumExtender.SeleniumGetText.getValueWithWait(locators.note);
    }

    public void setPhoneNumber(String phoneNumber) {
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeysWithWait(locators.phone, phoneNumber);
    }

    public String getPhoneNumber() {
        return SeleniumExtender.SeleniumGetText.getValueWithWait(locators.phone);
    }

    public void uploadPhoto(String photoToUpload) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithoutWait(locators.upload, System.getProperty("user.dir") + "\\src\\test\\resources\\Task3\\" + photoToUpload);
    }

    public String getPhoto() {
        return locators.photo.getAttribute("src");
    }

    public void clickSaveButton() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.saveBtn);
    }

    public String getConfirmationMessage() {
        return SeleniumExtender.SeleniumGetText.getTextWithWait(locators.confirmationMessage);
    }
}
