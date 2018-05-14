package pages.executors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.locators.DragAndDropLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

public class DragAndDropPage {

    private DragAndDropLocators locators;

    public DragAndDropPage() {
        locators = new DragAndDropLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void dragProductLogo(String logoNumber) {
        WebElement drag = SeleniumExtender.SeleniumFind.findElement(locators.logo(logoNumber));
        SeleniumExtender.SeleniumDragAndDrop.dragAndDropElementWithoutWait(drag, locators.dropPlace);
    }

    public void setProductQuantity(String productNumber, String productQuantity) {
        WebElement product = SeleniumExtender.SeleniumFind.findElement(locators.product(productNumber));
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeys(product, productQuantity);
    }

    public String getTotalQuantityOfProducts() {
        return locators.finalQuantityOfProducts.getText();
    }

    public String getTotalPriceToPay() {
        return locators.summaryPrice.getText();
    }
}
