package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class UploadFileLocators {

    @FindBy(how = How.CSS, using = ".btn.btn-primary.btn-block.btn-file>input")
    public WebElement uploadBtn;

    @FindBy(how = How.XPATH, using = "//tr[1]/td[1]")
    public WebElement name;

    @FindBy(how = How.XPATH, using = "//tr[1]/td[2]")
    public WebElement surname;

    @FindBy(how = How.XPATH, using = "//tr[1]/td[3]")
    public WebElement phoneNumber;

    @FindBy(how = How.XPATH, using = "//tbody/tr")
    public List<WebElement> rowsNumber;
}
