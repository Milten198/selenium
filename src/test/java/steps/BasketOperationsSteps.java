package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.BasketOperationsPage;

public class BasketOperationsSteps {

    private BasketOperationsPage basketOperations;

    public BasketOperationsSteps() {
        basketOperations = new BasketOperationsPage();
    }

    @When("^I add product \"([^\"]*)\" with quantity of \"([^\"]*)\"$")
    public void iAddProductWithQuantityOf(String productName, String productQuantity) throws Throwable {
        basketOperations.addProductToBasket(productName, productQuantity);
    }

    @Then("^I can see total quantity of products in basket$")
    public void iCanSeeTotalQuantityOfProducts() throws Throwable {
        Assert.assertTrue(basketOperations.verifyTotalQuantity());
    }

    @And("^I can see total price to pay in basket$")
    public void iCanSeeTotalPrizeToPay() throws Throwable {
        Assert.assertTrue(basketOperations.verifyTotalPrice());
    }

    @Then("^I can see \"([^\"]*)\" products in basket$")
    public void iCanSeeProductsInBasket(String productsNumber) throws Throwable {
        Assert.assertTrue(basketOperations.verifyNumberOfProductsInBasket(productsNumber));
    }

    @And("^I remove product \"([^\"]*)\" from basket$")
    public void iRemoveProductFromBasket(String productName) throws Throwable {
        basketOperations.removeProductFromBasket(productName);
    }
}
