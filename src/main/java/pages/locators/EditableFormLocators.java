package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditableFormLocators {

    @FindBy(how = How.CSS, using = ".dropdown-toggle.menu-border")
    public WebElement menuDropDown;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Formularz')]")
    public WebElement subMenuForm;

    @FindBy(how = How.XPATH, using = "//a[text() = 'Przejdź do trybu edycji']")
    public WebElement subMenuFormEdit;

    @FindBy(how = How.ID, using = "in-name")
    public WebElement name;

    @FindBy(how = How.ID, using = "in-surname")
    public WebElement surname;

    @FindBy(how = How.ID, using = "in-notes")
    public WebElement note;

    @FindBy(how = How.ID, using = "in-phone")
    public WebElement phone;

    @FindBy(how = How.ID, using = "in-file")
    public WebElement upload;

    @FindBy(how = How.ID, using = "save-btn")
    public WebElement saveBtn;

    @FindBy(how = How.CSS, using = ".preview-photo")
    public WebElement photo;

    @FindBy(how = How.CSS, using = ".notifyjs-bootstrap-base.notifyjs-bootstrap-success>span")
    public WebElement confirmationMessage;
}
