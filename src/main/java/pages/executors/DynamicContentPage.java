package pages.executors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import pages.locators.DynamicContentLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class DynamicContentPage {

    private DynamicContentLocators locators;
    private JavascriptExecutor jse;

    public DynamicContentPage() {
        locators = new DynamicContentLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
        jse = (JavascriptExecutor) SeleniumExecutor.getDriver();
    }

    public void scrollToTheBottom() {
        while (!isFooterVisible()) {
            scrollPageDown();
        }
    }

    public boolean isFooterVisible() {
        return SeleniumExtender.SeleniumIsPresent.isElementPresent(locators.footer);
    }

    public String numberOfLoadedFragments() {
        return String.valueOf(locators.loadedFragments.size());
    }

    private void scrollPageDown() {
        jse.executeScript("window.scrollBy(0,5)", "");
    }
}
