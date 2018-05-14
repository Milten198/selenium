package pages.executors;


import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import pages.locators.BasketOperationsLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BasketOperationsPage extends AbstractPage {

    private BasketOperationsLocators locators;

    public BasketOperationsPage() {
        locators = new BasketOperationsLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void addProductToBasket(String productName, String productQuantity) {
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeysWithWait(locators.productInput(productName), productQuantity);
        SeleniumExtender.SeleniumClick.clickWithWait(locators.productAddButton(productName));
    }

    public void removeProductFromBasket(String productName) {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.removeProductFromBasket(productName));
    }

    public boolean verifyTotalQuantity() {
        return SeleniumExtender.SeleniumGetText.getTextWithWait(locators.totalQuantity).equals(totalQuantity());
    }

    public boolean verifyTotalPrice() {
        return SeleniumExtender.SeleniumGetText.getTextWithWait(locators.totalPrice).equals(totalPrice());
    }

    public boolean verifyNumberOfProductsInBasket(String numberOfProducts) {
        return String.valueOf(locators.listOfProductInBasket.size()).equals(numberOfProducts);
    }

    private String totalQuantity() {
        int totalQuantity = 0;
        for (int i = 0; i < locators.listOfQuantitiesInBasket.size(); i++) {
            totalQuantity += Integer.valueOf(locators.listOfQuantitiesInBasket.get(i).getText());
        }
        return String.valueOf(totalQuantity);
    }

    private String totalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < locators.listOfProductInBasket.size(); i++) {
            String productData = locators.listOfProductInBasket.get(i).getText();
            String productPrice = productData.substring(productData.indexOf('(') + 1, productData.indexOf(')'));
            totalPrice += Double.parseDouble(productPrice) * Integer.parseInt(locators.listOfQuantitiesInBasket.get(i).getText());
        }
        BigDecimal bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_EVEN);
        return String.valueOf(bd) + " zł";
    }
}
