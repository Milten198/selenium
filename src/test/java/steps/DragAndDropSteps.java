package steps;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.DragAndDropPage;

public class DragAndDropSteps {

    private DragAndDropPage dragAndDrop;

    public DragAndDropSteps() {
        dragAndDrop = new DragAndDropPage();
    }

    @When("^I set quantity \"([^\"]*)\" of product \"([^\"]*)\"$")
    public void iSetQuantityOfProduct(String productQuantity, String productNumber) throws Throwable {
        dragAndDrop.setProductQuantity(productNumber, productQuantity);
    }

    @And("^I drag and drop a \"([^\"]*)\" into the basket$")
    public void iDragAndDropAIntoTheBasket(String productNumber) throws Throwable {
        dragAndDrop.dragProductLogo(productNumber);
    }

    @Then("^I can see total \"([^\"]*)\" of product in basket$")
    public void iCanSeeTotalOfProductInBasket(String totalQuantity) throws Throwable {
        Assert.assertEquals("Wrong quantity", totalQuantity, dragAndDrop.getTotalQuantityOfProducts());
    }


    @And("^I can see total \"([^\"]*)\" to pay in basket$")
    public void iCanSeeTotalToPay(String summaryPrice) throws Throwable {
        Assert.assertEquals("Wrong qprice to pay", summaryPrice, dragAndDrop.getTotalPriceToPay());
    }
}
