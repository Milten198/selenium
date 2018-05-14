package pages.executors;


import org.openqa.selenium.support.PageFactory;
import pages.locators.LoginLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class LoginPage {

    private LoginLocators locators;

    public LoginPage() {
        locators = new LoginLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public boolean checkLoginPageIsVisible() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.loginBtn, "Login");
    }

    public void setLogin(String userLogin) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.loginInput, userLogin);
    }

    public void setPassword(String userPassword) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.passwordInput, userPassword);
    }

    public void clickLoginBtn() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.loginBtn);
    }

    public boolean checkLoginErrorMessage() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.errorMessage, "Nieprawidłowe dane logowania");
    }
}

