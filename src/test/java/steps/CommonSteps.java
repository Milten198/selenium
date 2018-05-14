package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.executors.HomePage;
import util.enums.PagesType;
import util.helpers.SeleniumExtender;

public class CommonSteps {

    private HomePage homePage;

    public CommonSteps() {
        this.homePage = new HomePage();
    }

    @Given("^I'm on page with task number \"([^\"]*)\"$")
    public void imOnPageWithTaskNumber(String taskNumber) throws Throwable {
        homePage.openLoginPage(PagesType.Asta);
        homePage.openTaskWithNumber(taskNumber);
    }

    @Then("^I can see alert with message \"([^\"]*)\"$")
    public void iCanSeeAlertWithMessage(String alertMessage) throws Throwable {
        Assert.assertEquals("Wrong alert or no alert has been displayed", alertMessage, SeleniumExtender.SeleniumAlertHandle.getAlertText());
    }
}
