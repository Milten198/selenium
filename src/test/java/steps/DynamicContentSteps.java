package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.DynamicContentPage;

public class DynamicContentSteps {

    private DynamicContentPage dynamicContent;

    public DynamicContentSteps() {
        this.dynamicContent = new DynamicContentPage();
    }

    @When("^I scroll the page to the bottom$")
    public void iScrollThePageToTheBottom() throws Throwable {
        dynamicContent.scrollToTheBottom();
    }

    @Then("^I can see footer$")
    public void iCanSeeFooter() throws Throwable {
        Assert.assertTrue("Footer is not visible", dynamicContent.isFooterVisible());
    }

    @Then("^Loaded fragments are equal to \"([^\"]*)\"$")
    public void loadedFragmentsAreEqualTo(String numberOfLoadedFragments) throws Throwable {
        Assert.assertEquals("Wrong number of loaded fragments", numberOfLoadedFragments, dynamicContent.numberOfLoadedFragments());
    }
}
