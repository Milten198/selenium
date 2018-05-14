package pages.executors;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import pages.locators.HomeLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

import static util.helpers.SeleniumExtender.SeleniumClick.clickWithWait;

public class HomePage extends AbstractPage {

    private HomeLocators locators;

    public HomePage() {
        locators = new HomeLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void openTaskWithNumber(String taskNumber) {
        WebElement taskLink = SeleniumExtender.SeleniumFind.findElement(locators.taskLink(taskNumber));
        clickWithWait(taskLink);
    }
}
