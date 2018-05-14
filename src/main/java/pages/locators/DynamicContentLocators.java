package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class DynamicContentLocators {

    @FindBy(how = How.XPATH, using = "//div[@class='jscroll-added']")
    public List<WebElement> loadedFragments;

    @FindBy(how = How.XPATH, using = "//h3[text() = 'Koniec']")
    public WebElement footer;

    @FindBy(how = How.XPATH, using = "//h3[text() = 'Lorem ipsum']")
    public WebElement header;

}
