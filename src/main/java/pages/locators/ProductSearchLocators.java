package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductSearchLocators {

    @FindBy(how = How.XPATH, using = "//span[@class = 'select2-selection__placeholder']")
    public WebElement categoryDropDown;

    @FindBy(how = How.XPATH, using = "//li[contains(@class, 'select2-results__option')]")
    public List<WebElement> categories;

    @FindBy(how = How.XPATH, using = "//div[@class = 'caption']/h4")
    public List<WebElement> productsNames;

    @FindBy(how = How.XPATH, using = "//p[contains(text(), 'Cena')]")
    public List<WebElement> productsPrizes;

    @FindBy(how = How.XPATH, using = "//div[@class = 'caption']/p/strong")
    public List<WebElement> productsCategory;

    @FindBy(how = How.CLASS_NAME, using = "select2-search__field")
    public WebElement searchInput;
}
