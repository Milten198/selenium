package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PopUpLocators {


    @FindBy(how = How.XPATH, using = "//iframe")
    public WebElement iFrame;

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    public WebElement candidateName;

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    public WebElement candidateEmail;

    @FindBy(how = How.XPATH, using = "//input[@name='phone']")
    public WebElement candidatePhoneNumber;

    @FindBy(how = How.ID, using = "save-btn")
    public WebElement saveBtn;

    @FindBy(how = How.CSS, using = ".container>h1")
    public WebElement confirmationMessage;

    @FindBy(how = How.XPATH, using = "(//span[@class='error'])[1]")
    public WebElement emailErrorMessage;

    @FindBy(how = How.XPATH, using = "(//span[@class='error'])[2]")
    public WebElement phoneNumberErrorMessage;
}
