package pages.locators;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginLocators {

    @FindBy(how = How.ID, using = "LoginForm__username")
    public WebElement loginInput;

    @FindBy(how = How.ID, using = "LoginForm__password")
    public WebElement passwordInput;

    @FindBy(how = How.ID, using = "LoginForm_save")
    public WebElement loginBtn;

    @FindBy(how = How.CSS, using = ".list-unstyled")
    public WebElement errorMessage;
}
