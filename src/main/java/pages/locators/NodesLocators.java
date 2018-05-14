package pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.SeleniumExecutor;

public class NodesLocators {

    @FindBy(how = How.XPATH, using = "//i[following-sibling::a[text() = 'Root node']]")
    public WebElement rootNodeArrow;

    @FindBy(how = How.CSS, using = ".col-md-9.content-container>h1")
    public WebElement header;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Root')]")
    public WebElement rootNodeName;

    public WebElement childNodeName(String childNumber) {
        return SeleniumExecutor.getDriver()
                .findElement(By.xpath(String.format("//a[text() = 'Child node %s']", childNumber)));
    }

    @FindBy(how = How.XPATH, using = "//a[text() = 'Zmień nazwę']")
    public WebElement changeName;

    @FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/div[1]/div/ul/li/span/input")
    public WebElement newNameInput;
}
