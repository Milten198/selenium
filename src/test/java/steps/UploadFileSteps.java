package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.UploadFilePage;

public class UploadFileSteps {

    private UploadFilePage uploadFile;

    public UploadFileSteps() {
        this.uploadFile = new UploadFilePage();
    }

    @When("^I upload file \"([^\"]*)\"$")
    public void iUploadFile(String fileName) throws Throwable {
        uploadFile.uploadFile(fileName);
    }

    @Then("^Data from file are shown in table$")
    public void dataFromFileAreShownInTable() throws Throwable {
        Assert.assertTrue("Data from file are not equal to data from table", uploadFile.compareTwoLists());
    }
}
