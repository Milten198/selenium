package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CardPaymentLocators {

    @FindBy(how = How.ID, using = "task8_form_cardType")
    public WebElement cardType;

    @FindBy(how = How.ID, using = "task8_form_name")
    public WebElement name;

    @FindBy(how = How.ID, using = "task8_form_cardNumber")
    public WebElement cardNumber;

    @FindBy(how = How.ID, using = "task8_form_cardCvv")
    public WebElement cardCVV;

    @FindBy(how = How.ID, using = "task8_form_cardInfo_month")
    public WebElement expirationMonth;

    @FindBy(how = How.ID, using = "task8_form_cardInfo_year")
    public WebElement expirationYear;

    @FindBy(how = How.CSS, using = ".btn.btn-primary.col-md-4.pull-right")
    public WebElement payBtn;

    @FindBy(how = How.CSS, using = ".alert.alert-success")
    public WebElement paymentConfirmation;

    @FindBy(how = How.CSS, using = ".list-unstyled>li")
    public WebElement expirationMessage;
}
