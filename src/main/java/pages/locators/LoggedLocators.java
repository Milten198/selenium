package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoggedLocators {

    @FindBy(how = How.ID, using = "logout")
    public WebElement logoutLink;

    @FindBy(how = How.CSS, using = ".col-md-offset-5.col-md-7>a")
    public WebElement downloadLink;
}
