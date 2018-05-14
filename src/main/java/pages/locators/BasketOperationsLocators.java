package pages.locators;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.SeleniumExecutor;

import java.util.List;

public class BasketOperationsLocators {

    @FindBy(how = How.CSS, using = ".col-md-9.text-on-button-level")
    public List<WebElement> listOfProductInBasket;

    @FindBy(how = How.XPATH, using = "//div/span[@class = 'row-in-basket-quantity']")
    public List<WebElement> listOfQuantitiesInBasket;

    @FindBy(how = How.CSS, using = ".summary-quantity")
    public WebElement totalQuantity;

    @FindBy(how = How.CSS, using = ".summary-price")
    public WebElement totalPrice;

    public WebElement removeProductFromBasket(String productName) {
        return SeleniumExecutor.getDriver()
                .findElement(By.xpath(String.format("//button[@data-product-name = '%s'][contains(text(), 'Usuń')]", productName)));
    }

    public WebElement productInput(String productName) {
        return SeleniumExecutor.getDriver()
                .findElement(By.xpath(String.format("//div[h4='%s']/div/input", productName)));
    }

    public WebElement productAddButton(String productName) {
        return SeleniumExecutor.getDriver()
                .findElement(By.xpath(String.format("//div[h4='%s']/div/span/button", productName)));
    }
}
