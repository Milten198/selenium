package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CompletionFormLocators {

    @FindBy(how = How.CSS, using = ".btn.btn-primary.btn-block.js-open-window")
    public WebElement applyBtn;
}
