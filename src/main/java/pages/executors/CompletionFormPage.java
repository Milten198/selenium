package pages.executors;

import org.openqa.selenium.support.PageFactory;
import pages.locators.CompletionFormLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class CompletionFormPage {

    private CompletionFormLocators locators;

    public CompletionFormPage() {
        locators = new CompletionFormLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void clickOnApplyButton() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.applyBtn);
    }

    public void switchToPopUpWindow() {
        SeleniumExtender.SeleniumSwitchWindow.switchToAnyNotParentWindow();
    }
}
