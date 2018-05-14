package pages.executors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import pages.locators.NodesLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class NodesPage {

    private NodesLocators locators;

    public NodesPage() {
        locators = new NodesLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void clickOnRootNodeFolder() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.rootNodeName);
    }

    public void doubleClickOnRootNodeFolder() {
        SeleniumExtender.SeleniumClick.doubleClickWithWait(locators.rootNodeName);
    }

    public void clickRootNodeArrow() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.rootNodeArrow);
    }

    public String verifyChildNameIsDisplay(String childNumber) {
        return SeleniumExtender.SeleniumGetText.getTextWithWait(locators.childNodeName(childNumber));
    }

    public String verifyRootNodeNameIsDisplay() {
        return SeleniumExtender.SeleniumGetText.getTextWithWait(locators.rootNodeName);
    }

    public String getHeaderText() {
        return SeleniumExtender.SeleniumGetText.getTextWithWait(locators.header);
    }

    public void rightClickOnRootNodeName() {
        SeleniumExtender.SeleniumClick.clickRightWithWait(locators.rootNodeName);
    }

    public void clickChangeButton() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.changeName);
    }

    public void setNewRootName(String name) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithWait(locators.newNameInput, name);
        locators.newNameInput.sendKeys(Keys.ENTER);
    }
}
