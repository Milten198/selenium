package pages.executors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import pages.locators.ProductSearchLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;
import util.objects.ProductSearchItem;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchPage extends AbstractPage {

    private ProductSearchLocators locators;
    private List<ProductSearchItem> products;

    public ProductSearchPage() {
        locators = new ProductSearchLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
        products = new ArrayList<>();
    }

    public void expandListOfCategories() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.categoryDropDown);
    }

    public void pickCategory(String category) {
        SeleniumExtender.SeleniumClick.clickWithWait(selectCategory(category));
    }

    public boolean verifyProductListOfSelectedCategory(String category) {
        createProductListOfSelectedCategory();
        for (ProductSearchItem item : products) {
            if (!item.getCategory().equals(category)) {
                return false;
            }
        }
        return true;
    }

    public void typeCharactersToSearchInput(String characters) {
        SeleniumExtender.SeleniumSendKeys.clearBeforeSendKeysWithWait(locators.searchInput, characters);
    }

    public boolean verifySearchedCategory(String category) {
        return locators.categories.get(0).getText().equals(category);
    }

    private void createProductListOfSelectedCategory() {
        for (int i = 0; i < locators.productsNames.size(); i++) {
            products.add(new ProductSearchItem(locators.productsNames.get(i).getText(), locators.productsPrizes.get(i).getText(),
                    locators.productsCategory.get(i).getText()));
        }
    }

    private WebElement selectCategory(String category) {
        for (WebElement element : locators.categories) {
            if (element.getText().equals(category)) {
                return element;
            }
        }
        return null;
    }
}
