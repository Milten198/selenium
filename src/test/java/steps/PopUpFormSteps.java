package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.CompletionFormPage;
import pages.executors.PopUpWindow;

public class PopUpFormSteps {

    private CompletionFormPage completionForm;
    private PopUpWindow popUpWindow;

    public PopUpFormSteps() {
        this.completionForm = new CompletionFormPage();
        this.popUpWindow = new PopUpWindow();
    }

    @When("^I click on Apply button$")
    public void iClickOnApplyButton() throws Throwable {
        completionForm.clickOnApplyButton();
    }

    @And("^Form in window is open$")
    public void formInWindowIsOpen() throws Throwable {
        completionForm.switchToPopUpWindow();
        popUpWindow.switchToFrame();
        Assert.assertTrue(popUpWindow.checkPopUpFormIsDisplayed());
    }

    @And("^I fill the Name field with \"([^\"]*)\"$")
    public void iFillTheNameFieldWith(String name) throws Throwable {
        popUpWindow.setName(name);
    }

    @And("^I fill the Email field with \"([^\"]*)\"$")
    public void iFillTheEmailFieldWith(String email) throws Throwable {
        popUpWindow.setEmail(email);
    }

    @And("^I fill the Phone number field with \"([^\"]*)\"$")
    public void iFillThePhoneNumberFieldWith(String phoneNumber) throws Throwable {
        popUpWindow.setPhoneNumber(phoneNumber);
    }

    @And("^I submit Save button$")
    public void iSubmitSaveButton() throws Throwable {
        popUpWindow.clickOnSaveButton();
    }

    @Then("^Confirmation message is displayed$")
    public void confirmationMessageIsDisplayed() throws Throwable {
        Assert.assertTrue("Confirmation message is wrong", popUpWindow.checkConfirmationMessage());
    }

    @Then("^I can see error message for email$")
    public void iCanSeeErrorMessageForEmail() throws Throwable {
        Assert.assertTrue("Wrong error message is displayed", popUpWindow.checkEmailErrorMessage());
    }

    @And("^I can see error message for phone number$")
    public void iCanSeeErrorMessageForPhoneNumber() throws Throwable {
        Assert.assertTrue("Wrong error message is displayed", popUpWindow.checkPhoneNumberErrorMessage());
    }
}
