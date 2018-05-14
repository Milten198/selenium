package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.InvalidElementStateException;
import pages.executors.EditableFormPage;

public class EditableFormSteps {

    private EditableFormPage editableForm;

    public EditableFormSteps() {
        editableForm = new EditableFormPage();
    }

    @When("^I enable editable mode$")
    public void iUnlockEditableMode() throws Throwable {
        editableForm.enableEditionMode();
    }

    @And("^I fill all the fields with my data$")
    public void iFillAllTheFieldsWithMyData() throws Throwable {
        editableForm.setName("Johnny");
        editableForm.setSurname("Cash");
        editableForm.setNote("I walk the line");
        editableForm.setPhoneNumber("800100200");
    }

    @And("^I upload my photo$")
    public void iUploadMyPhoto() throws Throwable, Exception {
        editableForm.uploadPhoto("bob.jpg");
    }

    @And("^I click save button$")
    public void iClickSaveButton() throws Throwable {
        editableForm.clickSaveButton();
    }

    @Then("^Confirmation message \"([^\"]*)\" appears$")
    public void confirmationMessageAppears(String message) throws Throwable {
        Assert.assertEquals("Wrong message or no message at all", message, editableForm.getConfirmationMessage());
    }

    @And("^All data are correct$")
    public void allDataAreCorrect() throws Throwable {
        Assert.assertEquals("Wrong name", "Johnny", editableForm.getName());
        Assert.assertEquals("Wrong surname", "Cash", editableForm.getSurname());
        Assert.assertEquals("Wrong note", "I walk the line", editableForm.getNote());
        Assert.assertEquals("Wrong phone number", "800100200", editableForm.getPhoneNumber());
        Assert.assertTrue("Wrong img src", editableForm.getPhoto().contains("data:image/jpeg;base64"));
    }

    @When("^I try to modify data in form with no editable mode$")
    public void iTryToModifyDataInFormWithNoEditableMode() throws Throwable {
        try {
            editableForm.setName("Johnny");
        } catch (InvalidElementStateException e) {
            Assert.assertTrue(e.getMessage().contains("Element is not currently interactable and may not be manipulated"));
        }

    }

    @Then("^Edition is not possible$")
    public void editionIsNotPossible() throws Throwable {
        Assert.assertEquals("Name has been changed","Salma", editableForm.getName());
    }
}
