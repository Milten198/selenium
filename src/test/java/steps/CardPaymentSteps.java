package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.CardPaymentPage;

public class CardPaymentSteps {

    private CardPaymentPage cardPayment;

    public CardPaymentSteps() {
        cardPayment = new CardPaymentPage();
    }

    @When("^I select a credit card type \"([^\"]*)\"$")
    public void iSelectACreditCardType(String cardType) throws Throwable {
        cardPayment.selectCardType(cardType);
    }

    @And("^I type name \"([^\"]*)\"$")
    public void iTypeName(String name) throws Throwable {
        cardPayment.setName(name);
    }

    @And("^I type credit card number \"([^\"]*)\"$")
    public void iTypeCreditCardNumber(String cardNumber) throws Throwable {
        cardPayment.setCardNumber(cardNumber);
    }

    @And("^I type CVV code \"([^\"]*)\"$")
    public void iTypeCVVCode(String cvv) throws Throwable {
        cardPayment.setCVVCode(cvv);
    }

    @And("^I select expiration date \"([^\"]*)\", \"([^\"]*)\"$")
    public void iSelectExpirationDate(String month, String year) throws Throwable {
        cardPayment.selectExpirationMonth(month);
        cardPayment.selectExpirationYear(year);
    }

    @And("^I click pay button$")
    public void iClickPayButton() throws Throwable {
        cardPayment.clickPayButton();
    }

    @Then("^Payment confirmation is displayed$")
    public void paymentConfirmationIsDisplayed() throws Throwable {
        Assert.assertTrue(cardPayment.confirmPayment());
    }

    @Then("^Payment expiration date message \"([^\"]*)\" is displayed$")
    public void paymentExpirationDateMessageIsDisplayed(String message) throws Throwable {
        Assert.assertTrue(cardPayment.confirmExpirationMessage(message));
    }
}
