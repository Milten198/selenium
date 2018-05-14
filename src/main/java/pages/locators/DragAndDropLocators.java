package pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class DragAndDropLocators {

    @FindBy(how = How.CSS, using = ".col-md-12.place-to-drop.ui-droppable")
    public WebElement dropPlace;

    @FindBy(how = How.CSS, using = ".summary-quantity")
    public WebElement finalQuantityOfProducts;

    @FindBy(how = How.CSS, using = ".summary-price")
    public WebElement summaryPrice;

    public By product(String productName) {
        return By.xpath(String.format("//div[2][h4='%s']/div/input", productName));
    }

    public By logo(String logoNumber) {
        return By.xpath(String.format("//div[img][following-sibling::div/h4[text() = '%s']]", logoNumber));
    }

}
