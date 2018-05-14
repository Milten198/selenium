package pages.executors;

import org.openqa.selenium.support.PageFactory;
import pages.locators.CardPaymentLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class CardPaymentPage {

    private CardPaymentLocators locators;

    public CardPaymentPage() {
        locators = new CardPaymentLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void selectCardType(String cardType) {
        SeleniumExtender.SeleniumDropDown.selectFromDropDownWithWait(locators.cardType, cardType);
    }

    public void setName(String name) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.name, name);
    }

    public void setCardNumber(String cardNumber) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.cardNumber, cardNumber);
    }

    public void setCVVCode(String cvv) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.cardCVV, cvv);
    }

    public void selectExpirationMonth(String month) {
        SeleniumExtender.SeleniumDropDown.selectFromDropDownWithWait(locators.expirationMonth, month);
    }

    public void selectExpirationYear(String year) {
        SeleniumExtender.SeleniumDropDown.selectFromDropDownWithWait(locators.expirationYear, year);
    }

    public void clickPayButton() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.payBtn);
    }

    public boolean confirmPayment() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayed(locators.paymentConfirmation);
    }

    public boolean confirmExpirationMessage(String message) {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.expirationMessage, message);
    }

}
